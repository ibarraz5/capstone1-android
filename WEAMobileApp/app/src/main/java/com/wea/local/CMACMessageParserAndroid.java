package com.wea.local;

import com.wea.local.model.CollectedUserData;
import com.wea.local.model.CMACMessageModel;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

public class CMACMessageParserAndroid {
    public static void parseMessage() throws InterruptedException {
        CMACMessageModel model = null;
        HttpURLConnection con;

        try {
            URL getMessage = new URL("http://localhost:8080/wea/getMessage");
            Serializer serializer = new Persister();
            InputStream inputStream = getMessage.openStream();

            model = serializer.read(CMACMessageModel.class, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
/*
        CollectedUserData userData = new CollectedUserData(LocalDateTime.now(), "048151",
                model.getMessageNumber(), model.getCapIdentifier());

        //simulate short delay between receipt and display
        Thread.sleep(15);
        userData.setTimeDisplayed(LocalDateTime.now());
        userData.setLocationDisplayed("048151");

        URL getUpload = null;
        try {
            URL upload = new URL("http://localhost:8080/wea/upload");
            con = (HttpURLConnection) upload.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/xml");
            con.setDoOutput(true);

            XmlMapper mapper = new XmlMapper();
            mapper.findAndRegisterModules();
            mapper.writeValue(con.getOutputStream(), userData);

            Map<String, List<String>> map = con.getHeaderFields();
            getUpload = new URL(map.get("Location").get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (getUpload != null) {
            try {
                XmlMapper mapper = new XmlMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);
                CollectedUserData response = mapper.readValue(getUpload, CollectedUserData.class);

                String responseString = mapper.writeValueAsString(response);
                System.out.println(responseString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}
