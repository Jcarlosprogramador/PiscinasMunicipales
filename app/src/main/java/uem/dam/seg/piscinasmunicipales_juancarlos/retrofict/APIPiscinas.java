package uem.dam.seg.piscinasmunicipales_juancarlos.retrofict;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import uem.dam.seg.piscinasmunicipales_juancarlos.model.Contexto;
import uem.dam.seg.piscinasmunicipales_juancarlos.model.Piscinas;

public interface APIPiscinas {

   /* https://datos.madrid.es/egob/catalogo/210227-0-piscinas-publicas.json?distrito_nombre=NOMBRE_DISTRITO*/
    /*public static final String BASE_URL = "http://10.0.2.2:3000/";*/

    public static final String BASE_URL = "https://datos.madrid.es/";

// recuperamos las piscinas de un distrito
    @GET("egob/catalogo/210227-0-piscinas-publicas.json")
    Call<Piscinas> obtenerPiscinas(@Query("distrito_nombre") String distrito);

// accedemos el valor de cada piscina
    @GET("egob/catalogo/tipo/entidadesyorganismos/{id}")
    Call<Contexto> obtenerPiscina(@Path("id") String id);





}
