import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import wea.message.model.CollectedUserData;
import wea.message.model.WEAMessageModel;
import wea.message.model.wrapper.UploadWrapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class WEAMessageParser {
    public static void main(String args[]) {
        WEAMessageModel model = null;
        HttpURLConnection con;

        try {
            URL getMessage = new URL("http://localhost:8080/wea/getMessage");
            XmlMapper mapper = new XmlMapper();
            model = mapper.readValue(getMessage, WEAMessageModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        CollectedUserData userData = new CollectedUserData(LocalDateTime.now(), "048151");
        UploadWrapper wrapper = new UploadWrapper(userData, model);

        URL getUpload = null;
        try {
            URL upload = new URL("http://localhost:8080/wea/upload");
            con = (HttpURLConnection) upload.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/xml");
            con.setDoOutput(true);

            XmlMapper mapper = new XmlMapper();
            mapper.findAndRegisterModules();
            mapper.writeValue(con.getOutputStream(), wrapper);

            Map<String, List<String>> map = con.getHeaderFields();
            getUpload = new URL(map.get("Location").get(0));
            System.out.println(map.get("Location").get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (getUpload != null) {
            try {
                XmlMapper mapper = new XmlMapper();
                UploadWrapper response = mapper.readValue(getUpload, UploadWrapper.class);

                String responseString = mapper.writeValueAsString(response);
                System.out.println(responseString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
