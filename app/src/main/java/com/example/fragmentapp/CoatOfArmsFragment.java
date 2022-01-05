package com.example.fragmentapp;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.jetbrains.annotations.NotNull;

public class CoatOfArmsFragment extends Fragment {

    static final String ARG_INDEX = "index";

    public CoatOfArmsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coat_of_arms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            int index = args.getInt(ARG_INDEX);
            ImageView imageCoatOfArms = view.findViewById(R.id.coat_of_arms_image_view);
            TypedArray images = getResources().obtainTypedArray(R.array.coat_of_arms);
            imageCoatOfArms.setImageResource(images.getResourceId(index, 0));
            images.recycle();
        }

    }

    public static CoatOfArmsFragment newInstance(int index) {
        CoatOfArmsFragment fragment = new CoatOfArmsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }
}





