package www.teacheredu.cn.ocrdemo.http;


import okhttp3.RequestBody;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AntLeaseApi implements ApiService {

    private AntLeaseApi() {
    }

    static class AntLeaseApiInner {
        private static AntLeaseApi antLeaseApi = new AntLeaseApi();
    }

    public static AntLeaseApi getInstance() {
        return AntLeaseApiInner.antLeaseApi;
    }

//    @Override
//    public Observable<Response<PlaceOrder>> placeOrder(RequestBody requestBody) {
//        return RetrofitUtil
//                .getApi(Hosts.host)
//                .placeOrder(requestBody)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
}
