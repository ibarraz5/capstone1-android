package com.wea.mobileapp;

import android.app.AlertDialog;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.wea.local.CMACProcessor;
import com.wea.local.model.CMACMessageModel;
import com.wea.mobileapp.databinding.ActivityMainBinding;
import com.wea.local.DBHandler;

import android.view.Menu;
import android.view.MenuItem;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final CMACMessageModel[] cmacMessage = new CMACMessageModel[1];

        DBHandler dbHandler = new DBHandler(MainActivity.this);

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        CMACProcessor.setServerIp(getApplicationContext());

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Retrieving message from server...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                AtomicBoolean success = new AtomicBoolean(false);
                //get a message
                Thread thread = new Thread(() -> {
                    try {
                        cmacMessage[0] = CMACProcessor.parseMessage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (CMACProcessor.setDisplayData(MainActivity.this, cmacMessage[0])) {
                    //TODO: Display message using alert dialog builder here
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("WEA ALERT TEST")
                            .show();
                }

                //upload the message
                thread = new Thread(() -> {
                    try {
                        success.set(CMACProcessor.uploadUserData());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (success.get()) {
                    Snackbar.make(view, "Successfully uploaded user data", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}