package uem.dam.seg.piscinasmunicipales_juancarlos;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uem.dam.seg.piscinasmunicipales_juancarlos.model.Contexto;
import uem.dam.seg.piscinasmunicipales_juancarlos.model.Graph;
import uem.dam.seg.piscinasmunicipales_juancarlos.retrofict.APIPiscinas;
import uem.dam.seg.piscinasmunicipales_juancarlos.retrofict.RetrofitClient;

public class ScrollingPiscinaActivity extends AppCompatActivity {

    TextView tvTexto;
    String aid;

    TextView texto;
    TextView tvCoPostal;
    TextView tvLoca;
    TextView tvDir;
    TextView tvAcc;
    TextView tvHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_piscina);

        // recuperamos la parte final de la url para hacer la consulta.
        aid = getIntent().getStringExtra(ConsultaActivity.CLAVE_TITULO);
        tvTexto = findViewById(R.id.tvTexto);


        Retrofit retrofit = RetrofitClient.getClient(APIPiscinas.BASE_URL);
        APIPiscinas apiPiscinas = retrofit.create(APIPiscinas.class);
        Call<Contexto> call = apiPiscinas.obtenerPiscina(aid);

        // referenciamos los atributos que queremos mostrar en el activity
        texto = findViewById(R.id.tvTexto);
        tvCoPostal = findViewById(R.id.tvCoPostal);
        tvLoca = findViewById(R.id.tvLocalidad);
        tvDir = findViewById(R.id.tvDireccion);
        tvAcc = findViewById(R.id.tvAcceso);
        tvHora = findViewById(R.id.tvHorario);

        call.enqueue(new Callback<Contexto>() {
            @Override
            public void onResponse(Call<Contexto> call, Response<Contexto> response) {
                //recuperamos en la consulta un objeto de tipo graph que va contener toda la
                //información de la piscina en concreto.
                if (response.isSuccessful()) {
                    Contexto c = response.body();
                    List<Graph> g = c.getGraph();
                    String titulo = g.get(0).getTitle();
                    // getAddress getOrganization .son objetos de tipo graph para acceder a ella
                    String localidad = g.get(0).getAddress().getLocality();
                    String codPostal = g.get(0).getAddress().getPostalCode();
                    String direccion = g.get(0).getAddress().getStreetAddress();
                    String acceso = g.get(0).getOrganization().getOrganizationDesc();
                    String horario = g.get(0).getOrganization().getSchedule();
                    tvTexto.setText(titulo);
                    tvLoca.setText(localidad);
                    tvCoPostal.setText(codPostal);
                    tvDir.setText(direccion);
                    tvAcc.setText(acceso);
                    tvHora.setText(horario);


                } else {
                    Log.e("ERROR ON RESPONSE", "ERROR: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Contexto> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                Log.e("ERROR ON FAILURE", "ERROR: " + t.getMessage());
            }
        });


    }
}
