package www.teacheredu.cn.ocrdemo.base;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import www.teacheredu.cn.ocrdemo.R;


public class ProgressBarView extends RelativeLayout implements View.OnClickListener {
    private Context mContext;
    RelativeLayout progressOuterRL;
    RelativeLayout loadingRL;
//    ImageView cloudIV;
    ImageView loadingIV;

    View errorLL;
    ImageView errorIV;
    TextView tipTV;
    TextView repeatTV;
    private int state;//当前状态
    private AnimationDrawable animationDrawable;
    private Animation translateAnimation;

    private final int LOADING = 1;
    private final int NO_NET = 2;
    private final int LOAD_FAIL = 3;
    private final int LOAD_EMPTY = 4;

    public ProgressBarView(Context context) {
        super(context);
        initDatas(context);
        if (isInEditMode()) return;
        initViews();
    }


    public ProgressBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDatas(context);
        if (isInEditMode()) return;
        initViews();
    }

    private void initDatas(Context context) {
        this.mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.main_progressbar, this);
        setVisibility(View.GONE);
//        setId(R.id.progress);
    }

    private void initViews() {


        progressOuterRL = (RelativeLayout) findViewById(R.id.progressOuterRL);
        loadingRL = (RelativeLayout) findViewById(R.id.loadingRL);
//        cloudIV = (ImageView) findViewById(R.id.cloudIV);
        loadingIV = (ImageView) findViewById(R.id.loadingIV);

        errorLL = (LinearLayout) findViewById(R.id.errorLL);
        errorIV = (ImageView) findViewById(R.id.errorIV);
        tipTV = (TextView) findViewById(R.id.tipTV);
        repeatTV = (TextView) findViewById(R.id.repeatTV);

        translateAnimation = AnimationUtils.loadAnimation(mContext, R.anim.progress_cloud_translate);

        errorLL.setOnClickListener(this);

    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        performClick();
//        return false;
//    }


    public void startLoading() {
        setStatusVisible(true, false, LOADING);
//        cloudIV.startAnimation(translateAnimation);
        animationDrawable = (AnimationDrawable) loadingIV.getDrawable();
        animationDrawable.start();
    }

    /**
     * 加载成功
     */
    public void loadSuccess() {
        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
        if (translateAnimation.hasStarted()) {
            translateAnimation.cancel();
//            cloudIV.clearAnimation();
        }
        setVisibility(View.GONE);
        setNetDiagVisible(false);
    }

    public void loadEmpty() {
        loadEmpty("加载数据为空");
    }

    public void loadEmpty(String loadEmpty) {
        setStatusVisible(false, true, LOAD_EMPTY);
        showFail_NoNet_Empty(loadEmpty);
        setNetDiagVisible(false);
    }
    public void loadEmptyWithoutRepeatBT(String tip, @DrawableRes int resId){
        setStatusVisible(false,true,LOAD_EMPTY);
        tipTV.setText(tip);
        repeatTV.setVisibility(View.GONE);
        errorIV.setImageResource(resId);
        setNetDiagVisible(false);
    }
    public void loadEmptyWithoutRepeatBT(String tip, String desc, @DrawableRes int resId){
        setStatusVisible(false,true,LOAD_EMPTY);
        tipTV.setText(tip);
        repeatTV.setVisibility(View.VISIBLE);
        repeatTV.setText(desc);
        repeatTV.setTextColor(0xFF666666);
        errorIV.setImageResource(resId);
        setNetDiagVisible(false);
    }

    public void loadFail() {
        loadFail("数据加载失败");
    }

    public void loadFail(String loadFailTip) {
        setStatusVisible(false, true, LOAD_FAIL);
//        showFail_NoNet_Empty(loadFailTip);
        showFail_NoNet_Empty("数据加载失败");
        errorIV.setImageResource(R.mipmap.img_loading_failed);
//        setNetDiagVisible(true);
        setNetDiagVisible(false);  //隐藏入口
    }

    public void noNet() {
        noNet(mContext.getString(R.string.net_error));
    }

    public void noNet(String noNetTip) {
        setStatusVisible(false, true, NO_NET);
        showFail_NoNet_Empty(noNetTip);
        errorIV.setImageResource(R.mipmap.img_no_network);
//        setNetDiagVisible(true);
        setNetDiagVisible(false);  //隐藏入口
    }

    private void setStatusVisible(boolean loadingShowRL, boolean errorShowLL, int state) {
        this.state = state;
        setVisibility(View.VISIBLE);
        loadingRL.setVisibility(loadingShowRL ? View.VISIBLE : View.GONE);
        errorLL.setVisibility(errorShowLL ? View.VISIBLE : View.GONE);
        repeatTV.setTextColor(getResources().getColor(R.color.font_blue02));
        repeatTV.setVisibility(errorShowLL ? View.VISIBLE : View.GONE);
    }

    private void showFail_NoNet_Empty(String tip) {
        tipTV.setText(tip);
    }

    @Override
    public void onClick(View v) {
        switch (state) {
            case LOADING:
                break;
            case LOAD_EMPTY:
            case NO_NET:
            case LOAD_FAIL:
                if (repeatLoadDataListener != null) {
                    repeatLoadDataListener.repeatLoadData();
                }
                break;
        }
    }

    /**
     * 设置网络诊断是否显示
     * @param visible
     */
    private void setNetDiagVisible(boolean visible){
        if(tipTV == null){
            return;
        }

        Drawable right = visible ? tipTV.getCompoundDrawables()[2] : null;
        tipTV.setCompoundDrawables(tipTV.getCompoundDrawables()[0],
                tipTV.getCompoundDrawables()[1], right, tipTV.getCompoundDrawables()[3]);


        tipTV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                mContext.startActivity(new Intent(mContext,NetDiagActivity.class));
            }
        });
        tipTV.setClickable(visible);

    }

    public void setRepeatLoadDataListener(RepeatLoadDataListener repeatLoadDataListener) {
        this.repeatLoadDataListener = repeatLoadDataListener;
    }

    private RepeatLoadDataListener repeatLoadDataListener;

    public interface RepeatLoadDataListener {
        public void repeatLoadData();
    }

}
