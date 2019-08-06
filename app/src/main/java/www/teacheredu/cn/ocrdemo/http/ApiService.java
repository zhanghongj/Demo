package www.teacheredu.cn.ocrdemo.http;


import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

public interface ApiService {
    /**
     * 下单
     *
     * @param requestBody
     * @return
     */
//    @POST(Urls.placeOrder)
//    Observable<Response<PlaceOrder>> placeOrder(@Body RequestBody requestBody);
//
//    /**
//     * 微信支付
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.placeOrderWXPay)
//    Observable<Response<PlaceOrderWXPay>> placeOrderWXPay(@Body RequestBody requestBody);
//
//    /**
//     * 微信支付
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.placeOrderAliPay)
//    Observable<Response<PlaceOrderAliPay>> getAliSign(@Body RequestBody requestBody);
//
//    /**
//     * 订单列表
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.orderList)
//    Observable<Response<Order>> orderList(@Body RequestBody requestBody);
//
//    /**
//     * 订单详情
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.orderDetail)
//    Observable<Response<OrderDetail>> orderDetail(@Body RequestBody requestBody);
//
//    /**
//     * 请求发送短信验证码
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.sendNumber)
//    Observable<Response<CommentResult>> sendNumber(@Body RequestBody requestBody);
//
//
//    /**
//     * 手机号登录
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.phoneLogin)
//    Observable<Response<PhoneLogin>> phoneLogin(@Body RequestBody requestBody);
//
//    /**
//     * 轮播图
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.banner)
//    Observable<Response<Banner>> getBanner(@Body RequestBody requestBody);
//
//
//    /**
//     * 广播通知
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.broadcastMessage)
//    Observable<Response<BroadcastMessage>> getBroadcastMessage(@Body RequestBody requestBody);
//
//    /**
//     * 上架商品列表
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.shelfproductlist)
//    Observable<Response<ShelfProductList>> getShelfProductList(@Body RequestBody requestBody);
//
//    /**
//     * 验证是否是首次下单
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.orderVerify)
//    Observable<Response<OrderVerify>> getOrderVerify(@Body RequestBody requestBody);
//
//    @POST(Urls.rentPeroid)
//    Observable<Response<LeasePeroid>> getRentPeroid(@Body RequestBody requestBody);
//
//    /**
//     * 我的概况
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.myInfo)
//    Observable<Response<MyInfo>> getMyInfo(@Body RequestBody requestBody);
//
//    /**
//     * 查询个人信息
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.queryInfo)
//    Observable<Response<QueryPersonalInfo>> queryInfo(@Body RequestBody requestBody);
//
//    /**
//     * 提交个人信息
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.submitInfo)
//    Observable<Response<SubmitPersonalInfo>> submitInfo(@Body RequestBody requestBody);
//
//    /**
//     * 获取产品详情
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.productDetail)
//    Observable<Response<ProductDetail>> getProductDetail(@Body RequestBody requestBody);
//
//    /**
//     * 通知支付成功
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.notifyPaySuccess)
//    Observable<Response<Order>> notifyPaySuccess(@Body RequestBody requestBody);
//
//    /**
//     * 提交建议
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.submitSuggestions)
//    Observable<Response<CommentResult>> submitSuggestions(@Body RequestBody requestBody);
//
//    /**
//     * 蚂蚁币明细
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.getCurrency)
//    Observable<Response<AntCurrency>> getCurrency(@Body RequestBody requestBody);
//
//    /**
//     * 我的消息列表
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.myMessage)
//    Observable<Response<MyMessage>> getMyMessage(@Body RequestBody requestBody);
//
//    /**
//     * 我的押金列表
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.depositList)
//    Observable<Response<DepositList>> getDeposit(@Body RequestBody requestBody);
//
//    /**
//     * 绑定银行卡
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.bindBankCard)
//    Observable<Response<CommentResult>> bindBankCard(@Body RequestBody requestBody);
//
//    /**
//     * 获取绑定银行卡信息
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.getBankCardInfo)
//    Observable<Response<BankCardInfo>> getBankCardInfo(@Body RequestBody requestBody);
//
//    /**
//     * 绑定电池
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.bindBattery)
//    Observable<Response<CommentResult>> bindBattery(@Body RequestBody requestBody);
//
//    /**
//     * 换电解绑
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.changeUnbinding)
//    Observable<Response<CommentResult>> changeUnbinding(@Body RequestBody requestBody);
//
//    /**
//     * 换电授权
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.changeAuth)
//    Observable<Response<CommentResult>> changeAuth(@Body RequestBody requestBody);
//
//    /**
//     * 退电解绑
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.unrentUnbinding)
//    Observable<Response<CommentResult>> unrentUnbinding(@Body RequestBody requestBody);
//
//    /**
//     * 退电授权
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.unrentAuth)
//    Observable<Response<CommentResult>> unrentAuth(@Body RequestBody requestBody);
//
//    /**
//     * 转租请求
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.subletRequest)
//    Observable<Response<CommentResult>> subletRequest(@Body RequestBody requestBody);
//
//    /**
//     * 转租取消
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.subletCancel)
//    Observable<Response<CommentResult>> subletCancel(@Body RequestBody requestBody);
//
//    /**
//     * 转租被拒
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.subletReject)
//    Observable<Response<CommentResult>> subletReject(@Body RequestBody requestBody);
//
//    /**
//     * 上传身份证正面
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.submitfrontidimg)
//    Observable<Response<FrontImg>> submitfrontidimg(@Body RequestBody requestBody);
//
//    /**
//     * 核对信息
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.confirmidinfo)
//    Observable<Response<CommentResult>> confirmidinfo(@Body RequestBody requestBody);
//
//    /**
//     * 提交身份证背面
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.submitbacktidimg)
//    Observable<Response<BackImg>> submitbacktidimg(@Body RequestBody requestBody);
//
//    /**
//     * 提交手持照片
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.submitmyimg)
//    Observable<Response<CommentResult>> submitmyimg(@Body RequestBody requestBody);
//
//    /**
//     * 微信登录
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.loginByWx)
//    Observable<Response<LoginResult>> loginByWx(@Body RequestBody requestBody);
//
//    /**
//     * 绑定手机
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.bindPhone)
//    Observable<Response<BindPhone>> bindPhone(@Header("token") String token, @Body RequestBody requestBody);
//
//    /**
//     *
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.seeBinding)
//    Observable<Response<SeeBinding>> seeBinding(@Header("token") String token, @Body RequestBody requestBody);
//
//    /**
//     * 获取个人信息
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.getUserInfo)
//    Observable<Response<UserInfo>> getUserInfo(@Body RequestBody requestBody);
//
//    /**
//     * 获取新消息个数
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.getNewmsgnum)
//    Observable<Response<CommentResult>> getNewmsgnum(@Body RequestBody requestBody);
//
//    /**
//     * 改变消息状态
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.changeMessStatus)
//    Observable<Response<Void>> changeMessStatus(@Body RequestBody requestBody);
//
//    /**
//     * 获取城市列表
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.getCity)
//    Observable<Response<City>> getCity(@Body RequestBody requestBody);
//
//    /**
//     * 转租接收
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.subletAccept)
//    Observable<Response<CommentResult>> subletAccept(@Body RequestBody requestBody);
//
//    /**
//     * 转租接收
//     *
//     * @param requestBody
//     * @return
//     */
//    @POST(Urls.getIdCard)
//    Observable<Response<IDCard>> getIdCard(@Body RequestBody requestBody);
}
