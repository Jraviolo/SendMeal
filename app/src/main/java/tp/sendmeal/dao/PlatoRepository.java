package tp.sendmeal.dao;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tp.sendmeal.dao.rest.PlatoRest;
import tp.sendmeal.domain.Plato;

public class PlatoRepository {
    public static String _SERVER = "http://10.0.2.2:5000";

    //public static String _SERVER = "http://192.168.0.1:5000";

    //public static String _SERVER = "http:/192.168.1.102:5000";
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
                .baseUrl(_SERVER)
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
        Log.d("APP SendMeal", "ENTRO A LISTARPLATOS");
        Call<List<Plato>> llamada = this.platoRest.buscarTodos();
        Log.d("APP SendMeal", "DESPUES DE BUSCARTODOS");
        llamada.enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                if(response.isSuccessful()){
                    listaPlatos.clear();
                    listaPlatos.addAll(response.body());
                    Log.d("APP SendMeal", "DESPUES DE ADDALL");
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


    public void borrarPlato(final Plato plato, final Handler h){
        Call<Void> llamada = this.platoRest.borrarPlato(plato.getIdPlato());
        llamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("APP SendMeal", "Despues que ejecuta"+ response.isSuccessful());
                Log.d("APP SendMeal", "Codigo"+ response.code());

                if(response.isSuccessful()){
                    Log.d("APP SendMeal", "EJECUTO");

                    for(Plato plato : listaPlatos){
                        Log.d("APP SendMeal", "Plato"+ plato.getIdPlato());
                    }
                    Log.d("APP SendMeal", "Borra Plato: " + plato.getIdPlato() + plato.getTitulo());
                    listaPlatos.remove(plato);

                    for(Plato plato : listaPlatos){
                        Log.d("APP SendMeal", "Plato"+ plato.getIdPlato());
                    }

                    Message m = new Message();
                    m.arg1 = _BORRADO_PLATO;
                    h.sendMessage(m);

                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("APP SendMeal", "ERROR "+t.getMessage());
                Message m = new Message();
                m.arg1 = _ERROR_PLATO;
                h.sendMessage(m);

            }
        });
    }

    public void actualizarPlato(final Plato plato, final Handler h){

        Call<Plato> llamada = this.platoRest.actualizar(plato.getIdPlato(),plato);
        llamada.enqueue(new Callback<Plato>() {
            @Override
            public void onResponse(Call<Plato> call, Response<Plato> response) {
                Log.d("APP SendMeal", "Despues que ejecuta"+ response.isSuccessful());
                Log.d("APP SendMeal", "Codigo"+ response.code());

                if(response.isSuccessful()){
                    Log.d("APP SendMeal", "EJECUTO");
                    listaPlatos.remove(plato);
                    listaPlatos.add(response.body());
                    Message m = new Message();
                    m.arg1 = _UPDATE_PLATO;
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

    public List<Plato> buscarPlato(final String nombrePlato, final Handler h){
        Log.d("APP SendMeal", "ENTRO A BuscarPLATO");
        final List<Plato> listaResultado = new List<Plato>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Plato> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] ts) {
                return null;
            }

            @Override
            public boolean add(Plato plato) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends Plato> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, @NonNull Collection<? extends Plato> collection) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Plato get(int i) {
                return null;
            }

            @Override
            public Plato set(int i, Plato plato) {
                return null;
            }

            @Override
            public void add(int i, Plato plato) {

            }

            @Override
            public Plato remove(int i) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<Plato> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Plato> listIterator(int i) {
                return null;
            }

            @NonNull
            @Override
            public List<Plato> subList(int i, int i1) {
                return null;
            }
        };
        Call<List<Plato>> llamada = this.platoRest.buscarPlato(nombrePlato);
        //Call<List<Plato>> llamada = this.platoRest.buscarPlato(2);
        Log.d("APP SendMeal", "despues de BuscarPLATO");
        llamada.enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                if(response.isSuccessful()){
                    listaResultado.clear();
                    listaResultado.addAll(response.body());

                    Log.d("APP SendMeal", "DESPUES DE ADDALL - size: "+ listaResultado.size());
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

        return listaResultado;
    }

    public List<Plato> getListaPlatos(){

        return listaPlatos;
    }
}
