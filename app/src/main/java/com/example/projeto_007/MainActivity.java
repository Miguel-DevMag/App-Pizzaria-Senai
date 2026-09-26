package com.example.projeto_007;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mapeando o botão do XML para o Java
        Button btnVerCardapio = findViewById(R.id.btnVerCardapio);

        // Configurando o evento de clique
        btnVerCardapio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Criando a Intent para navegar para a HomePageActivity
                Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
    }
}