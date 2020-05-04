package com.opus_bd.lostandfound.Fragments.ForgetPassword;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.opus_bd.lostandfound.R;


public class FPOTPFragment extends Fragment {
    int inputID, passId, view;
    public void setInputID(int inputID) {
        this.inputID = inputID;
    }

    public void setPassId(int passId) {
        this.passId = passId;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_o_t_p2, container, false);
    }
}
