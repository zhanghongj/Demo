package www.teacheredu.cn.ocrdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * Created by dell on 2017/3/10.
 */

public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int[] getScreenPixel(Context context) {
        int[] screenPixel = new int[2];
        int width = 0, height = 0;
        final DisplayMetrics metrics = new DisplayMetrics();
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Method mGetRawH, mGetRawW;

        try {
            // For JellyBean 4.2 (API 17) and onward
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
                display.getRealMetrics(metrics);

                width = metrics.widthPixels;
                height = metrics.heightPixels;
            } else {
                mGetRawH = Display.class.getMethod("getRawHeight");
                mGetRawW = Display.class.getMethod("getRawWidth");

                try {
                    width = (Integer) mGetRawW.invoke(display);
                    height = (Integer) mGetRawH.invoke(display);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        }

        screenPixel[0] = width;
        screenPixel[1] = height;
        return screenPixel;
    }

    /**
     * 获取屏幕的宽度
     *
     * @param context
     * @return
     */
    public static int screenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        Point screenPoint = new Point();
        wm.getDefaultDisplay().getSize(screenPoint);
        return screenPoint.x;
    }

    /**
     * 获取屏幕的宽度
     *
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR2)
    public static int screenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        Point screenPoint = new Point();
        wm.getDefaultDisplay().getSize(screenPoint);
        return screenPoint.y;
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR2)
    public static int screenHeightWithoutStatusBar(Context context) {
        return screenHeight(context) - getStatusBarHeight(context);
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 描 述：获取NavigationBarHeight高度<br/>
     * 作 者：谌珂<br/>
     * 历 史: (5.3.1) 谌珂 2017/12/9 <br/>
     */
    public static int getNavigationBarHeight(Activity context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return hasNavBar(context) ? resources.getDimensionPixelSize(resourceId) : 0;
    }

    public static boolean hasNavBar(Activity context) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return false;
        }
        Display d = context.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        d.getRealMetrics(dm);
        int realHeight = dm.heightPixels;
        int realWidth = dm.widthPixels;
        d.getMetrics(dm);
        int displayHeight = dm.heightPixels;
        int displayWidth = dm.widthPixels;
        return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
    }
}
