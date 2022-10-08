package wea.message.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

@JacksonXmlRootElement(localName = "CMAC_user_data")
public class CollectedUserData {
    @JsonProperty("CMAC_user_time_received")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime received;
    @JsonProperty("CMAC_user_location")
    private String location;

    public CollectedUserData() {}

    public CollectedUserData(LocalDateTime received, String location) {
        this.received = received;
        this.location = location;
    }

    public LocalDateTime time() {
        return received;
    }
}
