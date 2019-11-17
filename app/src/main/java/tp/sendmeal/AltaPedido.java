package tp.sendmeal;

import android.app.Activity;
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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.List;

import tp.sendmeal.dao.PedidoDB;
import tp.sendmeal.dao.PedidoDao;
import tp.sendmeal.dao.PedidoRepository;
import tp.sendmeal.dao.PlatoRepository;
import tp.sendmeal.domain.ItemsPedido;
import tp.sendmeal.domain.Pedido;
import tp.sendmeal.domain.PedidoAndItems;
import tp.sendmeal.domain.Plato;

public class AltaPedido extends AppCompatActivity {
    private static int CODIGO_UBICACION=123;
    private static int CODIGO_PLATOS=321;
    private long id_pedido;
    private double latitud;
    private double longitud;

    private Button btnCrear;
    private Button btnEnviar;
    private Button btnUbicacion;
    private Button btnBuscarPlatos;

    private List<ItemsPedido> listaItems = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private ItemPedidoRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_pedido);

        btnBuscarPlatos=(Button) findViewById(R.id.buttonBuscarPlatos);

        btnUbicacion=(Button) findViewById(R.id.buttonUbicacion);

        btnCrear = (Button) findViewById(R.id.buttonCrearPedido);
        btnCrear.setEnabled(false);


        btnEnviar = (Button) findViewById(R.id.buttonEnviarPedido);
        btnEnviar.setEnabled(false);


        setSupportActionBar((Toolbar) findViewById(R.id.toolbarAltaPedido));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        mRecyclerView = (RecyclerView) findViewById(R.id.CardItems);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ItemPedidoRecyclerAdapter(listaItems);
        mRecyclerView.setAdapter(mAdapter);

//ACCIONES DE BOTONES
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listaItems.isEmpty()) {
                    showToast("Agregue un plato");
                }
                else {
                    crearPedido();
                    btnUbicacion.setEnabled(false);
                    btnEnviar.setEnabled(true);
                    btnCrear.setEnabled(false);
                }
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarPedido();
            }
        });

        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view.getContext(), Mapa.class);
                ((Activity) view.getContext()).startActivityForResult(i, CODIGO_UBICACION);

            }
        });

        btnBuscarPlatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent b = new Intent(view.getContext(), BuscarPlato.class);
                ((Activity) view.getContext()).startActivityForResult(b, CODIGO_PLATOS);

            }
        });
    }

    public void crearPedido() {

        Pedido pedido =new Pedido();
        pedido.setEstado(1);
        pedido.setLat(latitud);
        pedido.setLng(longitud);

        //pedido.setItems(itemsss);
        pedido.setItems(listaItems);
        PedidoDao pdao= PedidoRepository.getInstance(AltaPedido.this).getPedidoDB().pedidoDao();
        Long pedidoId = pdao.insertPedidoAndItems(pedido);

        id_pedido=pedidoId;

    /*   FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(AltaPedido.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String updatedToken = instanceIdResult.getToken();
                Log.e("Updated token",updatedToken);
            }
        });
*/

    }

    public void enviarPedido(){
        PedidoDao pdao= PedidoRepository.getInstance(AltaPedido.this).getPedidoDB().pedidoDao();
        long pedidoId=id_pedido;
        //ASI LO TRAIGO DE NUEVO
        PedidoAndItems pedidoAndItems= pdao.loadPedidoAndItemsById(pedidoId);
        //LE CARGO LA LISTA DE ITEMS AL PEDIDO
        System.out.println("Recuperaste el pedido nuemro:"+pedidoId);
        pedidoAndItems.getPedido().setItems(pedidoAndItems.getItems());
        System.out.println("Seteaste la lista de items");
        String t = pedidoAndItems.getItems().get(0).getPlato().getTitulo();
        System.out.println("El item 0 tiene el nombre de plato"+ t);
        PedidoRepository.getInstance().crearPedido(pedidoAndItems.getPedido(), miHandler);

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODIGO_UBICACION && resultCode == RESULT_OK && data != null) {

            String indice="";
            Bundle extras = data.getExtras();
            latitud= extras.getDouble("LATITUD");
            longitud = extras.getDouble("LONGITUD");
            System.out.println(latitud+" "+longitud);
            btnCrear.setEnabled(true);
        }

        if (requestCode == CODIGO_PLATOS  && resultCode == RESULT_OK && data != null) {
            Log.d("ALTA PEDIDO1", "entro ");
            Bundle extras2 = data.getExtras();
            Plato plato = new Plato();
            plato.setIdPlato(extras2.getInt("ID"));
            plato.setTitulo(extras2.getString("NOMBRE"));
            plato.setDescripcion(extras2.getString("DESCRIPCION"));
            plato.setPrecio(extras2.getDouble("PRECIO"));
            plato.setCalorias(extras2.getInt("CALORIAS"));

            ItemsPedido item =new ItemsPedido();
            item.setCantidad(1);
            item.setPlato(plato);
            item.setPrecioItem(plato.getPrecio());

            Log.d("ALTA PEDIDO2", "plato "+ plato.getTitulo());
            listaItems.add(item);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void showToast(String txtToast){
        Toast toast1 = Toast.makeText(getApplicationContext(),txtToast, Toast.LENGTH_SHORT);
        toast1.show();
    }


    //PUEDE FALLAR

    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg){
            Log.d("APP SendMeal","Vuelve al handler"+msg.arg1);
            switch (msg.arg1){
                case PlatoRepository._ALTA_PLATO:
                case PlatoRepository._UPDATE_PLATO:
                    Intent i = new Intent(AltaPedido.this,Home.class);
                    startActivity(i);
                    break;
            }
        }
    };
}