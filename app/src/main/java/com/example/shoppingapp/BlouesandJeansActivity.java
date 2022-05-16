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

public class BlouesandJeansActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    ImageView imageView;
    ImageButton plusquantity, minusquantity;
    TextView quantitynumber, clothesname, clothesprice;
    Button addtoCart;
    int quantity;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blouesand_jeans);

        imageView = findViewById(R.id.imageViewInfo);
        plusquantity = findViewById(R.id.addquantity);
        minusquantity  = findViewById(R.id.subquantity);
        quantitynumber = findViewById(R.id.quantity);
        clothesname = findViewById(R.id.drinkNameinInfo);
        clothesprice = findViewById(R.id.coffeePrice);
        addtoCart = findViewById(R.id.addtocart);
        clothesname.setText("Blouses And Jeans");
        imageView.setImageResource(R.drawable.blousejeans);

        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BlouesandJeansActivity.this, SummaryActivity.class);
                startActivity(intent);
                SaveCart();
            }
        });

        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int basePrice = 5;
                quantity++;
                displayQuantity();
                int Price = basePrice * quantity;
                String setnewPrice = String.valueOf(Price);
                clothesprice.setText(setnewPrice);
            }
        });

        minusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int basePrice = 100;
                if (quantity == 0) {
                    Toast.makeText(BlouesandJeansActivity.this, "Cant decrease quantity < 0", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity();
                    int coffePrice = basePrice * quantity;
                    String setnewPrice = String.valueOf(coffePrice);
                    clothesprice.setText(setnewPrice);
                }
            }
        });



    }

    private boolean SaveCart() {
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
            String name1 = cursor.getString(name);
            String price1 = cursor.getString(price);
            String quantity1 = cursor.getString(quantity);
            clothesname.setText(name1);
            clothesprice.setText(price1);
            quantitynumber.setText(quantity1);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        clothesname.setText("");
        clothesprice.setText("");
        quantitynumber.setText("");
    }
}