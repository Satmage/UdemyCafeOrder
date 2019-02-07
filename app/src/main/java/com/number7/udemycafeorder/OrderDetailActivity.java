package com.number7.udemycafeorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {

    private TextView textViewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        textViewOrder = findViewById(R.id.textViewOrder);
        // intent - Получаем данные активности
        Intent intent = getIntent();

        /*
        * Если intent содержит переменную order с предыдущей активности то
        * присваиваем переменной order значения intent
        * и выводим эти значения в textViewOrder
        * иначе
        * Возвращаемся на окно регестрации
        * */
        if(intent.hasExtra("order")){
            String order = intent.getStringExtra("order");
            textViewOrder.setText(order);
        }else{
            Intent backToLogin = new Intent(this, MainActivity.class);
            startActivity(backToLogin);
        }
    }
}
