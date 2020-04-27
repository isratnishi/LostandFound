package com.opus_bd.lostandfound.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import com.google.android.material.card.MaterialCardView;
import com.opus_bd.lostandfound.Adapter.GalleryAdapter;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformationModel;
import com.opus_bd.lostandfound.Model.Documentaion.Colors;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentType;
import com.opus_bd.lostandfound.Model.Documentaion.MetroAreaModel;
import com.opus_bd.lostandfound.Model.Documentaion.RegistrationLevelModel;
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
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleEntryActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    // Layout
    @BindView(R.id.llInput)
    LinearLayout llInput;
    @BindView(R.id.mcvReport)
    MaterialCardView mcvReport;

    // Information View
    @BindView(R.id.mcvVehicleInformation)
    MaterialCardView mcvVehicleInformation;
    @BindView(R.id.mcvVehicleIdendityInformation)
    MaterialCardView mcvVehicleIdendityInformation;
    @BindView(R.id.mcvVehicleAttachment)
    MaterialCardView mcvVehicleAttachment;
    @BindView(R.id.mcvVehiclePlaceTimeInformation)
    MaterialCardView mcvVehiclePlaceTimeInformation;
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
    @BindView(R.id.etImage)
    EditText etImage;

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

    boolean isllVehicleAttachment = true;
    @BindView(R.id.llVehicleAttachment)
    LinearLayout llVehicleAttachment;

    @BindView(R.id.ivVehicleAttachment)
    ImageView ivVehicleAttachment;

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
    ArrayList<MetroAreaModel> metroAreaModelArrayList = new ArrayList<>();
    ArrayList<RegistrationLevelModel> registrationLevelModels = new ArrayList<>();
    public int SELECTED_DOCUMENT_ID;
    public int SELECTED_VEHICLETYPE_ID;
    public String SELECTED_VEHICLEMODEL_Name;
    public String SELECTED_REGNO_1;
    public String SELECTED_REGNO_2;
    public int SELECTED_COLOR_ID;
    public int SELECTED_DIVISION_ID;
    public int SELECTED_DISTRICT_ID;
    public int SELECTED_THANA_ID;


    //date picker
    SimpleDateFormat formatter;
    int mYear, mMonth, mDay;
    Calendar calendar = Calendar.getInstance();

    //time picker
    int mHour, mMin, mSec;

//Report view textView

    @BindView(R.id.tvModel)
    TextView tvModel;

    @BindView(R.id.tvRegNoName)
    TextView tvRegNoName;

    @BindView(R.id.tvEngineNo)
    TextView tvEngineNo;

    @BindView(R.id.tvChesisNo)
    TextView tvChesisNo;

    @BindView(R.id.tvCCNo)
    TextView tvCCNo;

    @BindView(R.id.tvMadeIn)
    TextView tvMadeIn;

    @BindView(R.id.tvMadeDate)
    TextView tvMadeDate;

    @BindView(R.id.tvIdentitySign)
    TextView tvIdentitySign;

    @BindView(R.id.tvAddressDetails)
    TextView tvAddressDetails;

    @BindView(R.id.tvVehicleDate)
    TextView tvVehicleDate;

    @BindView(R.id.tvVehicleTime)
    TextView tvVehicleTime;

    @BindView(R.id.tvSPDivision)
    TextView tvSPDivision;

    @BindView(R.id.tvSPDistrict)
    TextView tvSPDistrict;

    @BindView(R.id.tvSPThana)
    TextView tvSPThana;

    @BindView(R.id.tvPhoto)
    TextView tvPhoto;
/*tvPhoto.setText("Selected Images" + mArrayUri.size());
    @BindView(R.id.tvD)
    TextView tvDocumentType;*/

    @BindView(R.id.tvVehicleType)
    TextView tvVehicleType;

    @BindView(R.id.tvMadeBy)
    TextView tvMadeBy;
    @BindView(R.id.tvColor)
    TextView tvColor;
    @BindView(R.id.tvBlueBook)
    TextView tvBlueBook;

//Multiple Image add

    private Button btn;
    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    List<String> imagesEncodedList;
    private GridView gvGallery, gvGallery1;
    private GalleryAdapter galleryAdapter;

    String selectOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_entry);
        ButterKnife.bind(this);
        mcvVehicleInformation.setVisibility(View.VISIBLE);
        mcvVehicleIdendityInformation.setVisibility(View.GONE);
        mcvVehicleAttachment.setVisibility(View.GONE);
        mcvVehiclePlaceTimeInformation.setVisibility(View.GONE);
        mcvReport.setVisibility(View.GONE);
        Utilities.showLogcatMessage("Activity Open ");
        selectOne=getResources().getString(R.string.select_option);
        getMatropolitonName();
        getRegiSerial();
        //Spinner
        getAllDocument();
        getAllVehicleType();
        getAllColor();
        //getDivision();
        GetAllMetropolitanArea();
        getDistrict();
        GetAllRegistrationLevel();

        //date picker
        initializeVariables();
        etRegNoName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // if user is typing string one character at a time
                if (count == 1) {
                    // auto insert dashes while user typing their ssn
                    if (start == 1 || start == 7) {
                        etRegNoName.setText(etRegNoName.getText() + "-");
                        etRegNoName.setSelection(etRegNoName.getText().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//Multiple image

        gvGallery = (GridView) findViewById(R.id.gv);
        gvGallery1 = (GridView) findViewById(R.id.gv1);

        etImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ImagePicker();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);
            }
        });
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
    public void ivVehicleInformation() {
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

    @OnClick({R.id.ivVIdentityInfo, R.id.btnNext1})
    public void ivVIdentityInfo() {

        if (isllVehicleIdentificationChecked) {
            // show password
            llVehicleInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVehicleInformation);
            isllVehicleEntryChecked = true;

            mcvVehicleIdendityInformation.setVisibility(View.VISIBLE);
            llVIdentityInfo.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivVIdentityInfo);
            isllVehicleIdentificationChecked = false;

        } else {
            // hide password
            llVIdentityInfo.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVIdentityInfo);
            isllVehicleIdentificationChecked = true;
        }
    }

    @OnClick({R.id.ivVehicleAttachment, R.id.btnNext2})
    public void ivVehicleAttachment() {
        if (isllVehicleAttachment) {
            // show password
            llVIdentityInfo.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVIdentityInfo);
            isllVehicleIdentificationChecked = true;

            mcvVehicleAttachment.setVisibility(View.VISIBLE);
            llVehicleAttachment.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivVehicleAttachment);
            isllVehicleAttachment = false;

        } else {
            // hide password
            llVehicleAttachment.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVehicleAttachment);
            isllVehicleAttachment = true;
        }

    }


    @OnClick({R.id.ivVPATInfo, R.id.btnNext3})
    public void ivVPATInfo() {
        if (isllVPATChecked) {
            // show password
            llVehicleAttachment.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVehicleAttachment);
            isllVehicleAttachment = true;

            mcvVehiclePlaceTimeInformation.setVisibility(View.VISIBLE);
            llVPATInfo.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivVPATInfo);
            isllVPATChecked = false;

        } else {
            // hide password
            llVPATInfo.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVPATInfo);
            isllVPATChecked = true;
        }

    }


    @OnClick(R.id.btnNext4)
    public void btnNext4() {
        llInput.setVisibility(View.GONE);
        mcvReport.setVisibility(View.VISIBLE);
        try {
            tvVehicleType.setText(spnVehicleType.getSelectedItem().toString());
            tvVehicleDate.setText(etVehicleDate.getText().toString());
            tvModel.setText(etModel.getText().toString());
            tvMadeBy.setText(spnMadeBy.getSelectedItem().toString());
            tvMadeIn.setText(etMadeIn.getText().toString());
            tvSPDistrict.setText(spnSPDistrict.getSelectedItem().toString());
            tvAddressDetails.setText(etAddressDetails.getText().toString());
            tvRegNoName.setText(spnRegNoName1.getSelectedItem().toString() + " " + spnRegNoName2.getSelectedItem().toString()
                    + " " + etRegNoName.getText().toString());
         /*   tvReligion.setText(spnReligion.getSelectedItem().toString());
            tvGender.setText(spnGender.getSelectedItem().toString());
            tvBloodGroup.setText(spnBloodGroup.getSelectedItem().toString());
            tvMaritalStatus.setText(spnMaritalStatus.getSelectedItem().toString());
            tvOcupation.setText(spnOcupation.getSelectedItem().toString());

            tvHair.setText(etHair.getText().toString());
            tvNose.setText(etNose.getText().toString());
            tvHeight.setText(etHeight.getText().toString());
            tvEye.setText(etEye.getText().toString());
            tvColor.setText(etColor.getText().toString());*/


        } catch (Exception e) {

        }



       /* Intent intent = new Intent(UnknownManActivity.this, CodeGenerateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }

    @OnClick(R.id.Edit)
    public void Edit() {
        llInput.setVisibility(View.VISIBLE);
        mcvReport.setVisibility(View.GONE);
       /* tvName.setText(etName.getText().toString());
        tvFathersName.setText(etFathersName.getText().toString());
        tvSpouseName.setText(etSpouseName.getText().toString());
        tvNidNum.setText(etNidNum.getText().toString());
        tvReligion.setText(spnReligion.getSelectedItem().toString());*/


       /* Intent intent = new Intent(UnknownManActivity.this, CodeGenerateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }

    @OnClick(R.id.Submit)
    public void Submit() {
        //submitToServer();
        Intent intent = new Intent(VehicleEntryActivity.this, CodeGenerateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void submitToServer() {

        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();

        final GDInformationModel gdInformationModel = new GDInformationModel();

        //GD Information
        gdInformationModel.setUserName(UserName);
        gdInformationModel.setGdFor(Constants.GDFOR);
        gdInformationModel.setGdDate(etVehicleDate.getText().toString());
        gdInformationModel.setIdentityNo("3453453");
        gdInformationModel.setGDTypeId(Constants.ENTRY_TYPE_ID);
        gdInformationModel.setProductTypeId(Constants.PRODUCT_TYPE_ID);

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

                        Toast.makeText(VehicleEntryActivity.this, "Successfully Done!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(VehicleEntryActivity.this, CodeGenerateActivity.class);
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
        vehicleList.add(0,selectOne);
        for (int i = 0; i < body.size(); i++) {
            vehicleList.add(i+1,body.get(i).getVehicleTypeName());
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, vehicleList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnVehicleType.setAdapter(dataAdapter2);
        spnVehicleType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_VEHICLETYPE_ID = body.get(i).getId();
                    getAllVehicleModel(body.get(i-1).getId());
                } else {
                    SELECTED_VEHICLETYPE_ID = 0;
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
        colorList.add(0,selectOne);
        for (int i = 0; i < body.size(); i++) {
            colorList.add(i+1,body.get(i).getColorName());
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, colorList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnColor.setAdapter(dataAdapter2);
        spnColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_COLOR_ID = body.get(i).getId();
                } else {
                    SELECTED_COLOR_ID = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    public void GetAllMetropolitanArea() {


        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<MetroAreaModel>> colors = retrofitService.GetAllMetropolitanArea();
        colors.enqueue(new Callback<List<MetroAreaModel>>() {
            @Override
            public void onResponse(Call<List<MetroAreaModel>> call, Response<List<MetroAreaModel>> response) {

                if (response.body() != null) {

                    metroAreaModelArrayList.clear();
                    metroAreaModelArrayList.addAll(response.body());

                    addMetroAreaSpinnerData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<MetroAreaModel>> call, Throwable t) {
                Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addMetroAreaSpinnerData(final List<MetroAreaModel> body) {
        List<String> colorList = new ArrayList<>();
        //colorList.add(0,selectOne);
        for (int i = 0; i < body.size(); i++) {
            colorList.add(body.get(i).getAreaName());
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, colorList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRegNoName1.setAdapter(dataAdapter2);
        spnRegNoName1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               if (i >= 0) {
                    SELECTED_REGNO_1 = body.get(i).getAreaName();
                } else {
                   SELECTED_REGNO_1 = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void GetAllRegistrationLevel() {


        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<RegistrationLevelModel>> colors = retrofitService.GetAllRegistrationLevel();
        colors.enqueue(new Callback<List<RegistrationLevelModel>>() {
            @Override
            public void onResponse(Call<List<RegistrationLevelModel>> call, Response<List<RegistrationLevelModel>> response) {

                if (response.body() != null) {

                    registrationLevelModels.clear();
                    registrationLevelModels.addAll(response.body());

                    addRegistrationLevelModelSpinnerData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<RegistrationLevelModel>> call, Throwable t) {
                Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addRegistrationLevelModelSpinnerData(final List<RegistrationLevelModel> body) {

        List<String> colorList = new ArrayList<>();
//        colorList.add(0,selectOne);
        for (int i = 0; i < body.size(); i++) {
            colorList.add(body.get(i).getLevelName());
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, colorList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRegNoName2.setAdapter(dataAdapter2);
        spnRegNoName2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               if (i >= 0) {
                    SELECTED_REGNO_2 = body.get(i).getLevelName();
                } else {
                   SELECTED_REGNO_2 = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getDivision() {

        String token = SharedPrefManager.getInstance(this).getToken();

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

    }


    public void addDivisionSpinnerData(final List<Division> body) {
        List<String> divisionList = new ArrayList<>();
        divisionList.add(0,selectOne);
        for (int i = 0; i < body.size(); i++) {
            divisionList.add(i+1,body.get(i).getDivisionName());
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, divisionList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSPDivision.setAdapter(dataAdapter2);
        spnSPDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_DIVISION_ID = body.get(i).getId();
                    //getDistrict(body.get(i).getId());
                } else {
                    SELECTED_DIVISION_ID = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void getDistrict( ) {


            RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
            Call<List<District>> divisions = retrofitService.getAllDistricts();
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

    }
/*    public void getDistrict(int id) {


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

    }*/


    public void addDistrictSpinnerData(final List<District> body) {
        List<String> districtList = new ArrayList<>();
        districtList.add(0,selectOne);
        for (int i = 0; i < body.size(); i++) {
            districtList.add(i+1,body.get(i).getDistrictName());
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, districtList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSPDistrict.setAdapter(dataAdapter2);
        spnSPDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_DISTRICT_ID = body.get(i).getId();
                        getAllThana(body.get(i).getId());
                    } else {
                        SELECTED_DISTRICT_ID = 0;
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
        thanaList.add(0,selectOne);
        for (int i = 0; i < body.size(); i++) {
            thanaList.add(i+1,body.get(i).getThanaName());
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, thanaList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSPThana.setAdapter(dataAdapter2);
        spnSPThana.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_THANA_ID = body.get(i).getId();
                } else {
                    SELECTED_THANA_ID = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

//File Upload

    public void FileUpload() {

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
    public void etBlueBook() {
        try {
            showFileChooser();
        } catch (Exception e) {
            Utilities.showLogcatMessage(" " + e.toString());

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
/*

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (resultCode) {
                case 1:
                    if (resultCode == RESULT_OK) {
                        String path = data.getData().getPath();
                        etBlueBook.setText(path);
                    }


                    break;

            }
        } catch (Exception e) {
            Utilities.showLogcatMessage("onActivityResult " + e.toString());

        }


    }
*/


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

    //InformationEntryActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, InformationEntryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        super.onBackPressed();
    }
//DashBoard

    @OnClick(R.id.fab)
    public void fab() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
//Date Picker

    private void initializeVariables() {
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        etVehicleDate.setText(formatter.format(calendar.getTime()));
        etVehicleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(mYear,mMonth,mDay,R.style.DatePickerSpinner);
            }
        });

        mHour = calendar.get(Calendar.HOUR);
        mMin = calendar.get(Calendar.MINUTE);
        mSec = calendar.get(Calendar.SECOND);
        SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm", Locale.ENGLISH);
        etVehicleTime.setText(formatter1.format(calendar.getTime()));


    }

   /* @OnClick(R.id.etVehicleDate)
    public void etDateOnClick() {
       *//* DatePickerDialog mDatePicker;
        mDatePicker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int
                            selectedYear, int selectedMonth, int selectedDay) {
                        Calendar saleDateCalender = Calendar.getInstance();
                        saleDateCalender.set(Calendar.YEAR, selectedYear);
                        saleDateCalender.set(Calendar.MONTH, selectedMonth);
                        saleDateCalender.set(Calendar.DAY_OF_MONTH, selectedDay);

                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                        etVehicleDate.setText(formatter.format(saleDateCalender.getTime()));
                    }
                }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Select Date");
        mDatePicker.show();*//*

        showDate(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, R.style.DatePickerSpinner);
    }*/

  /*  @Override
    public void onDateSet(com.tsongkha.spinnerdatepicker.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        dateTextView.setText(simpleDateFormat.format(calendar.getTime()));
    }

    @Override
    public void onCancelled(DatePicker view) {
        dateTextView.setText(R.string.cancelled);
    }*/


    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(this)
                .callback(VehicleEntryActivity.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .showTitle(true)
                .build()
                .show();
    }

    @OnClick(R.id.etVehicleTime)
    public void etVehicleTime() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        int second = mcurrentTime.get(Calendar.SECOND);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                etVehicleTime.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

//Multiple image
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Utilities.showLogcatMessage("requestCode "+requestCode);
        Utilities.showLogcatMessage("resultCode "+resultCode);
        Utilities.showLogcatMessage("data "+data);
      *//*  try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                imagesEncodedList = new ArrayList<String>();
                if (data.getData() != null) {

                    Uri mImageUri = data.getData();

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded = cursor.getString(columnIndex);
                    cursor.close();

                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                    mArrayUri.add(mImageUri);
                    galleryAdapter = new GalleryAdapter(getApplicationContext(), mArrayUri);
                    gvGallery.setAdapter(galleryAdapter);
                    gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                    ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                            .getLayoutParams();
                    mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);


                    gvGallery1.setAdapter(galleryAdapter);
                    gvGallery1.setVerticalSpacing(gvGallery1.getHorizontalSpacing());
                    ViewGroup.MarginLayoutParams mlp1 = (ViewGroup.MarginLayoutParams) gvGallery1
                            .getLayoutParams();
                    mlp1.setMargins(0, gvGallery1.getHorizontalSpacing(), 0, 0);
                    tvPhoto.setText("Selected Images " + mArrayUri.size());

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                            galleryAdapter = new GalleryAdapter(getApplicationContext(), mArrayUri);
                            gvGallery.setAdapter(galleryAdapter);
                            gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                                    .getLayoutParams();
                            mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);


                            gvGallery1.setAdapter(galleryAdapter);
                            gvGallery1.setVerticalSpacing(gvGallery1.getHorizontalSpacing());
                            ViewGroup.MarginLayoutParams mlp1 = (ViewGroup.MarginLayoutParams) gvGallery1
                                    .getLayoutParams();
                            mlp1.setMargins(0, gvGallery1.getHorizontalSpacing(), 0, 0);

                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                        tvPhoto.setText("Selected Images " + mArrayUri.size());
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG)
                    .show();
            Utilities.showLogcatMessage(" "+e.getLocalizedMessage());
        }*//*


    }*/

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        etVehicleDate.setText(formatter.format(calendar.getTime()));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


   /* // Multipole Image picker
public void ImagePicker(){
    Options options = Options.init()
            .setRequestCode(200)                                           //Request code for activity results
            .setCount(3)                                                   //Number of images to restict selection count
            .setFrontfacing(false)                                         //Front Facing camera on start
           *//* .setPreSelectedUrls(returnValue)                               //Pre selected Image Urls*//*
            .setExcludeVideos(false)                                       //Option to exclude videos
            .setVideoDurationLimitinSeconds(30)                            //Duration for video recording
            .setScreenOrientation(Options.SCREEN_ORIENTATION_PORTRAIT)     //Orientaion
            .setPath("/pix/images");                                       //Custom Path For media Storage

    Pix.start(VehicleEntryActivity.this, options);


}
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 200) {
            ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
        }
    }*/
}
