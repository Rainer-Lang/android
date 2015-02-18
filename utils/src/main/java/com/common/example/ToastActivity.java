package com.common.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.common.utils.Common;
import com.common.utils.R;

public class ToastActivity extends Activity {

    EditText et_Url;
    Button btnOpenUrl;
    private ToastActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        mContext = ToastActivity.this;
        init();

    }

    private void init() {

        et_Url = (EditText) findViewById(R.id.et_address);
        btnOpenUrl = (Button) findViewById(R.id.btnOpenMap);

        btnOpenUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogOrToast(true);
            }
        });

        findViewById(R.id.btnOpenDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogOrToast(false);
            }
        });

    }

    private void showDialogOrToast(boolean toast) {
        if (Common.isEmptyEditText(et_Url)) {
            Common.showAlertDialog(mContext, getString(R.string.app_name), "Hello world!", toast);
        } else {
            Common.showAlertDialog(mContext, getString(R.string.app_name), et_Url.getText().toString(), toast);
        }
    }

}
