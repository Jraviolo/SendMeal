package tp.sendmeal;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.cardview.widget.CardView;
import java.util.ArrayList;

import tp.sendmeal.domain.Plato;

public class ListaPlatos extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    //private RecyclerView.Adapter mAdapter;
    private PlatoRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycler);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbarListItems));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ArrayList<Plato> listaP = new ArrayList<>();
        Plato plato1 = new Plato();
        plato1.setPrecio(148.00);
        plato1.setTitulo("Hamburguesa1");
        Plato plato2 = new Plato();
        plato2.setPrecio(200.00);
        plato2.setTitulo("Hamburguesa2");
        listaP.add(plato1);
        listaP.add(plato2);
        Plato plato3 = new Plato();
        plato3.setPrecio(220.00);
        plato3.setTitulo("Hamburguesa3");
        listaP.add(plato3);
        Plato plato4 = new Plato();
        plato4.setPrecio(210.00);
        plato4.setTitulo("Hamburguesa4");
        listaP.add(plato4);

        mRecyclerView = (RecyclerView) findViewById(R.id.CardRecycler);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PlatoRecyclerAdapter(listaP);
        mRecyclerView.setAdapter(mAdapter);

    }

}
