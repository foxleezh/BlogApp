package com.foxleezh.middleware.mvvm;

import com.google.gson.annotations.Expose;

/**
 * Created by foxlee on 15/7/22.
 */
public class BaseRespModel<M extends BaseRespModel,T extends BaseViewModel & INetVM> extends BaseModel{

    public String desc;

    public int status;

    @Expose
    public boolean fromCache;//是否从数据库中获取
    @Expose
    public Object args;//请求时附带的参数
    @Expose
    public T vm;//viewModel

    public BaseRespModel<M,T> setVM(T vm){
        this.vm=vm;
        return this;
    }

//    public NetRequest loadData(NetRequest<M> request,Object args) {
//        this.args=args;
//        return loadData(request);
//    }
//
//    public NetRequest loadData(NetRequest<M> request) {
//        if(vm==null){
//            throw new RuntimeException("you must setVM before invoke loadData");
//        }
//        if (vm.getView() == null) {
//            return request;
//        }
//        vm.showLoading();
//        request.clazz((Class<M>) getClass()).
//                listener(new ApiManager.ResponseListener<M>() {
//                             @Override
//                             public void onResponse(M response) {
//                                 if (vm.getView() != null) {
//                                     response.args=args;
//                                     vm.setModelData(response);
//                                     vm.dismissLoading();
//                                     vm.setData(response);
//                                 }
//                             }
//
//                             @Override
//                             public void onErrorResponse(VolleyError error) {
//                                 if (vm.getView() != null) {
//                                     vm.dismissLoading();
//                                     vm.showError(PublicUtils.getMsg(error),args);
//                                 }
//                             }
//
//                             @Override
//                             public void onAsyncResponse(M response) {
//
//                             }
//                         }
//                ).
//                run();
//        return request;
//    }
}
