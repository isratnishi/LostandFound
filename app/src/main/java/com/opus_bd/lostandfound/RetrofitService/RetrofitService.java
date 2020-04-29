package com.opus_bd.lostandfound.RetrofitService;


import com.opus_bd.lostandfound.Model.Dashboard.GDInformation;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformationModel;
import com.opus_bd.lostandfound.Model.Documentaion.Colors;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentType;
import com.opus_bd.lostandfound.Model.Documentaion.MetroAreaModel;
import com.opus_bd.lostandfound.Model.Documentaion.NationalIdentityTypesModel;
import com.opus_bd.lostandfound.Model.Documentaion.Occupation;
import com.opus_bd.lostandfound.Model.Documentaion.RegistrationLevelModel;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleModel;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleType;
import com.opus_bd.lostandfound.Model.GlobalData.District;
import com.opus_bd.lostandfound.Model.GlobalData.Division;
import com.opus_bd.lostandfound.Model.GlobalData.Thana;
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

    @GET("api/AddressMaster/GetDivisions")
    Call<List<Division>> GetDivisions();

    @GET("api/AddressMaster/GetAllDistrict")
    Call<List<District>> getAllDistricts();

    @GET("api/AddressMaster/GetThanaByDistrictId/{id}")
    Call<List<Thana>> GetThanaByDistrictId(@Path("id") int id);



    @GET("api/DocumentMaster/GetVehicleTypes")
    Call<List<VehicleType>> GetVehicleTypes();

    @GET("api/DocumentMaster/GetVehicleModelByVehicleId/{id}")
    Call<List<VehicleModel>> GetVehicleModelByVehicleId(@Path("id") int id);
//Global data


    @GET("api/LostFound/GetGDInformationByUser/{UserName}")
    Call<List<GDInformation>> GetGDInformationByUser(@Header("Authorization") String token,@Path("UserName") String userName);



    @GET("api/DocumentMaster/GetColors")
    Call<List<Colors>> GetColors();
    @GET("api/VehicleMaster/GetAllMetropolitanArea")
    Call<List<MetroAreaModel>> GetAllMetropolitanArea();

    @GET("api/VehicleMaster/GetAllRegistrationLevel")
    Call<List<RegistrationLevelModel>> GetAllRegistrationLevel();

    @GET("api/DocumentMaster/GetOccupationInfo")
    Call<List<Occupation>> GetOccupationInfo();

}
