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

public class OpenUrlINBrowserAct extends Activity {
    Context mContext;
    EditText et_Url;
    Button btnOpenUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_url_in_browser_act);
        mContext = OpenUrlINBrowserAct.this;
        init();

    }

    private void init() {

        et_Url = (EditText) findViewById(R.id.et_Url);
        btnOpenUrl = (Button) findViewById(R.id.btnOpenUrl);

        btnOpenUrl.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String url;
                if (Common.isNetworkAvailable(mContext)) {
                    if (Common.isEmptyEditText(et_Url)) {
                        url = "http://www.multidots.com/";
                    } else {
                        if (et_Url.getText().toString().startsWith("http"))
                            url = et_Url.getText().toString();
                        else
                            url = "http://" + et_Url.getText().toString();
                    }
                    Common.openURL(mContext, url);
                } else {
                    Common.showNETWORDDisabledAlert(mContext);
                }

            }
        });
    }
}