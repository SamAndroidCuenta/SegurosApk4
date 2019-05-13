package com.jteniapps.SegurosApk1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClasseMensajeEnvioDatos extends AppCompatActivity {
    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classe_mensaje_envio_datos);

        btnVolver = findViewById(R.id.id_btn_irapantallaprincipal);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Navigation_MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
