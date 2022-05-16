package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
public class ClothesActivity extends AppCompatActivity {
    List<Model> modelList;
    RecyclerView recyclerView;
    OrderAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);
        modelList = new ArrayList<>();
        modelList.add(new Model("Sockes", getString(R.string.Sockes), R.drawable.sockes ));
        modelList.add(new Model("Jumbers", getString(R.string.Jumber), R.drawable.blovers));
        modelList.add(new Model("coats", getString(R.string.coat), R.drawable.coat));
        modelList.add(new Model("Blouses", getString(R.string.Blouses), R.drawable.blouses));
        modelList.add(new Model("Tshirts", getString(R.string.tshirts), R.drawable.tshirts));
        modelList.add(new Model("Blouses AND Jeans", getString(R.string.BlouseandJeans), R.drawable.blousejeans));
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        mAdapter = new OrderAdapter(this, modelList);
        recyclerView.setAdapter(mAdapter);
    }
}