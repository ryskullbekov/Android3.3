package com.example.android33.ui.fragments.city;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android33.R;
import com.example.android33.databinding.FragmentCityBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CityFragment extends Fragment {

    private FragmentCityBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCityBinding.inflate(inflater, container, false);
        setSelectedItem();
        return binding.getRoot();
    }


    public void setSelectedItem() {
        binding.mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                navController.navigate(CityFragmentDirections.actionCityFragmentToWeatherFragment().setCityName(binding.userField.getText().toString().trim()));

            }
        });
    }}