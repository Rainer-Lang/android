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

public class GetPrefAct extends Activity {
    Context mContext;
    CheckBox cb_GetBoolean;
    EditText et_GetStringPref, et_GetIntegerPref, et_GetLongPref,
            et_GetFloatPref;
    Button btn_GetPref, btn_RemoveAllPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_pref_act);
        mContext = GetPrefAct.this;

        init();

    }

    private void init() {
        // TODO Auto-generated method stub
        cb_GetBoolean = (CheckBox) findViewById(R.id.cb_GetBoolean);

        et_GetStringPref = (EditText) findViewById(R.id.et_GetStringPref);
        et_GetIntegerPref = (EditText) findViewById(R.id.et_GetIntegerPref);
        et_GetLongPref = (EditText) findViewById(R.id.et_GetLongPref);
        et_GetFloatPref = (EditText) findViewById(R.id.et_GetFloatPref);

        btn_GetPref = (Button) findViewById(R.id.btn_GetPref);

        btn_GetPref.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Common.getStringPrefrences(mContext, "strPref");
                // Common.getIntPrefrences(mContext, "intPref");
                // Common.getLongPrefrences(mContext, "longPref");
                // Common.getBooleanPrefrences(mContext, "booleanPref");
                // Common.getFloatPrefrences(mContext, "floatPref");
                if (Common.getStringPrefrences(mContext, "Yes", getString(R.string.app_name)).equals(
                        "Yes")) {
                    et_GetStringPref.setText(Common.getStringPrefrences(mContext, "strPref", getString(R.string.app_name)));
                    et_GetIntegerPref.setText(String.valueOf(Common.getIntPrefrences(mContext, "intPref", getString(R.string.app_name))));
                    et_GetLongPref.setText(String.valueOf(Common.getLongPrefrences(mContext, "longPref", getString(R.string.app_name))));
                    et_GetFloatPref.setText(String.valueOf(Common.getFloatPrefrences(mContext, "floatPref", getString(R.string.app_name))));

                    if (Common.getBooleanPrefrences(mContext, "booleanPref", getString(R.string.app_name))) {
                        cb_GetBoolean.setText("True");
//                        Toast.makeText(mContext, "Selected true" + Common.getBooleanPrefrences(mContext, "booleanPref", getString(R.string.app_name)), Toast.LENGTH_LONG).show();

                        cb_GetBoolean.setChecked(true);
                    } else {
                        cb_GetBoolean.setText("False");
//                        Toast.makeText(mContext, "Selected" + Common.getBooleanPrefrences(mContext, "booleanPref", getString(R.string.app_name)), Toast.LENGTH_LONG).show();

                        cb_GetBoolean.setChecked(false);
                    }
                } else {
                    Toast.makeText(mContext, "Please set preferences.", Toast.LENGTH_LONG).show();
                }

            }
        });

        btn_RemoveAllPref = (Button) findViewById(R.id.btn_RemoveAllPref);
        btn_RemoveAllPref.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Common.removeAllPrefrences(mContext, getString(R.string.app_name));
                Common.setStringPrefrences(mContext, "Yes", "No", getString(R.string.app_name));
                cb_GetBoolean.setText("False");
                cb_GetBoolean.setChecked(false);
                et_GetStringPref.setText("");
                et_GetIntegerPref.setText("");
                et_GetLongPref.setText("");
                et_GetFloatPref.setText("");
            }
        });
    }

}
