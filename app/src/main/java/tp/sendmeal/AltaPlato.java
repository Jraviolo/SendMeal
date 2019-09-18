package tp.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tp.sendmeal.domain.Plato;

public class AltaPlato extends AppCompatActivity implements View.OnClickListener{

    private EditText idPlato;
    private EditText nombre;
    private EditText descripcion;
    private EditText precio;
    private EditText calorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_plato);
        Button btnGuardar = (Button) findViewById(R.id.APbtnGuardar);
        btnGuardar.setOnClickListener(this);

        idPlato = (EditText) findViewById(R.id.APeditId);
        nombre = (EditText) findViewById(R.id.APeditNombre);
        descripcion = (EditText) findViewById(R.id.APeditDescripcion);
        precio = (EditText) findViewById(R.id.APeditPrecio);
        calorias = (EditText) findViewById(R.id.APeditCalorias);

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
}
