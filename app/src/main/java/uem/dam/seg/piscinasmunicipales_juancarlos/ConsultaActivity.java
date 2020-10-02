package uem.dam.seg.piscinasmunicipales_juancarlos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uem.dam.seg.piscinasmunicipales_juancarlos.model.Graph;
import uem.dam.seg.piscinasmunicipales_juancarlos.model.Piscinas;
import uem.dam.seg.piscinasmunicipales_juancarlos.retrofict.APIPiscinas;
import uem.dam.seg.piscinasmunicipales_juancarlos.retrofict.PiscinasAdapter;
import uem.dam.seg.piscinasmunicipales_juancarlos.retrofict.RetrofitClient;

public class ConsultaActivity extends AppCompatActivity {
// referenciamos los atributos necesarios para el recycler
    String municipio;

    RecyclerView rvPiscinas;
    PiscinasAdapter piscinasAdapter;
    LinearLayoutManager layoutManager;
// la nececitamos para pasar de un intent al siguiente con el valor tituo de la clase de las piscinas del municipio
    static final String CLAVE_TITULO ="TITULO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        // recuperamos el valor del municipio para pasar el siguiente y mostrar las piscinas del siguiente municipio
        municipio = getIntent().getStringExtra(MainActivity.CLAVE_MUNICIPIO);

        // consulta
        Retrofit retrofit = RetrofitClient.getClient(APIPiscinas.BASE_URL);
        APIPiscinas apiPiscinas = retrofit.create(APIPiscinas.class);
        Call<Piscinas> call = apiPiscinas.obtenerPiscinas(municipio);


        call.enqueue(new Callback<Piscinas>() {
            @Override
            public void onResponse(Call<Piscinas> call, Response<Piscinas> response) {
                // si la consulta es correcta recuperamos en una lista todas las piscinas
                if (response.isSuccessful()) {
                    Piscinas p = response.body();
                    List<Graph> listaGraph = p.getGraph();
                    configurarRecyclerView(listaGraph);



                } else {
                    Log.e("ERROR ON RESPONSE", "ERROR: " + response.code());
                }
            }

            @Override
            //consulta es erronea saltará el error
            public void onFailure(Call<Piscinas> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                Log.e("ERROR ON FAILURE", "ERROR: " + t.getMessage());
            }
        });
    }

    private void configurarRecyclerView(final List<Graph> listaGraph) {
        rvPiscinas = findViewById(R.id.recyPiscina);

        piscinasAdapter = new PiscinasAdapter(listaGraph);
        piscinasAdapter.setListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                // al pulsar sobre un elemento de recycler accederemos al siguiente activity
                // pasando al titulo de la piscina, para poder hacer la consulta
                Intent i = new Intent(ConsultaActivity.this, ScrollingPiscinaActivity.class);
                //recuperamos la url del jSon
                String id = listaGraph.get(rvPiscinas.getChildAdapterPosition(v)).getAid();
                String parteFinal = id.substring(id.lastIndexOf("/") + 1);
                // lo que hacemos con el parteFinal es partir la cadena a partir del ultimo caracter de la barra"/" y
                //nos quedamos con la parte de la url que especifica una piscina en concreto
                i.putExtra(CLAVE_TITULO, parteFinal);
                startActivity(i);
            }
        });
        layoutManager = new LinearLayoutManager(this);

        rvPiscinas.setLayoutManager(layoutManager);
        rvPiscinas.setHasFixedSize(true);
        rvPiscinas.setAdapter(piscinasAdapter);
    }
}
