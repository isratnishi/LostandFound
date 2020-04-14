package com.opus_bd.lostandfound.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformationModel;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentType;
import com.opus_bd.lostandfound.Model.User.RegistrationModel;
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

public class InformationEntryActivity extends AppCompatActivity {
    @BindView(R.id.llVOwn)
    LinearLayout llVOwn;

    @BindView(R.id.llVOthers)
    LinearLayout llVOthers;

    @BindView(R.id.LLInputForOthers)
    LinearLayout LLInputForOthers;

    @BindView(R.id.LLItems)
    LinearLayout LLItems;
    @BindView(R.id.llInput)
    LinearLayout llInput;
    @BindView(R.id.llEntry)
    LinearLayout llEntry;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv4)
    ImageView iv4;


    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.v3)
    View v3;




   /* @BindView(R.id.cvVOwn)
    CardView cvVOwn;

    @BindView(R.id.cvVOthers)
    CardView cvVOthers;





    @BindView(R.id.cvEntry)
    CardView cvEntry;*/

    @BindView(R.id.cvItems)
    CardView cvItems;
    @BindView(R.id.etUserName)
    EditText etUserName;
    @BindView(R.id.etNumber)
    EditText etNumber;
    @BindView(R.id.cvInput)
    CardView cvInput;
    @BindView(R.id.fabOwn)
    FloatingActionButton fabOwn;
    @BindView(R.id.fabVihecal)
    FloatingActionButton fabVihecal;

    @BindView(R.id.fabOther)
    FloatingActionButton fabOther;
    //Spinner
    @BindView(R.id.spnDocumentType)
    AppCompatSpinner spnDocumentType;

    ArrayList<DocumentType> documentTypeArrayList = new ArrayList<>();
    public int SELECTED_DOCUMENT_ID;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_entry);
        ButterKnife.bind(this);
        LLItems.setVisibility(View.GONE);
        LLInputForOthers.setVisibility(View.GONE);
        llEntry.setVisibility(View.GONE);
        iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));


        //Spinner
        getAllDocument();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.llVOwn, R.id.fabOwn})
    public void LlVOwn() {
        fabOwn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimaryDark));
        fabOther.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.grey_40));
        LLInputForOthers.setVisibility(View.GONE);
        cvInput.setVisibility(View.GONE);
        LLItems.setVisibility(View.VISIBLE);
        cvItems.setVisibility(View.VISIBLE);
        iv4.setVisibility(View.GONE);
        v3.setVisibility(View.GONE);
        iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.llVOthers, R.id.fabOther})
    public void llVOthers() {
        fabOwn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.grey_40));
        fabOther.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimaryDark));
        LLInputForOthers.setVisibility(View.VISIBLE);
        cvInput.setVisibility(View.GONE);
        LLItems.setVisibility(View.GONE);
        cvItems.setVisibility(View.GONE);
        iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.btnOthersInput)
    public void btnOthersInput() {
        LLInputForOthers.setVisibility(View.GONE);
        // llEntry.setVisibility(View.GONE);
        cvItems.setVisibility(View.VISIBLE);
        LLItems.setVisibility(View.VISIBLE);
        iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.llVVihecal)
    public void llVVihecal() {

        fabVihecal.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimaryDark));
        LLInputForOthers.setVisibility(View.GONE);
        llInput.setVisibility(View.GONE);
        llEntry.setVisibility(View.VISIBLE);
        LLItems.setVisibility(View.VISIBLE);
        cvItems.setVisibility(View.GONE);
        iv4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));

    }

    @OnClick(R.id.fab)
    public void fab() {
        Intent intent = new Intent(InformationEntryActivity.this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @OnClick(R.id.btnEntryInput)
    public void btnEntryInput() {
        submitToServer();
       /* Intent intent = new Intent(InformationEntryActivity.this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/

    }


    private void submitToServer() {

        String token = SharedPrefManager.getInstance(this).getToken();
        final GDInformationModel gdInformationModel = new GDInformationModel();

        gdInformationModel.setGdFor("2");
        gdInformationModel.setGdDate("2020-04-14");
        gdInformationModel.setDocumentTypeId(SELECTED_DOCUMENT_ID);
        gdInformationModel.setIdentityNo(etNumber.getText().toString());
        // registrationModel.setOtpCode(etNidNum.getText().toString());


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

                        Toast.makeText(InformationEntryActivity.this, "Successfully Done!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(InformationEntryActivity.this, DashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    } else {
                        Toast.makeText(InformationEntryActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage("Exception 2" + e.toString());
                    Toast.makeText(InformationEntryActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                // Utilities.hideProgress(LoginActivity.this);
                Toast.makeText(InformationEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    //Spinner 

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
                    Toast.makeText(InformationEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
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
}
