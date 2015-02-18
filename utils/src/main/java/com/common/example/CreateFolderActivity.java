package com.common.example;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.common.utils.Common;
import com.common.utils.R;

public class CreateFolderActivity extends Activity {

    EditText et_Url;
    Button btnOpenUrl;
    private CreateFolderActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_folder);
        mContext = CreateFolderActivity.this;
        init();
    }

    private void init() {

        et_Url = (EditText) findViewById(R.id.et_address);
        btnOpenUrl = (Button) findViewById(R.id.btnOpenMap);

        btnOpenUrl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Common.isEmptyEditText(et_Url)) {
                    boolean created = Common.createFolder(mContext, Environment.getExternalStorageDirectory().getPath(), "TestFolder");
                    if (created)
                        Common.showAlertDialog(mContext, getString(R.string.app_name), "Folder created  " + Environment.getExternalStorageDirectory().getPath() + "/TestFolder", false);
                    else
                        Common.showAlertDialog(mContext, getString(R.string.app_name), "Folder not created or folder is already created", false);
                } else {
                    boolean created = Common.createFolder(mContext, Environment.getExternalStorageDirectory().getPath(), et_Url.getText().toString());
                    if (created)
                        Common.showAlertDialog(mContext, getString(R.string.app_name), "Folder created  " + Environment.getExternalStorageDirectory().getPath() + "/" + et_Url.getText().toString(), false);
                    else
                        Common.showAlertDialog(mContext, getString(R.string.app_name), "Folder not created or folder is already created", false);
                }
            }

        });
    }

}
