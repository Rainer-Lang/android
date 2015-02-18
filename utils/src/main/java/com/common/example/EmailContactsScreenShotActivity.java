package com.common.example;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.common.utils.Common;
import com.common.utils.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class EmailContactsScreenShotActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_contacts_screen_shot);

        final TextView tvEmails = (TextView) findViewById(R.id.email_list_tv);

        ArrayList<String> nameEmailDetails = Common.getNameEmailDetails(this);
        String strNameEmail = "";
        for (int i = 0; i < nameEmailDetails.size(); i++) {
            strNameEmail += "\n\n " + nameEmailDetails.get(i);
        }

        tvEmails.setText(strNameEmail);

        findViewById(R.id.screenshot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(EmailContactsScreenShotActivity.this);
                progressDialog.setMessage("Please wait");
                progressDialog.show();
                Bitmap b = Common.captureView(tvEmails);
//                ImageView ivEmail = (ImageView) findViewById(R.id.email_iv_screen);
//                ivEmail.setImageBitmap(b);
                String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                        "/UtilsExample";
                File dir = new File(file_path);
                if (!dir.exists())
                    dir.mkdirs();
                File file = new File(dir, "ScreenShot" + Calendar.getInstance().getTime() + ".png");
                FileOutputStream fOut = null;
                progressDialog.dismiss();
                try {
                    fOut = new FileOutputStream(file);
                    b.compress(Bitmap.CompressFormat.PNG, 85, fOut);
                    fOut.flush();
                    fOut.close();
                    Common.showAlertDialog(EmailContactsScreenShotActivity.this, getString(R.string.app_name), "Screenshot saved in " + file.getPath(), false);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }


}
