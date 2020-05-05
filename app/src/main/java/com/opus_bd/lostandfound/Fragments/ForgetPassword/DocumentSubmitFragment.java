package com.opus_bd.lostandfound.Fragments.ForgetPassword;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.opus_bd.lostandfound.Activity.ForgetPasswordActivity;
import com.opus_bd.lostandfound.Activity.RegistrationProcessActivity;
import com.opus_bd.lostandfound.Fragments.Registration.IdFragment;
import com.opus_bd.lostandfound.Fragments.Registration.InputFragment;
import com.opus_bd.lostandfound.GeneralPeople.RegistrationActivity;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.MessageEvent;
import com.opus_bd.lostandfound.Utils.Utilities;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DocumentSubmitFragment extends Fragment {

    int iid,passid;
    @BindView(R.id.rlfpd)
    RelativeLayout rlfpd;
    @BindView(R.id.textViewfpd)
    TextView textViewfpd;
    public void setIid(int iid) {
        this.iid = iid;
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
        View v= inflater.inflate(R.layout.fragment_document_submit, container, false);
        ButterKnife.bind(this, v);
        ForgetPasswordActivity.Step=1;

        EventBus.getDefault().post(new MessageEvent(true));
        //setView(iid);
        return v;
    }

    public void setView(int i) {
        if (i == 1) {
            rlfpd.setVisibility(View.VISIBLE);
            passid=1;

        } else if (i == 2) {
            rlfpd.setVisibility(View.GONE);
            textViewfpd.setText("Select below");
            passid=2;
        }
    }

    @OnClick({R.id.cvNIDfpd, R.id.textNext})
    public void button1() {
        InputFragment idFragment = new InputFragment();
        //DocumentSubmitFragment.setInputID(1);
        idFragment.setPassId(passid);
        Utilities.showLogcatMessage("passid 1"+passid);
        /*  RegistrationActivity.id=1;

          EventBus.getDefault().post(new MessageEvent(true));
          ((RegistrationActivity) getActivity()).selectPage(1);*/
        ForgetPasswordActivity.NationalIdentityType="1";
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit);
        ft.replace(R.id.fpFregmentContainer, idFragment, "NewFragmentTag");
        ft.commit();
    }

    @OnClick(R.id.cvBIDfpd)
    public void button2() {
        FPInputFragment idFragment = new FPInputFragment();
//        idFragment.setInputID(2);
//        idFragment.setPassId(passid);
        Utilities.showLogcatMessage("passid 1"+passid);
        /*  RegistrationActivity.id=1;

          EventBus.getDefault().post(new MessageEvent(true));
          ((RegistrationActivity) getActivity()).selectPage(1);*/
        ForgetPasswordActivity.NationalIdentityType="2";
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit);
        ft.replace(R.id.fpFregmentContainer, idFragment, "NewFragmentTag");
        ft.commit();
    }

    @OnClick(R.id.cvpassportfpd)
    public void button3() {
        InputFragment idFragment = new InputFragment();
        idFragment.setInputID(3);
        idFragment.setPassId(passid);
        Utilities.showLogcatMessage("passid 1"+passid);
        ForgetPasswordActivity.NationalIdentityType="3";
        /*  RegistrationActivity.id=1;

          EventBus.getDefault().post(new MessageEvent(true));
          ((RegistrationActivity) getActivity()).selectPage(1);*/

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit);
        ft.replace(R.id.fpFregmentContainer, idFragment, "NewFragmentTag");
        ft.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.isUpdate()) {
            Utilities.showLogcatMessage(" id " + ForgetPasswordActivity.UserName);
        }
    }

}
