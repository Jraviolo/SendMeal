package tp.sendmeal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import tp.sendmeal.dao.PlatoRepository;
import tp.sendmeal.domain.Plato;

public class BuscarPlato extends AppCompatActivity implements View.OnClickListener{

    private String nombrePlato;
    private Plato plato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_plato);
        Button btnBuscar = (Button) findViewById(R.id.btnBuscar);
        nombrePlato = findViewById(R.id.editBuscar).toString();
        btnBuscar.setOnClickListener(this);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbarBuscarPlato));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Buscar Plato");

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

        //HACER LOGICA BUSQUEDA
     //   PlatoRepository.getInstance().buscarPlato(plato,miHandler);


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
}
