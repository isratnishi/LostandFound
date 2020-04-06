package com.opus_bd.lostandfound.Fragments.Registration;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.opus_bd.lostandfound.GeneralPeople.RegistrationActivity;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RegistrationProcessActivity;
import com.opus_bd.lostandfound.Utils.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reg, container, false);
        ButterKnife.bind(this, v);
        RegistrationProcessActivity.Step=6;

        EventBus.getDefault().post(new MessageEvent(true));
        return v;
    }
    @OnClick(R.id.textNext)
    public void btnLogIn() {
       /* IdFragment idFragment=new IdFragment();
        idFragment.setId(1);*/
        // ((RegistrationActivity) getActivity()).selectPage(5);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit);
        ft.replace(R.id.fragmentContainer, new OTPFragment(), "NewFragmentTag");
        ft.commit();
    }
}
