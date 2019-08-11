package com.example.jittanan.yhinyhang.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jittanan.yhinyhang.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_profile extends Fragment {

    private ImageView userProfile;
    private TextView emailTextView, usernameTextView, genderTextView, birthdayTextView, elementTextView, foodLoseTextView, bodyTextView, numYhinTextView, numYhangTextView;

    private SharedPreferences sp;
    private String PREF_NAME = "Log in";

    public Fragment_profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        userProfile = view.findViewById(R.id.user_profile);

        sp = getActivity().getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        emailTextView = view.findViewById(R.id.email_text_view);
        usernameTextView = view.findViewById(R.id.username_text_view);
        genderTextView = view.findViewById(R.id.gender_text_view);
        birthdayTextView = view.findViewById(R.id.birth_text_view);
        elementTextView = view.findViewById(R.id.element_text_view);
        foodLoseTextView = view.findViewById(R.id.foodlose_text_view);
        bodyTextView = view.findViewById(R.id.body_text_view);
        numYhinTextView = view.findViewById(R.id.num_yhin_text_view);
        numYhangTextView = view.findViewById(R.id.num_yhang_text_view);
        userProfile = view.findViewById(R.id.user_profile);


        String email    = sp.getString("email", "");
        String username = sp.getString("username", "");
        String gender   = sp.getString("gender", "");
        String birthday = sp.getString("birthday", "");
        String element  = sp.getString("element", "");
        String foodLose = sp.getString("foodLose", "");
        String image    = sp.getString("image", "");
        String body     = sp.getString("body", "");
        String numYhin  = sp.getString("numYhin", "");
        String numYhang = sp.getString("numYhang", "");


        emailTextView.setText(email);
        usernameTextView.setText(username);
        birthdayTextView.setText(birthday);
        elementTextView.setText(element);
        if(foodLose.equals("-")){
            foodLoseTextView.setText("ไม่แพ้วัตถุดิบอาหารชนิดใด");
        }else {
            foodLoseTextView.setText(foodLose);
        }
        bodyTextView.setText(body);
        numYhinTextView.setText(numYhin);
        numYhangTextView.setText(numYhang);

        if(gender.equals("m")) {
            genderTextView.setText("ชาย");
        }
        else {
            genderTextView.setText("หญิง");
        }


        return view;
    }

}
