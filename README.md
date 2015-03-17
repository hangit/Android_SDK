This readme file contains instructions to download and run the HangIT SDK sample applications.

Contents
* Prerequisites
* Additional references
* Download the sample application
* Run the sample application
* Modular options

Prerequisites
* Android Studio.
* An Android device or emulator.
* Basic understanding of Android development.

Additional references
* An integration guide can be found in the /IntegrationGuides directory.
* This document references AndroidManifest content in the /AndroidManifest directory.  This directory contains differing sample AndroidManifest.xml files that demonstrate modular options for the HangITSDK.

Download the sample application
* Pull the /PublisherExample directory to your local machine.
* Open the /PublisherExample by opening Android Studio and selecting File -> Open -> Choose the /PublisherExample directory -> "Choose"
* If your directory structure in the Android perspective is not displaying properly.  Click the "Sync Project with Gradle Files" option.
* Verify the project has no compilation errors by selecting Build -> Make project.
* Replace the [YOUR SDK KEY GOES HERE] text in /values/strings.xml with the sdk key provided to you by HangIT.

Run the sample application
* Start an emulator or connect a device running Android API 12 or higher.  For information on how to enable a device for development see http://developer.android.com/tools/device.html.
* Run the application by selecting Run -> Run App or by pressing ctrl+r.
* Choose the device or emulator to run the application and wait for the application to install and start.
* If HangIT is running successfully we should eventually see a Toast indicating "HangIT is running".

**Modular Option**

The instructions above allow the HangIT SDK to run in the background of the application.  The HangIT SDK also includes modular options.

Modular : Notifications - The notification feature sends notifications to the device when a HangIT place is encountered.
* To enable the sample application to transmit notifications replace the contents of the AndroidManifest.xml file with the contents found in /AndroidManifest/Notifications.xml.
* Run the application by selecting Run -> Run app or by pressing ctrl+r.
* When the device encounters a HangIT place, a notification will be sent to the device and viewable in the notifications tray.
* Note: The key configuration in the AndroidManifest.xml file is <meta-data android:name="com.hangit.android.hangit_sdk.notification_icon" android:value="ic_launcher" />.  HangIT SDK will only transmit notifications if a notification_icon is supplied.  ic_launcher can be replaced with any image.  This image will accompany the notification in the Android notification tray.

Modular : Notification CallBack - The notification callback feature enables the application to display an activity when a notificiation is received by the device.
* To enable the sample application to accept notification callbacks, replace the contents of the AndroidManifest.xml file with the contents found in /AndroidManifest/NotificationCallBack.xml.
* Run the application by selecting Run -> Run app or by pressing ctrl+r.
* When the device encounteres a HangIT place, a notification will be sent to the device.  When down pressing the notification the NotificationCallBack activity will open.
* Note: The key configuration in the AndroidManifest.xml file is <meta-data android:name="com.hangit.android.hangit_sdk.notification_activity" android:value="com.hangit.android.publisherexample.NotificationCallback" />.  "com.hangit.android.publisherexample.NotificationCallback" can be replaced with any valid activity as long as the activity is referenced with an <activity> tag in the <application> node of the AndroidManifest.xml file.
* Note 2: The HangIT SDK includes additional preset notification callback activities: "com.hangit.android.hangit_sdk.UIWebViewActivity" and "com.hangit.android.hangit_sdk.UIADUnitActivity" can be used to replace "com.hangit.android.publisherexample.NotificationCallback".  To utilize these activities a corresponding <activity> node is required in the <application> node of the AndroidManifest.xml.  Example: "<activity android:name="com.hangit.android.hangit_sdk.UIWebViewActivity" android:screenOrientation="portrait"/>".  UIWebViewActivity is a callback activity that displays a full page web view of the location entered.  UIADUNitActivity is a callback activity that displays HangIT's ad unit.  UIADUnitActivity does have a google map component and will require a valid google api key.  Add the following tag to the <application> node of the AndroidManifest.xml file with a valid key in place of [YOUR API KEY] : <meta-data android:name="com.google.android.maps.v2.API_KEY" android:value="[YOUR API KEY]" />

Modular : Map & Wallet - The Map & Wallet is a preset view provided by the HangIT SDK displaying HangIT locations in the area via a Google Map and a Wallet list.
* To display the Map & Wallet component replace the contents of the AndroidManifest.xml file with the contents of /AndroidManifest/MapAndWallet.xml.
* Run the application by selecting Run -> Run app or by pressing ctrl+r.
* The application will display a button "Launch HangIT Main Fragment".  Click the button and the Map & Wallet should be displayed.
* Note: The Map & Wallet feature does have a google map component and will require a valid google api key.  Add the following tag to the <application> node of the AndroidManifest.xml file with a valid key in place of [YOUR API KEY] : <meta-data android:name="com.google.android.maps.v2.API_KEY" android:value="[YOUR API KEY]" />

Modular : Clear user - The clear user features allows HangIT SDK to reset.  Any place encountered in the device's history will be cleared for a future encounter.
* Replace the contents of sample application's AndriodManifest.xml file with the contents from /AndroidManifest/ClearUser.xml
* Run the application.  The application will clear the user and error out.


