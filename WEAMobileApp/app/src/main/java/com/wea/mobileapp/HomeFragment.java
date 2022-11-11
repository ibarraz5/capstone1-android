package com.wea.mobileapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.wea.local.CMACProcessor;
import com.wea.local.model.CMACMessageModel;
import com.wea.mobileapp.databinding.HomeFragmentBinding;

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
        //must be a final single element array to get results in a thread
        final CMACMessageModel[] cmacMessage = new CMACMessageModel[1];

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cmacMessage[0] = CMACProcessor.parseMessage();
                } catch (Exception e) {
                    e.printStackTrace();
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

        CMACProcessor.displayMessage(getContext(), cmacMessage[0]);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}