package tp.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nombre;
    private EditText clave;
    private EditText clave2;
    private EditText correo;
    private EditText tarjeta;
    private EditText ccv;
    private EditText vtoTarjeta;
    private RadioGroup groupTipoCuenta;
    private String tipoCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnRegistrar = (Button) findViewById(R.id.AMbtnRegistrar);
        btnRegistrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        nombre = (EditText) findViewById(R.id.AMeditNombre);
        clave = (EditText)findViewById(R.id.AMeditClave);
        clave2 = (EditText)findViewById(R.id.AMeditClave2);
        correo = (EditText)findViewById(R.id.AMeditCorreo);
        tarjeta = (EditText)findViewById(R.id.AMeditNumero);
        ccv = (EditText)findViewById(R.id.AMeditDig);
        vtoTarjeta = (EditText)findViewById(R.id.AMeditVto);
        groupTipoCuenta = (RadioGroup) findViewById(R.id.AMTipoCuenta);
        tipoCuenta = ((RadioButton) findViewById(groupTipoCuenta.getCheckedRadioButtonId())).getText().toString();

    }
    
}
