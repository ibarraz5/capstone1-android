package com.wea.local;

import com.wea.local.model.CollectedUserData;
import com.wea.local.model.CMACMessageModel;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CMACMessageParserAndroid {
    public static void parseMessage() throws InterruptedException {
        CMACMessageModel cmacMessage = null;
        HttpURLConnection con;

        try {
            URL getMessage = new URL("http://localhost:8080/wea/getMessage");
            Serializer serializer = new Persister();
            InputStream inputStream = getMessage.openStream();

            cmacMessage = serializer.read(CMACMessageModel.class, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

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

        URL getUpload = null;
        try {
            URL upload = new URL("http://localhost:8080/wea/upload");
            con = (HttpURLConnection) upload.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/xml");
            con.setDoOutput(true);

            Serializer serializer = new Persister();
            serializer.write(userData, con.getOutputStream());

            Map<String, List<String>> map = con.getHeaderFields();
            getUpload = new URL(map.get("Location").get(0));

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        //confirm the message was uploaded using the received location header value
        if (getUpload != null) {
            try {
                Serializer serializer = new Persister();
                InputStream inputStream = getUpload.openStream();

                System.out.println(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}