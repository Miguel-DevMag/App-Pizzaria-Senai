package com.example.projeto_007; // Substitua caso tenha mudado o nome do pacote

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // 1. Mapeamento de UI
        EditText editSearch = findViewById(R.id.editSearch);
        CardView cardPizza1 = findViewById(R.id.cardPizza1);
        CardView cardPizza2 = findViewById(R.id.cardPizza2);
        CardView cardPizza3 = findViewById(R.id.cardPizza3);

        TextView chipTradicionais = findViewById(R.id.chipTradicionais);
        TextView chipDoces = findViewById(R.id.chipDoces);
        TextView chipBebidas = findViewById(R.id.chipBebidas);

        TextView btnCart1 = findViewById(R.id.btnCart1);
        TextView btnCart2 = findViewById(R.id.btnCart2);
        TextView btnCart3 = findViewById(R.id.btnCart3);

        ImageView btnMenu = findViewById(R.id.btnMenu);
        ImageView btnCart = findViewById(R.id.btnCart);

        // 2. Feedback de Carrinho e Menu Lateral (UX)
        View.OnClickListener addToCartListener = v ->
                Toast.makeText(HomePageActivity.this, "Item adicionado ao carrinho \uD83D\uDED2", Toast.LENGTH_SHORT).show();

        btnCart1.setOnClickListener(addToCartListener);
        btnCart2.setOnClickListener(addToCartListener);
        btnCart3.setOnClickListener(addToCartListener);
        cardPizza1.setOnClickListener(addToCartListener); // Clicar no card inteiro também adiciona
        cardPizza2.setOnClickListener(addToCartListener);
        cardPizza3.setOnClickListener(addToCartListener);

        btnMenu.setOnClickListener(v -> Toast.makeText(HomePageActivity.this, "Menu lateral indisponível", Toast.LENGTH_SHORT).show());
        btnCart.setOnClickListener(v -> Toast.makeText(HomePageActivity.this, "Seu carrinho está vazio", Toast.LENGTH_SHORT).show());

        // 3. Lógica dos Chips de Categoria
        chipTradicionais.setOnClickListener(v -> {
            cardPizza1.setVisibility(View.VISIBLE);
            cardPizza2.setVisibility(View.VISIBLE);
            cardPizza3.setVisibility(View.VISIBLE);
            editSearch.setText("");
        });

        View.OnClickListener emptyCategoryListener = v -> {
            cardPizza1.setVisibility(View.GONE);
            cardPizza2.setVisibility(View.GONE);
            cardPizza3.setVisibility(View.GONE);
            Toast.makeText(HomePageActivity.this, "Novidades em breve!", Toast.LENGTH_SHORT).show();
        };

        chipDoces.setOnClickListener(emptyCategoryListener);
        chipBebidas.setOnClickListener(emptyCategoryListener);

        // 4. Mecanismo de Busca Dinâmica
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().toLowerCase().trim();

                cardPizza1.setVisibility("calabresa".contains(query) ? View.VISIBLE : View.GONE);
                cardPizza2.setVisibility("frango c/ catupiry".contains(query) || "frango".contains(query) ? View.VISIBLE : View.GONE);
                cardPizza3.setVisibility("mussarela clássica".contains(query) || "queijo".contains(query) || "mussarela".contains(query) ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}