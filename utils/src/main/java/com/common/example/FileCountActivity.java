package com.common.example;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.common.utils.Common;
import com.common.utils.R;

import java.io.File;

public class FileCountActivity extends ActionBarActivity {

    EditText et_Url;
    Button btnOpenUrl;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_count);
        mContext = FileCountActivity.this;
        init();

    }

    private void init() {
        ((TextView) findViewById(R.id.textView1)).setText("Default folder is : " + Environment.getExternalStorageDirectory().getPath() + "/");
        ((TextView) findViewById(R.id.tv_address)).setText(Environment.getExternalStorageDirectory().getPath() + "/");
        et_Url = (EditText) findViewById(R.id.et_address);
        btnOpenUrl = (Button) findViewById(R.id.btnOpenMap);

        btnOpenUrl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Common.isEmptyEditText(et_Url)) {

                    getCheckedRadioButton(Environment.getExternalStorageDirectory().getPath());

                } else {
                    File fileCount = new File(Environment.getExternalStorageDirectory().getPath() + "/" + et_Url.getText().toString());
                    if (fileCount.exists())
                        getCheckedRadioButton(Environment.getExternalStorageDirectory().getPath() + "/" + et_Url.getText().toString());
                    else
                        Common.showAlertDialog(FileCountActivity.this, getString(R.string.app_name), "Folder do not exist " + Environment.getExternalStorageDirectory().getPath() + et_Url.getText().toString(), false);
                }
            }

        });
    }

    private void getCheckedRadioButton(String folderName) {
        RadioGroup rgb = (RadioGroup) findViewById(R.id.radio_group);
        switch (rgb.getCheckedRadioButtonId()) {
            case R.id.jpg_radioButton:
                fileCount(folderName, "jpg");
                break;
            case R.id.mp3_radioButton:
                fileCount(folderName, "mp3");
                break;
            case R.id.pdf_radioButton:
                fileCount(folderName, "pdf");
                break;
        }


    }


    void fileCount(String folderName, String fileExtension) {

        Common.showAlertDialog(this, getString(R.string.app_name), "Total " + Common.getFileCounts(fileExtension, folderName) + " " + fileExtension + " file's", false);

    }
}
