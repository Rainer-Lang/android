package com.common.example;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.common.utils.Common;
import com.common.utils.R;

public class DateDifferenceActivity extends Activity {


    TextView tvDate1;
    TextView tvDate2;
    TextView tvDifference;
    private DateDifferenceActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_difference);
        mContext = DateDifferenceActivity.this;
        init();

    }

    private void init() {

        tvDate1 = (TextView) findViewById(R.id.tv_date1);
        tvDate2 = (TextView) findViewById(R.id.tv_date2);
        tvDifference = (TextView) findViewById(R.id.tvDifference);

        findViewById(R.id.btn_date1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Common.showDatePickerDialog(mContext, "dd/MM/yyyy", tvDate1);

            }
        });

        findViewById(R.id.btn_date2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Common.showDatePickerDialog(mContext, "dd/MM/yyyy", tvDate2);

            }
        });


        findViewById(R.id.btn_calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tvDate1.getText().toString()) | TextUtils.isEmpty(tvDate2.getText().toString()))
                    Common.showAlertDialog(mContext, "", "Please select both dates.", true);
                else {
                    long diff = Common.calculateDays(Common.stringToDate(tvDate1.getText().toString(), "dd/MM/yyy"), Common.stringToDate(tvDate2.getText().toString(), "dd/MM/yyy"));
                    tvDifference.setText("Difference is " + diff + " days.");
                }
            }
        });
    }

}
