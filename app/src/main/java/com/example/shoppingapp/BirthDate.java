package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BirthDate extends AppCompatActivity {
    Button btn_birth, btn_today, btn_calculate,f;
    TextView tvResult;
    DatePickerDialog.OnDateSetListener dateSetListener1, dateSetListener2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth_date);
        btn_birth = findViewById(R.id.bt_birth);
        btn_today = findViewById(R.id.bt_today);
        btn_calculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);
        f=findViewById(R.id.finish);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(Calendar.getInstance().getTime());
        btn_today.setText(date);
        btn_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(BirthDate.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener1, year, month, day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BirthDate.this,Login.class ));
                Toast.makeText(BirthDate.this,"Registerd successfully",Toast.LENGTH_SHORT).show();
            }
        });

        dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                btn_birth.setText(date);
            }
        };

        btn_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(BirthDate.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener2, year, month, day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                btn_today.setText(date);
            }
        };
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // converting the inputted date to string
                String sDate = btn_birth.getText().toString();
                String eDate = btn_today.getText().toString();
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date date1 = simpleDateFormat1.parse(sDate);
                    Date date2 = simpleDateFormat1.parse(eDate);
                    long startdate = date1.getTime();
                    long endDate = date2.getTime();
                    if (startdate <= endDate) {
                        org.joda.time.Period period = new Period(startdate, endDate, PeriodType.yearMonthDay());
                        int years = period.getYears();
                        int months = period.getMonths();
                        int days = period.getDays();
                        tvResult.setText(years + " Years |" + months + "Months |" + days + "Days");
                    } else {
                        Toast.makeText(BirthDate.this, "BirthDate should not be larger then today's date!", Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}