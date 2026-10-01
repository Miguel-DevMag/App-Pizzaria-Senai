package com.example.projeto_007; // Mantenha o seu pacote original

import androidx.appcompat.app.AlertDialog;
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

    // Variáveis para controlar o carrinho real
    private int totalItensCarrinho = 0;
    private double valorTotalCarrinho = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // 1. Mapeamento
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

        ImageView btnBack = findViewById(R.id.btnBack);
        ImageView btnCart = findViewById(R.id.btnCart);

        // FUNCIONALIDADE 1: VOLTAR PARA A TELA INICIAL

        btnBack.setOnClickListener(v -> {
            finish(); // Encerra a tela do cardápio e volta para a anterior
        });


        // FUNCIONALIDADE 2: CARRINHO DE COMPRAS REAL

        btnCart.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomePageActivity.this);
            builder.setTitle("Seu Carrinho \uD83D\uDED2");

            if (totalItensCarrinho == 0) {
                builder.setMessage("O seu carrinho está vazio. Adicione algumas pizzas deliciosas!");
                builder.setPositiveButton("Ok", null);
            } else {
                String totalFormatado = String.format("R$ %.2f", valorTotalCarrinho);
                builder.setMessage("Você tem " + totalItensCarrinho + " item(s) no carrinho.\n\nValor Total: " + totalFormatado);

                builder.setPositiveButton("Finalizar Pedido", (dialog, which) -> {
                    Toast.makeText(HomePageActivity.this, "Pedido enviado com sucesso!", Toast.LENGTH_LONG).show();
                    // Zera o carrinho após o pedido
                    totalItensCarrinho = 0;
                    valorTotalCarrinho = 0.0;
                });
                builder.setNegativeButton("Continuar Comprando", null);
            }
            builder.show();
        });


        // FUNCIONALIDADE 3: ADICIONAR PRODUTOS

        btnCart1.setOnClickListener(v -> adicionarAoCarrinho("Calabresa", 45.90));
        btnCart2.setOnClickListener(v -> adicionarAoCarrinho("Frango c/ Catupiry", 49.90));
        btnCart3.setOnClickListener(v -> adicionarAoCarrinho("Mussarela", 42.90));


        // FUNCIONALIDADE 4: FILTROS E PESQUISA

        chipTradicionais.setOnClickListener(v -> {
            cardPizza1.setVisibility(View.VISIBLE);
            cardPizza2.setVisibility(View.VISIBLE);
            cardPizza3.setVisibility(View.VISIBLE);
            editSearch.setText("");
        });

        View.OnClickListener filtroVazio = v -> {
            cardPizza1.setVisibility(View.GONE);
            cardPizza2.setVisibility(View.GONE);
            cardPizza3.setVisibility(View.GONE);
            Toast.makeText(HomePageActivity.this, "Esta categoria será atualizada em breve!", Toast.LENGTH_SHORT).show();
        };

        chipDoces.setOnClickListener(filtroVazio);
        chipBebidas.setOnClickListener(filtroVazio);

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

    // Método auxiliar para processar as compras
    private void adicionarAoCarrinho(String nomePizza, double preco) {
        totalItensCarrinho++;
        valorTotalCarrinho += preco;
        Toast.makeText(this, "1x " + nomePizza + " adicionada \uD83D\uDED2", Toast.LENGTH_SHORT).show();
    }
}