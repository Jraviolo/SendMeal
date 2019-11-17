package tp.sendmeal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import tp.sendmeal.dao.PlatoRepository;
import tp.sendmeal.domain.Plato;

public class BuscarPlato extends AppCompatActivity implements View.OnClickListener{

    private String nombrePlato;
    private EditText nombre;
    private Plato plato;
    private RecyclerView mRecyclerView;
    //private RecyclerView.Adapter mAdapter;
    private PlatoRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static List<Plato> listaP;
            /*= new List<Plato>() {
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
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_plato);
        Button btnBuscar = (Button) findViewById(R.id.btnBuscar);
        nombre = findViewById(R.id.editBuscar);
        btnBuscar.setOnClickListener(this);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbarBuscarPlato));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Buscar Plato");
       // listaP = PlatoRepository.getInstance().getListaPlatos();
       // PlatoRepository.getInstance().listarPlatos(miHandler);



/*
        String indice="";
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                System.out.println("ERROR");
            } else {
                indice= extras.getString("INDICE");
            }
        } else {
            indice= (String) savedInstanceState.getSerializable("INDICE");
        }

*/




    }



    @Override
    public void onClick(View view) {
        nombrePlato = nombre.getText().toString();
        Log.d("ONCLICK buscar", "Nombre plato: "+nombrePlato);
        //HACER LOGICA BUSQUEDA
        listaP = PlatoRepository.getInstance().buscarPlato(nombrePlato,miHandler);

        mRecyclerView = (RecyclerView) findViewById(R.id.CardRecycler);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PlatoRecyclerAdapter(listaP,true);
        mRecyclerView.setAdapter(mAdapter);

        //mAdapter.notifyDataSetChanged();
    }



    public void showToast(String txtToast){
        Toast toast1 = Toast.makeText(getApplicationContext(),txtToast, Toast.LENGTH_SHORT);
        toast1.show();
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message m){
            listaP = PlatoRepository.getInstance().getListaPlatos();
            switch (m.arg1){
                case PlatoRepository._CONSULTA_PLATO:
                    Log.d("case consultaplato BUSCRPLATO", "size lista"+ listaP.size());

                    mAdapter = new PlatoRecyclerAdapter(listaP,true);
                    mRecyclerView.setAdapter(mAdapter);
                    break;
                case PlatoRepository._BORRADO_PLATO:
                    mAdapter.notifyDataSetChanged();
                    break;
                default:
                    Log.d("DEFAULT BUSCRPLATO", "handler");
                    break;
            }
        }
    };
}
