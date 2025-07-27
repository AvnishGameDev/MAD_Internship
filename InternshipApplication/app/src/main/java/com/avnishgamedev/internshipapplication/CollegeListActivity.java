package com.avnishgamedev.internshipapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CollegeListActivity extends AppCompatActivity {

    List<CollegeListItem> colleges;
    CollegeListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collegelist);

        EditText edtCollegeName = findViewById(R.id.EDT_CollegeName);
        EditText edtCollegeLocation = findViewById(R.id.EDT_CollegeLocation);
        Button btnAddCollege = findViewById(R.id.BTN_AddCollege);
        RecyclerView rvCollegeList = findViewById(R.id.RV_CollegeList);

        colleges = new ArrayList<CollegeListItem>();
        colleges.add(new CollegeListItem("IIT Bombay", "Mumbai"));
        colleges.add(new CollegeListItem("VJTI", "Mumbai"));
        colleges.add(new CollegeListItem("VIT", "Vellore"));
        colleges.add(new CollegeListItem("VVP Polytechnic", "Solapur"));
        adapter = new CollegeListAdapter(this, colleges);

        btnAddCollege.setOnClickListener(v -> {
            if (edtCollegeName.getText().toString().isEmpty() || edtCollegeLocation.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill College name and location!", Toast.LENGTH_SHORT).show();
                return;
            }
            colleges.add(new CollegeListItem(edtCollegeName.getText().toString(), edtCollegeLocation.getText().toString()));
            adapter.notifyItemInserted(colleges.size() - 1);
            edtCollegeName.setText("");
            edtCollegeLocation.setText("");
        });

        rvCollegeList.setLayoutManager(new LinearLayoutManager(this));
        rvCollegeList.setAdapter(adapter);
    }
}
