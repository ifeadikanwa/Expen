package com.example.expen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IncomeCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_category);
    }

    //when a category is clicked go to AddEntry activity with the name of the category and the type of entry that was clicked
    public void onIncomeCategoryClick(View view){
        Intent intent = new Intent(IncomeCategoryActivity.this, AddEntryActivity.class);
        intent.putExtra(MainActivity.ENTRY_TYPE, "income");
        intent.putExtra(MainActivity.CATEGORY, view.getContentDescription().toString());
        startActivity(intent);
        finish();
    }
}