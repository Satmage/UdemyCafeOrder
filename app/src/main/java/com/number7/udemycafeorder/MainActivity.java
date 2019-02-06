package com.number7.udemycafeorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    /*
    * Обработка кнопки "Отправить заказ"
    * Передает имя и пароль пользователя
    *
    * Проверка: если имя и пароль не пустые, передать данные в активность CreateOrderActivity
    * иначе: вывести короткое всплывающее уведомление
    * */
    public void onClickCreateOrderMain(View view) {

        String name = editTextName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // проверка
        if(!name.isEmpty() && !password.isEmpty()){
            Intent intent = new Intent(this,CreateOrderActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("password",password);
            startActivity(intent);
        }else{
            // уведомление
            Toast.makeText(this,R.string.Main_toast_create_order, Toast.LENGTH_SHORT).show();
        }

    }
}
