package com.example.expen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;



public class AddEntryDialog extends AppCompatDialogFragment {
    TextView addExpense;
    TextView addIncome;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //build alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_entry_dialog, null);
        builder.setView(view);

        addExpense = view.findViewById(R.id.addExpenseTextView);
        addIncome = view.findViewById(R.id.addIncomeTextView);

        //go to choose expense category activity
        addExpense.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(), ExpenseCategoryActivity.class));
            dismiss();
        });

        //go to choose income category activity
        addIncome.setOnClickListener(view2 -> {
            startActivity(new Intent(getContext(), IncomeCategoryActivity.class));
            dismiss();
        });

        return builder.create();
    }
}
