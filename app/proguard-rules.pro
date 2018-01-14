# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\user\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


#we choose to keep the function getCurrentUser() to obtain the User who connected to the app
-keep class * com.google.firebase.auth.FirebaseAuth{
public FirebaseUser getCurrentUser();
}

#in our app we have to know which User is connected to the Firebase and what is the UID of this user.
-keep class *import com.google.firebase.auth.FirebaseUser{
public abstract String getUid();
}

#we choose to keep User class and his private fields because we wanted to know
#which field User hold and how to build uppon them more things in the future
-keepclassmembers class com.example.shahar.ex3_mt.User {
    private <fields>;
}