package com.example.apoorva.buildaburger;

import android.app.Activity;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    int count = 0;
    Burger myBurger;
    RadioButton wheat;
    RadioButton white;
    EditText bCount;
    TextView price, calories;
    Button calculate;
    int maxCount = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.patty);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.patties, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        white = (RadioButton) findViewById(R.id.white);
        wheat = (RadioButton) findViewById(R.id.wheat);
        bCount = (EditText) findViewById(R.id.count);
        price = (TextView) findViewById(R.id.price);
        calories = (TextView) findViewById(R.id.calories);
        calculate = (Button) findViewById(R.id.calculate);

        white.setOnClickListener(this);
        wheat.setOnClickListener(this);
        calculate.setOnClickListener(this);

        myBurger = new Burger(this);

    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.calculate){
            if((Integer.parseInt(bCount.getText().toString())) > maxCount) {
                bCount.setText("" + maxCount, TextView.BufferType.EDITABLE);
                Toast toast = Toast.makeText(this, "You can only select up to 10 burgers", Toast.LENGTH_SHORT);
                toast.show();
            }
            showOutput();
        }
        else if(v.getId() == R.id.white){
            myBurger.setBun(getResources().getString(R.string.White));
        }
        else if (v.getId() == R.id.wheat){
            myBurger.setBun(getResources().getString(R.string.Wheat));
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);
        myBurger.setPatty(item);
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void onToppingSelected(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            count++;
        }
        else{
            count--;
        }
        if (count>3){
            ((CheckBox) view).setChecked(false);
            count--;
            Toast toast = Toast.makeText(this, "You can only select up to 3 toppings", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            switch (view.getId()){
                case R.id.mushrooms:
                    if(checked) myBurger.addTopping(getResources().getString(R.string.Mushrooms));
                    else myBurger.removeTopping(getResources().getString(R.string.Mushrooms));
                    break;
                case R.id.lettuce:
                    if(checked) myBurger.addTopping(getResources().getString(R.string.Lettuce));
                    else myBurger.removeTopping(getResources().getString(R.string.Lettuce));
                    break;
                case R.id.tomato:
                    if(checked) myBurger.addTopping(getResources().getString(R.string.Tomatoes));
                    else myBurger.removeTopping(getResources().getString(R.string.Tomatoes));
                    break;
                case R.id.pickles:
                    if(checked) myBurger.addTopping(getResources().getString(R.string.Pickles));
                    else myBurger.removeTopping(getResources().getString(R.string.Pickles));
                    break;
                case R.id.mayo:
                    if(checked) myBurger.addTopping(getResources().getString(R.string.Mayo));
                    else myBurger.removeTopping(getResources().getString(R.string.Mayo));
                    break;
                case R.id.mustard:
                    if(checked) myBurger.addTopping(getResources().getString(R.string.Mustard));
                    else myBurger.removeTopping(getResources().getString(R.string.Mustard));
                    break;

            }
        }
    }

    public void showOutput(){
        int burgers = Integer.parseInt(bCount.getText().toString());
        if(burgers<1) burgers = 1;

        double cost = myBurger.getPrice()*burgers;
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        price.setText("" + formatter.format(cost));

        int cals = myBurger.getCalories()*burgers;
        calories.setText("" + cals + "kCal");
    }


}
