package com.example.expen;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expen.model_classes.Categories;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class IncomeAdapter extends FirestoreRecyclerAdapter<Categories, IncomeAdapter.IncomeHolder> {

    public IncomeAdapter (FirestoreRecyclerOptions<Categories> options){
        super(options);
    }

    class IncomeHolder extends RecyclerView.ViewHolder{
        CardView categoryIcon;
        TextView categoryName;
        TextView percentage;
        TextView amount;

        public IncomeHolder(@NonNull View itemView) {
            super(itemView);

            categoryIcon = itemView.findViewById(R.id.inc_category_img);
            categoryName = itemView.findViewById(R.id.inc_category_txt);
            percentage = itemView.findViewById(R.id.percentage_txt);
            amount = itemView.findViewById(R.id.inc_amount_txt);

        }
    }

    @Override
    protected void onBindViewHolder(@NonNull IncomeAdapter.IncomeHolder holder, int position, @NonNull Categories model) {

        holder.categoryName.setText(model.getCategoryName());

        if(model.getCategoryPercentage() == null){
            holder.percentage.setText("0%");
        }
        else{
            holder.percentage.setText(model.getCategorySpent() + "%");
        }

        if(model.getCategoryAmount() == null){
            holder.amount.setText("0");
        }
        else{
            holder.amount.setText(model.getCategorySpent());
        }



        switch(model.getCategoryName()){
            case "Other":
                holder.categoryIcon.setCardBackgroundColor(Color.MAGENTA);
                break;
            case "Paycheck":
                holder.categoryIcon.setCardBackgroundColor(Color.BLUE);
                break;
            case "Gift":
                holder.categoryIcon.setCardBackgroundColor(Color.RED);
                break;
            case "Interest":
                holder.categoryIcon.setCardBackgroundColor(Color.GREEN);
                break;

        }
    }

    @NonNull
    @Override
    public IncomeAdapter.IncomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_item_layout, parent, false);
        return new IncomeAdapter.IncomeHolder(v);
    }
}
