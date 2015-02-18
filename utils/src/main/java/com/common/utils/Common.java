package com.common.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.service.textservice.SpellCheckerService.Session;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author H!ral
 */
@SuppressWarnings("deprecation")
@SuppressLint("SimpleDateFormat")
public class Common {
    /**
     * Preference file name
     */
//    public static final String APP_PREFS = "app_prefs";
    // -----------------------
    public static final String APP_FB_PREFS = "LoginWithFb";
    public static final String LOGIN_FIRST_NAME = "LOGIN_FIRST_NAME";
    public static final String LOGIN_LAST_NAME = "LOGIN_LAST_NAME";
    public static final String LOGIN_EMAIL = "LOGIN_EMAIL";
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static final String IMAGE_DIRECTORY_NAME = "CommonDirectory";
    //	public static Facebook facebook;
    static final String TOKEN = "access_token";
    static final String EXPIRES = "expires_in";
    static final String[] PERMISSIONS = new String[]{"publish_stream"};
    private static final long MIN_CLICK_INTERVAL = 2 * 1000;
    public static String prefranceDate, prefrenceTime;
    /**
     * SharedPreferences
     */
    static SharedPreferences prefs;
    static LocationManager locationManager;
    static boolean isNetworkEnabled = false, isGPSEnabled = false;
    static Location location;
    static double latitude, longitude;
    // -----------------------------------------------
    static KeyguardManager keyguardManager;
    static KeyguardLock lock;
    //	private static AsyncFacebookRunner mAsyncRunner;
    static SharedPreferences preferences;
    private static Calendar dateTime = Calendar.getInstance();
    private static String[] suffix = new String[]{"", "k", "m", "b", "t"};

    // -----------------------------------------------
    private static int MAX_LENGTH = 5;

    // -----------------------------------------------
    private static Uri fileUri;

    // -----------------------------------------------
    public Session currentSession;

    /**
     * check EditText is empty or not
     *
     * @param edText pass EditText for check is empty or not
     * @return true or false
     */
    public static boolean isEmptyEditText(EditText edText) {
        if (edText.getText().toString().trim().length() > 0)
            return false;
        else
            return true;
    }

    /**
     * check availability of Internet
     *
     * @param context
     * @return true or false
     */
    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    /**
     * set String Preference Value
     *
     * @param context
     * @param prefName Preference name
     * @param Value    Preference value
     */
    public static void setStringPrefrences(Context context, String prefName, String Value, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putString(prefName, Value);
        editor.commit();
    }

    /**
     * get String Preference Value
     *
     * @param context
     * @param prefName
     * @return
     */
    public static String getStringPrefrences(Context context, String prefName, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        if (prefs.contains(prefName))
            return prefs.getString(prefName, null);
        else
            return "";
    }

    // -----------------------------------------------

    /**
     * set Integer Preference Value
     *
     * @param context
     * @param prefName
     * @param Value
     */
    public static void setIntPrefrences(Context context, String prefName, int Value, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putInt(prefName, Value);
        editor.commit();
    }

    // -----------------------------------------------

    /**
     * get Integer Preference Value
     *
     * @param context
     * @param prefName
     * @return
     */
    public static int getIntPrefrences(Context context, String prefName, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(prefName, 0);
    }

    // -----------------------------------------------

    /**
     * set Boolean Preference Value
     *
     * @param context
     * @param prefName
     * @param Value
     */
    public static void setBooleanPrefrences(Context context, String prefName, Boolean Value, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putBoolean(prefName, Value);
        editor.commit();
    }

    // -----------------------------------------------

    /**
     * get Boolean Preference Value
     *
     * @param context
     * @param prefName
     * @return
     */
    public static boolean getBooleanPrefrences(Context context, String prefName, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(prefName, false);
    }

    // -----------------------------------------------

    /**
     * set Float Preference Value
     *
     * @param context
     * @param prefName
     * @param Value
     */
    public static void setFloatPrefrences(Context context, String prefName, Float Value, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putFloat(prefName, Value);
        editor.commit();
    }

    // -----------------------------------------------
    // static File SDCardRoot = Environment.getExternalStorageDirectory();

    /**
     * get Float Preference Value
     *
     * @param context
     * @param prefName
     * @return
     */
    public static float getFloatPrefrences(Context context, String prefName, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return prefs.getFloat(prefName, 0);
    }

    // ------------------------------------------------

    /**
     * set Long Preference Value
     *
     * @param context
     * @param prefName
     * @param Value
     */
    public static void setLongPrefrences(Context context, String prefName, Long Value, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putLong(prefName, Value);
        editor.commit();
    }

    // -----------------------------------------------

    /**
     * get Long Preference Value
     *
     * @param context
     * @param prefName
     * @return
     */
    public static long getLongPrefrences(Context context, String prefName, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return prefs.getLong(prefName, 0);
    }

    /**
     * check the email address is valid or not.
     *
     * @param email pass email id in string
     * @return true when its valid otherwise false
     */
    public static boolean isEmailIdValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }

    /**
     * Get today's date in any format.
     *
     * @param dateFormat pass format for get like: "yyyy-MM-dd hh:mm:ss"
     * @return current date in string format
     */
    public static String getCurrentDate(String dateFormat) {
        Date d = new Date();
        String currentDate = new SimpleDateFormat(dateFormat).format(d.getTime());
        return currentDate;
    }

    /**
     * Use for getting your device id if available.
     *
     * @param context
     * @return your device id
     */
    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    // ------------------------------

    /**
     * remove all the preferences of your app. Note: only remove all which set
     * by using this sdk.
     *
     * @param context
     */
    public static void removeAllPrefrences(Context context, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }

    // ===========================================
    // future current

    /**
     * using for getting your current location
     *
     * @param context
     * @return current location
     */
    @SuppressWarnings("static-access")
    public static Location getCurrentLocation(Context context) {
        try {
            locationManager = (LocationManager) context
                    .getSystemService(context.LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            System.out.println("gps band chhe" + isGPSEnabled);
            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
//                Common.showGPSDisabledAlert("Please enable your location or connect to cellular network.", context);
            } else {
                if (isNetworkEnabled) {
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    // --------------------------------------------

    /**
     * use for apply pinchzoom functionality to your imageview
     *
     * @param mImageView on which you have to apply pinchzoom functionality
     */
    public static void applyPinchZoomImage(ImageView mImageView) {
        mImageView.setOnTouchListener(new PinchZoom());
    }

    // ----------------------------------------------

    /**
     * use for getting application Icon.
     *
     * @param mContext
     * @return Icon as drawable from the application
     */
    public static Drawable getAppIcon(Context mContext) {
        Drawable icon = null;
        final PackageManager pm = mContext.getPackageManager();
        String packageName = mContext.getPackageName();
        try {
            icon = pm.getApplicationIcon(packageName);
            return icon;
        } catch (NameNotFoundException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    /**
     * use for make local notification from application
     *
     * @param mContext
     * @param title    for the Notification
     * @param message  for the notification
     * @param mIntent  for open activity to open on touch of notification
     */
    @SuppressLint("NewApi")
    @SuppressWarnings({"static-access"})
    public static void sendLocatNotification(Context mContext, String title,
                                             String message, Intent mIntent) {
        System.out.println("called: " + title + " : " + message);
        int appIconResId = 0;
        PendingIntent pIntent = null;
        if (mIntent != null)
            pIntent = PendingIntent.getActivity(mContext, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        final PackageManager pm = mContext.getPackageManager();
        String packageName = mContext.getPackageName();
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = pm.getApplicationInfo(packageName,
                    PackageManager.GET_META_DATA);
            appIconResId = applicationInfo.icon;
        } catch (NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // Notification notification = new Notification.Builder(mContext)
        // .setSmallIcon(appIconResId).setWhen(System.currentTimeMillis())
        // .setContentTitle(title).setContentText(message)
        // .setContentIntent(pIntent).getNotification();

        Notification notification;
        if (mIntent == null) {
            notification = new Notification.Builder(mContext)
                    .setSmallIcon(appIconResId).setWhen(System.currentTimeMillis())
                    .setContentTitle(message)
                    .setStyle(new Notification.BigTextStyle().bigText(message))
                    .setAutoCancel(true)
                    .setContentText(message)
                    .setContentIntent(PendingIntent.getActivity(mContext, 0, new Intent(), 0))
                    .getNotification();

        } else {
            notification = new Notification.Builder(mContext)
                    .setSmallIcon(appIconResId).setWhen(System.currentTimeMillis())
                    .setContentTitle(message)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setStyle(new Notification.BigTextStyle().bigText(message))
                    .setContentIntent(pIntent).getNotification();
        }
        // Remove the notification on click
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        // Play default notification sound
        notification.defaults |= Notification.DEFAULT_SOUND;

        // Vibrate if vibrate is enabled
        notification.defaults |= Notification.DEFAULT_VIBRATE;

        NotificationManager manager = (NotificationManager) mContext
                .getSystemService(mContext.NOTIFICATION_SERVICE);
        // manager.notify(0, notification);
        manager.notify(R.string.app_name, notification);
    }

    // ---------------------------

    /**
     * use for sending mail to any id
     *
     * @param mContext
     * @param mailID   email id of whom to send mail
     */
    public static void sendMail(Context mContext, String mailID) {
        Uri uri = Uri.parse("mailto:" + mailID);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        mContext.startActivity(intent);
    }

    // ------------------------------

    /**
     * use for make disable sleep screen lock while application in use.
     *
     * @param mContext
     */
    @SuppressWarnings({"static-access"})
    public static void disableSleepMode(Context mContext) {
        System.out.println("disable");
        ((Activity) mContext).getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        keyguardManager = (KeyguardManager) mContext
                .getSystemService(Activity.KEYGUARD_SERVICE);
        lock = keyguardManager.newKeyguardLock(mContext.KEYGUARD_SERVICE);
        lock.disableKeyguard();
    }

    // ----------------------------

    /**
     * use for enable sleep screen while using application.
     */
    public static void enableSleepMode() {
        lock.reenableKeyguard();
    }

    // ------------------------------------------

    /**
     * use for open image from SDcard
     *
     * @param mContext
     * @param imagePath location of the image from your sdcard to open
     */
    public static void openImage(Context mContext, String imagePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(imagePath));
        intent.setDataAndType(uri, "image/*");
        mContext.startActivity(intent);
    }

    /**
     * use for open video fromSDcard
     *
     * @param mContext
     * @param videoPath location of video from SDcard to open
     */
    public static void openVideo(Context mContext, String videoPath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(videoPath));
        intent.setDataAndType(uri, "video/*");
        mContext.startActivity(intent);
    }

    /**
     * use for oepn any url in browser.
     *
     * @param mContext
     * @param url      to open in your mobile browser
     */
    public static void openURL(Context mContext, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        mContext.startActivity(intent);
    }

    // -----------------------------------

    /**
     * use to show address location pin on map.
     *
     * @param mContext
     * @param address  to show on map.
     */
    public static void showAddressOnMap(Context mContext, String address) {
        address = address.replace(' ', '+');
        Intent geoIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=" + address));
        mContext.startActivity(geoIntent);
    }

    /**
     * use to create folder in your directory
     *
     * @param mContext
     * @param path       location in which you have to create folder
     * @param folderName name of the folder
     */
    public static boolean createFolder(Context mContext, String path,
                                       String folderName) {
        File SDCardRoot = new File(path, folderName);
        if (!SDCardRoot.exists()) {
            return SDCardRoot.mkdir();
        }
//        else {
//            Toast.makeText(mContext, "fail", Toast.LENGTH_LONG).show();
//        }
        return false;
    }

    // ------------------------------------------

    /**
     * use image from URL.
     *
     * @param imgurl     of the image.
     * @param mImageView in which you have to set image
     */
    public static void downloadImageFromURL(final String imgurl,
                                            final ImageView mImageView) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    final Bitmap bitmap = BitmapFactory
                            .decodeStream((InputStream) new URL(imgurl)
                                    .getContent());
                    mImageView.post(new Runnable() {
                        @Override
                        public void run() {
                            if (bitmap != null) {

                                mImageView.setImageBitmap(bitmap);
                            }

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    /**
     * use to show datepicker
     *
     * @param mContext
     * @param format    of the date format
     * @param mTextView in which you have to set selected date
     */
    public static void showDatePickerDialog(final Context mContext,
                                            final String format, final TextView mTextView) {
        new DatePickerDialog(mContext, new OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
                dateTime.set(year, monthOfYear, dayOfMonth);

                mTextView.setText(dateFormatter.format(dateTime.getTime())
                        .toString());
            }
        }, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH),
                dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }


    /**
     * show timepicker
     *
     * @param mContext
     * @param mTextView formar of the time
     * @return show timepicker
     */
    public static void showTimePickerDialog(final Context mContext,
                                            final TextView mTextView) {
        new TimePickerDialog(mContext, new OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a");
                dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                dateTime.set(Calendar.MINUTE, minute);

                mTextView.setText(timeFormatter.format(dateTime.getTime())
                        .toString());
            }
        }, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE),
                false).show();
    }


    /**
     * get no of file counts from your directory
     *
     * @param format        of file extensions like .jpg, .png, .mp3, .mp4
     * @param directoryPath location of the directory
     * @return give counter integer
     */
    public static int getFileCounts(String format,
                                    String directoryPath) {
        int Sdcardcount = 0;
        File fileCount = new File(directoryPath);
        if (fileCount.exists()) {
            File[] list = fileCount.listFiles();
            for (File f : list) {
                String name = f.getName();
                if (name.endsWith(format))
                    Sdcardcount++;
            }
        }
        return Sdcardcount;
    }

    /**
     * calculate difference form two dates Note: both dates are in same format.
     *
     * @param mDate1 date 1
     * @param mDate2 date 2
     * @return date difference in long
     */
    public static long calculateDays(Date mDate1, Date mDate2) {
        return Math.abs((mDate1.getTime() - mDate2.getTime()) / (24 * 60 * 60 * 1000) + 1);
    }

    // tvLeft = (com.multidots.thirdeye.VerticalTextViewLeft)
    // findViewById(R.id.tvleft);
    // tvRight = (com.multidots.thirdeye.VerticalTextViewRight)
    // findViewById(R.id.tvright);

    // <com.multidots.thirdeye.VerticalTextViewLeft
    // android:id="@+id/tvleft"
    // style="@style/verticalTextStyle"
    // android:layout_width="wrap_content"
    // android:layout_height="fill_parent"
    // android:layout_above="@+id/tvbottom"
    // android:layout_below="@+id/tvtop"
    // android:background="@color/color_hint_back"
    // android:gravity="center"
    // android:text="@string/txt_left"
    // android:textColor="@android:color/darker_gray"
    // android:visibility="gone" />

    // <com.multidots.thirdeye.VerticalTextViewRight
    // android:id="@+id/tvright"
    // style="@style/verticalTextStyle"
    // android:layout_width="wrap_content"
    // android:layout_height="fill_parent"
    // android:layout_above="@+id/tvbottom"
    // android:layout_alignParentRight="true"
    // android:layout_below="@+id/tvtop"
    // android:background="@color/color_hint_back"
    // android:gravity="center"
    // android:text="@string/txt_right"
    // android:textColor="@android:color/darker_gray"
    // android:visibility="gone" />


    /**
     * Convert date in string format to Date format
     *
     * @param strdate which you have to convert in Date format
     * @param format  of the date like "yyyy-MM-dd"
     * @return date in Date format
     */
    public static Date stringToDate(String strdate, String format) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            date = formatter.parse(strdate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * use for getting device height
     *
     * @param mContext
     * @return height of your device
     */
    public static int getDeviceHeight(Context mContext) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay()
                .getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    /**
     * use for getting device width
     *
     * @param mContext
     * @return width of your device
     */
    public static int getDeviceWidth(Context mContext) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay()
                .getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }

    /**
     * get random number from your boundary
     *
     * @param number max number till you want get random.
     * @return random number
     */
    public static int getRandom(int number) {
        Random rand = new Random();
        return rand.nextInt(number);
    }

    /**
     * use for add postfix to the number like: 1st, 3rd..
     *
     * @param number which you have to add postfix
     * @return number in string with postfix
     */
    public static String getPostFixForNumber(int number) {
        String strValue = "";
        // int npos = Integer.valueOf(Pos);

        switch (number % 10) {
            case 1:
                strValue = (number % 100 == 11) ? "th" : "st";
                break;
            case 2:
                strValue = (number % 100 == 12) ? "th" : "nd";
                break;
            case 3:
                strValue = (number % 100 == 13) ? "th" : "rd";
                break;
            default:
                strValue = "th";
                break;
        }
        return number + strValue;
    }

    /**
     * make arraylist from "," separated string
     *
     * @param string "," separated string
     * @return array list
     */
    public static ArrayList<String> stringToArrayList(String string) {
        ArrayList<String> strValueList = new ArrayList<String>(
                Arrays.asList(string.split(",")));
        return strValueList;
    }

    /**
     * convert array list to "," separated string
     *
     * @param list array list
     * @return "," separated string
     */
    public static String arrayListToString(ArrayList<String> list) {
        String strValue = null;
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s + ",");
            strValue = sb.toString();
        }

        if (strValue.length() > 0
                && strValue.charAt(strValue.length() - 1) == ',') {
            strValue = strValue.substring(0, strValue.length() - 1);
        }
        return strValue;
    }

    /**
     * start background music
     *
     * @param mContext
     */
    public static void backgroundMusicStart(Context mContext) {
        mContext.startService(new Intent(mContext, BackgroundMusicService.class));
    }

    /**
     * stop background music
     *
     * @param mContext
     */
    public static void backgroundMusicStop(Context mContext) {
        mContext.stopService(new Intent(mContext, BackgroundMusicService.class));
    }

    /**
     * apply blur effect on bitmap
     *
     * @param mContext
     * @param bitmap   on which you have to apply effect
     * @return bitmap with effect
     */
    public static Bitmap blurEffectsOnBitmap(Context mContext, Bitmap bitmap) {
        return BlurEffect.fastblur(mContext, bitmap, 12);
    }

    /**
     * apply blue effect on darawable.
     *
     * @param mContext
     * @param drawable for applying effect
     * @param radius   for blur effect 0 to 25
     * @return drawable
     */
    public static Drawable blurEffectsOnDrawable(Context mContext, int drawable, int radius) {

        if (radius == 0)
            radius = 20;
        Bitmap blurBitmap;
        Bitmap bitmap = drawableTobitmap(mContext, drawable);
        blurBitmap = BlurEffect.fastblur(mContext, bitmap, radius);
        return new BitmapDrawable(blurBitmap);
    }

    /**
     * convert drawable to bitmap
     *
     * @param mContext
     * @param drawable for convert to bitmap
     * @return bitmap image
     */
    public static Bitmap drawableTobitmap(Context mContext, int drawable) {
        // TODO Auto-generated method stub
        Drawable myDrawable = mContext.getResources().getDrawable(drawable);
        return ((BitmapDrawable) myDrawable).getBitmap();
    }

    /**
     * convert bitmap to drawable
     *
     * @param mContext
     * @param bitmap   for convert to drawable
     * @return drawable image
     */
    public static Drawable bitmapToDrawable(Context mContext, Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }

	/*public static <T> void callFacebookLogin(Activity mContext, String appID) {
        // Intent mIntent = new Intent(mContext, className);
		// mIntent.putExtra("app_id", appID);
		// mContext.startActivity(mIntent);

		facebook = new Facebook(appID);
		mAsyncRunner = new AsyncFacebookRunner(facebook);
		restoreCredentials(facebook, mContext);
		if (!facebook.isSessionValid()) {
			System.out.println("In  if");

			LoginWithFaceBook(mContext);
		} else {
			System.out.println("In  if else ");
			Editor editor = mContext.getSharedPreferences(APP_FB_PREFS,
					Context.MODE_PRIVATE).edit();
			editor.clear();
			editor.commit();
		}
	}

	public static void LoginWithFaceBook(Activity mContext) {
		// TODO Auto-generated method stub
		facebook.authorize(mContext, PERMISSIONS, Facebook.FORCE_DIALOG_AUTH,
				new LoginDialogListener());
	}

	public static boolean restoreCredentials(Facebook facebook, Context mContext) {
		SharedPreferences sharedPreferences = mContext.getSharedPreferences(
				APP_FB_PREFS, Context.MODE_PRIVATE);
		facebook.setAccessToken(sharedPreferences.getString(TOKEN, null));
		facebook.setAccessExpires(sharedPreferences.getLong(EXPIRES, 0));
		return facebook.isSessionValid();
	}

	public static boolean saveCredentials(Context mContext) {
		Editor editor = mContext.getSharedPreferences(APP_FB_PREFS,
				Context.MODE_PRIVATE).edit();
		System.out.println("111 data fb:" + facebook.getAccessToken() + ":"
				+ facebook.getAccessExpires());
		editor.putString(TOKEN, facebook.getAccessToken());
		editor.putLong(EXPIRES, facebook.getAccessExpires());
		return editor.commit();
	}

	public static void getProfileInformation() {

		mAsyncRunner.request("me", new RequestListener() {
			public void onComplete(String response, Object state) {
				Log.d("Profile", response);
				System.out.println("FaceBook ni profile no response -->>"
						+ response);
				String json = response;
				try {
					JSONObject profile = new JSONObject(json);
					// getting name of the user
					final String name = profile.getString("name");
					// getting email of the user

					final String first_name = profile.getString("first_name");
					final String last_name = profile.getString("last_name");
					final String email = profile.getString("email");

					Editor editor = preferences.edit();

					editor.putString(Common.LOGIN_FIRST_NAME, first_name);
					editor.putString(Common.LOGIN_LAST_NAME, last_name);
					editor.putString(Common.LOGIN_EMAIL, email);

					editor.commit();

					System.out.println("name" + name + "email" + email
							+ first_name + last_name);

					// runOnUiThread(new Runnable() {
					// @Override
					// public void run() {
					// // Likes();
					// // Intent iii = new Intent(ctx, Logout.class);
					// // startActivity(iii);
					// // What u want to do after response come do here....
					// }
					// });

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onIOException(IOException e, Object state) {
			}

			@Override
			public void onFileNotFoundException(FileNotFoundException e,
					Object state) {
			}

			@Override
			public void onMalformedURLException(MalformedURLException e,
					Object state) {
			}

			@Override
			public void onFacebookError(FacebookError e, Object state) {
			}
		});
	}*/

    /**
     * Use current volume in application
     *
     * @param mContext
     */
    public static void setCurrentDeviceVolume(Context mContext) {
        AudioManager audioManager = (AudioManager) mContext
                .getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = audioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume,
                0);
    }

    /**
     * save your bitmap in your preference
     *
     * @param bitmap  which you have to store in preference
     * @param context
     * @param name    of preference for your image
     */
    public static void setBitmapToPreference(Bitmap bitmap, Context context, String name, String PREFS_FILE_NAME) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        setStringPrefrences(context, name, temp, PREFS_FILE_NAME);
    }


    /**
     * get bitmap which you stored in preference
     *
     * @param mContext
     * @param name            of bitmap preference
     * @param PREFS_FILE_NAME
     * @return bitmap image
     */
    public static Bitmap getBitmapFromPreference(Context mContext, String name, String PREFS_FILE_NAME) {
        try {
            String imageString = getStringPrefrences(mContext, name, PREFS_FILE_NAME);
            byte[] encodeByte = Base64.decode(imageString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    /**
     * get the version of the application
     *
     * @param mContext
     * @return version code.
     */
    public static int getAppVersionCode(Context mContext) {
        PackageInfo pInfo = null;
        try {
            pInfo = mContext.getPackageManager().getPackageInfo(
                    mContext.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pInfo.versionCode;
    }

    // -----------------------
    public static boolean isSDCardAvailable(Context mContext) {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

    }

    // --------------------------------------------------------------

    // -----------------------------------
    @SuppressLint("DefaultLocale")
    public static void onBlueTooth(String action) {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();
        if (action.toLowerCase().equalsIgnoreCase("on")) {
            if (!mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.enable();
            }
        }

        if (action.toLowerCase().equalsIgnoreCase("off")) {
            if (mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.disable();
            }
        }
    }

    public static void onWifi(Context mContext, String action) {
        WifiManager wm = ((WifiManager) mContext
                .getSystemService(Context.WIFI_SERVICE));
        if (action.toLowerCase().equalsIgnoreCase("on")) {
            if (!wm.isWifiEnabled()) {
                wm.setWifiEnabled(true);
            }
        }

        if (action.toLowerCase().equalsIgnoreCase("off")) {
            if (wm.isWifiEnabled()) {
                wm.setWifiEnabled(false);
            }
        }
    }

    public static void hideKeyboard(Context mContext, View v) {
        // TODO Auto-generated method stub
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * generate base64 from bitmap image
     *
     * @param mBitmap bitmap image
     * @return base64 string
     */
    public static String createBase64(Bitmap mBitmap) {
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos1);
        byte[] b1 = baos1.toByteArray();
        return Base64.encodeToString(b1, Base64.DEFAULT);
    }

    // --------------------------------------------------------------

    // scroll color set folowing things in listview

    // android:scrollbarSize="10dp"
    // android:scrollbarThumbVertical="@drawable/custom_scroll_style"

    // and create following custom_scroll_style.xml file in drawable

    // <?xml version="1.0" encoding="utf-8"?>
    // <shape xmlns:android="http://schemas.android.com/apk/res/android" >
    //
    // <gradient
    // android:angle="45"
    // android:endColor="#FF3401"
    // android:centerColor="#ff5c33"
    // android:startColor="#FF3401" />
    //
    // <corners android:radius="8dp" />
    // <size android:width="4dp"/>
    // <padding
    // android:left="0.5dp"
    // android:right="0.5dp" />
    //
    // </shape>

    /**
     * add post fix to any long digit like 10k for 10000
     *
     * @param args any digit
     * @return it will return "", "k", "m", "b", "t" with add post fix to digit.
     */
    public static String postFixForDigits(long args/* , TextView mTextView */) {
        long[] numbers = new long[]{args};
        long numb = 0;
        for (long number : numbers) {
            numb = number;
        }
        return format(numb);
    }

    //---------------play sound from zip
//	try {
//	    ZipFile zip = new ZipFile("/mnt/sdcard/Tree_Male_Eng.zip");
//	    ZipEntry entry = zip.getEntry("Tree1_Male_Eng_Snow.mp3");
//	    if (entry != null) {
//	        InputStream in = zip.getInputStream(entry);
//	        // see Note #3.
//	        File tempFile = File.createTempFile("Tree1_Male_Eng_Snow", ".mp3");
//	        FileOutputStream out = new FileOutputStream(tempFile);
//	        IOUtils.copy(in, out);
//	        
//	        System.out.println("111111 temp:"+tempFile.toString());
//	        MediaPlayer mediaPlayer = new MediaPlayer();
//		    mediaPlayer.setDataSource(tempFile.toString());
//		    mediaPlayer.prepare();
//		    mediaPlayer.start();
//	        // do something with tempFile (like play it)
//	    } else {
//	        // no such entry in the zip
//	    }
//	} catch (IOException e) {
//	    // handle your exception cases...
//	    e.printStackTrace();
//	}

    private static String format(double number) {
        String r = new DecimalFormat("##0E0").format(number);
        r = r.replaceAll("E[0-9]",
                suffix[Character.getNumericValue(r.charAt(r.length() - 1)) / 3]);
        while (r.length() > MAX_LENGTH || r.matches("[0-9]+\\.[a-z]")) {
            r = r.substring(0, r.length() - 2) + r.substring(r.length() - 1);
        }
        return r;
    }

    // -----------------------------------------------------------------
    public static String ImageToBase64(Uri uri, Context mContext) {
        String mBase64 = "";
        InputStream imageStream = null;
        try {
            imageStream = mContext.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        yourSelectedImage.compress(Bitmap.CompressFormat.JPEG, 90, bao);

        byte[] ba = bao.toByteArray();

        String ba1 = Base64.encodeToString(ba, 0);
        return mBase64;
    }

    /**
     * Opens android share dialog pass one of uri or shareText
     *
     * @param context
     * @param title
     * @param uri
     * @param shareText
     */
    public static void openShareDialog(Context context, String title, String uri, String shareText, String shareSubject) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, shareText);
        share.putExtra(Intent.EXTRA_SUBJECT, shareSubject);

        if (!TextUtils.isEmpty(uri)) {
            share.setType("image/*");
            share.putExtra(Intent.EXTRA_STREAM, Uri.parse(uri));
        }
        context.startActivity(Intent.createChooser(share, title));
    }

    /**
     * Changes mobile profile to "Silent" or "Vibrate" or "Normal" mode
     *
     * @param context
     * @param mode    types of mode  - "0- Silent"
     *                - "1 - Vibrate"
     *                - "2 - Normal"
     */
    public static void chooseProfile(Context context, int mode) {
        AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (mode == 0)
            audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        else if (mode == 1)
            audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        else if (mode == 2)
            audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }

    /**
     * Get Rounded cornered bitmap
     *
     * @param bitmap
     * @param roundPixels
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int roundPixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = roundPixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * Preventing any view from getting clicked for particular time
     *
     * @param view
     */
    public static void preventDoubleClick(final View view) {
        view.setEnabled(false);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, MIN_CLICK_INTERVAL);
    }

    /**
     * Function to display simple Alert Dialog or Toast
     *
     * @param context - application context
     * @param title   - alert dialog title
     * @param message - alert message
     * @param toast   - show as toast or dialog
     */
    public static void showAlertDialog(Context context, String title, String message, boolean toast) {
        if (toast) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        } else {

            AlertDialog alertDialog = null;
            if (!((Activity) context).isFinishing()) {
                if (alertDialog == null)
                    alertDialog = new AlertDialog.Builder(context).create();
                // Setting Dialog Title
                alertDialog.setTitle(title);
                // Setting Dialog Message
                alertDialog.setMessage(message);
                // Setting OK Button
                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                // Showing Alert Message
                alertDialog.show();
            }
        }
    }

    /**
     * Captures image in Common Directory
     *
     * @param mContext
     * @param CAMERA_CAPTURE_IMAGE_REQUEST_CODE
     * @param camera
     */
    public static void captureImage(Context mContext, int CAMERA_CAPTURE_IMAGE_REQUEST_CODE, String camera) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        if (camera.equalsIgnoreCase("Front")) {
            intent.putExtra("android.intent.extras.CAMERA_FACING", Camera.CameraInfo.CAMERA_FACING_FRONT);
        } else if (camera.equalsIgnoreCase("Back")) {
            intent.putExtra("android.intent.extras.CAMERA_FACING", Camera.CameraInfo.CAMERA_FACING_BACK);
        }

        ((Activity) mContext).startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    public static void pickImage(Context mContext, int CAMERA_PICK_IMAGE_REQUEST_CODE) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ((Activity) mContext).startActivityForResult(intent, CAMERA_PICK_IMAGE_REQUEST_CODE);
    }

    public static void previewCapturedImage(ImageView ivImagePreview) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);
            ivImagePreview.setImageBitmap(bitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (OutOfMemoryError outOfMemoryError) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            // downsizing image as it throws OutOfMemory Exception for larger images
            options.inSampleSize = 2;
            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);
            ivImagePreview.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void recordVideo(Context mContext, int CAMERA_CAPTURE_VIDEO_REQUEST_CODE, String camera) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        if (camera.equalsIgnoreCase("Front")) {
            intent.putExtra("android.intent.extras.CAMERA_FACING", Camera.CameraInfo.CAMERA_FACING_FRONT);
        } else if (camera.equalsIgnoreCase("Back")) {
            intent.putExtra("android.intent.extras.CAMERA_FACING", Camera.CameraInfo.CAMERA_FACING_BACK);
        }
        ((Activity) mContext).startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
    }

    public static void pickVideo(Context mContext, int CAMERA_PICK_VIDEO_REQUEST_CODE) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("video/*");
        ((Activity) mContext).startActivityForResult(intent, CAMERA_PICK_VIDEO_REQUEST_CODE);
    }

    public static void previewVideo(VideoView videoPreview) {
        try {
            videoPreview.setVideoPath(fileUri.getPath());
            // start playing
            videoPreview.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onSaveInstanceState(Bundle outState) {
        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    public static void onRestoreInstanceState(Bundle savedInstanceState) {
        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

//    public static Uri getPickUri(int requestCode, int resultCode, Intent data){
//        return data.getData();
//    }

    public static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {
        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), IMAGE_DIRECTORY_NAME);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }
        return mediaFile;
    }

    /**
     * getpath of picked image or video
     *
     * @param context
     * @param uri
     * @return
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static boolean isWebsiteUrlValid(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }

    public static ArrayList<String> getNameEmailDetails(Context context) {
        ArrayList<String> emlRecs = new ArrayList<String>();
        HashSet<String> emlRecsHS = new HashSet<String>();
        ContentResolver cr = context.getContentResolver();
        String[] PROJECTION = new String[]{ContactsContract.RawContacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_ID,
                ContactsContract.CommonDataKinds.Email.DATA,
                ContactsContract.CommonDataKinds.Photo.CONTACT_ID};
        String order = "CASE WHEN "
                + ContactsContract.Contacts.DISPLAY_NAME
                + " NOT LIKE '%@%' THEN 1 ELSE 2 END, "
                + ContactsContract.Contacts.DISPLAY_NAME
                + ", "
                + ContactsContract.CommonDataKinds.Email.DATA
                + " COLLATE NOCASE";
        String filter = ContactsContract.CommonDataKinds.Email.DATA + " NOT LIKE ''";
        Cursor cur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, PROJECTION, filter, null, order);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    // names comes in hand sometimes
                    String name = cur.getString(1);
                    String emlAddr = cur.getString(3);

                    // keep unique only
                    if (emlRecsHS.add(emlAddr.toLowerCase())) {
                        emlRecs.add(name + ", " + emlAddr);
                    }
                } while (cur.moveToNext());
            }
            cur.close();
        }
        return emlRecs;
    }

    /**
     * Captures the view and returns bitmap
     *
     * @param v
     * @return bitmap of view captured
     */
    public static Bitmap captureView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
//        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }


    public static void showNETWORDDisabledAlert(final Context ctx) {
        AlertDialog alert;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
        alertDialogBuilder.setMessage("Let the app use data connectivity.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent callGPSSettingIntent = new Intent(
                                android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS);
                        ctx.startActivity(callGPSSettingIntent);
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alert = alertDialogBuilder.create();
        alert.show();
    }

    public static void showGPSDisabledAlert(String msg, final Context ctx) {
        AlertDialog alert;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
        alertDialogBuilder.setMessage(msg).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent callGPSSettingIntent = new Intent(
                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        ctx.startActivity(callGPSSettingIntent);
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alert = alertDialogBuilder.create();
        alert.show();
    }

    @SuppressWarnings("static-access")
    public static boolean getGpsStatus(Context context) {
        locationManager = (LocationManager) context
                .getSystemService(context.LOCATION_SERVICE);

        // getting GPS status
        isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        System.out.println("gps band chhe" + isGPSEnabled);
        return isGPSEnabled;
    }

    public static char getRandomCharacter() {
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');

        return c;
    }

    public static int pickColor(View view, int x, int y)
            throws NullPointerException {

        int red = 0;
        int green = 0;
        int blue = 0;
        int color = 0;

        int offset = 1; // 3x3 Matrix
        int pixelsNumber = 0;

        int xImage = 0;
        int yImage = 0;


        ImageView imageView = (ImageView) view;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap imageBitmap = bitmapDrawable.getBitmap();


        xImage = (int) (x * ((double) imageBitmap.getWidth() / (double) imageView.getWidth()));
        yImage = (int) (y * ((double) imageBitmap.getHeight() / (double) imageView.getHeight()));


        for (int i = xImage - offset; i <= xImage + offset; i++) {
            for (int j = yImage - offset; j <= yImage + offset; j++) {
                try {
                    color = imageBitmap.getPixel(i, j);
                    red += Color.red(color);
                    green += Color.green(color);
                    blue += Color.blue(color);
                    pixelsNumber += 1;
                } catch (Exception e) {
                    //Log.w(TAG, "Error picking color!");
                }
            }
        }
        red = red / pixelsNumber;
        green = green / pixelsNumber;
        blue = blue / pixelsNumber;

        return Color.rgb(red, green, blue);
    }

    public static String beautyHexString(String hexString) {
        if (hexString.length() < 2) {
            return "0".concat(hexString);
        } else {
            return hexString;
        }
    }

    public static void getRemoteFileSize(Context context, String url) {
        new DownloadFile(context, url, "", false).execute();
    }

    public static void downloadRemoteFile(Context context, String url, String filename) {
        new DownloadFile(context, url, filename, true).execute();
    }

    // -------------------------------------
    private String getRealPathFromURI(Uri targetUri, Context mContext) {
        // TODO Auto-generated method stub
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = mContext.getContentResolver().query(targetUri, proj,
                null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void showNETWORDDisabledAlertToUser(final Context ctx) {
        AlertDialog alert;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
        alertDialogBuilder.setMessage("Let the app use data connectivity.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent callGPSSettingIntent = new Intent(
                                android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS);
                        ctx.startActivity(callGPSSettingIntent);
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alert = alertDialogBuilder.create();
        alert.show();
    }

    private void showGPSDisabledAlertToUser(String msg, final Context ctx) {
        AlertDialog alert;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
        alertDialogBuilder.setMessage(msg).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent callGPSSettingIntent = new Intent(
                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        ctx.startActivity(callGPSSettingIntent);
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alert = alertDialogBuilder.create();
        alert.show();
    }
}


