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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.common.utils.Common;
import com.common.utils.R;

public class DownloadImageFromUrlAct extends Activity {
    Context mContext;
    ImageView ivDownLoadImage;
    Button btnDownLoadImage;
    TextView tvDefaultDownLoadImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_image_from_url);
        mContext = DownloadImageFromUrlAct.this;

        init();

    }

    private void init() {
        ivDownLoadImage = (ImageView) findViewById(R.id.ivDownLoadImage);
        btnDownLoadImage = (Button) findViewById(R.id.btnDownLoadImage);
        tvDefaultDownLoadImageUrl = (TextView) findViewById(R.id.tvDefaultDownLoadImageUrl);

        btnDownLoadImage.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (Common.isNetworkAvailable(mContext)) {
                    btnDownLoadImage.setEnabled(false);
                    String imgUrl = "http://www.sbsmobile.com/siteimg/cat-menu/galaxyS5.png";
                    Toast.makeText(mContext, "Please wait downloading..!", Toast.LENGTH_LONG).show();
                    Common.downloadImageFromURL(imgUrl, ivDownLoadImage);
                } else {
                    Common.showNETWORDDisabledAlert(mContext);
                }
            }
        });

    }
}
