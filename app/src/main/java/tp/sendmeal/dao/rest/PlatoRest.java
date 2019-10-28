package tp.sendmeal.dao.rest;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import tp.sendmeal.domain.Plato;

public interface PlatoRest {
    @GET("plato/{nombre}")
    Call<List<Plato>> buscarPlato(@Path("nombre") String nombre);

    @DELETE ("plato/{id}")
    Call<Void> borrar(@Path("id") Integer id);

    @PUT("plato/{id}")
    Call<Plato> actualizar(@Path("id") Integer id, @Body Plato plato);

    @POST("plato/")
    Call<Plato> crear(@Body Plato plato);
}
