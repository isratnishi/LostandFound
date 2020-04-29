package com.opus_bd.lostandfound.Activity;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.hbb20.CountryCodePicker;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformationModel;
import com.opus_bd.lostandfound.Model.GlobalData.District;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeadBodyFoundActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    @BindView(R.id.llInput)
    LinearLayout llInput;
    @BindView(R.id.mcvReport)
    MaterialCardView mcvReport;


    boolean isllPersonInfromationChecked = true;
    @BindView(R.id.llPersonInfromation)
    LinearLayout llPersonInfromation;
    @BindView(R.id.ivTPersonInfromation)
    ImageView ivTPersonInfromation;


    boolean isllPersonPhysicalChecked = true;
    @BindView(R.id.mcvPersonPhysical)
    MaterialCardView mcvPersonPhysical;
    @BindView(R.id.llPersonPhysical)
    LinearLayout llPersonPhysical;
    @BindView(R.id.ivTPersonPhysical)
    ImageView ivTPersonPhysical;


    boolean isllPersonAddressChecked = true;
    @BindView(R.id.mcvPersonAddress)
    MaterialCardView mcvPersonAddress;
    @BindView(R.id.llPersonAddress)
    LinearLayout llPersonAddress;
    @BindView(R.id.ivTPersonAddress)
    ImageView ivTPersonAddress;


    //input field

    /*    (Person Information)*/
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etAge)
    EditText etAge;
    @BindView(R.id.etFathersName)
    EditText etFathersName;
    @BindView(R.id.etSpouseName)
    EditText etSpouseName;
    @BindView(R.id.spnDocType)
    Spinner spnDocType;
    @BindView(R.id.etNidNum)
    EditText etNidNum;
    @BindView(R.id.spnNumberType)
    Spinner spnNumberType;
    @BindView(R.id.etNum)
    EditText etNum;
    @BindView(R.id.spnDeadBodyCondition)
    Spinner spnDeadBodyCondition;
    @BindView(R.id.etDateofdeath)
    EditText etDateofdeath;
    @BindView(R.id.spnImageType)
    Spinner spnImageType;
    @BindView(R.id.etImageName)
    EditText etImageName;


    /* (Location)*/

    @BindView(R.id.spnSPDistrict)
    Spinner spnSPDistrict;
    @BindView(R.id.spnSPThana)
    Spinner spnSPThana;
    @BindView(R.id.spnVillage)
    Spinner spnVillage;
    @BindView(R.id.etAddressDetails)
    EditText etAddressDetails;
    @BindView(R.id.spnAddressType)
    Spinner spnAddressType;
    @BindView(R.id.etOneLineAddress)
    EditText etOneLineAddress;
    @BindView(R.id.etfoundDate)
    EditText etfoundDate;
    @BindView(R.id.etfoundTime)
    EditText etfoundTime;

    /*(Physical Description)*/

    @BindView(R.id.spnEye)
    Spinner spnEye;
    @BindView(R.id.spnNose)
    Spinner spnNose;
    @BindView(R.id.spnHair)
    Spinner spnHair;
    @BindView(R.id.spnforehead)
    Spinner spnForhead;
    @BindView(R.id.spnbeard)
    Spinner spnBeard;
    @BindView(R.id.etWeightkg)
    EditText etWeight;
    @BindView(R.id.spngender)
    Spinner spnGender;
    @BindView(R.id.spnphysical_constitution)
    Spinner spnphysical_constitution;
    @BindView(R.id.spnshape_of_face)
    Spinner spnFaceShape;
    @BindView(R.id.spnchin)
    Spinner spnChin;
    @BindView(R.id.spnskin_color)
    Spinner spnSkinColor;
    @BindView(R.id.spnmustache)
    Spinner spnMustache;
    @BindView(R.id.spnears)
    Spinner spnEar;
    @BindView(R.id.spnneck)
    Spinner spnNeck;
    @BindView(R.id.etFeet)
    EditText etFeet;
    @BindView(R.id.etInches)
    EditText etInches;
    @BindView(R.id.spndescription_of_teeth)
    Spinner spndescription_of_teeth;
    @BindView(R.id.spnspecial_physical_description)
    Spinner spnspecial_physical_description;
    @BindView(R.id.etidentification_mark)
    EditText etIdentityficationMark;


    //ReportView

    /*    (Person Information)*/
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvAge)
    TextView tvAge;
    @BindView(R.id.tvFathersName)
    TextView tvFathersName;
    @BindView(R.id.tvSpouseName)
    TextView tvSpouseName;
    @BindView(R.id.tvDocType)
    TextView tvDocType;
    @BindView(R.id.tvNidNum)
    TextView tvNidNum;
    @BindView(R.id.tvNumberType)
    TextView tvNumberType;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvDeadBodyCondition)
    TextView tvDeadBodyCondition;
    @BindView(R.id.tvDateofdeath)
    TextView tvDateofdeath;
    @BindView(R.id.tvImageType)
    TextView tvImageType;
    @BindView(R.id.tvImageName)
    TextView tvImageName;

    /* (Location)*/

    @BindView(R.id.tvSPDistrict)
    TextView tvSPDistrict;
    @BindView(R.id.tvSPThana)
    TextView tvSPThana;
    @BindView(R.id.tvVillage)
    TextView tvVillage;
    @BindView(R.id.tvAddressDetails)
    TextView tvAddressDetails;
    @BindView(R.id.tvAddressType)
    TextView tvAddressType;
    @BindView(R.id.tvOneLineAddress)
    TextView tvOneLineAddress;
    @BindView(R.id.tvfoundDate)
    TextView tvfoundDate;
    @BindView(R.id.tvfoundTime)
    TextView tvfoundTime;

    /*(Physical Description)*/

    @BindView(R.id.tvEye)
    TextView tvEye;
    @BindView(R.id.tvNose)
    TextView tvNose;
    @BindView(R.id.tvHair)
    TextView tvHair;
    @BindView(R.id.tvforehead)
    TextView tvForhead;
    @BindView(R.id.tvbeard)
    TextView tvBeard;
    @BindView(R.id.tvWeightkg)
    TextView tvWeightkg;
    @BindView(R.id.tvgender)
    TextView tvGender;
    @BindView(R.id.tvphysical_constitution)
    TextView tvphysical_constitution;
    @BindView(R.id.tvshape_of_face)
    TextView tvFaceShape;
    @BindView(R.id.tvchin)
    TextView tvChin;
    @BindView(R.id.tvskin_color)
    TextView tvSkinColor;
    @BindView(R.id.tvmustache)
    TextView tvMustache;
    @BindView(R.id.tvears)
    TextView tvEar;
    @BindView(R.id.tvneck)
    TextView tvNeck;
    @BindView(R.id.tvFetv)
    TextView tvFeet;
    @BindView(R.id.tvInches)
    TextView tvInches;
    @BindView(R.id.tvdescription_of_tetvh)
    TextView tvdescription_of_teeth;
    @BindView(R.id.tvspecial_physical_description)
    TextView tvspecial_physical_description;
    @BindView(R.id.tvidentification_mark)
    TextView tvIdentityficationMark;


    //date picker
    SimpleDateFormat formatter;
    int mYear, mMonth, mDay;
    Calendar calendar = Calendar.getInstance();

    //time picker
    int mHour, mMin, mSec;


    ArrayList<District> districtArrayList = new ArrayList<>();
    ArrayList<Thana> thanaArrayList = new ArrayList<>();
    public int SELECTED_DISTRICT_ID;
    public int SELECTED_DISTRICT_ID_LP;
    public int SELECTED_THANA_ID;
    public int SELECTED_THANA_ID_LP;
    String selectOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dead_body_found);
        ButterKnife.bind(this);
        mcvReport.setVisibility(View.GONE);
        llPersonInfromation.setVisibility(View.VISIBLE);
        Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonInfromation);
        mcvPersonPhysical.setVisibility(View.GONE);
        mcvPersonAddress.setVisibility(View.GONE);
        //date picker
        initializeVariables();
        selectOne = getResources().getString(R.string.select_option);
        getDistrict();


    }

//Date Picker

    private void initializeVariables() {
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        etDateofdeath.setText(formatter.format(calendar.getTime()));

        etDateofdeath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(mYear, mMonth, mDay, R.style.DatePickerSpinner);
            }
        });
        etfoundDate.setText(formatter.format(calendar.getTime()));

        etfoundDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(mYear, mMonth, mDay, R.style.DatePickerSpinner);
            }
        });


        mHour = calendar.get(Calendar.HOUR);
        mMin = calendar.get(Calendar.MINUTE);
        mSec = calendar.get(Calendar.SECOND);
        SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm", Locale.ENGLISH);
        etfoundTime.setText(formatter1.format(calendar.getTime()));


    }

    protected void attachBaseContext(Context base) {
        SharedPreferences tprefs = base.getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, MODE_PRIVATE);
        boolean language = tprefs.getBoolean(SharedPrefManager.KEY_State, true);
        if (language)
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.ENGLISH));
        else
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.BANGLA));
    }

    @OnClick(R.id.ivTPersonInfromation)
    public void ivTPersonInfromation() {
        if (isllPersonInfromationChecked) {
            // show password
            llPersonInfromation.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonInfromation);
            isllPersonInfromationChecked = false;
        } else {
            // hide password
            llPersonInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonInfromation);
            isllPersonInfromationChecked = true;
        }

    }


    @OnClick({R.id.ivTPersonAddress, R.id.btnNext1})
    public void ivTPersonAddress() {
        if (isllPersonAddressChecked) {
            // show password
            llPersonInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonInfromation);
            isllPersonInfromationChecked = true;

            mcvPersonAddress.setVisibility(View.VISIBLE);
            llPersonAddress.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonAddress);
            isllPersonAddressChecked = false;

        } else {
            // hide password
            llPersonAddress.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonAddress);
            isllPersonAddressChecked = true;
        }

    }


    @OnClick({R.id.ivTPersonPhysical, R.id.btnNext2})
    public void ivTPersonPhysical() {
        if (isllPersonPhysicalChecked) {
            // show password
            llPersonAddress.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonAddress);
            isllPersonAddressChecked = true;

            mcvPersonPhysical.setVisibility(View.VISIBLE);
            llPersonPhysical.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonPhysical);
            isllPersonPhysicalChecked = false;

        } else {
            // hide password
            llPersonPhysical.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonPhysical);
            isllPersonPhysicalChecked = true;
        }

    }

    @OnClick(R.id.btnNext3)
    public void ReportShown() {
        llInput.setVisibility(View.GONE);
        mcvReport.setVisibility(View.VISIBLE);
        try {
            tvName.setText(etName.getText().toString());
            tvAge.setText(etAge.getText().toString());
            tvFathersName.setText(etFathersName.getText().toString());
            tvSpouseName.setText(etSpouseName.getText().toString());
            tvDocType.setText(spnDocType.getSelectedItem().toString());
            tvNidNum.setText(etNidNum.getText().toString());
            tvNumberType.setText(spnNumberType.getSelectedItem().toString());
            tvGender.setText(spnGender.getSelectedItem().toString());
            tvNum.setText(etNum.getText().toString());
            tvDeadBodyCondition.setText(spnDeadBodyCondition.getSelectedItem().toString());
            tvDateofdeath.setText(etDateofdeath.getText().toString());
            tvImageType.setText(spnImageType.getSelectedItem().toString());
            tvImageName.setText(etImageName.getText().toString());

        } catch (Exception e) {
            Utilities.showLogcatMessage(" Expetion 1: "+e.toString());
        }

        try{
            /* (Location)*/
             tvSPDistrict.setText(spnSPDistrict.getSelectedItem().toString());
            tvSPThana.setText(spnSPThana.getSelectedItem().toString());
            tvSPDistrict.setText(spnSPDistrict.getSelectedItem().toString());
            tvSPThana.setText(spnSPThana.getSelectedItem().toString());
            tvAddressDetails.setText(etAddressDetails.getText().toString());
            tvVillage.setText(spnVillage.getSelectedItem().toString());
            tvAddressType.setText(spnAddressType.getSelectedItem().toString());
            tvOneLineAddress.setText(etOneLineAddress.getText().toString());
            tvfoundDate.setText(etfoundDate.getText().toString());
            tvfoundTime.setText(etfoundTime.getText().toString());

        } catch (Exception e) {
Utilities.showLogcatMessage(" Expetion 2: "+e.toString());
        }

try{
            /*(Physical Description)*/
            tvEye.setText(spnEye.getSelectedItem().toString());
            tvNose.setText(spnNose.getSelectedItem().toString());
            tvHair.setText(spnHair.getSelectedItem().toString());
            tvForhead.setText(spnForhead.getSelectedItem().toString());
            tvBeard.setText(spnBeard.getSelectedItem().toString());
            tvWeightkg.setText(etWeight.getText().toString());
            tvGender.setText(spnGender.getSelectedItem().toString());
            tvphysical_constitution.setText(spnphysical_constitution.getSelectedItem().toString());
            tvFaceShape.setText(spnFaceShape.getSelectedItem().toString());
            tvChin.setText(spnChin.getSelectedItem().toString());
            tvSkinColor.setText(spnSkinColor.getSelectedItem().toString());
            tvMustache.setText(spnMustache.getSelectedItem().toString());

            tvEar.setText(spnEar.getSelectedItem().toString());
            tvNeck.setText(spnNeck.getSelectedItem().toString());
            tvFeet.setText(etFeet.getText().toString());
            tvInches.setText(etInches.getText().toString());
            tvdescription_of_teeth.setText(spndescription_of_teeth.getSelectedItem().toString());

            tvspecial_physical_description.setText(spnspecial_physical_description.getSelectedItem().toString());
            tvIdentityficationMark.setText(etIdentityficationMark.getText().toString());


} catch (Exception e) {
    Utilities.showLogcatMessage(" Expetion 3: "+e.toString());
}


       /* Intent intent = new Intent(DeadBodyFoundActivity.this, CodeGenerateActivity.class);
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


       /* Intent intent = new Intent(DeadBodyFoundActivity.this, CodeGenerateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }

    @OnClick(R.id.Submit)
    public void Submit() {
        //submitToServer();
        Intent intent = new Intent(DeadBodyFoundActivity.this, CodeGenerateActivity.class);
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
        gdInformationModel.setGdDate("2020-04-14");
        gdInformationModel.setIdentityNo("3453453");
        gdInformationModel.setGDTypeId(Constants.ENTRY_TYPE_ID);
        gdInformationModel.setProductTypeId(Constants.PRODUCT_TYPE_ID);

        //gdInformationModel.setDocumentTypeId(SELECTED_DOCUMENT_ID);
        gdInformationModel.setDocumentDescription("");

        //Man Information

      /*  gdInformationModel.setName(tvName.getText().toString());
        gdInformationModel.setFatherName(tvFathersName.getText().toString());
        gdInformationModel.setSpouseName(tvSpouseName.getText().toString());
        gdInformationModel.setManIdentityNo(tvNidNum.getText().toString());
        gdInformationModel.setReligion(tvReligion.getText().toString());
        gdInformationModel.setHeightTo(tvHeight.getText().toString());
        gdInformationModel.setColor(tvColor.getText().toString());
        gdInformationModel.setEye(tvEye.getText().toString());
        gdInformationModel.setHeir(tvHair.getText().toString());*/
      /*  gdInformationModel.setMadeIn(etMadeIn.getText().toString());
        gdInformationModel.setModelDate(etMadeDate.getText().toString());
*/
       /* //Identity Info
        gdInformationModel.setColorsId(SELECTED_COLOR_ID);
        gdInformationModel.setIdentifySign(etIdentitySign.getText().toString());

        //Place And Time
        gdInformationModel.setDivisionId(SELECTED_DIVISION_ID);
        gdInformationModel.setDistrictId(SELECTED_DISTRICT_ID);
        gdInformationModel.setThanaId(SELECTED_THANA_ID);
        gdInformationModel.setPlaceDetails(etAddressDetails.getText().toString());
        gdInformationModel.setPlaceDetails(etAddressDetails.getText().toString());
        gdInformationModel.setLafDate(etVehicleDate.getText().toString());
        gdInformationModel.setLafTime(etVehicleTime.getText().toString());*/


        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> registrationRequest = retrofitService.SaveGDInformation(token, gdInformationModel);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Utilities.showLogcatMessage("responce");

                        Toast.makeText(DeadBodyFoundActivity.this, "Successfully Done!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DeadBodyFoundActivity.this, CodeGenerateActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    } else {
                        Toast.makeText(DeadBodyFoundActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage("Exception 2" + e.toString());
                    Toast.makeText(DeadBodyFoundActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                // Utilities.hideProgress(LoginActivity.this);
                Toast.makeText(DeadBodyFoundActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
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

    public void getDistrict() {


        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<District>> divisions = retrofitService.getAllDistricts();
        divisions.enqueue(new Callback<List<District>>() {
            @Override
            public void onResponse(Call<List<District>> call, Response<List<District>> response) {

                if (response.body() != null) {

                    districtArrayList.clear();
                    districtArrayList.addAll(response.body());

                    addDistrictSpinnerData(response.body());
                    addDistrictSpinnerDataLP(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<District>> call, Throwable t) {
                Toast.makeText(DeadBodyFoundActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addDistrictSpinnerData(final List<District> body) {
        List<String> districtList = new ArrayList<>();
        districtList.add(0, selectOne);
        for (int i = 0; i < body.size(); i++) {
            districtList.add(i + 1, body.get(i).getDistrictName());
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
                Toast.makeText(DeadBodyFoundActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void addThanaSpinnerData(final List<Thana> body) {
        List<String> thanaList = new ArrayList<>();
        thanaList.add(0, selectOne);
        for (int i = 0; i < body.size(); i++) {
            thanaList.add(i + 1, body.get(i).getThanaName());
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

    public void addDistrictSpinnerDataLP(final List<District> body) {
        List<String> districtList = new ArrayList<>();
        districtList.add(0, selectOne);
        for (int i = 0; i < body.size(); i++) {
            districtList.add(i + 1, body.get(i).getDistrictName());
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, districtList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSPDistrict.setAdapter(dataAdapter2);
        spnSPDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_DISTRICT_ID_LP = body.get(i).getId();
                        getAllThanaLP(body.get(i).getId());
                    } else {
                        SELECTED_DISTRICT_ID_LP = 0;
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

    public void getAllThanaLP(int id) {

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<Thana>> thana = retrofitService.GetThanaByDistrictId(id);
        thana.enqueue(new Callback<List<Thana>>() {
            @Override
            public void onResponse(Call<List<Thana>> call, Response<List<Thana>> response) {

                if (response.body() != null) {

                    thanaArrayList.clear();
                    thanaArrayList.addAll(response.body());

                    addThanaSpinnerDataLP(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Thana>> call, Throwable t) {
                Toast.makeText(DeadBodyFoundActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addThanaSpinnerDataLP(final List<Thana> body) {
        List<String> thanaList = new ArrayList<>();
        thanaList.add(0, selectOne);
        for (int i = 0; i < body.size(); i++) {
            thanaList.add(i + 1, body.get(i).getThanaName());
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, thanaList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSPThana.setAdapter(dataAdapter2);
        spnSPThana.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_THANA_ID_LP = body.get(i).getId();
                } else {
                    SELECTED_THANA_ID_LP = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    //TIME AND dATE
    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(this)
                .callback(DeadBodyFoundActivity.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .showTitle(true)
                .build()
                .show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        etfoundDate.setText(formatter.format(calendar.getTime()));
        etDateofdeath.setText(formatter.format(calendar.getTime()));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @OnClick(R.id.etfoundTime)
    public void etfoundTime() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        int second = mcurrentTime.get(Calendar.SECOND);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String status = "AM";

                if (hour > 11) {
                    // If the hour is greater than or equal to 12
                    // Then the current AM PM status is PM
                    status = "PM";
                }

                // Initialize a new variable to hold 12 hour format hour value
                int hour_of_12_hour_format;

                if (hour > 11) {

                    // If the hour is greater than or equal to 12
                    // Then we subtract 12 from the hour to make it 12 hour format time
                    hour_of_12_hour_format = hour - 12;
                } else {
                    hour_of_12_hour_format = hour;
                }
                etfoundTime.setText(selectedHour + ":" + selectedMinute + " " + status);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
}
