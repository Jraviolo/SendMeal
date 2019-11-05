package tp.sendmeal.dao.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import tp.sendmeal.domain.Pedido;

public interface PedidoRest {

    @GET("pedidos/")
    Call<List<Pedido>> buscarTodos();

    @DELETE("pedidos/{id}")
    Call<Void> borrarPedidoo(@Path("id") Integer idPedido);

    @PUT("pedidos/{id}")
    Call<Pedido> actualizar(@Path("id") Integer id, @Body Pedido pedido);

    @POST("pedidos/")
    Call<Pedido> crear(@Body Pedido pedido);
}
