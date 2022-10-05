package wea.message.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "CMAC_Alert_Attributes")
public class WEAMessageModel {
    @JsonProperty("CMAC_message_number")
    private String CMAC_message_number;

    @JsonProperty("CMAC_sender")
    private String CMAC_sender;

    public String getCMAC_message_number() {
        return CMAC_message_number;
    }

    public String getCMAC_sender() {
        return CMAC_sender;
    }
}
