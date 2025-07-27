package com.avnishgamedev.healthtrackerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class LogFragment extends Fragment {

    EditText etSymptom;
    Button btnSave;
    RecyclerView rvSymptoms;

    DBHelper db;
    SymptomsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_log, container, false);

        etSymptom = v.findViewById(R.id.etSymptom);

        btnSave = v.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(view -> {
            if (db.addSymptom(etSymptom.getText().toString())) {
                Toast.makeText(getContext(), "Symptom added", Toast.LENGTH_SHORT).show();
                adapter.notifyItemInserted(db.getSymptoms().size() - 1);
                etSymptom.setText("");
            } else {
                Toast.makeText(getContext(), "Failed to add symptom", Toast.LENGTH_SHORT).show();
            }
        });

        rvSymptoms = v.findViewById(R.id.rvSymptoms);

        db = new DBHelper(getContext());

        adapter = new SymptomsAdapter(db.getSymptoms());
        rvSymptoms.setAdapter(adapter);

        return v;
    }
}
