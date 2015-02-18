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

public class PostFixForNumberAct extends Activity {
    Context mContext;
    EditText et_FixNumber;
    Button btn_FixNumber;
    TextView tvFixNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_fix_for_number_act);
        mContext = PostFixForNumberAct.this;
        init();

    }

    private void init() {

        tvFixNumber = (TextView) findViewById(R.id.tvFixNumber);

        et_FixNumber = (EditText) findViewById(R.id.et_FixNumber);

        btn_FixNumber = (Button) findViewById(R.id.btn_FixNumber);

        btn_FixNumber.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!Common.isEmptyEditText(et_FixNumber)) {
                    int postfixNumber = Integer.parseInt(et_FixNumber.getText().toString());
                    tvFixNumber.setText("Postfix number : " + Common.getPostFixForNumber(postfixNumber));
                } else {
                    Toast.makeText(mContext, "Please enter number for postfix", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
