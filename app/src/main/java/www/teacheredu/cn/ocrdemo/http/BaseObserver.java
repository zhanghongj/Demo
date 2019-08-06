package www.teacheredu.cn.ocrdemo.http;

import rx.Observer;

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        onFailed();
    }

    protected void onFailed() {
//        Toast.makeText(App.getAppContext(), "服务器异常", Toast.LENGTH_SHORT).show();
    }
}
