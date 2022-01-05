package com.example.fragmentapp;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;


public class CitiesFragment extends Fragment {

    private static final String CURRENT_CITY = "CurrentCity";
    // Текущая позиция (выбранный город)
    private int currentPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cities, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(CURRENT_CITY, 0);
        }
            initList(view);
        if (isLandscape()) {
            showLandCoatOfArms(currentPosition);
        }
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] cities = getResources().getStringArray(R.array.cities);
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i];
            TextView tv = new TextView(getContext());
            tv.setText(city);
            tv.setTextSize(30);
            layoutView.addView(tv);

            final int position = i;
            tv.setOnClickListener(v -> {
                currentPosition = position;
                showCoatOfArms(position);
            });
        }
    }

    private void showCoatOfArms(int index) {
        if (isLandscape()) {
            showLandCoatOfArms(index);
        } else {
            showPortCoatOfArms(index);
        }

    }

    private void showPortCoatOfArms(int index) {
        CoatOfArmsFragment coatOfArmsFragment = CoatOfArmsFragment.newInstance(index);
        //чтобы запустить фрагмент из фрагмента нужно вызвать getSupportFragmentManager() через requireActivity()
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, coatOfArmsFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showLandCoatOfArms(int index) {
        CoatOfArmsFragment detail = CoatOfArmsFragment.newInstance(index);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.coat_of_arms_container, detail);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        outState.putInt(CURRENT_CITY, currentPosition);
        super.onSaveInstanceState(outState);

    }
}
