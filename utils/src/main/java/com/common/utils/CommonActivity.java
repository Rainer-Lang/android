package com.common.utils;

//
//import android.annotation.TargetApi;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.widget.VideoView;
//
//import com.common.utils.social.DialogListener;
//import com.common.utils.social.SocialAuthAdapter;
//import com.common.utils.social.SocialAuthError;
//import com.common.utils.social.SocialAuthListener;
//
//import org.brickred.socialauth.Contact;
//import org.brickred.socialauth.Profile;
//
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.List;
//
public class CommonActivity //extends Activity implements OnClickListener {
{
//
//    TextView text1, text2, text3, text4, text5, text6, text7, text8;
//    Context mContext;
//    Activity mActivity;
//    private AlertDialog dialog;
//    private ProgressDialog mDialog;
//    private SocialAuthAdapter adapter;
//    private int provider = 0;
//    private ImageView ivImage;
//    private VideoView vvVideo;
//    private ProgressDialog pDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mContext = CommonActivity.this;
//        mActivity = CommonActivity.this;
//        adapter = new SocialAuthAdapter(new ResponseListener());
//
//        ivImage = (ImageView) findViewById(R.id.imageView1);
//        vvVideo = (VideoView) findViewById(R.id.videoView1);
//        pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Please Wait");
//
//        text1 = (TextView) findViewById(R.id.text1);
//        text2 = (TextView) findViewById(R.id.text2);
//        text3 = (TextView) findViewById(R.id.text3);
//        text4 = (TextView) findViewById(R.id.text4);
//        text5 = (TextView) findViewById(R.id.text5);
//        text6 = (TextView) findViewById(R.id.text6);
//        text7 = (TextView) findViewById(R.id.text7);
//        text8 = (TextView) findViewById(R.id.text8);
//
//        text1.setOnClickListener(this);
//        text2.setOnClickListener(this);
//        text3.setOnClickListener(this);
//        text4.setOnClickListener(this);
//        text5.setOnClickListener(this);
//        text6.setOnClickListener(this);
//        text7.setOnClickListener(this);
//        text8.setOnClickListener(this);
//
//    }
//
//
//    @Override
//    public void onClick(View v) {
//
//        if (v == text1) {
////            Common.captureImage(this, 101);
//            provider = 1;
//            pDialog.show();
//            adapter.authorize(CommonActivity.this, SocialAuthAdapter.Provider.LINKEDIN);
//        } else if (v == text2) {
//            text2.setVisibility(View.GONE);
//            adapter.signOut(CommonActivity.this, SocialAuthAdapter.Provider.LINKEDIN.toString());
//        } else if (v == text3) {
////            Common.recordVideo(this, 102);
//            provider = 2;
//            pDialog.show();
//            adapter.authorize(CommonActivity.this, SocialAuthAdapter.Provider.FACEBOOK);
//        } else if (v == text4) {
//            text4.setVisibility(View.GONE);
//            adapter.signOut(CommonActivity.this, SocialAuthAdapter.Provider.FACEBOOK.toString());
//        } else if (v == text5) {
////            Common.pickImage(this, 103);
//            provider = 3;
//            pDialog.show();
//            adapter.authorize(CommonActivity.this, SocialAuthAdapter.Provider.TWITTER);
//        } else if (v == text6) {
//            text6.setVisibility(View.GONE);
//            adapter.signOut(CommonActivity.this, SocialAuthAdapter.Provider.TWITTER.toString());
//        } else if (v == text7) {
////            Common.pickVideo(this, 104);
//            provider = 4;
//            pDialog.show();
//            adapter.authorize(CommonActivity.this, SocialAuthAdapter.Provider.GOOGLEPLUS);
//        } else if (v == text8) {
//            text8.setVisibility(View.GONE);
//            adapter.signOut(CommonActivity.this, SocialAuthAdapter.Provider.GOOGLEPLUS.toString());
//        }
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        Common.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        Common.onRestoreInstanceState(savedInstanceState);
//    }
//
//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case 101:
//                    Common.previewCapturedImage(ivImage);
//                    break;
//                case 102:
//                    Common.previewVideo(vvVideo);
//                    break;
//                case 103:
//                    Uri uri = data.getData();
//                    try {
//                        Bitmap bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//                        ivImage.setImageBitmap(bm);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
////                    BitmapFactory.Options options = new BitmapFactory.Options();
////                    final Bitmap bitmap = BitmapFactory.decodeFile(uri.getPath(), options);
//                    break;
//                case 104:
//
//
//                    Uri fileUri = data.getData();
////                    final int takeFlags = data.getFlags()
////                            & (Intent.FLAG_GRANT_READ_URI_PERMISSION
////                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
////                    // Check for the freshest data.
////                    getContentResolver().takePersistableUriPermission(fileUri, takeFlags);
//////                    Uri fileUri = MediaStore.Video.Media.getContentUri(data.getDataString());
////                    Log.d("URI " , " video Uri : " + fileUri.getPath());
//                    vvVideo.setVideoPath(Common.getPath(this, fileUri));
//                    // start playing
//                    vvVideo.start();
//            }
//        }
//    }
//
//
//
//    // To get status of message after authentication
//    private final class MessageListener implements SocialAuthListener<Integer> {
//        @Override
//        public void onExecute(String provider, Integer t) {
//            Integer status = t;
//            if (status.intValue() == 200 || status.intValue() == 201 || status.intValue() == 204)
//                Toast.makeText(CommonActivity.this, "Message posted on" + provider, Toast.LENGTH_LONG).show();
//            else
//                Toast.makeText(CommonActivity.this, "Message not posted" + provider, Toast.LENGTH_LONG).show();
//        }
//
//        @Override
//        public void onError(SocialAuthError e) {
//            e.getInnerException().printStackTrace();
//        }
//    }
//
//    private final class ResponseListener implements DialogListener {
//
//        @Override
//        public void onComplete(Bundle values) {
//
//            Log.d("Custom-UI", "Successful");
//
//            // Changing Sign In Text to Sign Out
//            pDialog.dismiss();
//            Toast.makeText(CommonActivity.this, "Connected", Toast.LENGTH_SHORT).show();
//
////            mDialog = new ProgressDialog(CommonActivity.this);
////            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
////            mDialog.setMessage("Loading...");
////            mDialog.show();
//            switch (provider) {
//                case 1:
//                    text2.setVisibility(View.VISIBLE);
//                    break;
//                case 2:
//                    text4.setVisibility(View.VISIBLE);
//                    break;
//                case 3:
//                    text6.setVisibility(View.VISIBLE);
//                    break;
//                case 4:
//                    text8.setVisibility(View.VISIBLE);
//                    break;
//            }
////            if (provider != 4 && provider != 1){
////                Bitmap bitmap = Common.drawableTobitmap(CommonActivity.this, R.drawable.facebook);
////                try {
////                    adapter.uploadImageAsync("Image Message", "icon.png", bitmap, 0,
////                            new UploadImageListener());
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
////                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
////                photoPickerIntent.setType("image/*");
////                startActivityForResult(photoPickerIntent, 101);
////            }
////            adapter.updateStatus("Hi Test Status Update @" + Calendar.getInstance().getTimeInMillis(), new MessageListener(), false);
////            adapter.getUserProfileAsync(new ProfileDataListener());
//            adapter.getContactListAsync(new ContactDataListener());
//
//        }
//
//        @Override
//        public void onError(SocialAuthError error) {
//            Log.d("Custom-UI", "Error");
//            pDialog.dismiss();
//            error.printStackTrace();
//        }
//
//        @Override
//        public void onCancel() {
//            Log.d("Custom-UI", "Cancelled");
//            pDialog.dismiss();
//        }
//
//        @Override
//        public void onBack() {
//            pDialog.dismiss();
//            Log.d("Custom-UI", "Dialog Closed by pressing Back Key");
//
//        }
//    }
//
//    private final class ContactDataListener implements SocialAuthListener<List<Contact>> {
//
//        @Override
//        public void onExecute(String provider, List<Contact> t) {
//
//            Log.d("Custom-UI", "Receiving Data");
////            mDialog.dismiss();
//            List<Contact> contactsList = t;
//
//            if (contactsList != null && contactsList.size() > 0) {
//                Intent intent = new Intent(CommonActivity.this, ContactActivity.class);
//                intent.putExtra("provider", provider);
//                intent.putExtra("contact", (Serializable) contactsList);
//                startActivity(intent);
//            } else {
//                Log.d("Custom-UI", "Contact List Empty");
//            }
//        }
//
//        @Override
//        public void onError(SocialAuthError e) {
//
//        }
//    }
//    // To receive the profile response after authentication
//    private final class ProfileDataListener implements SocialAuthListener<Profile> {
//
//        @Override
//        public void onExecute(String provider, Profile t) {
//
//            Log.d("Custom-UI", "Receiving Data");
////            mDialog.dismiss();
//            Profile profileMap = t;
//
//            Intent intent = new Intent(CommonActivity.this, ProfileActivity.class);
//            intent.putExtra("provider", provider);
//            intent.putExtra("profile", profileMap);
//            startActivity(intent);
//        }
//
//        @Override
//        public void onError(SocialAuthError e) {
//
//        }
//    }
//
//    // To get status of image upload after authentication
//    private final class UploadImageListener implements SocialAuthListener<Integer> {
//
//        @Override
//        public void onExecute(String provider, Integer t) {
////            mDialog.dismiss();
//            Integer status = t;
//            Log.d("Custom-UI", String.valueOf(status));
//            if (status.intValue() == 200 || status.intValue() == 201 || status.intValue() == 204)
//                Toast.makeText(CommonActivity.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
//            else
//                Toast.makeText(CommonActivity.this, "Image not Uploaded", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onError(SocialAuthError e) {
//
//        }
//    }
//
//
}