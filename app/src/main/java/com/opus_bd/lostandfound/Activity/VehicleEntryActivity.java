package com.opus_bd.lostandfound.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.bumptech.glide.Glide;
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
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleEntryActivity extends AppCompatActivity {

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

//        llVIdentityInfo.setVisibility(View.GONE);
//        llVehicleInfromation.setVisibility(View.GONE);
//        llVPATInfo.setVisibility(View.VISIBLE);

    }

    // add items into spinner dynamically
//    public void addItemsOnSpinner2() {
//
//        spinner2 = (Spinner) findViewById(R.id.spinner2);
//        List<String> list = new ArrayList<String>();
//        list.add("list 1");
//        list.add("list 2");
//        list.add("list 3");
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, list);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner2.setAdapter(dataAdapter);
//    }

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
        if (token != null) {
            RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
            Call<List<DocumentType>> registrationRequest = retrofitService.GetAllDocumentType();
            registrationRequest.enqueue(new Callback<List<DocumentType>>() {
                @Override
                public void onResponse(Call<List<DocumentType>> call, Response<List<DocumentType>> response) {

                    if (response.body() != null) {

                        documentTypeArrayList.clear();
                        documentTypeArrayList.addAll(response.body());
                        Utilities.showLogcatMessage(" Div Size " + response.body().size());
                        for (int i = 0; i < response.body().size(); i++) {
                            Utilities.showLogcatMessage(" Div ID" + response.body().get(i).getId());
                        }

                        addDocumentTypeNamePresentSpinnerData(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<DocumentType>> call, Throwable t) {
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

        String token = SharedPrefManager.getInstance(this).getToken();
        if (token != null) {
            RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
            Call<List<VehicleType>> vehicleTypes = retrofitService.GetVehicleTypes();
            vehicleTypes.enqueue(new Callback<List<VehicleType>>() {
                @Override
                public void onResponse(Call<List<VehicleType>> call, Response<List<VehicleType>> response) {

                    if (response.body() != null) {

                        vehicleTypeArrayList.clear();
                        vehicleTypeArrayList.addAll(response.body());
                        Utilities.showLogcatMessage(" Div Size " + response.body().size());
                        for (int i = 0; i < response.body().size(); i++) {
                            Utilities.showLogcatMessage(" Div ID" + response.body().get(i).getId());
                        }

                        addVehicleTypeNamePresentSpinnerData(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<VehicleType>> call, Throwable t) {
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

    public void addVehicleTypeNamePresentSpinnerData(final List<VehicleType> body) {
        List<String> vehicleList = new ArrayList<>();
        vehicleList.add("যানবাহনের ধরণ");
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

        String token = SharedPrefManager.getInstance(this).getToken();
        if (token != null) {
            RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
            Call<List<VehicleModel>> vehicleModels = retrofitService.GetVehicleModelByVehicleId(id);
            vehicleModels.enqueue(new Callback<List<VehicleModel>>() {
                @Override
                public void onResponse(Call<List<VehicleModel>> call, Response<List<VehicleModel>> response) {

                    if (response.body() != null) {

                        VehicleModelArrayList.clear();
                        VehicleModelArrayList.addAll(response.body());
                        Utilities.showLogcatMessage(" Div Size " + response.body().size());
                        for (int i = 0; i < response.body().size(); i++) {
                            Utilities.showLogcatMessage(" Div ID" + response.body().get(i).getId());
                        }

                        addVehicleMadyBySpinnerData(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<VehicleModel>> call, Throwable t) {
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

    public void addVehicleMadyBySpinnerData(final List<VehicleModel> body) {
        List<String> vehicleMadyBy = new ArrayList<>();
        vehicleMadyBy.add("্রান্ডের নাম");
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

        String token = SharedPrefManager.getInstance(this).getToken();
        if (token != null) {
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
        } else {
            Toast.makeText(this, "Not registered! Please sign in to continue", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
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

        String token = SharedPrefManager.getInstance(this).getToken();
        if (token != null) {
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
        } else {
            Toast.makeText(this, "Not registered! Please sign in to continue", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
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


}
