package com.jteniapps.SegurosApk1;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity implements OnClickListener {
    EditText correo;
    EditText usuario;
    EditText clave;
    EditText confirmarClave;

    FirebaseAuth mAuth;


    String txtnombre;
    String txtEmail;
    String txtPass;
    String txtConfirmPass;


    Button btn_confirmar;
    Button btn_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gui();//lo usamos para no sobre cargar
        btn_confirmar.setOnClickListener(this);
        btn_cancelar.setOnClickListener(this);
    }

    public void metodo_registrar(View view){
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);

    }

    public void metodo_cancelar(View view){
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);

    }

    public void gui(){
    correo = findViewById(R.id.id_correo_main2);
    usuario = findViewById(R.id.id_usuario_main2);
    clave = findViewById(R.id.id_clave_main2);
    confirmarClave = findViewById(R.id.id_confirmar_clave);

    btn_confirmar = findViewById(R.id.id_btn_confirmarRegistro);
    btn_cancelar = findViewById(R.id.id_btn_cancelar);
    mAuth = FirebaseAuth.getInstance();

    }

    public  void  onClick (View view){
        txtnombre = usuario.getText().toString();
        txtEmail = correo.getText().toString();
        txtPass =  clave.getText().toString();
        txtConfirmPass = confirmarClave.getText().toString();


        switch (view.getId()){
            case R.id.id_btn_cancelar:
                finish();
                break;
            case R.id.id_btn_confirmarRegistro:

                if(correo.getText().toString().isEmpty()){
                    validacion_campos_vacios();
                }else
                         //validacion  de espacios vacios
                   if (txtEmail.contains(" ")){
                       Toast.makeText(this, "Error en email, ESPACIO VACIO NO VALIDO", Toast.LENGTH_SHORT).show();
                   }//validacion  de espacios vacios
                   else
                       //validacion  arroba
                   if (txtEmail.contains("@")){
                       //Toast.makeText(this, "Correo Valido ", Toast.LENGTH_SHORT).show();
                   }else{
                       Toast.makeText(this, "Correo Invalido", Toast.LENGTH_SHORT).show();
                   }
                      //validacion  arroba


                if(usuario.getText().toString().isEmpty()){
                    validacion_campos_vacios();
                }else

                if(clave.getText().toString().isEmpty()){
                    validacion_campos_vacios();
                }else
                        if(clave.length()<6){ //validacion de 6 caracteres como minimo en campo de contraseña
                            validacion_seis_caracteres_minimos();                         }

                if(confirmarClave.getText().toString().isEmpty()){
                    validacion_campos_vacios();
                          // aqui validamos que las contraseñas coincidan
                    Toast.makeText(this, "las contraseñas si coinsiden", Toast.LENGTH_SHORT).show();
                    // aqui validamos que las contraseñas coincidan
                }else{
                    if(confirmarClave.getText().toString().equals(clave.getText().toString())){
                        /*Toast.makeText(this, "las contraseñas si coinsiden", Toast.LENGTH_SHORT).show();*/
                        creatAcount(txtEmail,txtPass);
                        break;
                    }else {
                        Toast.makeText(this, "las contraseñas no coinsiden", Toast.LENGTH_SHORT).show();

                    }

                }

        }


    }

    public  void  creatAcount(String email, String pass){
        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            saveDisplayName();
                            Toast.makeText(Main2Activity.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                            sendEmailVerification();//aqui llamamos al medoto de verificacion de correo
                           startActivity(new Intent(Main2Activity.this, MainActivity.class));
                        }else
                        {
                            /*Toast.makeText(Main2Activity.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();*/
                            showErrorDialog("Existe un error en su registro o el usuario ya existe. Para descartar si el usuario o email ya existen por fabor Verifique sus datos y vuelva a intentarlo. De lo contrario intente con otro email"); //esto despliega el mensaje de abajo cuadrado
                        }
                    }
                });

    }

    public void showErrorDialog(String msj){
        new AlertDialog.Builder(this)
                .setTitle("Registro Denegado")
                .setMessage(msj)
                .setPositiveButton("ok", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void saveDisplayName(){
        SharedPreferences preferences = getSharedPreferences("usuario", 0);
        preferences.edit().putString("username", txtnombre).apply();
    }

    protected void sendEmailVerification(){
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Main2Activity.this, "Ingresa a tu email y valida tu cuenta, enviamos un mensaje para ti", Toast.LENGTH_SHORT).show();
                /*showErrorDialog("Se ha enviado un correo de confirmacion a su cuenta");*/
                //showErrorDialog("Registro exitoso.... como ultimo paso, hemos enviado un mensaje de validacion al correo que acabas de ingresar, por fabor, ingresa a tu email y valida tu registro dando Click en el vinculo en azul"); //esto despliega el mensaje de abajo cuadrado

            }
        });
    }

    //metodo para validar campos vacios
    public void  validacion_campos_vacios(){
        Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();


    }

    //metodo para validar 6 caracteres minimos en contraseña
    public void  validacion_seis_caracteres_minimos(){
        Toast.makeText(this, "La contraseña debe tener 6 caracteres como minimo", Toast.LENGTH_SHORT).show();
    }


}
