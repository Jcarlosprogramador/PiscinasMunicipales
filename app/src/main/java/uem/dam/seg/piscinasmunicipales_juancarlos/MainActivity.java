package uem.dam.seg.piscinasmunicipales_juancarlos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etMunicipio;
    static final String CLAVE_MUNICIPIO = "MUNICIPIO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMunicipio = findViewById(R.id.etMunicios);
    }

    public void Consulta(View view) {

        if(etMunicipio.getText().toString().isEmpty()){
            Toast.makeText(this, R.string.toas_campo_vacio, Toast.LENGTH_SHORT).show();
        }else{
            String muni = etMunicipio.getText().toString().trim().toUpperCase();
            if (!muni.equalsIgnoreCase("Arganzuela") && !muni.equalsIgnoreCase("Barajas") &&
                    !muni.equalsIgnoreCase("Carabanchel") && !muni.equalsIgnoreCase("Centro") &&
                    !muni.equalsIgnoreCase("Chamartin") && !muni.equalsIgnoreCase("Chamberi") &&
                    !muni.equalsIgnoreCase("Ciudad Lineal") && !muni.equalsIgnoreCase("Fuencarral-El Pardo") &&
                    !muni.equalsIgnoreCase("Hortaleza") && !muni.equalsIgnoreCase("Latina") &&
                    !muni.equalsIgnoreCase("Moncloa-Aravaca") && !muni.equalsIgnoreCase("Moratalaz") &&
                    !muni.equalsIgnoreCase("Puente de Vallecas") && !muni.equalsIgnoreCase("Retiro") &&
                    !muni.equalsIgnoreCase("Salamanca") && !muni.equalsIgnoreCase("San Blas-Canillejas") &&
                    !muni.equalsIgnoreCase("Tetuan") && !muni.equalsIgnoreCase("Usera") &&
                    !muni.equalsIgnoreCase("Vicalvaro") && !muni.equalsIgnoreCase("Villa de Vallecas") &&
                    !muni.equalsIgnoreCase("Villaverde")) {
                Toast.makeText(this, R.string.toas_erroneo, Toast.LENGTH_SHORT).show();
            }else{

                Intent i = new Intent(MainActivity.this,ConsultaActivity.class);
                i.putExtra(CLAVE_MUNICIPIO,  muni);
                startActivity(i);
            }

        }

    }
}
