
<h1>Hangit Android SDK Integration Guide</h1>

<h2>Overview</h2>

You can use the HangIt platform to leverage location awareness for your Android application.  

 - The HangIt SDK monitors and records device location and triggers events when the boundary of a targeted location is crossed.
 - The SDK also delivers local push notifications and displays rich messages you have configured in HangIt's Campaign Portal.
 - The SDK allows your application to observe these events and deep link back into the application.

<h2>Implementations</h2>

There are three basic implementations of the HangIt SDK, indicated in grey below. Each provides a different level of customization for the developer.

 1. Full Message Flow ** RECOMMENDED **
 2. Local Notification Only
 3. Capture the Trigger on the Location Event Only

This Android Integration Guide will cover the fastest way to get started with HangIt using the Event Trigger only (option #3 above), and will cover the full message flow in later sections.

![enter image description here](https://lh3.googleusercontent.com/-Xsf4iDY30-o/VVDwY9-VDSI/AAAAAAAAA4Y/Gp6B51XOvII/s0/Flow+-+Full+Service.png "Flow - Full Service.png")

The flow chart above illustrates a full message flow which presents a local event trigger to your app, initiates a local push notification to the user, and loads a "rich" message.  It is also possible to only implement the event trigger and local push notification, or simply the event notification to the app.

<h2>Getting Started</h2>

>**Note:** For more information and sample code, please refer to our sample application at https://github.com/hangit/Android_SDK_2.

The following steps outline the most basic implementation of the HangIt SDK.  Instructions for additional functionality are included later in this document.

A successful implementation of the Hangit Android SDK requires the following steps:

 - Configure your Android manifest.xml to set permissions and references to Hangit SDK services (identify which activity to launch when Hangit location notification event is received by the app.)
 - Set the Hangit SDK API key
 - Setup the SDK to run in the background of your app.
	 - Specify [Activity](http://developer.android.com/reference/android/app/Activity.html) that will initialize the background SDK service
	 - Configure Activity listed in Android manifest that will accept the intent from the event notification and its related set of data points (describes campaign event, or offer).

<h3>Building the Project</h3>

For the purpose of example in this SDK, [Gradle](https://gradle.org/whygradle/) is used to manage the language build and dependencies.  However, it should be noted that Gradle usage is optional, and it is possible to use a standard .aar file using [Eclipse/maven](http://www.iphonedroid.com/blog/en/utilizar-ficheros-aar-en-eclipse/#.VUedf9pVikp), for example.

See the following [article](http://www.iphonedroid.com/blog/en/utilizar-ficheros-aar-en-eclipse/#.VUedf9pVikp) for more information.

<h3>Installing</h3>

>**Info:** Sample code can be found in our repository https://github.com/hangit/Android_SDK_2

To install the sdk framework

 - Add a reference to the project
 - Add the hangit_sdk library to your project's **/libs** folder
 - Add a dependency for [Google Play Services](https://play.google.com/store?hl=en)

**Adding the Reference:**

To add the reference

 - Add a flatDir repository reference to the build.gradle file in the Android application.

```objective-c
Build.Gradle repositories
repositories {
    flatDir {
        dirs 'libs'
    }
}
```

**Adding the hangit_sdk Libray:**

 - Downlad the hangit_sdk.aar file. The hangit_sdk.aar file is located in the HangIt repository at https://github.com/hangit/Android_SDK_2/tree/master/PublisherExample/app/libs .
 - Copy the `hang_sdk.aar` file to your project's **/libs** folder.

**Adding the dependency:**

Without Google Play services, the `AndroidManifest.xml` file will throw a compilation error on meta-data com.google.android.gms.version.

Use the code below to add the dependency

```objective-c
Build.Gradle dependencies
dependencies {
    compile(name:'hangit_sdk', ext:'aar')
    compile 'com.google.android.gms:play-services:6.5.87'
}
```



<h2>Setting Up the Android Manifest</h2>

You will configure the [manifest](http://developer.android.com/guide/topics/manifest/manifest-intro.html) to set appropriate permissions and declare the services required by your app and the SDK.

Set the permissions 

```objective-c
AndroidManifest.xml <manifest>

<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="com.google.android.providers.gsf.permission.READ_GSERVICES"/>
<uses-permission android:name="com.google.android.gms.permission.ACTIVITY_RECOGNITION" />
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>
<meta-data android:name="com.google.android.gms.version" android:value="@integer/google_play_services_version" />
```

Add references to the hangit_sdk services within the node.

```objective-c
AndroidManifest.xml Application

<service android:name="com.hangit.android.hangit_sdk.ServiceHangITLocation">
</service>
<service android:name="com.hangit.android.hangit_sdk.ServiceActivityRecognition" />
<receiver android:name="com.hangit.android.hangit_sdk.ReceiverBootup" >
    <intent-filter>
        <action android:name="android.intent.action.BOOT_COMPLETED" />
        <action android:name="android.intent.action.QUICKBOOT_POWERON" />
    </intent-filter>
</receiver>
```

Add references to relevant activities.  Here BackgroundOnly is used as an example.  

```objective-c
AndroidManifest.xml Application

<activity
	android:name=".BackgroundOnly"
	android:label="Background Only" >
	<intent-filter>
		<action android:name="android.intent.action.MAIN" />
		<category android:name="android.intent.category.LAUNCHER" />
	</intent-filter>
</activity>
```

<h3>Definition of Services</h3>

`ServiceHangITLocation`
: Interacts with the Android device to obtain locations updates via GPS, wifi, and network.

`ServiceActivityRecognition`
: Interacts with the Android device's activity messages for location accuracy.

`ReceiverBootup`
: Allows our services to start when the device boots up.  **Important:** Without this permission the background SDK service will not run, if the device is restarted.


<h3>Add the SDK API Key</h3> 

>**Info:** To obtain a HangIt SDK key, go to http://portal.hangit.com

Add the HangIt SDK key to a reference file. In the provided sample code the SDK key is stored in the [strings.xml](http://developer.android.com/guide/topics/resources/string-resource.html) file.

```objective-c
String.xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="hangit_sdk_key">[YOUR API KEY FROM HANGIT PORTAL]</string>
</resources>
```

<h2>Running hangit_sdk client in the background of your application</h2>

To configure the SDK client to run in the background of your app. the following is required:

 - Configure the method calls on the Activity that will be used to start the SDK background service (`HangITClient.addEventListener()`).
 - Set the method that will start the background SDK services (`HangITClient.initialize()`).

 

<h3>Starting the Background Service</h3>

Add the following code snippet to the `onCreate` method of the Activity intended to start the HangIt background services.  This will add an EventListener to listen for Hangit events from the SDK.

```objective-c
MainActivity onCreate
ManagerGeneral.getHangITClient().addEventListener(this);
```
The `HangITClient.initialize()` method is required to start the hangItClient background services. Add the following code snippet after the configuration method calls in the `onCreate` method of the Activity intended to start HangIT background services.

```objective-c
MainActivity onCreate
ManagerGeneral.getHangITClient().initialize(this, getString(R.string.hangit_sdk_key));
```

<h3>Creating the Activity Class</h3>

After an event is received and the HangItClient sends a notification to the device, the notification will open the activity configured in Android Manifest at `"Meta-data android:name=com.hangit.android.hangit_sdk.notification_activity"` under the `<service android:name="com.hangit.android.hangit_sdk.ServiceHangITLocation">` node. The HangItClient will pass key values on an intent to the `NotificationActivityClass.` The code below outlines a sample Activity class accepting the intent with a list of data points available.

```objective-c
OfferNotificationActivity.java
public class OfferNotificationActivity extends Activity {
        private static final String KEY_PLACE_ID = "com.hangit.android.hangit_sdk.KEY_OFFER_ID";
        private static final String KEY_CENTROID_LAT = "com.hangit.android.hangit_sdk.KEY_CENTROID_LAT";
        private static final String KEY_CENTROID_LONG = "com.hangit.android.hangit_sdk.KEY_CENTROID_LONG";
        private static final String KEY_CAMPAIGN_NAME = "com.hangit.android.hangit_sdk.KEY_CAMPAIGN_NAME";
        private static final String KEY_EVENT_NAME = "com.hangit.android.hangit_sdk.KEY_EVENT_NAME";
        private static final String KEY_EVENT_ID = "com.hangit.android.hangit_sdk.KEY_EVENT_ID";
        private static final String KEY_CENTROID_RADIUS = "com.hangit.android.hangit_sdk.KEY_CENTROID_RADIUS";
        private static final String KEY_ALERT_TEXT = "com.hangit.android.hangit_sdk.KEY_ALERT_TEXT";
        private static final String KEY_URL = "com.hangit.android.hangit_sdk.KEY_URL";
        private static final String TAG = "OfferNotificationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_offer_notification);

        Intent intent = getIntent();

        int placeId = intent.getIntExtra(KEY_PLACE_ID, 0);
        double lat = intent.getDoubleExtra(KEY_CENTROID_LAT, 0);
        double lng = intent.getDoubleExtra(KEY_CENTROID_LONG, 0);
        String campaignName = intent.getStringExtra(KEY_CAMPAIGN_NAME);
        String eventName = intent.getStringExtra(KEY_EVENT_NAME);
        int eventId = intent.getIntExtra(KEY_EVENT_ID, 0);
        double radius = intent.getDoubleExtra(KEY_CENTROID_RADIUS, 0);
        String alertText = intent.getStringExtra(KEY_ALERT_TEXT);
        String url = intent.getStringExtra(KEY_URL);
    }
}
```

	

> Written with [StackEdit](https://stackedit.io/).
