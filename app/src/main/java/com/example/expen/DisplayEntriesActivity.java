package com.example.expen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.expen.model_classes.Categories;
import com.example.expen.model_classes.Entry;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

public class DisplayEntriesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EntriesAdapter adapter;
    FirestoreRepository firestoreRepository;
    String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_entries);

        getSupportActionBar().setTitle("");

        TextView categoryTxtView = findViewById(R.id.entries_category_name);
        firestoreRepository = new FirestoreRepository();
        recyclerView = findViewById(R.id.entries_recyclerview);

        Intent intent = getIntent();
        categoryName = intent.getStringExtra(MainActivity.CATEGORY);
        categoryTxtView.setText(categoryName);

        setupRecyclerview();
    }

    private void setupRecyclerview() {
        Query query = firestoreRepository.categoriesRef
                .document(categoryName)
                .collection(FirestoreRepository.ENTRIES)
                .orderBy(FirestoreRepository.ENTRY_DATE_FIELD, Query.Direction.DESCENDING);


        FirestoreRecyclerOptions<Entry> options = new FirestoreRecyclerOptions.Builder<Entry>()
                .setQuery(query, Entry.class)
                .build();

        adapter = new EntriesAdapter(options);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        LinearLayoutManager verticalLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(verticalLayout);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}