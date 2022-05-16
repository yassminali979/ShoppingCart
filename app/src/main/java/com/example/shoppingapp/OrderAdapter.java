package com.example.shoppingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    List<Model> modelList;
    Context context;

    public OrderAdapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        String name = modelList.get(position).getmDrinkName();
        String description = modelList.get(position).getmDrinkDetail();
        int images = modelList.get(position).getmDrinkPhoto();
        holder.clothesname.setText(name);
        holder.description.setText(description);
        holder.imageView.setImageResource(images);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView clothesname, description;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            clothesname = itemView.findViewById(R.id.coffeeName);
            description = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.coffeeImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position == 0) {
               Intent intent = new Intent(context, ClothesActivity.class);
               context.startActivity(intent);
            }
            if (position == 1) {
               Intent intent2 = new Intent(context, JumberActivity.class);
               context.startActivity(intent2);
            }
            if (position == 2) {
                Intent intent3 = new Intent(context, CoatActivity.class);
                context.startActivity(intent3);
            }
            if (position == 3) {
                Intent intent4 = new Intent(context, BlousesActivity.class);
                context.startActivity(intent4);
            }
            if (position == 4) {
                Intent intent4 = new Intent(context, TshirtsActivity.class);
                context.startActivity(intent4);
            }

            if (position == 5) {
                Intent intent4 = new Intent(context, BlouesandJeansActivity.class);
                context.startActivity(intent4);
            }

        }
    }
}
