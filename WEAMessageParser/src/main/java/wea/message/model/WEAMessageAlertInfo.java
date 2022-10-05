package wea.message.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "CMAC_alert_info")
public class WEAMessageAlertInfo {
    @JsonProperty("CMAC_expires_date_time")
    private String expires;

    @JsonProperty("CMAC_sender_name")
    private String senderName;

    @JsonProperty("CMAC_Alert_Area")
    private WEAMessageAlertArea alertArea;

    public String getExpires() {
        return expires;
    }

    public String getSenderName() {
        return senderName;
    }

    public WEAMessageAlertArea getAlertArea() {
        return alertArea;
    }
}
