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
    private double latitud;
    private double longitud;

    private Button btnCrear;
    private Button btnEnviar;
    private Button btnUbicacion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_pedido);

        btnUbicacion=(Button) findViewById(R.id.buttonUbicacion);

        btnCrear = (Button) findViewById(R.id.buttonCrearPedido);
        btnCrear.setEnabled(false);


        btnEnviar = (Button) findViewById(R.id.buttonEnviarPedido);
        btnEnviar.setEnabled(false);


        setSupportActionBar((Toolbar) findViewById(R.id.toolbarAltaPedido));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

//ACCIONES DE BOTONES
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearPedido();
                btnEnviar.setEnabled(true);
            }
        });

        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view.getContext(), Mapa.class);
                ((Activity) view.getContext()).startActivityForResult(i, CODIGO_UBICACION);

            }
        });

    }


    public void crearPedido() {
        Pedido pedido =new Pedido();
        pedido.setEstado(1);
        pedido.setLat(latitud);
        pedido.setLng(longitud);

        ItemsPedido item =new ItemsPedido();
        item.setCantidad(1);

        Plato plato1 = new Plato();
        plato1.setPrecio((double)123);
        plato1.setTitulo("hola ale");
        plato1.setDescripcion("jajaja1");
        plato1.setCalorias(123);
        plato1.setIdPlato(1);

        item.setPlato(plato1);
        item.setPrecioItem((float)10);


        PedidoDao pdao= PedidoRepository.getInstance(AltaPedido.this).getPedidoDB().pedidoDao();
        Long pedidoId = pdao.insertPedido(pedido);
        System.out.println("Insertaste el pedido con el id:"+pedidoId);
        item.setPedidoId(pedidoId);
        pdao.insertItem(item);
        System.out.println("Insertaste Item con nombre de plato:"+item.getPlato().getTitulo());

//ASI LO TRAIGO DE NUEVO
        PedidoAndItems pedidoAndItems= pdao.loadPedidoAndItemsById(pedidoId);
        //LE CARGO LA LISTA DE ITEMS AL PEDIDO
        System.out.println("Recuperaste el pedido nuemro:"+pedidoId);
        pedidoAndItems.getPedido().setItems(pedidoAndItems.getItems());
        System.out.println("Seteaste la lista de items");
        String t = pedidoAndItems.getItems().get(0).getPlato().getTitulo();
        System.out.println("El item 0 tiene el nombre de plato"+ t);
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
    }


    //PUEDE FALLAR

    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg){
            Log.d("APP SendMeal","Vuelve al handler"+msg.arg1);
            switch (msg.arg1){
                case PlatoRepository._ALTA_PLATO:
                case PlatoRepository._UPDATE_PLATO:
                    Intent i = new Intent(AltaPedido.this,ListaPlatos.class);
                    startActivity(i);
                    break;
            }
        }
    };
}