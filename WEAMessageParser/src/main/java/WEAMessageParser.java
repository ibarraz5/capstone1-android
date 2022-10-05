import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import wea.message.model.WEAMessageModel;

import java.io.File;

public class WEAMessageParser {
    public static void main(String args[]) {
        WEAMessageModel model = null;

        try {
            XmlMapper mapper = new XmlMapper();
            model = mapper.readValue(new File("src/main/resources/sampleMessage.xml"),
                    WEAMessageModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (model != null){
            System.out.println(model.getCMAC_message_number());
            System.out.println(model.getCMAC_sender());
        }
    }
}
