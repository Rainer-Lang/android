package com.common.example;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.common.utils.ContactActivity;
import com.common.utils.ProfileActivity;
import com.common.utils.R;
import com.common.utils.social.DialogListener;
import com.common.utils.social.LinkedInActivity;
import com.common.utils.social.SocialAuthAdapter;
import com.common.utils.social.SocialAuthError;
import com.common.utils.social.SocialAuthListener;

import org.brickred.socialauth.Contact;
import org.brickred.socialauth.Profile;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class CommonActivity extends Activity implements OnClickListener {

    TextView text1, text2, text3, text4, text5, text6, text7, text8;
    private SocialAuthAdapter adapter;
    private int provider = 0;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        adapter = new SocialAuthAdapter(new ResponseListener());

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please Wait");

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);
        text5 = (TextView) findViewById(R.id.text5);
        text6 = (TextView) findViewById(R.id.text6);
        text7 = (TextView) findViewById(R.id.text7);
        text8 = (TextView) findViewById(R.id.text8);

        text1.setOnClickListener(this);
        text2.setOnClickListener(this);
        text3.setOnClickListener(this);
        text4.setOnClickListener(this);
        text5.setOnClickListener(this);
        text6.setOnClickListener(this);
        text7.setOnClickListener(this);
        text8.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        if (v == text1) {
            Intent intent = new Intent(this, LinkedInActivity.class);
            intent.putExtra("APIKEY", "bh82t52rdos6");
            intent.putExtra("APISECRET", "zQ1LLrGbhDZ36fH8");
            startActivityForResult(intent, 123);
//            provider = 1;
//            pDialog.show();
//            adapter.authorize(CommonActivity.this, SocialAuthAdapter.Provider.LINKEDIN);
        } else if (v == text2) {
            text2.setVisibility(View.GONE);
            adapter.signOut(CommonActivity.this, SocialAuthAdapter.Provider.LINKEDIN.toString());
        } else if (v == text3) {
            provider = 2;
            pDialog.show();
            adapter.authorize(CommonActivity.this, SocialAuthAdapter.Provider.FACEBOOK);
        } else if (v == text4) {
            text4.setVisibility(View.GONE);
            adapter.signOut(CommonActivity.this, SocialAuthAdapter.Provider.FACEBOOK.toString());
        } else if (v == text5) {
            provider = 3;
            pDialog.show();
            adapter.authorize(CommonActivity.this, SocialAuthAdapter.Provider.TWITTER);
        } else if (v == text6) {
            text6.setVisibility(View.GONE);
            adapter.signOut(CommonActivity.this, SocialAuthAdapter.Provider.TWITTER.toString());
        } else if (v == text7) {
            provider = 4;
            pDialog.show();
            adapter.authorize(CommonActivity.this, SocialAuthAdapter.Provider.GOOGLEPLUS);
        } else if (v == text8) {
            text8.setVisibility(View.GONE);
            adapter.signOut(CommonActivity.this, SocialAuthAdapter.Provider.GOOGLEPLUS.toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 101) {
                try {
                    JSONObject personJson = new JSONObject(data.getStringExtra("RESPONSE"));
                    Toast.makeText(CommonActivity.this, "LinkedIn Connected", Toast.LENGTH_SHORT).show();
//                    text4.setText(" Email id : " + personJson.getString("emailAddress") + " \n Name : " + personJson.getString("firstName") + "  " + personJson.getString("lastName") + " \n id : " + personJson.getString("id"));


//              Get Skills
                    JSONObject skillsJson = personJson.getJSONObject("skills");
                    JSONArray skillsArray = skillsJson.getJSONArray("values");
                    String personSkills;
                    StringBuilder sbSkills = new StringBuilder();
                    if (skillsArray != null && skillsArray.length() > 0) {
                        for (int i = 0; i < skillsArray.length(); i++) {
                            sbSkills.append(skillsArray.getJSONObject(i).getJSONObject("skill").getString("name") + ",");
                        }
                        personSkills = sbSkills.toString();
                        if (personSkills.length() > 0
                                && personSkills.charAt(personSkills.length() - 1) == ',') {
                            personSkills = personSkills.substring(0, personSkills.length() - 1);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // To get status of message after authentication
    private final class MessageListener implements SocialAuthListener<Integer> {
        @Override
        public void onExecute(String provider, Integer t) {
            Integer status = t;
            if (status.intValue() == 200 || status.intValue() == 201 || status.intValue() == 204)
                Toast.makeText(CommonActivity.this, "Message posted on" + provider, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(CommonActivity.this, "Message not posted" + provider, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(SocialAuthError e) {
            e.getInnerException().printStackTrace();
        }
    }

    private final class ResponseListener implements DialogListener {

        @Override
        public void onComplete(Bundle values) {
            Log.d("Custom-UI", "Successful");
            pDialog.dismiss();

            switch (provider) {
//                case 1:
//                    Toast.makeText(CommonActivity.this, "LinkedIn Connected", Toast.LENGTH_SHORT).show();
//                    text2.setVisibility(View.VISIBLE);
//                    break;
                case 2:
                    Toast.makeText(CommonActivity.this, "Facebook Connected", Toast.LENGTH_SHORT).show();
                    text4.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    Toast.makeText(CommonActivity.this, "Twitter Connected", Toast.LENGTH_SHORT).show();
                    text6.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    Toast.makeText(CommonActivity.this, "Google+ Connected", Toast.LENGTH_SHORT).show();
                    text8.setVisibility(View.VISIBLE);
                    break;
            }


//            Get Profile
//            adapter.getUserProfileAsync(new ProfileDataListener());

//            Get Contacts
//            adapter.getContactListAsync(new ContactDataListener());

//            Share Status
//            adapter.updateStatus("Hi Test Status Update @" + Calendar.getInstance().getTimeInMillis(), new MessageListener(), false);

//            Share Image
//            try {
//                Bitmap bitmap = Common.drawableTobitmap(CommonActivity.this, R.drawable.facebook);
//                adapter.uploadImageAsync("Image Message", "icon.png", bitmap, 0, new UploadImageListener());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        }

        @Override
        public void onError(SocialAuthError error) {
            Log.d("Custom-UI", "Error");
            pDialog.dismiss();
            error.printStackTrace();
        }

        @Override
        public void onCancel() {
            Log.d("Custom-UI", "Cancelled");
            pDialog.dismiss();
        }

        @Override
        public void onBack() {
            pDialog.dismiss();
            Log.d("Custom-UI", "Dialog Closed by pressing Back Key");

        }
    }

    private final class ContactDataListener implements SocialAuthListener<List<Contact>> {

        @Override
        public void onExecute(String provider, List<Contact> t) {

            Log.d("Custom-UI", "Receiving Data");
//            mDialog.dismiss();
            List<Contact> contactsList = t;

            if (contactsList != null && contactsList.size() > 0) {
                Intent intent = new Intent(CommonActivity.this, ContactActivity.class);
                intent.putExtra("provider", provider);
                intent.putExtra("contact", (Serializable) contactsList);
                startActivity(intent);
            } else {
                Log.d("Custom-UI", "Contact List Empty");
            }
        }

        @Override
        public void onError(SocialAuthError e) {

        }
    }

    // To receive the profile response after authentication
    private final class ProfileDataListener implements SocialAuthListener<Profile> {

        @Override
        public void onExecute(String provider, Profile t) {

            Log.d("Custom-UI", "Receiving Data");
//            mDialog.dismiss();
            Profile profileMap = t;

            Intent intent = new Intent(CommonActivity.this, ProfileActivity.class);
            intent.putExtra("provider", provider);
            intent.putExtra("profile", profileMap);
            startActivity(intent);
        }

        @Override
        public void onError(SocialAuthError e) {

        }
    }

    // To get status of image upload after authentication
    private final class UploadImageListener implements SocialAuthListener<Integer> {

        @Override
        public void onExecute(String provider, Integer t) {
//            mDialog.dismiss();
            Integer status = t;
            Log.d("Custom-UI", String.valueOf(status));
            if (status.intValue() == 200 || status.intValue() == 201 || status.intValue() == 204)
                Toast.makeText(CommonActivity.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(CommonActivity.this, "Image not Uploaded", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SocialAuthError e) {

        }
    }


}