package tp.sendmeal;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BroadcastNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        System.out.println("HOLA");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);
        BroadcastReceiver br = new OfertaReceiver();
        IntentFilter filtro = new IntentFilter();
        filtro.addAction(OfertaReceiver.EVENTO1);
        getApplication().getApplicationContext().registerReceiver(br,filtro);
        System.out.println("HOLA 2");
    }
}
