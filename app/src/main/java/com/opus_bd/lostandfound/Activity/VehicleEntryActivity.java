package com.opus_bd.lostandfound.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformationModel;
import com.opus_bd.lostandfound.Model.Documentaion.Colors;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentType;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleModel;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleType;
import com.opus_bd.lostandfound.Model.GlobalData.District;
import com.opus_bd.lostandfound.Model.GlobalData.Division;
import com.opus_bd.lostandfound.Model.GlobalData.Thana;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;

import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleEntryActivity extends AppCompatActivity {
    private static final int PICK_FILE_REQUEST = 1;
    private String selectedFilePath;
    ProgressDialog dialog;
    //Edit Text
    @BindView(R.id.etModel)
    EditText etModel;

    @BindView(R.id.etRegNoName)
    EditText etRegNoName;

    @BindView(R.id.etEngineNo)
    EditText etEngineNo;

    @BindView(R.id.etChesisNo)
    EditText etChesisNo;

    @BindView(R.id.etCCNo)
    EditText etCCNo;

    @BindView(R.id.etMadeIn)
    EditText etMadeIn;

    @BindView(R.id.etMadeDate)
    EditText etMadeDate;

    @BindView(R.id.etIdentitySign)
    EditText etIdentitySign;

    @BindView(R.id.etAddressDetails)
    EditText etAddressDetails;

    @BindView(R.id.etVehicleDate)
    EditText etVehicleDate;

    @BindView(R.id.etVehicleTime)
    EditText etVehicleTime;


    boolean isllVehicleEntryChecked = true;
    @BindView(R.id.llVehicleInfromation)
    LinearLayout llVehicleInfromation;

    @BindView(R.id.ivVehicleInformation)
    ImageView ivVehicleInformation;

    boolean isllVehicleIdentificationChecked = true;
    @BindView(R.id.llVIdentityInfo)
    LinearLayout llVIdentityInfo;

    @BindView(R.id.ivVIdentityInfo)
    ImageView ivVIdentityInfo;

    boolean isllVPATChecked = true;
    @BindView(R.id.llVPATInfo)
    LinearLayout llVPATInfo;

    @BindView(R.id.ivVPATInfo)
    ImageView ivVPATInfo;

    //Spinner

    @BindView(R.id.spnSPDivision)
    Spinner spnSPDivision;

    @BindView(R.id.spnSPDistrict)
    Spinner spnSPDistrict;

    @BindView(R.id.spnSPThana)
    Spinner spnSPThana;

    @BindView(R.id.spnDocumentType)
    Spinner spnDocumentType;

    @BindView(R.id.spnVehicleType)
    Spinner spnVehicleType;

    @BindView(R.id.spnMadeBy)
    Spinner spnMadeBy;

    @BindView(R.id.spnRegNoName1)
    Spinner spnRegNoName1;

    @BindView(R.id.spnRegNoName2)
    Spinner spnRegNoName2;

    @BindView(R.id.spnColor)
    Spinner spnColor;


    @BindView(R.id.etBlueBook)
    TextView etBlueBook;

    String[] metropoliton, regipartTwo;

    //Address List
    ArrayList<Division> divisionArrayList = new ArrayList<>();
    ArrayList<District> districtArrayList = new ArrayList<>();
    ArrayList<Thana> thanaArrayList = new ArrayList<>();

    ArrayList<DocumentType> documentTypeArrayList = new ArrayList<>();
    ArrayList<VehicleType> vehicleTypeArrayList = new ArrayList<>();
    ArrayList<VehicleModel> VehicleModelArrayList = new ArrayList<>();
    ArrayList<Colors> colorArrayList = new ArrayList<>();
    public int SELECTED_DOCUMENT_ID;
    public int SELECTED_VEHICLETYPE_ID;
    public String SELECTED_VEHICLEMODEL_Name;
    public String SELECTED_REGNO_1;
    public String SELECTED_REGNO_2;
    public int SELECTED_COLOR_ID;
    public int SELECTED_DIVISION_ID;
    public int SELECTED_DISTRICT_ID;
    public int SELECTED_THANA_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_entry);
        ButterKnife.bind(this);
        llVehicleInfromation.setVisibility(View.GONE);
        llVIdentityInfo.setVisibility(View.GONE);
        llVPATInfo.setVisibility(View.GONE);

        getMatropolitonName();
        getRegiSerial();
        //Spinner
        getAllDocument();
        getAllVehicleType();
        getAllColor();
        getDivision();
    }
    protected void attachBaseContext(Context base) {
        SharedPreferences tprefs = base.getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, MODE_PRIVATE);
        boolean language = tprefs.getBoolean(SharedPrefManager.KEY_State, true);
        if (language)
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.ENGLISH));
        else
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.BANGLA));
    }
    @OnClick(R.id.ivVehicleInformation)
    public void ivVehicleInformation(){
        if (isllVehicleEntryChecked) {
            // show password
            llVehicleInfromation.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivVehicleInformation);
            isllVehicleEntryChecked = false;
        } else {
            // hide password
            llVehicleInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVehicleInformation);
            isllVehicleEntryChecked = true;
        }

    }

    @OnClick({R.id.ivVIdentityInfo,R.id.btnNext1})
    public void ivVIdentityInfo(){

        if (isllVehicleIdentificationChecked) {
            // show password
            llVehicleInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVehicleInformation);
            isllVehicleEntryChecked = true;

            llVIdentityInfo.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivVIdentityInfo);
            isllVehicleIdentificationChecked = false;

        } else {
            // hide password
            llVIdentityInfo.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVIdentityInfo);
            isllVehicleIdentificationChecked = true;
        }

//        llVIdentityInfo.setVisibility(View.VISIBLE);
//        llVehicleInfromation.setVisibility(View.GONE);
//        llVPATInfo.setVisibility(View.GONE);
    }





    @OnClick({R.id.ivVPATInfo,R.id.btnNext2})
    public void ivVPATInfo(){
        if (isllVPATChecked) {
            // show password
            llVIdentityInfo.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVIdentityInfo);
            isllVehicleIdentificationChecked = true;

            llVPATInfo.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivVPATInfo);
            isllVPATChecked = false;

        } else {
            // hide password
            llVPATInfo.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVPATInfo);
            isllVPATChecked = true;
        }

       llVPATInfo.setVisibility(View.VISIBLE);

    }

    private void submitToServer() {

        String token = SharedPrefManager.getInstance(this).getToken();
        final GDInformationModel gdInformationModel = new GDInformationModel();

        //GD Information
        gdInformationModel.setUserName("01516146414");
        gdInformationModel.setGdFor("2");
        gdInformationModel.setGdDate("2020-04-14");
        gdInformationModel.setIdentityNo("3453453");
        gdInformationModel.setGDTypeId(1);
        gdInformationModel.setProductTypeId(1);

        gdInformationModel.setDocumentTypeId(SELECTED_DOCUMENT_ID);
        gdInformationModel.setDocumentDescription("");

        //Vehicle Information

        gdInformationModel.setVehicleTypeId(SELECTED_VEHICLETYPE_ID);
        gdInformationModel.setMadeBy(SELECTED_VEHICLEMODEL_Name);
        gdInformationModel.setModelNo(etModel.getText().toString());
        gdInformationModel.setRegNoFirstPart(SELECTED_REGNO_1);
        gdInformationModel.setRegNoSecondPart(SELECTED_REGNO_1);
        gdInformationModel.setRegNoThiredPart(etRegNoName.getText().toString());
        gdInformationModel.setEngineNo(etEngineNo.getText().toString());
        gdInformationModel.setChasisNo(etChesisNo.getText().toString());
        gdInformationModel.setCcNo(etCCNo.getText().toString());
        gdInformationModel.setMadeIn(etMadeIn.getText().toString());
        gdInformationModel.setModelDate(etMadeDate.getText().toString());

        //Identity Info
        gdInformationModel.setColorsId(SELECTED_COLOR_ID);
        gdInformationModel.setIdentifySign(etIdentitySign.getText().toString());

        //Place And Time
        gdInformationModel.setDivisionId(SELECTED_DIVISION_ID);
        gdInformationModel.setDistrictId(SELECTED_DISTRICT_ID);
        gdInformationModel.setThanaId(SELECTED_THANA_ID);
        gdInformationModel.setPlaceDetails(etAddressDetails.getText().toString());
        gdInformationModel.setPlaceDetails(etAddressDetails.getText().toString());
        gdInformationModel.setLafDate(etVehicleDate.getText().toString());
        gdInformationModel.setLafTime(etVehicleTime.getText().toString());


        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> registrationRequest = retrofitService.SaveGDInformation(token, gdInformationModel);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Utilities.showLogcatMessage("responce");


                     /*   try{
                            String auth=response.body().getJwt().replace("{\"auth_token\":\"","");
                            String auth1=auth.replace("\"}","");
                            Utilities.showLogcatMessage(" "+auth1);
                            SharedPrefManager.getInstance(context).saveToken(auth1);
                            SharedPrefManager.getInstance(context).saveotp(response.body().getOtpCode());
                            SharedPrefManager.getInstance(context).saveotp(response.body().getUserInfo().getUserName());
                        }
                        catch (Exception e) {
                            Utilities.showLogcatMessage("Exception 1"+e.toString());
                            Toast.makeText(context, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                        }*/

                        Toast.makeText(VehicleEntryActivity.this, "Successfully Done!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(VehicleEntryActivity.this, DashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    } else {
                        Toast.makeText(VehicleEntryActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage("Exception 2" + e.toString());
                    Toast.makeText(VehicleEntryActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                // Utilities.hideProgress(LoginActivity.this);
                Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void getMatropolitonName() {
        metropoliton = getResources().getStringArray(R.array.matropoliton);

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, metropoliton);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRegNoName1.setAdapter(dataAdapter2);
        spnRegNoName1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    SELECTED_REGNO_1 = metropoliton[i];

                } else {
                    SELECTED_REGNO_1 = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getRegiSerial() {
        regipartTwo = getResources().getStringArray(R.array.regiparttwo);

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, regipartTwo);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRegNoName2.setAdapter(dataAdapter2);
        spnRegNoName2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    SELECTED_REGNO_2 = regipartTwo[i];

                } else {
                    SELECTED_REGNO_2 = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getAllDocument() {

        String token = SharedPrefManager.getInstance(this).getToken();

            RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
            Call<List<DocumentType>> registrationRequest = retrofitService.GetAllDocumentType();
            registrationRequest.enqueue(new Callback<List<DocumentType>>() {
                @Override
                public void onResponse(Call<List<DocumentType>> call, Response<List<DocumentType>> response) {

                    if (response.body() != null) {

                        documentTypeArrayList.clear();
                        documentTypeArrayList.addAll(response.body());

                        for (int i = 0; i < response.body().size(); i++) {

                        }

                        addDocumentTypeNamePresentSpinnerData(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<DocumentType>> call, Throwable t) {
                    Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });

    }


    public void addDocumentTypeNamePresentSpinnerData(final List<DocumentType> body) {
        List<String> documentList = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            documentList.add(body.get(i).getDocumentTypeName());
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, documentList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDocumentType.setAdapter(dataAdapter2);
        spnDocumentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    SELECTED_DOCUMENT_ID = body.get(i).getId();

                } else {
                    SELECTED_DOCUMENT_ID = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getAllVehicleType() {

            RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
            Call<List<VehicleType>> vehicleTypes = retrofitService.GetVehicleTypes();
            vehicleTypes.enqueue(new Callback<List<VehicleType>>() {
                @Override
                public void onResponse(Call<List<VehicleType>> call, Response<List<VehicleType>> response) {

                    if (response.body() != null) {

                        vehicleTypeArrayList.clear();
                        vehicleTypeArrayList.addAll(response.body());
                        for (int i = 0; i < response.body().size(); i++) {
                        }

                        addVehicleTypeNamePresentSpinnerData(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<VehicleType>> call, Throwable t) {
                    Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    public void addVehicleTypeNamePresentSpinnerData(final List<VehicleType> body) {
        List<String> vehicleList = new ArrayList<>();

        for (int i = 0; i < body.size(); i++) {
            vehicleList.add(body.get(i).getVehicleTypeName());
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, vehicleList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnVehicleType.setAdapter(dataAdapter2);
        spnVehicleType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    SELECTED_VEHICLETYPE_ID = body.get(i).getId();
                    getAllVehicleModel(body.get(i).getId());
                } else {
                    SELECTED_VEHICLETYPE_ID = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getAllVehicleModel(int id) {

            RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
            Call<List<VehicleModel>> vehicleModels = retrofitService.GetVehicleModelByVehicleId(id);
            vehicleModels.enqueue(new Callback<List<VehicleModel>>() {
                @Override
                public void onResponse(Call<List<VehicleModel>> call, Response<List<VehicleModel>> response) {

                    if (response.body() != null) {

                        VehicleModelArrayList.clear();
                        VehicleModelArrayList.addAll(response.body());
                        addVehicleMadyBySpinnerData(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<VehicleModel>> call, Throwable t) {
                    Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });

    }

    public void addVehicleMadyBySpinnerData(final List<VehicleModel> body) {
        List<String> vehicleMadyBy = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            vehicleMadyBy.add(body.get(i).getModelName());
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, vehicleMadyBy);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMadeBy.setAdapter(dataAdapter2);
        spnMadeBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    SELECTED_VEHICLEMODEL_Name = body.get(i).getModelName();
                } else {
                    SELECTED_VEHICLEMODEL_Name = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getAllColor() {


            RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
            Call<List<Colors>> colors = retrofitService.GetColors();
            colors.enqueue(new Callback<List<Colors>>() {
                @Override
                public void onResponse(Call<List<Colors>> call, Response<List<Colors>> response) {

                    if (response.body() != null) {

                        colorArrayList.clear();
                        colorArrayList.addAll(response.body());

                        addColorSpinnerData(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Colors>> call, Throwable t) {
                    Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });

    }

    public void addColorSpinnerData(final List<Colors> body) {
        List<String> colorList = new ArrayList<>();
        colorList.add("Color");
        for (int i = 0; i < body.size(); i++) {
            colorList.add(body.get(i).getColorName());
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, colorList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnColor.setAdapter(dataAdapter2);
        spnColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    SELECTED_COLOR_ID = body.get(i).getId();
                } else {
                    SELECTED_COLOR_ID = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getDivision() {

        String token = SharedPrefManager.getInstance(this).getToken();
        if (token != null) {
            RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
            Call<List<Division>> divisions = retrofitService.GetDivisions();
            divisions.enqueue(new Callback<List<Division>>() {
                @Override
                public void onResponse(Call<List<Division>> call, Response<List<Division>> response) {

                    if (response.body() != null) {

                        divisionArrayList.clear();
                        divisionArrayList.addAll(response.body());

                        addDivisionSpinnerData(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Division>> call, Throwable t) {
                    Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Not registered! Please sign in to continue", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    public void addDivisionSpinnerData(final List<Division> body) {
        List<String> divisionList = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            divisionList.add(body.get(i).getDivisionName());
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, divisionList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSPDivision.setAdapter(dataAdapter2);
        spnSPDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    SELECTED_DIVISION_ID = body.get(i).getId();
                    getDistrict(body.get(i).getId());
                } else {
                    SELECTED_DIVISION_ID = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getDistrict(int id) {

        String token = SharedPrefManager.getInstance(this).getToken();
        if (token != null) {
            RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
            Call<List<District>> divisions = retrofitService.getAllDistricts(id);
            divisions.enqueue(new Callback<List<District>>() {
                @Override
                public void onResponse(Call<List<District>> call, Response<List<District>> response) {

                    if (response.body() != null) {

                        districtArrayList.clear();
                        districtArrayList.addAll(response.body());

                        addDistrictSpinnerData(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<District>> call, Throwable t) {
                    Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Not registered! Please sign in to continue", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    public void addDistrictSpinnerData(final List<District> body) {
        List<String> districtList = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            districtList.add(body.get(i).getDistrictName());
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, districtList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSPDistrict.setAdapter(dataAdapter2);
        spnSPDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 0) {
                        SELECTED_DISTRICT_ID = body.get(i).getId();
                        getAllThana(body.get(i).getId());
                    } else {
                        SELECTED_DISTRICT_ID = 1;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getAllThana(int id) {

            RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
            Call<List<Thana>> thana = retrofitService.GetThanaByDistrictId(id);
            thana.enqueue(new Callback<List<Thana>>() {
                @Override
                public void onResponse(Call<List<Thana>> call, Response<List<Thana>> response) {

                    if (response.body() != null) {

                        thanaArrayList.clear();
                        thanaArrayList.addAll(response.body());

                        addThanaSpinnerData(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Thana>> call, Throwable t) {
                    Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });

    }


    public void addThanaSpinnerData(final List<Thana> body) {
        List<String> thanaList = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            thanaList.add(body.get(i).getThanaName());
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, thanaList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSPThana.setAdapter(dataAdapter2);
        spnSPThana.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    SELECTED_THANA_ID = body.get(i).getId();

                } else {
                    SELECTED_THANA_ID = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

//File Upload

    public void FileUpload( ) {


            //on upload button Click
            if (selectedFilePath != null) {
                dialog = ProgressDialog.show(VehicleEntryActivity.this, "", "Uploading File...", true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //creating new thread to handle Http Operations
                        uploadFile(selectedFilePath);
                    }
                }).start();
            } else {
                Toast.makeText(VehicleEntryActivity.this, "Please choose a File First", Toast.LENGTH_SHORT).show();
            }

        }
    @OnClick(R.id.etBlueBook)
    public void etBlueBook(){
        try {
            showFileChooser();
        }
        catch (Exception e) {
            Utilities.showLogcatMessage(" "+e.toString());

        }
    }

    public void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //sets the select file to all types of files
        intent.setType("*/*");
        //allows to select data and return it
        //starts new activity to select file and return data
        startActivityForResult(intent, 1);
        Utilities.showLogcatMessage(" File Choose");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            switch (resultCode) {
                case 1:
                    if (resultCode == RESULT_OK) {
                        String path = data.getData().getPath();
                        etBlueBook.setText(path);
                    }


                    break;

            }
        }
        catch (Exception e){
            Utilities.showLogcatMessage("onActivityResult "+e.toString());

        }


    }


    /*    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:


                break;

        }

       *//* super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
          *//**//*  if (requestCode == PICK_FILE_REQUEST) {
                try {
                    if (data == null) {
                        //no data present
                        Utilities.showLogcatMessage(" no data present");
                        return;
                    }


                    Uri selectedFileUri = data.getData();
                    String path=data.getData().getPath();
               *//**//**//**//* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    selectedFilePath = FilePath.getPath(this, selectedFileUri);
                }*//**//**//**//*
                    etBlueBook.setText(path);
                    Utilities.showLogcatMessage("Selected File Path:" + path);

                    if (selectedFilePath != null && !selectedFilePath.equals("")) {
                        etBlueBook.setText(selectedFilePath);
                    } else {
                        Toast.makeText(this, "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {
                    Utilities.showLogcatMessage(" "+e.toString());
                }

            }*//**//*
        }*//*
    }*/

    //android upload file to server
    public int uploadFile(final String selectedFilePath) {

        int serverResponseCode = 0;

        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";


        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File selectedFile = new File(selectedFilePath);


        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length - 1];

        if (!selectedFile.isFile()) {
            dialog.dismiss();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    etBlueBook.setText("Source File Doesn't Exist: " + selectedFilePath);
                }
            });
            return 0;
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                URL url = new URL("");
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("uploaded_file", selectedFilePath);

                //creating new dataoutputstream
                dataOutputStream = new DataOutputStream(connection.getOutputStream());

                //writing bytes to data outputstream
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + selectedFilePath + "\"" + lineEnd);

                dataOutputStream.writeBytes(lineEnd);

                //returns no. of bytes present in fileInputStream
                bytesAvailable = fileInputStream.available();
                //selecting the buffer size as minimum of available bytes or 1 MB
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                //setting the buffer as byte array of size of bufferSize
                buffer = new byte[bufferSize];

                //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                while (bytesRead > 0) {
                    //write the bytes read from inputstream
                    dataOutputStream.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();

                Utilities.showLogcatMessage("Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

                //response code of 200 indicates the server status OK
                if (serverResponseCode == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            etBlueBook.setText("File Upload completed.\n\n You can see the uploaded file here: \n\n" + "http://coderefer.com/extras/uploads/" + fileName);
                        }
                    });
                }

                //closing the input and output streams
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(VehicleEntryActivity.this, "File Not Found", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(VehicleEntryActivity.this, "URL error!", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(VehicleEntryActivity.this, "Cannot Read/Write File!", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
            return serverResponseCode;
        }

    }


}
