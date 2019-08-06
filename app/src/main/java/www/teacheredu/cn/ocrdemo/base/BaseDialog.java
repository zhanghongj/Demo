package www.teacheredu.cn.ocrdemo.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import www.teacheredu.cn.ocrdemo.R;


public abstract class BaseDialog extends Dialog {
    protected Context mContext;
//    protected Unbinder unbinder;
    protected BaseDialog(@NonNull Context context) {
        this(context, R.style.MyDialog);
        this.mContext = context;
    }
    protected BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(getLayoutId());
        setCancelable(false);
        setLayoutParams();
//        unbinder = ButterKnife.bind(this);
        initViews(savedInstanceState);
    }
    private void setLayoutParams(){
        setLayoutParams(Gravity.CENTER);
    }
    protected void setLayoutParams(int gravity){
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = gravity;
        window.setAttributes(lp);
    }
    abstract @LayoutRes
    int getLayoutId();
    abstract void initViews(Bundle savedInstanceState);

    @Override
    public void dismiss() {
//        unbinder.unbind();
        super.dismiss();
    }
}
