package tp.sendmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Home extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.MenuRegistrarme:
                Intent i1 = new Intent(this,MainActivity.class);
                startActivity(i1);
                return true;
            case R.id.MenuCrearItem:
                Intent i2 = new Intent(this,AltaPlato.class);
                startActivity(i2);
                return true;
            case R.id.MenuListaItems:
                Intent i3 = new Intent(this, ListaPlatos.class);
                startActivity(i3);
                return true;
            case R.id.MenuBuscarItem:
                Intent i4 = new Intent(this, BuscarPlato.class);
                startActivity(i4);
                return true;
            case R.id.MenuCrearPedido:
                Intent i5 = new Intent(this, AltaPedido.class);
                startActivity(i5);
                return true;
            case R.id.MenuMapaPedidos:
                Intent i6 = new Intent(this, MapaPedidos.class);
                startActivity(i6);
                return true;
            default:
                Toast.makeText(this, "default switch", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }
}
