package com.wea.local;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import com.tickaroo.tikxml.TikXml;
import com.wea.local.model.CollectedUserData;
import com.wea.local.model.CMACMessageModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Random;

import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;

/**
 * This class handles the parsing of incoming CMAC messages, storing them into the locla database, displaying messages,
 * and uploading the user's data to the server
 */
public class CMACProcessor {
    private static CollectedUserData userData;
    private static String SERVER_IP;

    /**
     * Sets the ip address of the server. To get the proper Context, this method should be called from MainActivity
     * @param context The application context
     */
    public static void setServerIp(Context context) {
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        
        try {
            AssetManager assets = context.getAssets();
            inputStreamReader = new InputStreamReader(assets.open("server_address.dat"));
            bufferedReader = new BufferedReader(inputStreamReader);
            SERVER_IP = bufferedReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

    /**
     * Parses a CMAC message creates the initial user data to be uploaded
     *
     * @return The deserialized CMAC XML object, or null if the object could not be deserialized, of th server ip was
     * never set
     * @throws InterruptedException
     */
    public static CMACMessageModel parseMessage() throws InterruptedException {
        if (SERVER_IP == null) {
            return null;
        }

        CMACMessageModel cmacMessage = null;
        HttpURLConnection con;

        try {
            URL getMessage = new URL("http://" + SERVER_IP + ":8080/wea/getMessage");
            InputStream inputStream = getMessage.openStream();

            BufferedSource source = Okio.buffer(Okio.source(inputStream));
            TikXml parser = new TikXml.Builder().exceptionOnUnreadXml(false).build();

            cmacMessage = parser.read(source, CMACMessageModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        //TODO: insert cmacMessage into the database here

        /*
        TODO: once we are able to get location data, the device's geocode should be passed as the
        first parameter. Optionally, we could also remove the location parameter and put the logic
        for determining the current location in the constructor
         */
        userData = new CollectedUserData("048151", cmacMessage);

        //simulate short delay between receipt and display
        Random rand = new Random();
        int sleepTime = rand.nextInt(30);
        Thread.sleep(sleepTime);

        URL getUpload = null;
        try {
            URL upload = new URL("http://" + SERVER_IP + ":8080/wea/upload");
            con = (HttpURLConnection) upload.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/xml");
            con.setDoOutput(true);

            BufferedSink sink = Okio.buffer((Sink) new Buffer());
            TikXml parser = new TikXml.Builder().exceptionOnUnreadXml(false).build();
            parser.write(sink, userData);
            con.getOutputStream().write(sink.buffer().readByteArray());

            Map<String, List<String>> map = con.getHeaderFields();
            getUpload = new URL(map.get("Location").get(0).replace("localhost", SERVER_IP));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        //confirm the message was uploaded using the received location header value
        try {
            InputStream inputStream = getUpload.openStream();

            BufferedSource source = Okio.buffer(Okio.source(inputStream));
            TikXml parser = new TikXml.Builder().exceptionOnUnreadXml(false).build();
            CollectedUserData uploadedData = parser.read(source, CollectedUserData.class);

            Log.i("Uploaded Message Number", uploadedData.getMessageNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cmacMessage;
    }

    /**
     * Displays a CMAC message
     * @param context The context of the activity that is displaying the alert
     * @param message The message to be displayed
     */
    public static void displayMessage(Context context, CMACMessageModel message) {
        //TODO: display message here (I recommend using AlertDialog.Builder(context))


    }

    /**
     *
     * @return
     */
    public static boolean uploadUserData() {
        return true;
    }
}
