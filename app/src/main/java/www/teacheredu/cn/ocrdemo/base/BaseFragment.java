package www.teacheredu.cn.ocrdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import www.teacheredu.cn.ocrdemo.utils.DensityUtil;


public abstract class BaseFragment extends Fragment {
//    protected Unbinder unbinder;
    protected Activity mActivity;
    private FrameLayout outerFL;
//    protected Session mSesson;
    //是否需要进度条 有的页面不需要进度条 绝大部分有网络请求的页面都需要进度条
    protected boolean isNeedProgressView = true;
    protected ProgressBarView progressBarView;
    protected BasePresenter presenter;
    protected int marginTop = 50;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        presenter = getPresenter();
        outerFL = new FrameLayout(mActivity);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        outerFL.setLayoutParams(layoutParams);
        View view = inflater.inflate(getLayoutId(), container, false);
//        mSesson = Session.getSession();
        outerFL.addView(view);
        addLoadingView(outerFL);
//        unbinder = ButterKnife.bind(this, view);
        return outerFL;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view, savedInstanceState);
        addListeners();
    }

    protected void addLoadingView(FrameLayout outerFL) {
        if (isNeedProgressView) {
            progressBarView = new ProgressBarView(mActivity);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.topMargin = DensityUtil.dip2px(mActivity, marginTop);
            outerFL.addView(progressBarView, params);
        }

    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract BasePresenter getPresenter();

    protected abstract void initViews(View view, @Nullable Bundle savedInstanceState);

    protected abstract void addListeners();

    protected abstract void onFragmentDestroy();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        onVisibilityChangedToUser(getUserVisibleHint());
    }

    /**
     * 当Fragment对用户的可见性发生了改变的时候就会回调此方法
     *
     * @param isVisibleToUser true：用户能看见当前Fragment；false：用户看不见当前Fragment
     */
    public void onVisibilityChangedToUser(boolean isVisibleToUser) {

    }

    @Override
    public void onResume() {
        super.onResume();
        onVisibilityChangedToUser(getUserVisibleHint());
    }

    @Override
    public void onDestroyView() {
//        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (presenter!=null){
            presenter.onUnSubscribe();
        }
        onFragmentDestroy();
        super.onDestroy();
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
}