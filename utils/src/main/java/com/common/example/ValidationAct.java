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
import android.widget.Toast;

import com.common.utils.Common;
import com.common.utils.R;

public class ValidationAct extends Activity {
    EditText etEmpty;
    Button btnEmpty, btnEmail;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edt_validation_act);
        mContext = ValidationAct.this;
        init();
    }

    private void init() {
        etEmpty = (EditText) findViewById(R.id.etValidation);

        btnEmpty = (Button) findViewById(R.id.btnEmpty);
        btnEmail = (Button) findViewById(R.id.btnEmail);

        btnEmpty.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Common.isEmptyEditText(etEmpty))
                    Toast.makeText(mContext, "Edittext is empty.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(mContext, "Edittext is not empty.", Toast.LENGTH_SHORT).show();
            }
        });

        btnEmail.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (Common.isEmptyEditText(etEmpty)) {
                    Toast.makeText(mContext, "Please enter your email.", Toast.LENGTH_SHORT).show();
                } else {
                    if (Common
                            .isEmailIdValid(etEmpty.getText().toString())) {
                        Toast.makeText(mContext, "Your email is valid.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "Your email is not valid.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}
