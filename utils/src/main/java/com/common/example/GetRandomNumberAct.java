package com.common.example;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.common.utils.Common;
import com.common.utils.R;

public class GetRandomNumberAct extends Activity {
    Context mContext;
    EditText et_MaxRandomNumber;
    Button btn_generateRandomNumber;
    TextView tvInfo, tvRandomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_random_number_act);
        mContext = GetRandomNumberAct.this;
        init();

    }

    private void init() {

        tvInfo = (TextView) findViewById(R.id.tvInfo);
        tvRandomNumber = (TextView) findViewById(R.id.tvRandomNumber);
        et_MaxRandomNumber = (EditText) findViewById(R.id.et_MaxRandomNumber);
        btn_generateRandomNumber = (Button) findViewById(R.id.btn_generateRandomNumber);
        btn_generateRandomNumber.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!Common.isEmptyEditText(et_MaxRandomNumber)) {
                    tvInfo.setText("Your random number will generate from 0 to " + et_MaxRandomNumber.getText().toString());
                    int randNumber = Integer.parseInt(et_MaxRandomNumber.getText().toString());
                    tvRandomNumber.setText("Random number : " + Common.getRandom(randNumber));
                } else {
                    Toast.makeText(mContext, "Please enter maximum number for random value", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
