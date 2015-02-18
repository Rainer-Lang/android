package com.common.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.common.utils.Common;
import com.common.utils.R;

public class DownloadActivity extends Activity {

    String url = "http://www.firstpost.com/wp-content/uploads/2013/09/01_Android-all-versions.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);


        findViewById(R.id.download_activity_btn_filesize).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.getRemoteFileSize(DownloadActivity.this, url);
            }
        });

        findViewById(R.id.download_activity_btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.downloadRemoteFile(DownloadActivity.this, url, "rabbit.jpg");
            }
        });
    }


}
