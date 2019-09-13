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

                return true;
            case R.id.MenuListaItems:

                return true;
            default:
                Toast.makeText(this, "default switch", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }
}
