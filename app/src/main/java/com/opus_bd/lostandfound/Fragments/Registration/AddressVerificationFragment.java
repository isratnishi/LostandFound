package com.opus_bd.lostandfound.Fragments.Registration;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RegistrationProcessActivity;
import com.opus_bd.lostandfound.Utils.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressVerificationFragment extends Fragment {
    @BindView(R.id.llnid)
    LinearLayout llnid;  @BindView(R.id.llnid2)
    LinearLayout llnid2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_address_verification, container, false);
        ButterKnife.bind(this,v);
        RegistrationProcessActivity.Step=5;

        EventBus.getDefault().post(new MessageEvent(true));
        return v;
    }

    @OnClick({R.id.buttonYes,R.id.textNext})
    public void btnLogIn() {
       /* IdFragment idFragment=new IdFragment();
        idFragment.setId(1);*/
        // ((RegistrationActivity) getActivity()).selectPage(5);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit);
        ft.replace(R.id.fragmentContainer, new RegFragment(), "NewFragmentTag");
        ft.commit();
    }

    @OnClick(R.id.buttonNo)
    public void buttonNo() {
        llnid2.setVisibility(View.VISIBLE);
        llnid.setVisibility(View.GONE);
    }
}
