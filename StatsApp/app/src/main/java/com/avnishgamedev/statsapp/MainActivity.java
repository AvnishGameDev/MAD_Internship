package com.avnishgamedev.statsapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BarChart barChart;
    PieChart pieChart;
    RadarChart radarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        barChart = findViewById(R.id.barchart);
        setupBarChart();

        pieChart = findViewById(R.id.piechart);
        setupPieChart();

        radarChart = findViewById(R.id.radarchart);
        setupRadarChart();
    }

    private void setupRadarChart() {
        ArrayList<RadarEntry> entries = new ArrayList<>();
        entries.add(new RadarEntry(40));
        entries.add(new RadarEntry(25));
        entries.add(new RadarEntry(10));
        entries.add(new RadarEntry(20));

        RadarDataSet dataSet = new RadarDataSet(entries, "Data");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        RadarData data = new RadarData(dataSet);
        radarChart.setData(data);
        radarChart.invalidate();
    }

    private void setupPieChart() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(40, "Data 1"));
        entries.add(new PieEntry(25, "Data 2"));
        entries.add(new PieEntry(10, "Data 3"));
        entries.add(new PieEntry(25, "Data 4"));

        PieDataSet dataSet = new PieDataSet(entries, "Data");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.animateY(1000);
        pieChart.getDescription().setText("Assessment Distribution");
        pieChart.invalidate();
    }

    private void setupBarChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 85));
        entries.add(new BarEntry(1, 95));

        BarDataSet dataSet = new BarDataSet(entries, "Data");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        List<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);
        BarData data = new BarData(dataSets);
        barChart.setData(data);
        barChart.animateY(1000);
        barChart.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_support) {
            // TODO: Handle
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}