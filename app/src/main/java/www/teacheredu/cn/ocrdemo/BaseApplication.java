package www.teacheredu.cn.ocrdemo;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * created by zhj
 * on  2019/8/6
 **/
public class BaseApplication extends MultiDexApplication {
    private static Application mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        closeAndroidPDialog();
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
    }

    public static Application getAppContext() {
        return mContext;
    }

    private void closeAndroidPDialog() {
        try {
            Class aClass = Class.forName( "android.content.pm.PackageParser$Package" );
            Constructor declaredConstructor = aClass.getDeclaredConstructor( String.class );
            declaredConstructor.setAccessible( true );
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName( "android.app.ActivityThread" );
            Method declaredMethod = cls.getDeclaredMethod( "currentActivityThread" );
            declaredMethod.setAccessible( true );
            Object activityThread = declaredMethod.invoke( null );
            Field mHiddenApiWarningShown = cls.getDeclaredField( "mHiddenApiWarningShown" );
            mHiddenApiWarningShown.setAccessible( true );
            mHiddenApiWarningShown.setBoolean( activityThread, true );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
