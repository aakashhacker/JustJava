package com.example.ganesh.justjava;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You Cannot Have More Than 100 Coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You Cannot Have Less Than 1 Coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        Log.v("MainActivity", "Name :" + name);

        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        Log.v("MainActivity", "Has Whipped Cream :" + hasWhippedCream);

        CheckBox choclateCheckbox = (CheckBox) findViewById(R.id.choclate_checkbox);
        boolean haschoclate = choclateCheckbox.isChecked();
        Log.v("MainActivity", "Has Choclate :" + haschoclate);

        int price = calculatePrice(hasWhippedCream, haschoclate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, haschoclate);
        displayMessage(priceMessage);
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChoclate) {
        int basePrice = 5;

        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }
        if (addChoclate) {
            basePrice = basePrice + 2;
        }
        return quantity * basePrice;
    }

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChoclate) {
        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage += "\n\n" + getString(R.string.toppingsa);
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream);
        priceMessage += "\n" + getString(R.string.order_summary_choclate, addChoclate);
        priceMessage += "\n\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage += "\n\n" + getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(price));
        priceMessage += "\n\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    private void displayQuantity(int numberOfCOffees) {
        TextView quantityTextview = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextview.setText("" + numberOfCOffees);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}
