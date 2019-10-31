package tp.sendmeal.dao.rest;

import android.content.Intent;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import retrofit2.http.Query;
import tp.sendmeal.domain.Plato;

public interface PlatoRest {
  //  @GET("platos/")
   // Call<List<Plato>> buscarPlato(@Query("titulo") String titulo);

    @GET("platos/{titulo}")
     Call<List<Plato>> buscarPlato(@Path("titulo") String titulo);

    @GET("platos/")
    Call<List<Plato>> buscarTodos();

    @DELETE ("platos/{idPlato}")
    Call<Void> borrarPlato(@Path("idPlato") Integer idPlato);

    @PUT("platos/{nombre}")
    Call<Plato> actualizar(@Path("idPlato") Integer idPlato, @Body Plato plato);

    @POST("platos/")
    Call<Plato> crear(@Body Plato plato);
}
