package com.number7.udemycafeorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {

    private TextView textViewHello;
    private TextView textViewAddition;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;

    private String drink;
    private String name;
    private String password;

    private StringBuilder builderAddition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        // присвоить переменой drink значение сроковой переменной
        drink = getString(R.string.createOrder_radioButton_tea);
        // Получить данные с интента (предыдущей активности)
        Intent intent = getIntent();

        /*
         * Если содержится в интенте "name" и "password"
         * присвоить переменным значения интента
         * иначе
         * присвоить переменным name и password значения по умолчанию
         * */
        if (intent.hasExtra("name") && intent.hasExtra("password")) {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        } else {
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }

        textViewHello = findViewById(R.id.textViewHello);
        textViewAddition = findViewById(R.id.textViewAddition);

        String addition = String.format(getString(R.string.createOrder_textView_Addition), drink);
        textViewAddition.setText(addition);

        String hello = String.format(getString(R.string.createOrder_textView_hello), name);
        textViewHello.setText(hello);

        checkBoxLemon = findViewById(R.id.checkBoxLemon);
        checkBoxMilk = findViewById(R.id.checkBoxMilk);
        checkBoxSugar = findViewById(R.id.checkBoxSugar);

        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);

        /*
         * Применяется при необходимости множества изменений в строке символов
         * */
        builderAddition = new StringBuilder();

    }

    public void onClickChangeDrinkRadioButton(View view) {
        RadioButton button = (RadioButton) view;
        int id = button.getId();
        /*
         * Если id равен id кнопки radioButtonTea то
         * drink - принимает значение строковой переменной tea
         * spinnerTea - становится видимым
         * spinnerCoffee - становится невидимым
         * checkBoxLemon - становится видимым
         * иначе...
         * */
        if (id == R.id.radioButtonTea) {
            drink = getString(R.string.createOrder_radioButton_tea);
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffee.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.VISIBLE);
        } else if (id == R.id.radioButtonCoffee) {
            drink = getString(R.string.createOrder_radioButton_coffee);
            spinnerCoffee.setVisibility(View.VISIBLE);
            spinnerTea.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.INVISIBLE);
        }

        String addition = String.format(getString(R.string.createOrder_textView_Addition), drink);
        textViewAddition.setText(addition);

    }

    public void onClickSendOrderImage(View view) {
        // обнуляем переменную buildAddition
        builderAddition.setLength(0);
        // если checkbox нажат то имя добавляется в переменную buildAddition
        if (checkBoxMilk.isChecked()) {
            builderAddition.append(getString(R.string.createOrder_checkBox_milk)).append(" ");
        }
        if (checkBoxSugar.isChecked()) {
            builderAddition.append(getString(R.string.createOrder_checkBox_sugar)).append(" ");
        }
        if (checkBoxLemon.isChecked() && drink.equals(getString(R.string.createOrder_radioButton_tea))) {
            builderAddition.append(getString(R.string.createOrder_checkBox_lemon)).append(" ");
        }
        // optionOfDrink - содержит опции напитка
        String optionOfDrink;
        /*
         * Если содержимое dink эквивалентно строковой переменной createOrder_radioButton_tea
         * то - optionOfDrink присваивается значение всплывающего списка SpinnerTea
         * иначе - всплывающего списка coffee
         * */
        if (drink.equals(getString(R.string.createOrder_radioButton_tea))) {
            //getSelectedItem() - выбаранный элемент всплывающего списка
            // toString() - преобразовать в строку
            optionOfDrink = spinnerTea.getSelectedItem().toString();
        } else {
            optionOfDrink = spinnerCoffee.getSelectedItem().toString();
        }
        // order - переменная со значением: Имя/пароль/напиток/категория напитка
        String order = String.format(getString(R.string.order), name, password, drink, optionOfDrink);
        // addition - переменная со значением добавок
        String additions;
        /*
         * Если длина строки в builderAddition > 0 то
         * в переменную additions заносятся добавки
         * иначе additions остается пустым
         * */
        if (builderAddition.length() > 0) {
            additions = getString(R.string.need_addition) + builderAddition.toString();
        } else {
            additions = " ";
        }

        /*
         * fullOrder - содержит полный заказ:
         * Имя/Пароль/Напиток/Категория напитка/Добавки
         */

        String fullOrder = order + additions;

        // при нажатии на картинку переход к OrderDetailActivity активности
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("order", fullOrder);
        startActivity(intent);
    }
}
