package com.prm.mobile;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.mobile.context.AppContentProvider;
import com.prm.mobile.view.Item;
import com.prm.mobile.view.ListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class CrudBasicActivity extends AppCompatActivity {

    private EditText studentNameEditText;
    private EditText studentIdEditText;
    private EditText studentScoreEditText;

    private List<Item> getItemsFromContentProvider() {
        List<Item> itemList = new ArrayList<>();

        String[] selectColumns = {"id", "name", "score"};
        Cursor cursor = getContentResolver().query(AppContentProvider.CONTENT_URI, selectColumns, null, null, null);

        if (cursor == null) {
            return itemList;
        }

        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            Item item = new Item(id, name, age);
            itemList.add(item);
        }
        cursor.close();
        return itemList;
    }

    private void initRecyclerView (){
        RecyclerView recyclerView = findViewById(R.id.listItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Item> itemList = getItemsFromContentProvider();
        ListRecyclerViewAdapter listRecyclerViewAdapter = ListRecyclerViewAdapter.getInstance();
        listRecyclerViewAdapter.setItemList(itemList);
        recyclerView.setAdapter(listRecyclerViewAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_basic);
        studentNameEditText = findViewById(R.id.studentName);
        studentScoreEditText = findViewById(R.id.studentScore);
        studentIdEditText = findViewById(R.id.studentId);
        initRecyclerView();
    }

    public void addStudent(View view) {
        try {
            String studentName = studentNameEditText.getText().toString().trim();
            String studentId = studentIdEditText.getText().toString().trim();
            float studentScore = Float.parseFloat(studentScoreEditText.getText().toString().trim());

            ContentValues student = new ContentValues();
            student.put("name", studentName);
            student.put("id", studentId);
            student.put("score", studentScore);

            getContentResolver().insert(AppContentProvider.CONTENT_URI, student);
            ListRecyclerViewAdapter.getInstance().setItemList(getItemsFromContentProvider());
        } catch (Exception exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}