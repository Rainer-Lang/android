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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.common.utils.Common;
import com.common.utils.R;

public class SetPrefAct extends Activity {
    Context mContext;
    CheckBox cbBoolean;
    EditText etStringPref, etIntegerPref, etLongPref, etFloatPref;
    Button btnSetPref;
    Boolean isCbSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_pref_act);
        mContext = SetPrefAct.this;

        init();
    }

    private void init() {
        cbBoolean = (CheckBox) findViewById(R.id.cbBoolean);

        cbBoolean.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cbBoolean.isChecked()) {
                    cbBoolean.setText("True");
                    isCbSelected = true;
//                    Toast.makeText(mContext, "Selected true" + isCbSelected + ".",
//                            Toast.LENGTH_LONG).show();

                    // cbBoolean.setChecked(true);
                } else {
                    cbBoolean.setText("False");
                    isCbSelected = false;
//                    Toast.makeText(mContext, "Selected " + isCbSelected + ".",
//                            Toast.LENGTH_LONG).show();

                    // cbBoolean.setChecked(false);
                }
            }
        });

        etStringPref = (EditText) findViewById(R.id.etStringPref);
        etIntegerPref = (EditText) findViewById(R.id.etIntegerPref);
        etLongPref = (EditText) findViewById(R.id.etLongPref);
        etFloatPref = (EditText) findViewById(R.id.etFloatPref);

        btnSetPref = (Button) findViewById(R.id.btnSetPref);

        btnSetPref.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!Common.isEmptyEditText(etStringPref)
                        && !Common.isEmptyEditText(etIntegerPref)
                        && !Common.isEmptyEditText(etLongPref)
                        && !Common.isEmptyEditText(etFloatPref)) {

                    Common.setStringPrefrences(mContext, "Yes", "Yes", getString(R.string.app_name));
                    Common.setStringPrefrences(mContext, "strPref", etStringPref.getText().toString(), getString(R.string.app_name));
                    Common.setIntPrefrences(mContext, "intPref", Integer.parseInt(etIntegerPref.getText().toString()), getString(R.string.app_name));
                    Common.setLongPrefrences(mContext, "longPref", Long.parseLong(etLongPref.getText().toString()), getString(R.string.app_name));
                    Common.setBooleanPrefrences(mContext, "booleanPref", isCbSelected, getString(R.string.app_name));
                    Common.setFloatPrefrences(mContext, "floatPref", Float.parseFloat(etFloatPref.getText().toString()), getString(R.string.app_name));
                    Toast.makeText(mContext, "All preferences are set.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mContext, "All fields are required to be filled.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
