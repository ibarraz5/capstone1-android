import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import wea.message.model.WEAMessageModel;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

        if (model != null){
            System.out.println(model.getMessageNumber());
            System.out.println(model.getSender());
            System.out.println(model.getAlertInfo().getExpires());
            System.out.println(model.getAlertInfo().getAlertArea().get(0).getAreaDescription());
            System.out.println(model.getAlertInfo().getAlertArea().get(0).getGeocodeList().get(0));
        } else {
            System.out.println("Parsing Error Occurred");
        }
    }
}
