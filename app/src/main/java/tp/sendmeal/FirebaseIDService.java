package tp.sendmeal;
/*
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

public class FirebaseIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";


    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG, "Refreshed token: SERVICIO CREADO!!!");
    }

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: "+ refreshedToken);
        saveTokenToPrefs(refreshedToken);
    }

    private void saveTokenToPrefs(String _token){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("registration_id", _token);
        editor.apply();

    }
    private String getTokenFromPrefs(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString("registration_id", null);
    }

*/