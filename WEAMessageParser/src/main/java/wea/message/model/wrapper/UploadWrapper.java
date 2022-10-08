package wea.message.model.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import wea.message.model.CollectedUserData;
import wea.message.model.WEAMessageModel;

@JacksonXmlRootElement(localName = "CMAC_upload")
public class UploadWrapper {
    @JsonProperty("CMAC_user_data")
    private CollectedUserData userData;
    @JsonProperty("CMAC_Alert_Attributes")
    private WEAMessageModel message;
    //unique identifier for a given upload

    public UploadWrapper(CollectedUserData userData, WEAMessageModel mesage) {
        this.userData = userData;
        this.message = mesage;
    }

    public CollectedUserData getUserData() {
        return userData;
    }

    public WEAMessageModel getMessage() {
        return message;
    }
}
