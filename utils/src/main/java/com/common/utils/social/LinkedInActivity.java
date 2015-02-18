package com.common.utils.social;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.common.utils.Common;
import com.common.utils.R;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * Created by Sanjay on 12/30/2014.
 */
public class LinkedInActivity extends Activity {

    private static final String OAUTH_CALLBACK_SCHEME = "x-oauthflow-linkedin";
    private static final String OAUTH_CALLBACK_HOST = "callback";
    private static final String OAUTH_CALLBACK_URL = OAUTH_CALLBACK_SCHEME
            + "://" + OAUTH_CALLBACK_HOST;
    private static final String PROTECTED_RESOURCE_URL = "http://api.linkedin.com/v1/people/~:(id,first-name,last-name,email-address,skills)?format=json";
    public static Token ACCESSTOKEN;
    private static Response response;
    private static OAuthService oas_linkedin;
    private static Token requestToken;
    private static String authURL;
    private final long startTime = 5 * 1000;
    private final long intervalTime = 5 * 1000;
    ProgressDialog progress;
    private String APIKEY = "";
    private String APISECRET = "";
    //	,skill:(name)
    private int eventType;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asn_activity_oauth);

        View view = findViewById(R.id.progress_container);
        view.setVisibility(View.GONE);
        Intent intent = getIntent();
        APIKEY = intent.getStringExtra("APIKEY");
        APISECRET = intent.getStringExtra("APISECRET");

        if (TextUtils.isEmpty(APIKEY) || TextUtils.isEmpty(APISECRET)) {
            Common.showAlertDialog(this, "Linkedin", "Please pass APIKEY and APISECRET in intent extras", true);
            setResult(RESULT_CANCELED);
            finish();
        } else {
            progress = new ProgressDialog(this);
            progress.setTitle("Loading...");
            progress.setMessage("Please Wait...");
            progress.show();
            new OauthStart().execute();
        }

    }

    private class OauthStart extends AsyncTask<Void, Void, WebView> {

        @Override
        protected WebView doInBackground(Void... params) {
            System.out.println("001 OauthStart doInBackground");
            oas_linkedin = new ServiceBuilder().provider(LinkedInApi.class)
                    .apiKey(APIKEY).apiSecret(APISECRET)
                    .callback(OAUTH_CALLBACK_URL).build();

            requestToken = oas_linkedin.getRequestToken();
            authURL = oas_linkedin.getAuthorizationUrl(requestToken);

            final WebView webview = (WebView) findViewById(R.id.web_view);
            return webview;
        }

        @Override
        protected void onPostExecute(final WebView result) {
            System.out.println("001 OauthStart onPostExecute-->" + result);
            result.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    System.out
                            .println("001 OauthStart onPostExecute shouldOverrideUrlLoading-->" + url);

                    if (url.startsWith(OAUTH_CALLBACK_URL)) {
                        System.out
                                .println("001 OauthStart onPostExecute shouldOverrideUrlLoading IN IF-->" + url);
                        new OauthEnd().execute(url);
                        return true;
                    }
                    System.out.println("view:" + view);
                    // if (!progress.equals(null)) {
                    // progress.dismiss();
                    // } else if (progress.equals(null)) {
                    // progress.show();
                    // }
                    countDownTimer = new MyCountDownTimer(startTime,
                            intervalTime);
                    countDownTimer.start();
                    return super.shouldOverrideUrlLoading(view, url);
                }
            });

            result.loadUrl(authURL);
            System.out.println("001 OauthStart onPostExecute after load");
            // first dialog close
            super.onPostExecute(result);
        }
    }

    private class OauthEnd extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            if (!progress.equals(null)) {
                progress.dismiss();
            } else {
                progress.show();
            }
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            System.out.println("001 OauthEnd doInBackground-->" + params[0]);
            String url = params[0];
            if (url.contains("user_refused")) {
                setResult(RESULT_CANCELED);
                finish();
            } else {
                final Uri uri = Uri.parse(url);
                final String verifier = uri.getQueryParameter("oauth_verifier");
                final Verifier v = new Verifier(verifier);
                System.out.println("hp Verifier>>>> " + v.getValue());
                final Token accessToken = oas_linkedin.getAccessToken(requestToken,
                        v);
                ACCESSTOKEN = accessToken;
                final OAuthRequest request = new OAuthRequest(Verb.GET,
                        PROTECTED_RESOURCE_URL);
                oas_linkedin.signRequest(accessToken, request);
                response = request.send();

//                TODO JSON response in intent RESPONSE
                Intent intent = new Intent();
                intent.putExtra("RESPONSE", response.getBody());
                setResult(RESULT_OK, intent);
                finish();
            }
            return null;
        }
    }

//    private class XmlWorker extends AsyncTask<Response, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Response... params) {
//            System.out.println("001 XmlWorker doInBackground");
//            // START XML PROCESSING
//            // TODO: I know this whole place is ugly and should be refactored
//            // into their own method
//            Log.e("XML Output", "XML response : " + params[0].getBody());
//
//
//            try {
//                JSONObject personJson = new JSONObject(params[0].getBody());
//                Utils.setPrefrences(LinkedInLogin.this, Utils.EMAILID, personJson.getString("emailAddress"));
//                Utils.setPrefrences(LinkedInLogin.this, Utils.FIRSTNAME, personJson.getString("firstName"));
//                Utils.setPrefrences(LinkedInLogin.this, Utils.LINKEDINID, personJson.getString("id"));
//                Utils.setPrefrences(LinkedInLogin.this, Utils.LASTNAME, personJson.getString("lastName"));
//                JSONObject skillsJson = personJson.getJSONObject("skills");
//                JSONArray skillsArray = skillsJson.getJSONArray("values");
//
//                String personSkills;
//                StringBuilder sbSkills = new StringBuilder();
//                if (skillsArray != null && skillsArray.length() > 0) {
//                    for (int i = 0; i < skillsArray.length(); i++) {
//                        sbSkills.append(skillsArray.getJSONObject(i).getJSONObject("skill").getString("name") + ",");
//                    }
//
//                    personSkills = sbSkills.toString();
//
//                    if (personSkills.length() > 0
//                            && personSkills.charAt(personSkills.length() - 1) == ',') {
//                        personSkills = personSkills.substring(0, personSkills.length() - 1);
//                    }
//                    Utils.setPrefrences(LinkedInLogin.this, Utils.SKILLS, personSkills);
//                }
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            System.out.println("001 XmlWorker onPostExecute");
//            Intent intent = new Intent(LinkedInLogin.this, MainAct.class);
//            intent.putExtra("isSocial", "Linkedin");
//            startActivity(intent);
//            super.onPostExecute(result);
//        }
//    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            if (!progress.equals(null)) {
                progress.dismiss();
            } else if (progress.equals(null)) {
                progress.show();
            }
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }
    }
}
