package tp.sendmeal;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class ComunicacionPush extends FirebaseMessagingService {
    private final String TAG = "APP_MSG";

    @Override
    public void onNewToken(String token){

        super.onNewToken(token);
        Log.d(TAG, "Refreshed token: "+ token);
       // sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived (RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);
      //  sendNotification(remoteMessage.getNotification().getBody());
    }


}
