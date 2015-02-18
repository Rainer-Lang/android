package com.common.example;
/**
 * @author Y@$!n
 *
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.common.utils.Common;
import com.common.utils.R;

public class SendLocatoNotificationAct extends Activity {
    Context mContext;
    EditText et_NotTitle, et_NotMsg, et_GetLongPref,
            et_GetFloatPref;
    Button btn_Send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_notification_act);
        mContext = SendLocatoNotificationAct.this;
        init();

    }

    private void init() {

        et_NotTitle = (EditText) findViewById(R.id.et_NotTitle);
        et_NotMsg = (EditText) findViewById(R.id.et_NotMsg);

        btn_Send = (Button) findViewById(R.id.btn_Send);

        btn_Send.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String title = et_NotTitle.getText().toString();
                String message = et_NotMsg.getText().toString();
                Intent mIntent = new Intent(mContext, SendLocatoNotificationAct.class);
                Common.sendLocatNotification(mContext, title, message, null);
            }
        });
    }
}
