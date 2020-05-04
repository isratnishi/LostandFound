package com.opus_bd.lostandfound.Fragments.ForgetPassword;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.opus_bd.lostandfound.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DocumentSubmitFragment extends Fragment {

    public DocumentSubmitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_document_submit, container, false);
    }
}
