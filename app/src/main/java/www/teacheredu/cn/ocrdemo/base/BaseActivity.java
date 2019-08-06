package www.teacheredu.cn.ocrdemo.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;


import java.util.List;

import www.teacheredu.cn.ocrdemo.R;
import www.teacheredu.cn.ocrdemo.utils.DensityUtil;
import www.teacheredu.cn.ocrdemo.utils.EasyPermissions;

public abstract class BaseActivity extends AppCompatActivity implements ProgressBarView.RepeatLoadDataListener,EasyPermissions.PermissionCallbacks {
//    private Unbinder unbinder;
    protected Activity mContext;
//    protected Session mSession;
    protected ProgressBarView progressBarView;
    //是否需要进度条 有的页面不需要进度条 绝大部分有网络请求的页面都需要进度条
    protected boolean isNeedProgressView = true;
    protected BasePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        presenter = getPresenter();
        setStatusBar();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //禁止横屏
        mContext = this;
        addLoadingView();
//        unbinder = ButterKnife.bind(this);
//        mSession = Session.getSession();
        initViews(savedInstanceState);
        addListeners();
    }

    /**
     * 设置状态栏颜色
     */
    protected void setStatusBar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
//            getWindow().setStatusBarColor(getResources().getColor(R.color.title_bac));

            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void addLoadingView() {
        if (isNeedProgressView) {
            progressBarView = new ProgressBarView(mContext);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.topMargin = DensityUtil.dip2px(this, 45);
            addContentView(progressBarView, params);
            progressBarView.setRepeatLoadDataListener(this);
        }
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract BasePresenter getPresenter();

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void addListeners();

    protected abstract void onActivityDestroy();

    @Override
    protected void onDestroy() {
//        unbinder.unbind();
        if (presenter!=null){
            presenter.onUnSubscribe();
        }
        onActivityDestroy();
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
    }


    @Override
    public void repeatLoadData() {
    }

    //<editor-fold desc="全局loading使用">
    public void startLoading(){
        if (progressBarView!=null){
            progressBarView.startLoading();
        }
    }
    public void noNet(){
        if (progressBarView!=null){
            progressBarView.noNet();
        }
    }
    public void loadFail(String message){
        if (progressBarView!=null){
            progressBarView.loadFail(message);
        }
    }
    public void loadSuccess(){
        if (progressBarView!=null){
            progressBarView.loadSuccess();
        }
    }
    //</editor-fold>
    protected static final int RC_PERM = 123;
    /**
     * 权限回调接口
     */
    private CheckPermListener mListener;

    public interface CheckPermListener {
        //权限通过后的回调方法
        void superPermission();
    }

    public void checkPermission(CheckPermListener listener, int resString, String... mPerms) {
        mListener = listener;
        if (EasyPermissions.hasPermissions(this, mPerms)) {
            if (mListener != null)
                mListener.superPermission();
        } else {
            EasyPermissions.requestPermissions(this, getString(resString),
                    RC_PERM, mPerms);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //同意了某些权限可能不是全部
        Log.e("BaseActivity","---onPermissionsGranted");
    }

    @Override
    public void onPermissionsAllGranted() {
        Log.e("BaseActivity","---onPermissionsAllGranted");
        if (mListener != null)
            mListener.superPermission();//同意了全部权限的回调
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.e("BaseActivity","---onPermissionsDenied");
        EasyPermissions.checkDeniedPermissionsNeverAskAgain(this,
                getString(R.string.perm_tip),
                R.string.setting, R.string.cancel, null, perms);
    }


}
