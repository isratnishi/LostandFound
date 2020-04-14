package com.opus_bd.lostandfound.RetrofitService;


import com.opus_bd.lostandfound.Model.Dashboard.GDInformationModel;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentType;
import com.opus_bd.lostandfound.Model.Documentaion.NationalIdentityTypesModel;
import com.opus_bd.lostandfound.Model.GlobalData.District;
import com.opus_bd.lostandfound.Model.User.RegistrationModel;
import com.opus_bd.lostandfound.Model.User.UserAuthModel;
import com.opus_bd.lostandfound.Model.User.UserLoginModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @GET("api/DocumentMaster/GetAllDocumentType")
    Call<List<DocumentType>> GetAllDocumentType();


    //Dashboard
    @POST("api/LostFound/SaveGDInformation")
    Call<String> SaveGDInformation(@Header("Authorization") String token, @Body GDInformationModel gdInformationModel);

//Global data

    @GET("api/AddressMaster/GetDistrictByDivisionId/{id}")
    Call<List<District>> getAllDistricts(@Path("id") int id);
}
