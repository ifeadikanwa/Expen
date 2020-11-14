package com.example.expen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expen.model_classes.Categories;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class ExpenseAdapter extends FirestoreRecyclerAdapter<Categories, ExpenseAdapter.ExpenseHolder> {
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ExpenseAdapter (FirestoreRecyclerOptions<Categories> options){
        super(options);
    }

    class ExpenseHolder extends RecyclerView.ViewHolder{
        ImageView categoryIcon;
        TextView categoryName;
        TextView budgeted;
        TextView spent;
        TextView remaining;

        public ExpenseHolder(@NonNull View itemView) {
            super(itemView);

            categoryIcon = itemView.findViewById(R.id.exp_category_img);
            categoryName = itemView.findViewById(R.id.exp_category_txt);
            budgeted = itemView.findViewById(R.id.budget_txt);
            spent = itemView.findViewById(R.id.spent_txt);
            remaining = itemView.findViewById(R.id.remaining_txt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });

        }
    }

    @Override
    protected void onBindViewHolder(@NonNull ExpenseAdapter.ExpenseHolder holder, int position, @NonNull Categories model) {

        holder.budgeted.setText(model.getCategoryBudget());

        if(model.getCategorySpent() == null){
            holder.spent.setText("0");
        }
        else{
            holder.spent.setText(model.getCategorySpent());
        }

        if(model.getCategoryRemaining() == null){
            holder.remaining.setText("0");
        }
        else{
            holder.remaining.setText(model.getCategoryRemaining());
        }

        holder.categoryName.setText(model.getCategoryName());

        switch(model.getCategoryName()){
            case "Auto":
                holder.categoryIcon.setImageResource(R.drawable.ic_auto_category);
                break;
            case "Beauty":
                holder.categoryIcon.setImageResource(R.drawable.ic_beauty_category);
                break;
            case "Clothing":
                holder.categoryIcon.setImageResource(R.drawable.ic_clothing_category);
                break;
            case "Entertainment":
                holder.categoryIcon.setImageResource(R.drawable.ic_entertainment_category);
                break;
            case "Financial":
                holder.categoryIcon.setImageResource(R.drawable.ic_financial_category);
                break;
            case "General":
                holder.categoryIcon.setImageResource(R.drawable.ic_general_category);
                break;
            case "Groceries":
                holder.categoryIcon.setImageResource(R.drawable.ic_groceries_category);
                break;
            case "Home":
                holder.categoryIcon.setImageResource(R.drawable.ic_home_category);
                break;
            case "Industry":
                holder.categoryIcon.setImageResource(R.drawable.ic_industry_category);
                break;
            case "Learning":
                holder.categoryIcon.setImageResource(R.drawable.ic_learning_category);
                break;
                case "Medical":
                holder.categoryIcon.setImageResource(R.drawable.ic_medical_category);
                break;
            case "Nightlife":
                holder.categoryIcon.setImageResource(R.drawable.ic_nightlife_category);
                break;
            case "Restaurant":
                holder.categoryIcon.setImageResource(R.drawable.ic_restaurants_category);
                break;
            case "Services":
                holder.categoryIcon.setImageResource(R.drawable.ic_services_category);
                break;
            case "Shop":
                holder.categoryIcon.setImageResource(R.drawable.ic_shop_category);
                break;
            case "Spiritual":
                holder.categoryIcon.setImageResource(R.drawable.ic_spiritual_category);
                break;
            case "Sports":
                holder.categoryIcon.setImageResource(R.drawable.ic_sports_category);
                break;
            case "Transport":
                holder.categoryIcon.setImageResource(R.drawable.ic_transport_category);
                break;
            case "Travel":
                holder.categoryIcon.setImageResource(R.drawable.ic_travel_category);
                break;

        }
    }

    @NonNull
    @Override
    public ExpenseAdapter.ExpenseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_item_layout, parent, false);
        return new ExpenseHolder(v);
    }
}
