package com.opus_bd.lostandfound.Fragments.Registration;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.opus_bd.lostandfound.GeneralPeople.RegistrationActivity;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RegistrationProcessActivity;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.MessageEvent;
import com.opus_bd.lostandfound.Utils.Utilities;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;




/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {
    @BindView(R.id.etNidNum)
    TextInputEditText etNidNum;
    @BindView(R.id.etbinNum)
    TextInputEditText etbinNum;
    @BindView(R.id.etPassNum)
    TextInputEditText etPassNum;
    @BindView(R.id.etExpary)
    TextInputEditText etExpary;

    @BindView(R.id.tNidNum)
    TextInputLayout tNidNum;
    @BindView(R.id.tbinNum)
    TextInputLayout tbinNum;
    @BindView(R.id.tPassNum)
    TextInputLayout tPassNum;
    @BindView(R.id.tExpary)
    TextInputLayout tExpary;
    int inputID;

    public void setInputID(int inputID) {
        this.inputID = inputID;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_input, container, false);
        ButterKnife.bind(this, v);
        RegistrationProcessActivity.Step=3;

        EventBus.getDefault().post(new MessageEvent(true));
        Utilities.showLogcatMessage(" inputId 1"+ RegistrationActivity.inputid);
        setView(inputID);
        return v;
    }

    public void setView(int i) {
        if(i==1){
            tNidNum.setVisibility(View.VISIBLE);
            tbinNum.setVisibility(View.GONE);
            tPassNum.setVisibility(View.GONE);
            tExpary.setVisibility(View.GONE);
        }else if(i==2){
            tNidNum.setVisibility(View.GONE);
            tbinNum.setVisibility(View.VISIBLE);
            tPassNum.setVisibility(View.GONE);
            tExpary.setVisibility(View.GONE);
        }else if(i==3){
            tNidNum.setVisibility(View.GONE);
            tbinNum.setVisibility(View.GONE);
            tPassNum.setVisibility(View.VISIBLE);
            tExpary.setVisibility(View.VISIBLE);
        }
    }
    @OnClick(R.id.button1)
    public void btnLogIn() {
        /*IdFragment idFragment=new IdFragment();
        idFragment.setId(1);*/
        //((RegistrationActivity) getActivity()).selectPage(3);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit);
        ft.replace(R.id.fragmentContainer, new CameraFragment(), "NewFragmentTag");
        ft.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.isUpdate()) {
            Utilities.showLogcatMessage(" inputid "+RegistrationActivity.inputid);

        }
    }
}
