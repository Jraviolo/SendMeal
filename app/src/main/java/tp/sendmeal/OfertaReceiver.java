package tp.sendmeal;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;


public class OfertaReceiver extends BroadcastReceiver {
    public static final String EVENTO1="SendMeal.EVENTO1_MSG";

    public static final int NOTIFICACION_ID=123;
    public static final String CHANNEL_ID="notificacion_oferta";

    Context context1;

    @Override
    public void onReceive(Context context, Intent intent){

        //GUARDO EL CONTEXTO XQ SI NO NO PUEDO CREAR EL CHANELL UNO VAYA  SABER XQ ME SALIO TOCANDO COSAS
        context1=context;

        //ESTE RANDOM ES XQ SI NO SIEMPRE MANDA EL PRIMER PLATO QUE SELECCIONAS COMO OFERTA AL EDITAR
        //VER stackoverflow.com/questions/7370324/notification-passes-old-intent-extras
        int uniqueInt = (int) (System.currentTimeMillis() & 0xfffffff);

        String indice=intent.getStringExtra("INDICE");

        //CREO EL INSTENT DE EDITAR PARA PASARLO A LA NOTIFICACION
        Intent destino = new Intent(context, EditarPlato.class);
        destino.putExtra("INDICE",indice);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, uniqueInt, destino, 0);

        createNotificationChannel();
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_whatshot_black_24dp)
                        .setContentTitle("Nuevo plato en oferta")
                        .setContentText("El plato numero "+indice+" se encuentra en oferta")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICACION_ID, mBuilder.build());
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Oferta plato";
            String description = "descripcion";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = (NotificationManager) context1.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
