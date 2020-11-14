package com.example.expen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                break;
            case R.id.income_tabbutton:
                selected = new IncomeFragment();
        }

        if(selected != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected).commit();
        }
    }

}