package wea.message.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

@JacksonXmlRootElement(localName = "CMAC_user_data")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectedUserData {
    @JsonProperty("CMAC_user_time_received")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeReceived;
    @JsonProperty("CMAC_user_time_displayed")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeDisplayed;
    @JsonProperty("CMAC_user_location_received")
    private String locationReceived;
    @JsonProperty("CMAC_user_location_displayed")
    private String locationDisplayed;
    @JsonProperty("CMAC_message_number")
    private String messageNumber;
    @JsonProperty("CMAC_cap_identifier")
    private String capIdentifier;

    public CollectedUserData() {}

    public CollectedUserData(LocalDateTime received, String location,
                             String messageNumber, String capIdentifier) {
        this.timeReceived = received;
        this.locationReceived = location;
        this.messageNumber = messageNumber;
        this.capIdentifier = capIdentifier;
    }

    public void setTimeDisplayed(LocalDateTime timeDisplayed) {
        this.timeDisplayed = timeDisplayed;
    }

    public void setLocationDisplayed(String locationDisplayed) {
        this.locationDisplayed = locationDisplayed;
    }
}
