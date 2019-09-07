package tp.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {
    private EditText nombre;
    private EditText clave;
    private EditText clave2;
    private EditText correo;
    private EditText tarjeta;
    private EditText ccv;
    private EditText vtoTarjeta;
    private EditText aliasCBU;
    private EditText CBU;
    private RadioGroup groupTipoCuenta;
    private RadioButton tipoCuenta;
    private Switch vendedor;
    private SeekBar seekCredito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnRegistrar = (Button) findViewById(R.id.AMbtnRegistrar);
        btnRegistrar.setOnClickListener(this);
        nombre = (EditText) findViewById(R.id.AMeditNombre);
        clave = (EditText)findViewById(R.id.AMeditClave);
        clave2 = (EditText)findViewById(R.id.AMeditClave2);
        correo = (EditText)findViewById(R.id.AMeditCorreo);
        tarjeta = (EditText)findViewById(R.id.AMeditNumero);
        ccv = (EditText)findViewById(R.id.AMeditDig);
        vtoTarjeta = (EditText)findViewById(R.id.AMeditVto);
        groupTipoCuenta = (RadioGroup) findViewById(R.id.AMTipoCuenta);
        vendedor = (Switch) findViewById(R.id.AMswitchVendedor);
        vendedor.setOnCheckedChangeListener(this);
        aliasCBU = (EditText) findViewById(R.id.AMeditAlias);
        CBU = (EditText) findViewById(R.id.AMeditCBU);
        seekCredito = (SeekBar) findViewById(R.id.AMseekCredito);
        seekCredito.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View view) {

        if(validarCampos()){
            //LO REGISTRA
        }

    }
@Override
    public void onCheckedChanged(CompoundButton btnView, boolean isChecked){
        if(isChecked) {
            findViewById(R.id.AMlayoutCuenta).setVisibility(View.VISIBLE);
        }
        else{
            findViewById(R.id.AMlayoutCuenta).setVisibility(View.GONE);
        }
    }
@Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
    }

    public void onStartTrackingTouch(SeekBar seekBar) {}

    public void onStopTrackingTouch(SeekBar seekBar) {}

    public boolean validarCampos(){


        if( nombre.getText().toString().isEmpty()) {
            showToast("El campo nombre es obligatorio");
            return false;
        }
        if( clave.getText().toString().isEmpty()) {
            showToast("Ingrese una contraseña");
            return false;
        }else {
            if (!clave.getText().toString().equals(clave2.getText().toString())){
                showToast("Las contraseñas no coinciden");
                return false;
            }
        }
        if( correo.getText().toString().isEmpty()) {
            showToast("El campo correo es obligatorio");
            return false;
        } else {
            if(!isValidEmail( correo.getText().toString()) ) {
                showToast("Ingrese un correo valido");
                return false;
            }
        }
        if( tarjeta.getText().toString().isEmpty()) {
            showToast("Ingrese un numero de tarjeta");
            return false;
        }
        if( ccv.getText().toString().isEmpty()) {
            showToast("Ingrese un ccv");
            return false;
        }
        if( vtoTarjeta.getText().toString().isEmpty() ) {
            showToast("Ingrese un vencimiento de tarjeta valido");
            return false;
            //falta validar fecha
        }
        if( groupTipoCuenta.getCheckedRadioButtonId() == -1){
            showToast("Seleccione el tipo de cuenta");
            return false;
        }
        if(vendedor.isChecked() && aliasCBU.getText().toString().isEmpty()){
            showToast("Ingrese el alias de CBU");
            return false;
        }
        if(vendedor.isChecked() && CBU.getText().toString().isEmpty()){
            showToast("Ingrese su CBU");
            return false;
        }

        if( !((CheckBox)findViewById(R.id.AMcheckCondiciones)).isChecked()) showToast("Debe acepar los terminos y condiciones");
        return true;
    }

    public final static boolean isValidEmail(CharSequence target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public void showToast(String txtToast){
        Toast toast1 = Toast.makeText(getApplicationContext(),txtToast, Toast.LENGTH_SHORT);
        toast1.show();
    }

}
