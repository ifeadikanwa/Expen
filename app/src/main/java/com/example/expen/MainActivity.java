package com.example.expen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String CATEGORY = "category";
    public static final String ENTRY_TYPE = "entryType";
    public static final String BUDGET_VALUE = "budgetValue";

    Button expenseButton;
    Button incomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseButton = findViewById(R.id.expense_tabbutton);
        incomeButton = findViewById(R.id.income_tabbutton);

        expenseButton.setBackground(getResources().getDrawable(R.drawable.tab_line));

        Fragment theDefault = new ExpenseFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, theDefault).commit();

    }

    //inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
                //todo: open dialog to add income or expense
            case R.id.add_action_mainact:
                AddEntryDialog dialog = new AddEntryDialog();
                dialog.show(getSupportFragmentManager(), "Add Entry Dialog");
                return true;
                //todo: open shopping session activity
            case R.id.shopping_session_action_mainact:
                startActivity(new Intent(MainActivity.this, ShoppingSessionActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void SwitchFragments(View view){
        Fragment selected = null;
        switch(view.getId()){
            case R.id.expense_tabbutton:
                selected = new ExpenseFragment();
                view.setBackground(getResources().getDrawable(R.drawable.tab_line));
                incomeButton.setBackground(getResources().getDrawable(android.R.color.transparent));
                break;
            case R.id.income_tabbutton:
                selected = new IncomeFragment();
                view.setBackground(getResources().getDrawable(R.drawable.tab_line));
                expenseButton.setBackground(getResources().getDrawable(android.R.color.transparent));
                break;
        }

        if(selected != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected).commit();
        }
    }

}