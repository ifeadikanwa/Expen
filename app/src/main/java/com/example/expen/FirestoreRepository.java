package com.example.expen;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.expen.model_classes.Categories;
import com.example.expen.model_classes.Entry;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class FirestoreRepository {
    public static final String TAG = FirestoreRepository.class.getSimpleName();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final CollectionReference categoriesRef = db.collection("categories");
    public static final String ENTRIES = "entries";
    public static final String CATEGORY_NAME_FIELD = "categoryName";
    public static final String IS_EXPENSE_FIELD = "expense";
    public static final String ENTRY_DATE_FIELD = "entryDate";

    public void createCategoryAndAddEntry(String categoryName, String categoryBudget, boolean hasContent, boolean isExpense,  String entryDescription, Double entryAmount, Date entryDate ){
        Categories category = new Categories(categoryName, categoryBudget, hasContent, isExpense);
        categoriesRef.document(categoryName)
                .set(category)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i(TAG, "Category added");
                        //add the entry to the created category
                        addEntry(categoryName, entryDescription, entryAmount, entryDate);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, e.toString());
                    }
                });

    }
    public void addEntry(String categoryName, String entryDescription, Double entryAmount, Date entryDate){
        Entry entry = new Entry(entryDescription, entryAmount, entryDate);
        categoriesRef.document(categoryName)
                .collection(ENTRIES)
                .add(entry)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i(TAG, "Entry added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, e.toString());
                    }
                });
    }

}
