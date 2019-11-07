package tp.sendmeal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import tp.sendmeal.dao.PedidoRepository;
import tp.sendmeal.domain.Pedido;

public class MapaPedidos extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private PolylineOptions lineaOpciones= new PolylineOptions();
    private Polyline linea;

    private ArrayList<Pedido> pedidos=new ArrayList<>();

    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_pedidos);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapPedidos);
        mapFragment.getMapAsync(this);


        spinner = (Spinner) findViewById(R.id.spinnerEstados);
        adapter = ArrayAdapter.createFromResource(this, R.array.estados_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        actualizarMapa();

        listarPedidos();
        cargarMarcadores("TODOS");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mMap.clear();

                cargarMarcadores(adapterView.getItemAtPosition(i).toString());
                linea = mMap.addPolyline(lineaOpciones);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        }
        );
    }

    private void actualizarMapa() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    9999);
            return;
        }
        mMap.setMyLocationEnabled(true);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String
            permissions[], int[] grantResults) {
        switch (requestCode) {
            case 9999: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    actualizarMapa();
                } else {
                    return;
                }
            }
        }
    }


    public void cargarMarcadores(String estado){


        BitmapDescriptor bitmapDescriptor;
        String titulo;
        String informacion;
        Boolean todos=false;

        lineaOpciones=new PolylineOptions();
        if(estado.equals("TODOS")) todos=true;


        for (int i=0;i<pedidos.size();i++){
            if(estado.equals(pedidos.get(i).getEstadoText()) || todos){
            switch (pedidos.get(i).getEstado()){
                case 1:
                    bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
                    break;
                case 2:
                    bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE);
                    break;
                case 3:
                    bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN);
                    break;
                case 4:
                    bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                    break;
                case 5:
                    bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA);
                    break;
                case 6:
                    bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
                    break;
                case 7:
                    bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
                    break;
                case 8:
                    bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + pedidos.get(i).getEstado());
            }

            titulo=pedidos.get(i).getEstadoText();
            informacion="id:"+pedidos.get(i).getId()+" Precio:";
            LatLng coordenadas= new LatLng(pedidos.get(i).getLat(),pedidos.get(i).getLng());
            mMap.addMarker(new MarkerOptions()
                    .position(coordenadas)
                    .title(titulo)
                    .snippet(informacion)
                    .icon(bitmapDescriptor));
            if(pedidos.get(i).getEstadoText().equals("En envio")){
                lineaOpciones.add(coordenadas);
            }
            }
        }
        
    }

    public void listarPedidos(){

        //ACA TIENE QUE ESTAR EL METODO QUE CARGA LOS PEDIDOS

       // PedidoRepository.getInstance().listaPedidos();

        Pedido p1 =new Pedido();
        p1.setEstado(1);
        p1.setId(1);
        p1.setLat(-31.620816);
        p1.setLng(-60.747582);
        pedidos.add(p1);

        Pedido p2 =new Pedido();
        p2.setEstado(2);
        p2.setId(1);
        p2.setLat(-31.00);
        p2.setLng(-60.747582);
        pedidos.add(p2);

        Pedido p3 =new Pedido();
        p3.setEstado(3);
        p3.setId(1);
        p3.setLat(-31.10);
        p3.setLng(-60.66);
        pedidos.add(p3);

        Pedido p4 =new Pedido();
        p4.setEstado(4);
        p4.setId(4);
        p4.setLat(-30.00);
        p4.setLng(-61.747582);
        pedidos.add(p4);

        Pedido p5 =new Pedido();
        p5.setEstado(5);
        p5.setId(5);
        p5.setLat(-29.00);
        p5.setLng(-60.899);
        pedidos.add(p5);

        Pedido p6 =new Pedido();
        p6.setEstado(6);
        p6.setId(6);
        p6.setLat(-31.3);
        p6.setLng(-62.747582);
        pedidos.add(p6);

        Pedido p7 =new Pedido();
        p7.setEstado(7);
        p7.setId(7);
        p7.setLat(-33.00);
        p7.setLng(-61.582);
        pedidos.add(p7);

        Pedido p8 =new Pedido();
        p8.setEstado(8);
        p8.setId(8);
        p8.setLat(-32.11);
        p8.setLng(-60.74782);
        pedidos.add(p8);

        Pedido p9 =new Pedido();
        p9.setEstado(6);
        p9.setId(9);
        p9.setLat(-30.1);
        p9.setLng(-62.582);
        pedidos.add(p9);
    }
}