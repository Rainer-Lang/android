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

public class SendE_MailAct extends Activity {
    Context mContext;
    EditText et_EmailId;
    Button btn_SendEmailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_email_act);
        mContext = SendE_MailAct.this;
        init();

    }

    private void init() {

        et_EmailId = (EditText) findViewById(R.id.et_EmailId);

        btn_SendEmailId = (Button) findViewById(R.id.btn_SendEmailId);

        btn_SendEmailId.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (Common.isNetworkAvailable(mContext)) {
                    String mailID = et_EmailId.getText().toString();
                    Common.sendMail(mContext, mailID);
                } else {
                    Common.showNETWORDDisabledAlert(mContext);
                }

            }
        });
    }
}