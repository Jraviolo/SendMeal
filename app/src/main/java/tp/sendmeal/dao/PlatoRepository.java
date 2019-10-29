package tp.sendmeal.dao;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tp.sendmeal.dao.rest.PlatoRest;
import tp.sendmeal.domain.Plato;

public class PlatoRepository {
    public static String _SERVER = "http://10.0.2.2:5000";
    private List<Plato> listaPlatos;

    public static final int _ALTA_PLATO = 1;
    public static final int _UPDATE_PLATO = 2;
    public static final int _BORRADO_PLATO = 3;
    public static final int _CONSULTA_PLATO = 4;
    public static final int _ERROR_PLATO = 9;

    private static PlatoRepository _INSTANCE;

    private PlatoRepository(){}

    public static PlatoRepository getInstance(){
        if(_INSTANCE==null){
            _INSTANCE = new PlatoRepository();
            _INSTANCE.configurarRetrofit();
            _INSTANCE.listaPlatos = new ArrayList<>();
        }
        return _INSTANCE;
    }


    private Retrofit rf;
    private PlatoRest platoRest;

    private void configurarRetrofit(){
        this.rf = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d("APP_2","INSTANCIA CREADA");

        this.platoRest = this.rf.create(PlatoRest.class);
    }


    public void crearPlato(Plato plato, final Handler h){
        Call<Plato> llamada = this.platoRest.crear(plato);
        llamada.enqueue(new Callback<Plato>() {
            @Override
            public void onResponse(Call<Plato> call, Response<Plato> response) {
                Log.d("APP SendMeal", "Despues que ejecuta"+ response.isSuccessful());
                Log.d("APP SendMeal", "Codigo"+ response.code());

                if(response.isSuccessful()){
                    Log.d("APP SendMeal", "EJECUTO");
                    listaPlatos.add(response.body());
                    Message m = new Message();
                    m.arg1 = _ALTA_PLATO;
                    h.sendMessage(m);

                }
            }

            @Override
            public void onFailure(Call<Plato> call, Throwable t) {
                Log.d("APP SendMeal", "ERROR "+t.getMessage());
                Message m = new Message();
                m.arg1 = _ERROR_PLATO;
                h.sendMessage(m);

            }
        });
    }

    public void listarPlatos(final Handler h){
        Call<List<Plato>> llamada = this.platoRest.buscarTodos();
        llamada.enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                if(response.isSuccessful()){
                    listaPlatos.clear();
                    listaPlatos.addAll(response.body());
                    Message m = new Message();
                    m.arg1 = _CONSULTA_PLATO;
                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {
                Message m = new Message();
                m.arg1 = _ERROR_PLATO;
                h.sendMessage(m);
            }
        });
    }


    public List<Plato> getListaPlatos(){
        return listaPlatos;
    }
}
