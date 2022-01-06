package com.example.android33.ui.fragments.weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android33.R;
import com.example.android33.base.BaseFragment;
import com.example.android33.databinding.FragmentWeatherBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherFragment extends BaseFragment<WeatherViewModel, FragmentWeatherBinding> {

    public WeatherFragment(){
    }

    private void cityFragment(){
        binding.location.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.action_weatherFragment_to_cityFragment);
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        cityFragment();
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
    }

    @Override
    protected void setUpObserves() {
        viewModel.getWeather(WeatherFragmentArgs.fromBundle(getArguments()).getCityName()).observe(getViewLifecycleOwner(), weatherModels -> {
            binding.location.setText(weatherModels.getName());
            binding.maxTemperature.setText(String.valueOf(weatherModels.getMain().getTempMax()));
            binding.minTemperature.setText(String.valueOf(weatherModels.getMain().getTempMin()));
            binding.wind.setText(String.valueOf(weatherModels.getWind().getSpeed()));
            binding.degrees.setText(String.valueOf(weatherModels.getCoord().getLat()));
        });
    }
}