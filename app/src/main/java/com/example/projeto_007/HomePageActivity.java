package com.example.projeto_007;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // 1. Mapeando a barra de pesquisa e os Cards
        EditText editSearch = findViewById(R.id.editSearch);
        CardView cardPizza1 = findViewById(R.id.cardPizza1);
        CardView cardPizza2 = findViewById(R.id.cardPizza2);
        CardView cardPizza3 = findViewById(R.id.cardPizza3);

        // 2. Mapeando os botões de adicionar ao carrinho
        TextView btnCart1 = findViewById(R.id.btnCart1);
        TextView btnCart2 = findViewById(R.id.btnCart2);
        TextView btnCart3 = findViewById(R.id.btnCart3);

        // 3. Funcionalidade: Adicionar ao Carrinho (Mensagem rápida)
        View.OnClickListener addCartListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePageActivity.this, "Item adicionado ao carrinho!", Toast.LENGTH_SHORT).show();
            }
        };

        btnCart1.setOnClickListener(addCartListener);
        btnCart2.setOnClickListener(addCartListener);
        btnCart3.setOnClickListener(addCartListener);

        // 4. Funcionalidade: Barra de Pesquisa Ativa
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().toLowerCase().trim();

                // Mostra ou esconde as pizzas baseadas no que foi digitado
                cardPizza1.setVisibility("calabresa".contains(query) ? View.VISIBLE : View.GONE);
                cardPizza2.setVisibility("frango c/ catupiry".contains(query) || "frango".contains(query) ? View.VISIBLE : View.GONE);
                cardPizza3.setVisibility("mussarela clássica".contains(query) || "queijo".contains(query) || "mussarela".contains(query) ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}