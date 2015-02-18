package com.common.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Sanjay on 2/2/2015.
 */
public class DownloadFile extends AsyncTask<String, String, String> {


    private final String strurl;
    private final Context mContext;
    private final String filename;
    private final boolean download;
    float filesize;
    private ProgressDialog pDialog;

    DownloadFile(Context context, String url, String filename, boolean download) {
        strurl = url;
        mContext = context;
        this.filename = filename;
        this.download = download;
    }

    private static File getOutputMediaFile(String filename) {
        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), Common.IMAGE_DIRECTORY_NAME);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(Common.IMAGE_DIRECTORY_NAME, "Oops! Failed create " + Common.IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + filename);

        return mediaFile;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Please Wait..");
        pDialog.show();

    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(strurl);
            System.out.println("5555 get size for -" + url);

            URLConnection urlConnection;
            urlConnection = url.openConnection();
            urlConnection.connect();
            filesize = urlConnection.getContentLength() / 1024f;
            filesize = filesize / 1024f;
            System.out.println("size of file:- " + filesize);


            if (download) {
                InputStream input = new BufferedInputStream(url.openStream());
                OutputStream output = new FileOutputStream(getOutputMediaFile(filename));
                byte data[] = new byte[1024];
                long total = 0;
                int count = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }

        if (!download)
            Common.showAlertDialog(mContext, "File Size", "Size of file at " + strurl + "  is " + filesize + " MB", false);
        else
            Common.showAlertDialog(mContext, "File Path", "File stored at " + getOutputMediaFile(filename).getPath().toString(), false);
    }

}
