package com.opus_bd.lostandfound.Fragments.Registration;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.opus_bd.lostandfound.GeneralPeople.Main2Activity;
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
public class OTPFragment extends Fragment {

    public OTPFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_o_t_p, container, false);
        ButterKnife.bind(this, v);
        RegistrationProcessActivity.Step=7;

        EventBus.getDefault().post(new MessageEvent(true));
        return v;
    }

    @OnClick(R.id.textNext)
    public void btnLogIn() {
        Intent intent = new Intent(getContext(), Main2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
