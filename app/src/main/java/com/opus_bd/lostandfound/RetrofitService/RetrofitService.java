package com.opus_bd.lostandfound.RetrofitService;


import com.opus_bd.lostandfound.Model.Documentaion.NationalIdentityTypesModel;
import com.opus_bd.lostandfound.Model.User.RegistrationModel;
import com.opus_bd.lostandfound.Model.User.UserAuthModel;
import com.opus_bd.lostandfound.Model.User.UserLoginModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("api/AccountInfo/LogIn")
    Call<UserAuthModel> login(@Body UserLoginModel userLoginModel);


    @POST("api/AccountInfo/Register")
    Call<UserAuthModel> Register(@Body RegistrationModel registrationModel);

    @POST("api/AccountInfo/OTPVarified")
    Call<String> OTPVarified(@Body RegistrationModel registrationModel);

//Documentation
    @GET("api/DocumentMaster/GetNationalIdentityTypes")
    Call<List<NationalIdentityTypesModel>> GetNationalIdentityTypes();
}
