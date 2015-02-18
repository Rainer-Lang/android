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
import android.widget.EditText;

import com.common.utils.Common;
import com.common.utils.R;

public class UrlValidationRippleActivity extends Activity {
    EditText etEmpty;
    Button btnEmail;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_ripple);
        mContext = UrlValidationRippleActivity.this;
        init();
    }

    private void init() {
        etEmpty = (EditText) findViewById(R.id.etValidation);
        btnEmail = (Button) findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Common.isEmptyEditText(etEmpty)) {
                    Common.showAlertDialog(mContext, getString(R.string.app_name), "Please enter URL.", false);
                } else {
                    if (Common
                            .isWebsiteUrlValid(etEmpty.getText().toString())) {
                        Common.showAlertDialog(mContext, getString(R.string.app_name), "URL is valid.", false);
                    } else {
                        Common.showAlertDialog(mContext, getString(R.string.app_name), "URL is not valid.", false);
                    }
                }

            }
        });

    }
}
