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
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.MessageEvent;
import com.opus_bd.lostandfound.Utils.Utilities;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class IdFragment extends Fragment {
int iid;

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

        View v=inflater.inflate(R.layout.fragment_id, container, false);
        ButterKnife.bind(this,v);
        Utilities.showLogcatMessage(" id "+iid);
        RegistrationProcessActivity.Step=2;

        EventBus.getDefault().post(new MessageEvent(true));
        return v;
    }

    @OnClick({R.id.cvNID,R.id.textNext})
    public void button1() {
        InputFragment idFragment=new InputFragment();
        idFragment.setInputID(1);
        /*  RegistrationActivity.id=1;

          EventBus.getDefault().post(new MessageEvent(true));
          ((RegistrationActivity) getActivity()).selectPage(1);*/

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit);
        ft.replace(R.id.fragmentContainer, idFragment, "NewFragmentTag");
        ft.commit();
    }

    @OnClick(R.id.cvBID)
    public void button2() {
        InputFragment idFragment=new InputFragment();
        idFragment.setInputID(2);
        /*  RegistrationActivity.id=1;

          EventBus.getDefault().post(new MessageEvent(true));
          ((RegistrationActivity) getActivity()).selectPage(1);*/

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit);
        ft.replace(R.id.fragmentContainer, idFragment, "NewFragmentTag");
        ft.commit();
    }

    @OnClick(R.id.cvpassport)
    public void button3() {
        InputFragment idFragment=new InputFragment();
        idFragment.setInputID(3);
        /*  RegistrationActivity.id=1;

          EventBus.getDefault().post(new MessageEvent(true));
          ((RegistrationActivity) getActivity()).selectPage(1);*/

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit);
        ft.replace(R.id.fragmentContainer, idFragment, "NewFragmentTag");
        ft.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.isUpdate()) {
            Utilities.showLogcatMessage(" id "+RegistrationActivity.id);
        }
    }
}
