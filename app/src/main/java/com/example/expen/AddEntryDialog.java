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

        addExpense = view.findViewById(R.id.addExpenseTextView);
        addIncome = view.findViewById(R.id.addIncomeTextView);

        //go to choose category activity
        addExpense.setOnClickListener(view1 -> startActivity(new Intent(getContext(), ExpenseCategoryActivity.class)));

        addIncome.setOnClickListener(view2 -> startActivity(new Intent(getContext(), IncomeCategoryActivity.class)));

        return builder.create();
    }
}
