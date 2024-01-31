package com.example.d308_mobile_application_development_android.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.d308_mobile_application_development_android.R;
import com.example.d308_mobile_application_development_android.Database.Repository;
import com.example.d308_mobile_application_development_android.R;
import com.example.d308_mobile_application_development_android.entities.Excursion;
import com.example.d308_mobile_application_development_android.entities.Vacation;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReportPage extends AppCompatActivity {

    private Repository repository;
    private TableLayout tableLayout;
    private TableLayout tableLayout2;
    private SearchView searchView;
    String query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_page);

        searchView = findViewById(R.id.searchView);
        tableLayout = findViewById(R.id.tablesearchresults);
        tableLayout2 = findViewById(R.id.tablesearchresults2);
        repository = new Repository(getApplication());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        }) ;

    }

    private void performSearch(String query) {
        tableLayout.removeAllViews();
        tableLayout2.removeAllViews();

        // Add header row for vacations table
        addHeaderRow(tableLayout, "ID", "Title", "Price", "Hotel", "Start Date", "End Date");

        List<Vacation> vacations = new ArrayList<>();
        List<Integer> vacationIds = new ArrayList<>();
        for (Vacation v : repository.getAllVacations()) {
            if (v.getVacationName().toLowerCase().equals(query.toLowerCase())) {
                vacations.add(v);
                vacationIds.add(v.getVacationID());
            }
        }

        for (Vacation vacation : vacations) {
            TableRow row = new TableRow(this);
            addStyledTextView(row, String.valueOf(vacation.getVacationID()));
            addStyledTextView(row, vacation.getVacationName());
            addStyledTextView(row, String.format(Locale.getDefault(), "%.2f", vacation.getPrice()));
            addStyledTextView(row, vacation.getHotel());
            addStyledTextView(row, vacation.getStartVacationDate());
            addStyledTextView(row, vacation.getEndVacationDate());
            tableLayout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            addDivider(tableLayout);
        }
        performExcursionSearch(vacationIds);
    }

    private void addExcursionRow(Excursion excursion) {
        TableRow row = new TableRow(this);
        addStyledTextView(row, String.valueOf(excursion.getVacationID()));
        addStyledTextView(row, String.valueOf(excursion.getExcursionID()));
        addStyledTextView(row, excursion.getExcursionName());
        addStyledTextView(row, String.format(Locale.getDefault(), "%.2f", excursion.getPrice()));
        addStyledTextView(row, excursion.getExcursionDate());
        tableLayout2.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        addDivider(tableLayout2);
    }

    private void addStyledTextView(TableRow row, String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(10, 10, 10, 10);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textView.setTextColor(Color.BLACK);
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        textView.setLayoutParams(params);
        row.addView(textView);
    }

    private void addDivider(TableLayout tableLayout) {
        View divider = new View(this);
        divider.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        divider.setBackgroundColor(Color.GRAY);
        tableLayout.addView(divider);
    }

    private void addHeaderRow(TableLayout tableLayout, String... headers) {
        TableRow headerRow = new TableRow(this);
        for (String header : headers) {
            addStyledTextView(headerRow, header);
        }
        tableLayout.addView(headerRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        addDivider(tableLayout);
    }

    private void performExcursionSearch(List<Integer> vacationIds) {
        // Add header row for excursions table
        addHeaderRow(tableLayout2, "Vacation ID", "Excursion ID", "Title", "Price", "Date");

        List<Excursion> excursions = repository.getAllExcursions();
        for (Excursion excursion : excursions) {
            if (vacationIds.contains(excursion.getVacationID())) {
                addExcursionRow(excursion);
                addDivider(tableLayout2);
            }
        }
    }


}