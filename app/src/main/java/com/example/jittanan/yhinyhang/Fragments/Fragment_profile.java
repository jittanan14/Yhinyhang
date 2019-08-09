package com.example.jittanan.yhinyhang.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jittanan.yhinyhang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_profile extends Fragment {

    ImageView profile_user;

    public Fragment_profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profile_user = view.findViewById(R.id.user_profile);


        return view;
    }

}
