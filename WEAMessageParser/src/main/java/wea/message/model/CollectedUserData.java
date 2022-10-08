package wea.message.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.time.LocalDateTime;

@JacksonXmlRootElement(localName = "CMAC_user_data")
public class CollectedUserData {
    @JsonProperty("CMAC_user_time_received")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime received;
    @JsonProperty("CMAC_user_location")
    private String location;

    public CollectedUserData(LocalDateTime received, String location) {
        this.received = received;
        this.location = location;
    }

    public LocalDateTime time() {
        return received;
    }
}
