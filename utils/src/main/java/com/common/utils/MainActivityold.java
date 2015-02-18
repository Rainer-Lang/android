package com.common.utils;

public class MainActivityold { //extends Activity {

//    TextView text1, text2, text3, text4, text5;
//    Context mContext;
//    ImageView img1, img2;
//    Activity mActivity;
//
//    @SuppressLint("SdCardPath")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mContext = MainActivity.this;
//        mActivity = MainActivity.this;
//
//        img1 = (ImageView) findViewById(R.id.imageView1);
////		Common.downloadImageFromURL("http://www.sbsmobile.com/siteimg/cat-menu/galaxyS5.png", img1);
////		Toast.makeText(mContext, "data: "+Common.postFixForDigits(100), Toast.LENGTH_LONG).show();
////		String root = "/mnt/sdcard/Android/data/";
////		Common.createFolder(mContext, root, "com.thirdeyegame.app");
////		try {
////			URI uri = new URI("http://cnet4.cbsistatic.com/hub/i/2011/10/27/a66dfbb7-fdc7-11e2-8c7c-d4ae52e62bcc/android-wallpaper5_2560x1600_1.jpg");
////			Common.ImageToBase64(uri, mContext);
////		} catch (URISyntaxException e) {
////			e.printStackTrace();
////		}
//        // Utils.callIntent(MainActivity.this,Login.class);
//        // showDialog(DIALOG_DATE);
//        // text1 = (TextView) findViewById(R.id.text1);
//        // text1 = (com.common.utils.VerticalTextViewLeft)
//        // findViewById(R.id.text1);
//        // text2 = (com.common.utils.VerticalTextViewRight)
//        // findViewById(R.id.text2);
//        // // text2 = (TextView) findViewById(R.id.text2);
//        text3 = (TextView) findViewById(R.id.text3);
//        text4 = (TextView) findViewById(R.id.text4);
//        text5 = (TextView) findViewById(R.id.text5);
//
//        text4.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Is Url: fsddfsdfs valid? " + Common.isWebsiteUrlValid("fsddfsdfs"), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        text5.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Is Url: https://android-arsenal.com/ valid? " + Common.isWebsiteUrlValid("https://android-arsenal.com/"), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        text3.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                ArrayList<String> nameEmailDetails = Common.getNameEmailDetails(MainActivity.this);
//                String strNameEmail = "";
//                for (int i = 0; i < nameEmailDetails.size(); i++) {
//                    strNameEmail += "\n\n " + nameEmailDetails.get(i);
//                }
//
//                text4.setText(strNameEmail);
////                Common.captureImage(MainActivity.this, 111, "Back");
////                Intent intent = new Intent(MainActivity.this, LinkedInActivity.class);
////                intent.putExtra("APIKEY", "75q8m3nmdttkn9");
////                intent.putExtra("APISECRET", "XMVxlM1YiRWabQzy");
////                startActivityForResult(intent, 101);
//
////              Common.onBlueTooth("on");
////				Common.callFacebookLogin(mActivity, "605560999527824");
////				Common.callFacebookLogin(mActivity, "165779420229798");
////				String url = "https://twitter.com/intent/tweet?source=webclient&text=TWEET+THIS!";
////				String newurl = "https://twitter.com/intent/tweet?text="
////						+ "#York University I FOUND #Software Developer @ZENPEAK JOB ALERTS  I'M INTERESTED! link to http://geemode.projectsmd.in/links/track?key=0a75523f2eac570d711546fef06d6d6253e37cb47b630&id=2471&url=http://bit.ly/1oveYZx";
////				Intent i = new Intent(Intent.ACTION_VIEW);
////				i.setData(Uri.parse(newurl));
////				startActivity(i);
////				Common.hideKeyboard(mContext, v);
//            }
//        });
////		System.out.println("68" + Common.isSDCardAvailable(mContext));
////		Button b = (Button) findViewById(R.id.btnSelectPhoto);
////		b.setOnClickListener(new OnClickListener() {
////
////			@Override
////			public void onClick(View v) {
////				Common.hideKeyboard(mContext, v);
////			}
////		});
//
//        // text4.setOnClickListener(new OnClickListener() {
//        //
//        // @Override
//        // public void onClick(View v) {
//        // Common.onWifi(mContext, "on");
//        // }
//        // });
//
//        // Intent i = new Intent(mContext, MainActivity.class);
//        // Common.sendLocatNotification(mContext, "test",
//        // "dfvua efy fgeuif gyewfgurewhfuijbrqeuibregbregregbrgbrebgr truireguiregui ",
//        // i);
//
//        // text5 = (TextView) findViewById(R.id.text5);
//        // img2 = (ImageView) findViewById(R.id.imageView2);
//
//        // Common.callFacebookLogin(mContext,
//        // Login.class,"605560999527824");
//        // app_id
//        // img2.setImageDrawable(Common.blurEffectsOnDrawable(mContext,
//        // R.drawable.android_icon, 0));
//
//        // Common.isSDCardAvailable(mContext);
//        // System.out.println("36 " + Common.getAppVersionCode(mContext));
//        // img1.setImageDrawable(Common.getAppIcon(mContext));
//
//        // Common.backgroundMusicStart(mContext);
//        // Common.setCurrentDeviceVolume(mContext);
//
//        // Common.path =
//        // Uri.parse("android.resource://com.common.utils/raw/back_music.mp3");
//        // System.out.println("path:" + Common.path);
//        // Common.path = R.raw.back_music;
//        // startService(new Intent(mContext, BackgroundMusicService.class));
//        // Common.backgroundMusicStart(mContext);
//
//        // text5.setOnClickListener(new OnClickListener() {
//        //
//        // @Override
//        // public void onClick(View v) {
//        // // TODO Auto-generated method stub
//        // Common.showDatePickerDialog(mContext, "yyyy-MM-dd HH:mm",
//        // text1).show();
//        // }
//        // });
//
//        // System.out.println("1664 "
//        // + Common.getCounts(mContext, ".png",
//        // "/mnt/sdcard/Pictures/Screenshots/"));
//        // Common.pinchZoomImage(img1);
//
//        // Uri uri = Uri.parse("tel: 9033801862");
//        // Intent it = new Intent(Intent.ACTION_CALL, uri);
//        // this.startActivity(it);
//
//        // Uri uri = Uri.parse("geo: 38.899533, -77.036476");
//        // Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        // startActivity(intent);
//
//        // String address =
//        // "Torrent-AMA Management Centre,\nCore-AMA Management House,\nATIRA Campus,\nDr. Vikram Sarabhai Marg,\nAhmedabad 380 015.";
//        // // Get
//        // // address
//        // address = address.replace(' ', '+');
//        // Intent geoIntent = new Intent(android.content.Intent.ACTION_VIEW,
//        // Uri.parse("geo:0,0?q=" + address));
//        // startActivity(geoIntent);
//
//        // File SDCardRoot = Environment.getExternalStorageDirectory();
//        // SDCardRoot = new File("/sdcard/hiratTest/", "new");
//        // if (!SDCardRoot.exists()) {
//        // SDCardRoot.mkdir();
//        // }
//
//        // Common.disableAutoLock(mContext);
//
//        // Bitmap bm = Common.drawableTobitmap(mContext,
//        // R.drawable.launch);
//        // Bitmap bm = Common.drawableTobitmap(mContext,
//        // R.drawable.airdroid);
//        // System.out.println("111 drawableTobitmap:" + bm);
//        // // Bitmap blur = Common.blurEffectsOnBitmap(mContext, bm);
//        // Drawable blur = Common.blurEffectsOnDrawable(mContext,
//        // R.drawable.airdroid);
//        // System.out.println("111 blurEffectsOnBitmap:" + blur);
//
//        // img1.setImageBitmap(blur);
//        // img1.setImageDrawable(blur);
//
//        // System.out.println("device id:" + Common.getDeviceId(mContext));
//        // text1.setText(Common.getDeviceId(mContext));
//        // text2.setText(Common.getCurrentDate("yyyy-MM-dd HH:mm"));
//        // text3.setText(Common.getCurrentDate("yyyy-MM-dd HH:mmZ"));
//        // text4.setText(Common.getCurrentDate("yyyy-MM-dd HH:mm:ss.SSSZ"));
//        // text5.setText(Common.getCurrentDate("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
//
//        // System.out.println("latitude:"
//        // + Common.getCurrentLocation(mContext).getLongitude());
//        // System.out.println("longitude:"
//        // + Common.getCurrentLocation(mContext).getLatitude());
//
//        // Intent i = new Intent(mContext, MainActivity.class);
//        // Common.locatNotification(mContext, "Hello", "testing", i);
//
//        // img.setBackgroundDrawable(Common.getAppIcon(mContext));
//
//        // Common
//        // .downloadImageFromURL(
//        // "https://lh3.googleusercontent.com/-tUnSh4hL1b0/AAAAAAAAAAI/AAAAAAAApfY/MD4kWdo33YQ/s100-c-k-no/photo.jpg",
//        // img1);
//        // Common.downloadImageFromURL(
//        // "http://aerogear.org/img/library_android.png", img2);
//        // Common.downloadImageFromURL(
//        // "http://www.360technosoft.com/images/android_icngreen.png",
//        // img3);
//
//        // Date d = new Date();
//        // String future1 = (String) DateFormat.format("dd/MM/yyyy",
//        // d.getTime());
//        // Date current = null,future = null;
//        // SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        //
//        // try {
//        // future = formatter.parse("29/08/2014");
//        // current = formatter.parse("25/08/2014");
//        // } catch (ParseException e) {
//        // // TODO Auto-generated catch block
//        // e.printStackTrace();
//        // }
//        // ArrayList<String> list = new ArrayList<String>();
//        // list.add("hello");
//        // list.add("hi");
//        // list.add("how r you");
//        // list.add("m fine");
//        //
//        // System.out.println("stringToArrayList:"
//        // + Common.stringToArrayList("hwllo, hi, how r, you"));
//        // System.out.println("arrayListToString:"
//        // + Common.arrayListToString(list));
//
//        // System.out.println("differance:"
//        // + Common.calculateDays(Common.stringToDate(
//        // "2014/08/29 03:05", "yyyy/MM/dd"), Common
//        // .stringToDate("2014/08/26 00:00", "yyyy/MM/dd HH:mm")));
//
//        // System.out.println("height: "+Common.getDeviceHeight(mContext)+"  weidth:"+Common.getDeviceWidth(mContext));
//
//        // System.out.println("random:"+Common.getRandom(10));
//        // System.out.println(Common.getCurrentDate("yyyy-MM-dd"));
//        // System.out.println(Common.getCurrentDate("yyyy-MM-dd HH:mm"));
//        // System.out.println(Common.getCurrentDate("yyyy-MM-dd HH:mmZ"));
//        // System.out.println(Common.getCurrentDate("yyyy-MM-dd HH:mm:ss.SSSZ"));
//        // System.out.println(Common.getCurrentDate("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if (requestCode == 101) {
//                try {
//                    JSONObject personJson = new JSONObject(data.getStringExtra("RESPONSE"));
//                    text4.setText(" Email id : " + personJson.getString("emailAddress") + " \n Name : " + personJson.getString("firstName") + "  " + personJson.getString("lastName") + " \n id : " + personJson.getString("id"));
//                    JSONObject skillsJson = personJson.getJSONObject("skills");
//                    JSONArray skillsArray = skillsJson.getJSONArray("values");
//                    String personSkills;
//                    StringBuilder sbSkills = new StringBuilder();
//                    if (skillsArray != null && skillsArray.length() > 0) {
//                        for (int i = 0; i < skillsArray.length(); i++) {
//                            sbSkills.append(skillsArray.getJSONObject(i).getJSONObject("skill").getString("name") + ",");
//                        }
//                        personSkills = sbSkills.toString();
//                        if (personSkills.length() > 0
//                                && personSkills.charAt(personSkills.length() - 1) == ',') {
//                            personSkills = personSkills.substring(0, personSkills.length() - 1);
//                        }
//                        text5.setText(personSkills);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}