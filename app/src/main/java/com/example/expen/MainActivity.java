package com.example.expen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String CATEGORY = "category";
    public static final String ENTRY_TYPE = "entryType";

    Button expenseButton;
    Button incomeButton;
    PieChart ringChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseButton = findViewById(R.id.expense_tabbutton);
        incomeButton = findViewById(R.id.income_tabbutton);
        ringChart = findViewById(R.id.ring_chart);

        setupRingChart();
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

    public void setupRingChart() {
        List<PieEntry> entries = new ArrayList<>();
        List<Integer> colorEntries = new ArrayList<>();
        // SAMPLE ----
        entries.add(new PieEntry(.75f));
        entries.add(new PieEntry(.2f));
        colorEntries.add(ColorsUtil.PAYCHECK_CATEGORY_COLOR);
        colorEntries.add(ColorsUtil.ENTERTAINMENT_CATEGORY_COLOR);
        // ------------
        // Here, get all the percentages of the category totals and add PieEntry with float value; ex
        // Also, category colors must be retrieved and put into colorEntries; read below for more info
        // for each category
        //     entries.add(new PieEntry(CATEGORY_PERCENTAGE));
        //     colorEntries.add(CATEGORY_COLOR);
        //     ** i created a ColorsUtil class which contains every category color; to use -> ColorsUtil.AUTO_CATEGORY_COLOR

        PieDataSet dataSet = new PieDataSet(entries, "");
        int[] primitiveColorInts = new int[colorEntries.size()];
        for (int c = 0; c < primitiveColorInts.length; c++) {
            primitiveColorInts[c] = colorEntries.get(c).intValue();
        }
        dataSet.setColors(primitiveColorInts);
        dataSet.setDrawValues(false);

        PieData data = new PieData(dataSet);
        ringChart.setData(data);

        ringChart.getLegend().setEnabled(false);
        ringChart.setDescription(null);
        ringChart.setHoleRadius(75);
        ringChart.setDrawCenterText(false);
        ringChart.setDrawHoleEnabled(true);
        ringChart.setHoleColor(getResources().getColor(R.color.light_blue));
        ringChart.setDrawRoundedSlices(true);
    }

}