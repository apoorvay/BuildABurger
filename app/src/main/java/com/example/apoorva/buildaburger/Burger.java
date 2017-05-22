package com.example.apoorva.buildaburger;

/**
 * Created by Apoorva on 9/18/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Burger{

    private final Context mContext;

    private String myPatty;
    private String myBun;
    private boolean[] myToppings = {false, false, false, false, false, false};
    double myPatty_cost, myToppings_cost=0;
    private final int myBun_cost = 1;
    private int myPatty_cal, myBun_cal=140, myToppings_cal;

    private String []patties, toppings, buns;
    private int[] patty_cal, topping_cal, bun_cal;

    private double[] patty_cost ={5.5,5,5,7.5,4.5};
    private double[] topping_cost = {1,0.3,0.3,0.5,0,0};

    public Burger(Context context) {
        mContext = context;
        patties = mContext.getResources().getStringArray(R.array.patties);
        toppings = mContext.getResources().getStringArray(R.array.toppings);
        buns = mContext.getResources().getStringArray(R.array.Buns);
        patty_cal = mContext.getResources().getIntArray(R.array.patty_calories);
        topping_cal = mContext.getResources().getIntArray(R.array.topping_calories);
        bun_cal = mContext.getResources().getIntArray(R.array.bun_calories);
    }

    public void setPatty(String patty){
        myPatty = patty;
        for(int x =0; x < patties.length; x++){
            if (myPatty.equals(patties[x])){
                myPatty_cost = patty_cost[x];
                myPatty_cal = patty_cal[x];
            }
        }
    }
    public void setBun(String bun){
        myBun = bun;
        for(int x =0; x < buns.length; x++){
            if (myBun.equals(buns[x])){
                myBun_cal = bun_cal[x];
            }
        }
    }

    public void addTopping(String topping){
        for (int  x= 0; x < toppings.length; x++){
            if (topping.equals(toppings[x])){
                myToppings[x] = true;
                myToppings_cost = myToppings_cost + topping_cost[x];
                myToppings_cal = myToppings_cal + topping_cal[x];
            }
        }
    }

    public void removeTopping(String topping){
        for (int  x= 0; x < toppings.length; x++){
            if (topping.equals(toppings[x])){
                myToppings[x] = false;
                myToppings_cost = myToppings_cost - topping_cost[x];
                myToppings_cal = myToppings_cal - topping_cal[x];
            }
        }
    }

    public double getPrice(){
        /*myToppings_cost = 0;
        for (int x = 0; x < myToppings.length; x++){
            if (myToppings[x]){
                myToppings_cost += topping_cost[x];
            }
        }*/
        return myBun_cost + myPatty_cost + myToppings_cost;
    }

    public int getCalories(){
       /* myToppings_cal = 0;
        for (int x = 0; x < myToppings.length; x++){
            if (myToppings[x]){
                myToppings_cal += topping_cal[x];
            }
        }*/
        return myBun_cal + myPatty_cal + myToppings_cal;
    }
}
