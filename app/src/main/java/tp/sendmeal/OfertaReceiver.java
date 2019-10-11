package tp.sendmeal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class OfertaReceiver extends BroadcastReceiver {
    public static final String EVENTO1="SendMeal.EVENTO1_MSG";

    @Override
    public void onReceive(Context context, Intent intent){
        System.out.println("Mensaje Oferta Receiver");
        Log.d("INDICE","MSJ OfertaReceiver"+intent.getAction());
        Toast.makeText(context, "ACA"+ intent.getAction(),Toast.LENGTH_LONG).show();

    }
}
