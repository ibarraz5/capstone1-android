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
        try {
            URL getMessage = new URL("http://localhost:8080/wea/getMessage");
            XmlMapper mapper = new XmlMapper();
            model = mapper.readValue(getMessage, WEAMessageModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        CollectedUserData userData = new CollectedUserData(LocalDateTime.now(), "048151");
        UploadWrapper wrapper = new UploadWrapper(userData, model);

        try {
            URL upload = new URL("http://localhost:8080/wea/upload");
            HttpURLConnection con = (HttpURLConnection) upload.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/xml");
            con.setDoOutput(true);

            XmlMapper mapper = new XmlMapper();
            mapper.findAndRegisterModules();
            mapper.writeValue(con.getOutputStream(), wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
