package com.wea.mobileapp;

import android.app.AlertDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.wea.local.CMACProcessor;
import com.wea.local.LocationUtils;
import com.wea.local.model.CMACMessageModel;
import com.wea.mobileapp.databinding.ActivityMainBinding;
import com.wea.local.DBHandler;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private ArrayList messageArr = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DBHandler dbHandler = new DBHandler(MainActivity.this);

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        CMACProcessor.setServerIp(getApplicationContext());

        binding.getMessageButton.setOnClickListener(getMessage());
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

    /**
     * Defines the OnClickListener for the getMessageButton
     * Clicking the button will triggger all three steps of the CMACProcessor class and display
     * a WEA on the screen
     *
     * @return The OnClickListener event
     */
    private View.OnClickListener getMessage() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CMACMessageModel[] cmacMessage = new CMACMessageModel[1];
                Snackbar.make(view, "Retrieving message from server...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //get a message
                Thread thread = new Thread(() -> {
                    try {
                        cmacMessage[0] = CMACProcessor.parseMessage();
                        System.out.println("PRINTING OUT CMAC MESSAGE 1");
                        messageArr.add(cmacMessage[0].getShortMessage("english"));
                        System.out.println(messageArr.get(0));
                        HistoryFragment.setText(messageArr);

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

                //if no message is received
                if (cmacMessage[0] == null) {
                    Snackbar.make(view, "No incoming messages found", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                } else {
                    Snackbar.make(view, "Retrieved message from server", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                Random rand = new Random();
                int randomSleep = rand.nextInt(100) + 1;

                //simulate a short random time between receiving and displaying the message
                try {
                    Thread.sleep(randomSleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                getWeaAlertDialog(cmacMessage, view).show();

                LocationUtils.getGPSLocation(MainActivity.this, MainActivity.this);

                String coords = "40.842226,14.211753 40.829498,14.229262, 40.833394,14.26617 40.84768,14.278701 40.858716,14.27715";
                Double[] myPoint = {40.8518, 14.2681};

                boolean inside = LocationUtils.isInsideArea(coords, myPoint);
                System.out.println("CHECKING INSIDE POLYGON");
                System.out.println(inside);

            }
        };
    }

    /**
     * Creates and returns an AlertDialog that displays a WEA message. The Dialog also handles setting the user data
     * for when the alert is displayed as well as upload the data to the server
     *
     * @param cmacMessage The CMAC Message to be displayed, this array should contain only one element
     * @param view        The view hosting the AlertDialog
     * @return A WEA AlertDialog
     */
    private AlertDialog getWeaAlertDialog(CMACMessageModel[] cmacMessage, View view) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.emergency_alert);
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        AlertDialog.Builder weaAlertBuilder = new AlertDialog.Builder(MainActivity.this)
                .setTitle(cmacMessage[0].getShortMessage("english"))
                .setIcon(R.drawable.alert_icon)
                .setMessage(cmacMessage[0].getLongMessage("english"))
                .setCancelable(false)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    vibrator.cancel();
                    mediaPlayer.stop();
                    //Handle device data upload on close
                    AtomicBoolean success = new AtomicBoolean(false);

                    Thread thread = new Thread(() -> {
                        try {
                            success.set(CMACProcessor.uploadUserData());
                            Log.i("success", success.toString());
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
                        Snackbar.make(view, "Failed to upload user data", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });

        final AlertDialog weaAlertDialog = weaAlertBuilder.create();
        weaAlertDialog.setOnShowListener(dialogInterface -> {
            //Handle setting device on message display
            CMACProcessor.setDisplayData(MainActivity.this, cmacMessage[0]);
            //set vibration and sound effects
            long[] vibrationPatter = {200, 1900, 150};
            vibrator.vibrate(VibrationEffect.createWaveform(vibrationPatter, 0));
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        });

        return weaAlertDialog;
    }

//    private
}