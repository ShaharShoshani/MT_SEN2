package com.example.shahar.ex3_mt;

/**
 * Created by user on 15/01/2018.
 */

import org.junit.Before;
import org.junit.runner.RunWith;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.fail;


@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class UiAutomatorTest {

    private static final String BASIC_SAMPLE_PACKAGE
            = "com.example.shahar.ex3_mt";
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String STRING_TO_BE_TYPED = "UiAutomator";
    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(getInstrumentation());
        // Start from the home screen
        mDevice.pressHome();
    }
    @org.junit.Test
    public void test()
    {
        openApp("com.example.shahar.ex3_mt");
        try {
            UiObject2 email = waitForObject(By.res("com.example.shahar.ex3_mt:id/email"));
            email.setText("wow");
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void openApp(String p)
    {
        Context con = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = con.getPackageManager().getLaunchIntentForPackage(p);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        con.startActivity(intent);
    }

    private UiObject2 waitForObject(BySelector selector) throws InterruptedException {
        UiObject2 object = null;
        int timeout = 30000;
        int delay = 1000;
        long time = System.currentTimeMillis();
        while (object == null) {
            object = mDevice.findObject(selector);
            Thread.sleep(delay);
            if (System.currentTimeMillis() - timeout > time) {
                fail();
            }
        }
        return object;
    }
}