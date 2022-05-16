package com.example.shoppingapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.shoppingapp.Database.OrderContract;

public class CartAdapter extends CursorAdapter {
    public CartAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cartlist, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView clothesname, price, quantity;
        clothesname = view.findViewById(R.id.drinkNameinOrderSummary);
        price = view.findViewById(R.id.priceinOrderSummary);
        quantity = view.findViewById(R.id.quantityinOrderSummary);
        int name = cursor.getColumnIndex(OrderContract.OrderEntry.NAME);
        int priceclothes = cursor.getColumnIndex(OrderContract.OrderEntry.PRICE);
        int quantityclothes = cursor.getColumnIndex(OrderContract.OrderEntry.QUANTITY);
        String namecloth = cursor.getString(name);
        String pricescloth = cursor.getString(priceclothes);
        String quantitycloth = cursor.getString(quantityclothes);
        clothesname.setText(namecloth);
        price.setText(pricescloth);
        quantity.setText(quantitycloth);

    }
}
