package com.example.mr_shareone.materialdesign;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mr_shareone.materialdesign.bean.Fruit;

import java.util.List;

/**
 * Autor：created by MR-SHAREONE on 2018/6/4 11 19
 * Emain:13437105740@163.com
 */
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context context;
    private List<Fruit> fruits;
    private Onclick onclick;

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ImageView fruitImage;
        public TextView fruitName;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            fruitImage = itemView.findViewById(R.id.fruit_image);
            fruitName = itemView.findViewById(R.id.fruit_name);
        }
    }

    static interface Onclick {
        public void itemOnclicked(Fruit fruit);
    }

    ;


    public void setFruits(List<Fruit> fruits) {
        if (fruits != null) {
            this.fruits = fruits;
            notifyDataSetChanged();
        }
    }

    public FruitAdapter(Context context, List<Fruit> fruits) {
        this.context = context;
        this.fruits = fruits;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
        if (context != null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.fruit_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Fruit fruit = fruits.get(position);
        holder.fruitName.setText(fruit.getName());
        Glide.with(context).load(fruit.getImageId()).into(holder.fruitImage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onclick != null) {
                    onclick.itemOnclicked(fruit);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (fruits == null) {
            return 0;
        } else {
            return fruits.size();
        }
    }
}
