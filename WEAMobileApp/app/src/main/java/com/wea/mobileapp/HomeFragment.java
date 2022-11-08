package com.wea.mobileapp;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.wea.local.CMACMessageParserAndroid;
import com.wea.mobileapp.databinding.HomeFragmentBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomeFragment extends Fragment {

    private HomeFragmentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader bufferedReader = null;
                InputStreamReader inputStreamReader = null;
                try {
                    //reads your local server ip address to use in API calls
                    AssetManager assets = getContext().getApplicationContext().getAssets();
                    inputStreamReader = new InputStreamReader(assets.open("server_address.dat"));
                    bufferedReader = new BufferedReader(inputStreamReader);
                    String address = bufferedReader.readLine();
                    CMACMessageParserAndroid.parseMessage(address);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //close resources
                    try {
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                    } catch (IOException e) {}
                }
            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        binding.historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_HomeFragment_to_HistoryFragment);

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}