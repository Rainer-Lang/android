package com.common.example;
/**
 * @author Y@$!n
 *
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.common.utils.Common;
import com.common.utils.R;

public class ScreenModeDis_Ean_Act extends Activity {
    Context mContext;
    Button btnDisable, btnEnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_mode_dis_ean_act);
        mContext = ScreenModeDis_Ean_Act.this;
        init();

    }

    private void init() {
        btnDisable = (Button) findViewById(R.id.btnDisable);
        btnEnable = (Button) findViewById(R.id.btnEnable);
        btnDisable.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Common.disableSleepMode(mContext);
                Common.showAlertDialog(mContext, getString(R.string.app_name), "Screen sleep mode disabled.", false);
                btnEnable.setEnabled(true);
                btnDisable.setEnabled(false);
            }
        });

        btnEnable.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Common.enableSleepMode();
                Common.showAlertDialog(mContext, getString(R.string.app_name), "Screen sleep mode enabled.", false);
                btnEnable.setEnabled(false);
                btnDisable.setEnabled(true);
            }
        });

    }
}
