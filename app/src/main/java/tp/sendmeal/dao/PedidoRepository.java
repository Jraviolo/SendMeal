package tp.sendmeal.dao;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tp.sendmeal.dao.rest.PedidoRest;
import tp.sendmeal.dao.rest.PlatoRest;
import tp.sendmeal.domain.ItemsPedido;
import tp.sendmeal.domain.Pedido;
import tp.sendmeal.domain.Plato;

public class PedidoRepository {

    private static PedidoRepository PR = null;

    private PedidoDB pedidoDB;

    //private ItemPedidoDao itemPedidoDao;
    private PedidoDao pedidoDao;

    private PedidoRepository(Context ctx){
        pedidoDB = Room.databaseBuilder(ctx,
                PedidoDB.class, "pedido-db").allowMainThreadQueries().build();

        pedidoDao=pedidoDB.pedidoDao();
    }

    public synchronized static PedidoRepository getInstance(Context ctx){
        if(PR ==null){
            PR = new PedidoRepository(ctx);
        }
        return PR;
    }

    public PedidoDB getPedidoDB() {
        return pedidoDB;
    }

    public void insertPedido(Pedido pedido){
        pedidoDao.insertPedidoAndItems(pedido);
    }
    public  void updatePedido(Pedido pedido){
        pedidoDao.updatePedido(pedido);
    }

    public void deletePedido(Pedido pedido){
        pedidoDao.deletePedido(pedido);
    }

    public Pedido selectPedidoById(int id){
        return pedidoDao.selectPedidoById(id);
    }

    /*
    public void insertItemPedido(ItemsPedido itemPedido){
        itemPedidoDao.insertItemPedido(itemPedido);
    }
    public  void updatePedido(ItemsPedido itemPedido){
        itemPedidoDao.updatePedido(itemPedido);
    }

    public void deletePedido(ItemsPedido itemPedido){
        itemPedidoDao.deletePedido(itemPedido);
    }

    public ItemsPedido selectItemPedidoById(int id){
        return itemPedidoDao.selectItemPedidoById(id);
    }

    public List<ItemsPedido> selectItemsPedidoByIdPlato(int id){
        return itemPedidoDao.selectItemsPedidoByIdPlato(id);
    }*/


    // PERSISTENCIA REST


    public static String _SERVER = "http://10.0.2.2:5000";

    //public static String _SERVER = "http://10.15.152.118:5000";

    //public static String _SERVER = "http:/192.168.1.102:5000";
    private List<Pedido> listaPedidos;

    public static final int _ALTA_PEDIDO = 1;
    public static final int _UPDATE_PEDIDO = 2;
    public static final int _BORRADO_PEDIDO = 3;
    public static final int _CONSULTA_PEDIDO = 4;
    public static final int _ERROR_PEDIDO = 9;

    private static PedidoRepository _INSTANCE;

    private PedidoRepository(){}

    public static PedidoRepository getInstance(){
        if(_INSTANCE==null){
            _INSTANCE = new PedidoRepository();
            _INSTANCE.configurarRetrofit();
            _INSTANCE.listaPedidos = new ArrayList<>();
        }
        return _INSTANCE;
    }


    private Retrofit rf;
    private PedidoRest pedidoRest;

    private void configurarRetrofit(){
        this.rf = new Retrofit.Builder()
                .baseUrl(_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d("APP_2","INSTANCIA CREADA");

        this.pedidoRest = this.rf.create(PedidoRest.class);
    }


    public void crearPedido(Pedido pedido, final Handler h){
        Call<Pedido> llamada = this.pedidoRest.crear(pedido);
        llamada.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                Log.d("APP SendMeal", "Despues que ejecuta"+ response.isSuccessful());
                Log.d("APP SendMeal", "Codigo"+ response.code());

                if(response.isSuccessful()){
                    Log.d("APP SendMeal", "EJECUTO");
                    listaPedidos.add(response.body());
                    Message m = new Message();
                    m.arg1 = _ALTA_PEDIDO;
                    h.sendMessage(m);

                }
            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                Log.d("APP SendMeal", "ERROR "+t.getMessage());
                Message m = new Message();
                m.arg1 = _ERROR_PEDIDO;
                h.sendMessage(m);

            }
        });
    }
}