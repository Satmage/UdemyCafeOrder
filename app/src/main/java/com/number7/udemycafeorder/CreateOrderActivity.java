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

        drink = getString(R.string.createOrder_radioButton_tea);

        Intent intent = getIntent();
        if (intent.hasExtra("name") && intent.hasExtra("password")) {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        } else {
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }


        setContentView(R.layout.activity_create_order);

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

        builderAddition = new StringBuilder();

    }

    public void onClickChangeDrinkRadioButton(View view) {
        RadioButton button = (RadioButton) view;
        int id = button.getId();
        if(id == R.id.radioButtonTea){
            drink = getString(R.string.createOrder_radioButton_tea);
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffee.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.VISIBLE);
        } else if (id == R.id.radioButtonCoffee){
            drink = getString(R.string.createOrder_radioButton_coffee);
            spinnerCoffee.setVisibility(View.VISIBLE);
            spinnerTea.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.INVISIBLE);
        }

        String addition = String.format(getString(R.string.createOrder_textView_Addition), drink);
        textViewAddition.setText(addition);

    }

    public void onClickSendOrderImage(View view) {
        builderAddition.setLength(0);
        if(checkBoxMilk.isChecked()){
            builderAddition.append(getString(R.string.createOrder_checkBox_milk)).append(" ");
        }
        if(checkBoxLemon.isChecked()){
            builderAddition.append(getString(R.string.createOrder_checkBox_lemon)).append(" ");
        }
        if(checkBoxSugar.isChecked() && drink.equals(getString(R.string.createOrder_radioButton_tea))){
            builderAddition.append(getString(R.string.createOrder_checkBox_sugar)).append(" ");
        }
        String optionOfDrink = " ";
        if(drink.equals(getString(R.string.createOrder_radioButton_tea))){
            optionOfDrink = spinnerTea.getSelectedItem().toString();
        }else{
            optionOfDrink = spinnerCoffee.getSelectedItem().toString();
        }
        String order = String.format(getString(R.string.order),name,password,drink, optionOfDrink);
        String additions;
        if(builderAddition.length() > 0){
            additions = getString(R.string.need_addition) + builderAddition.toString();
        }else{
            additions = " ";
        }
        String fullOrder = order + additions;
    }
}
