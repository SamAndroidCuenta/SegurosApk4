package com.jteniapps.SegurosApk1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView correo;
    TextView pass;
    Button btn_iniciar;
    Button btn_reg;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        gui();
        mAuth = FirebaseAuth.getInstance();

        btn_reg = findViewById(R.id.id_btn_registrarse);
        btn_iniciar = findViewById(R.id.id_btn_iniciarSesion);
        correo = findViewById(R.id.id_correo);
        pass = findViewById(R.id.id_clave);

        btn_reg.setOnClickListener(this);
        btn_iniciar.setOnClickListener(this);


    }

    public void gui() {
        btn_reg = findViewById(R.id.id_btn_registrarse);
        btn_iniciar = findViewById(R.id.id_btn_iniciarSesion);
        correo = findViewById(R.id.id_correo);
        pass = findViewById(R.id.id_clave);
    }

    public void metodo_iniciar(View view) {

        if (correo.getText().toString().isEmpty() || pass.getText().toString().isEmpty()) {
            validacion_campos_vacios();
        } else {
            Intent intent = new Intent(MainActivity.this, Navigation_MainActivity.class);
            startActivity(intent);
        }


    }

    public void metodo_registrar(View view) {

        /*if(correo.getText().toString().isEmpty()|| pass.getText().toString().isEmpty()){
            validacion_campos_vacios();
        }else {}*/
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);

    }

    //metodo para validar campos vacios
    public void validacion_campos_vacios() {
        Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.id_btn_iniciarSesion:
                singIn(correo.getText().toString(),pass.getText().toString());
                break;
            case R.id.id_btn_registrarse:
               startActivity(new Intent(this,Main2Activity.class));

        }


    }

    protected void singIn(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user.isEmailVerified()) {
                                Toast.makeText(MainActivity.this, "Bienvenido(a) .....Iniciando Sesión", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Navigation_MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, "La cuenta aun no ha sido validada. Validala en tu correo", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "error al iniciar", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}


