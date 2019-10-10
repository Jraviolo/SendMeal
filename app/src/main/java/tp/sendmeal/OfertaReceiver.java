package tp.sendmeal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OfertaReceiver extends BroadcastReceiver {
    public static final String EVENTO1="SendMeal.EVENTO1_MSG";

    @Override
    public void onReceive(Context context, Intent intent){
        Log.d("OFERTA",""+intent.getAction());

    }
}
