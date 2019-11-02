package tp.sendmeal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.cardview.widget.CardView;
import java.util.ArrayList;
import java.util.List;

import tp.sendmeal.dao.PlatoRepository;
import tp.sendmeal.domain.Plato;

public class ListaPlatos extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    //private RecyclerView.Adapter mAdapter;
    private PlatoRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //public static ArrayList<Plato> listaP = new ArrayList<>();
    public static List<Plato> listaP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycler);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbarListItems));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listaP = PlatoRepository.getInstance().getListaPlatos();
        PlatoRepository.getInstance().listarPlatos(miHandler);
        /*

        Plato plato1 = new Plato();
        plato1.setPrecioItem(148.00);
        plato1.setTitulo("Hamburguesa1");
        plato1.setDescripcion("jajaja1");
        plato1.setCalorias(123);
        plato1.setIdPlato(1);
        listaP.add(plato1);

        Plato plato2 = new Plato();
        plato2.setPrecioItem(200.00);
        plato2.setTitulo("Hamburguesa2");
        plato2.setDescripcion("jajaja2");
        plato2.setCalorias(123);
        plato2.setIdPlato(2);
        listaP.add(plato2);

        Plato plato3 = new Plato();
        plato3.setPrecioItem(220.00);
        plato3.setTitulo("Hamburguesa3");
        plato3.setDescripcion("jajaja3");
        plato3.setCalorias(123);
        plato3.setIdPlato(3);
        listaP.add(plato3);

        Plato plato4 = new Plato();
        plato4.setPrecioItem(210.00);
        plato4.setTitulo("Hamburguesa4");
        plato4.setDescripcion("jajaja4");
        plato4.setCalorias(123);
        plato4.setIdPlato(4);
        listaP.add(plato4);

        */


        mRecyclerView = (RecyclerView) findViewById(R.id.CardRecycler);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PlatoRecyclerAdapter(listaP, false);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int
            resultCode, @Nullable Intent data) {
        if( resultCode== Activity.RESULT_OK){
            if(requestCode==mAdapter.CODIGO_LISTA_PLATO){
                mAdapter.notifyDataSetChanged();
            }
        }
    }


    public void showToast(String txtToast){
        Toast toast1 = Toast.makeText(getApplicationContext(),txtToast, Toast.LENGTH_SHORT);
        toast1.show();
    }

    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message m){
            listaP = PlatoRepository.getInstance().getListaPlatos();
            switch (m.arg1){
                case PlatoRepository._CONSULTA_PLATO:
                    mAdapter = new PlatoRecyclerAdapter(listaP,false);
                    mRecyclerView.setAdapter(mAdapter);
                    break;
                case PlatoRepository._BORRADO_PLATO:
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };


}
