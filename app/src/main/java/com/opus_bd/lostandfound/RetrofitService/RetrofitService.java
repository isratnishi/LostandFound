package com.opus_bd.lostandfound.RetrofitService;


import com.opus_bd.lostandfound.Model.User.UserAuthModel;
import com.opus_bd.lostandfound.Model.User.UserLoginModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("api/AccountInfo/LogIn")
    Call<UserAuthModel> login(@Body UserLoginModel userLoginModel);



}
