package com.wea.local;

import android.util.Log;

import com.tickaroo.tikxml.TikXml;
import com.wea.local.model.CollectedUserData;
import com.wea.local.model.CMACMessageModel;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Random;

import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;

public class CMACMessageParserAndroid {
    public static void parseMessage(String address) throws InterruptedException {
        CMACMessageModel cmacMessage = null;
        HttpURLConnection con;

        try {
            URL getMessage = new URL("http://" + address + ":8080/wea/getMessage");

            InputStream inputStream = getMessage.openStream();

            BufferedSource source = Okio.buffer(Okio.source(inputStream));
            TikXml parser = new TikXml.Builder().exceptionOnUnreadXml(false).build();
                    //.addTypeAdapter(new StringTypeAdapter()).build();

            cmacMessage = parser.read(source, CMACMessageModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println(cmacMessage.getMessageNumber());

        /*
        TODO: once we are able to get location data, the device's geocode should be passed as the
        first parameter. Optionally, we could also remove the location parameter and put the logic
        for determining the current location in the constructor
         */
        CollectedUserData userData = new CollectedUserData("048151", cmacMessage);

        //simulate short delay between receipt and display
        Random rand = new Random();
        int sleepTime = rand.nextInt(30);
        Thread.sleep(sleepTime);

        //set when and where the message was displayed on the device
        userData.setTimeDisplayed();
        /*
        TODO: similar to the constructor, this parameter should be replaced when we are able to
        programmatically determine the user's location geocode, or the logic for that could simply
        take place inside the method
         */
        userData.setLocationDisplayed("048151");
/*
        URL getUpload = null;
        try {
            URL upload = new URL("http://" + address + ":8080/wea/upload");
            con = (HttpURLConnection) upload.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/xml");
            con.setDoOutput(true);

            Serializer serializer = new Persister();
            serializer.write(userData, con.getOutputStream());

            Map<String, List<String>> map = con.getHeaderFields();
            getUpload = new URL(map.get("Location").get(0).replace("localhost", address));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        //confirm the message was uploaded using the received location header value
        try {
            InputStream inputStream = getUpload.openStream();
            Serializer serializer = new Persister();
            CollectedUserData uploadedData = serializer.read(CollectedUserData.class, inputStream);

            Log.i("Uploaded Message Number", uploadedData.getMessageNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
