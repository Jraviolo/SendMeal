package tp.sendmeal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import tp.sendmeal.dao.PlatoRepository;
import tp.sendmeal.dao.rest.PlatoRest;
import tp.sendmeal.domain.Plato;


public class AltaPlato extends AppCompatActivity implements View.OnClickListener{

    private EditText idPlato;
    private EditText nombre;
    private EditText descripcion;
    private EditText precio;
    private EditText calorias;
    private ImageView foto;

    static final int REQUEST_IMAGE_CAPTURE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_plato);
        Button btnGuardar = (Button) findViewById(R.id.APbtnGuardar);
        Button btnFoto = (Button) findViewById(R.id.APbtnFoto);
        btnGuardar.setOnClickListener(this);



        setSupportActionBar((Toolbar) findViewById(R.id.toolbarAltaPlato));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        idPlato = (EditText) findViewById(R.id.APeditId);
        nombre = (EditText) findViewById(R.id.APeditNombre);
        descripcion = (EditText) findViewById(R.id.APeditDescripcion);
        precio = (EditText) findViewById(R.id.APeditPrecio);
        calorias = (EditText) findViewById(R.id.APeditCalorias);

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(i.resolveActivity(getPackageManager())!= null) {
                    startActivityForResult(i, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            foto.setImageBitmap(imageBitmap);
        }
    }



    @Override
    public void onClick(View view) {

        if(validarCampos()){
            /* Si validarCampos() devuelve true, se crea un nuevo plato */
            Plato plato = new Plato();
            plato.setIdPlato(Integer.parseInt(idPlato.getText().toString()));
            plato.setTitulo(nombre.getText().toString());
            plato.setDescripcion(descripcion.getText().toString());
            plato.setPrecio(Double.parseDouble(precio.getText().toString()));
            plato.setCalorias(Integer.parseInt(calorias.getText().toString()));

            PlatoRepository.getInstance().crearPlato(plato, miHandler);

            showToast("El plato fue creado");
        }

    }


    public boolean validarCampos(){

        //EL ID SE INGRESA O EL SISTEMA LO PONE SOLO??
        if( idPlato.getText().toString().isEmpty()) {
            showToast("El campo id es obligatorio");
            return false;
        }
        if( nombre.getText().toString().isEmpty()) {
            showToast("El campo nombre es obligatorio");
            return false;
        }
        if( precio.getText().toString().isEmpty()) {
            showToast("El campo precio es obligatorio");
            return false;
        }
        if( calorias.getText().toString().isEmpty()) {
            showToast("El campo calorias es obligatorio");
            return false;
        }

        return true;
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



    //PUEDE FALLAR

    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg){
            Log.d("APP SendMeal","Vuelve al handler"+msg.arg1);
            switch (msg.arg1){
                case PlatoRepository._ALTA_PLATO:
                case PlatoRepository._UPDATE_PLATO:
                    Intent i = new Intent(AltaPlato.this,ListaPlatos.class);
                    startActivity(i);
                    break;
            }
        }
    };
}
