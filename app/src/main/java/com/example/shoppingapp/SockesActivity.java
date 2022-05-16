package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.Database.OrderContract;

public class SockesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    ImageView imageView;
    ImageButton plus, minus;
    TextView quantitynumber, clothesname, clothesprice;
    Button addtoCart;
    int quantity;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sockes);
        imageView = findViewById(R.id.imageViewInfo);
        plus = findViewById(R.id.addquantity);
        minus = findViewById(R.id.subquantity);
        quantitynumber = findViewById(R.id.quantity);
        clothesname = findViewById(R.id.drinkNameinInfo);
        clothesprice = findViewById(R.id.coffeePrice);
        addtoCart = findViewById(R.id.addtocart);
        clothesname.setText("Sockes");

        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SockesActivity.this, SummaryActivity.class);
                startActivity(intent);
                Cart();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Price = 100;
                quantity++;
                displayQuantity();
                int price = Price * quantity;
                String newPrice = String.valueOf(price);
                clothesprice.setText(newPrice);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int Price = 100;
                if (quantity == 0) {
                    Toast.makeText(SockesActivity.this, "Cant decrease quantity < 0", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity();
                    int price = Price * quantity;
                    String newPrice = String.valueOf(price);
                    clothesprice.setText(newPrice);
                }
            }
        });

    }
    private boolean Cart() {
        String name = clothesname.getText().toString();
        String price = clothesprice.getText().toString();
        String quantity = quantitynumber.getText().toString();
        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderEntry.NAME, name);
        values.put(OrderContract.OrderEntry.PRICE, price);
        values.put(OrderContract.OrderEntry.QUANTITY, quantity);
        if (mCurrentCartUri == null) {
            Uri newUri = getContentResolver().insert(OrderContract.OrderEntry.CONTENT_URI, values);
            if (newUri==null) {
                Toast.makeText(this, "Failed to add to Cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Success  adding to Cart", Toast.LENGTH_SHORT).show();

            }
        }
        hasAllRequiredValues = true;
        return hasAllRequiredValues;

    }

    private void displayQuantity() {
        quantitynumber.setText(String.valueOf(quantity));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.NAME,
                OrderContract.OrderEntry.PRICE,
                OrderContract.OrderEntry.QUANTITY
        };

        return new CursorLoader(this, mCurrentCartUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {

            int name = cursor.getColumnIndex(OrderContract.OrderEntry.NAME);
            int price = cursor.getColumnIndex(OrderContract.OrderEntry.PRICE);
            int quantity = cursor.getColumnIndex(OrderContract.OrderEntry.QUANTITY);
            String nameclothes = cursor.getString(name);
            String priceclothes = cursor.getString(price);
            String quantityclothes = cursor.getString(quantity);
            clothesname.setText(nameclothes);
            clothesprice.setText(priceclothes);
            quantitynumber.setText(quantityclothes);
        }
    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        clothesname.setText("");
        clothesprice.setText("");
        quantitynumber.setText("");

    }
}