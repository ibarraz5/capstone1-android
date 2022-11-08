package com.wea.local.model;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.time.LocalDateTime;

@Root
@Element(name = "CMAC_user_data")
public class CollectedUserData {
    /*
    @Element(name = "CMAC_user_time_received")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeReceived;
    @Element(name = "CMAC_user_time_displayed")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeDisplayed;
    @Element(name = "CMAC_user_location_received")
    private String locationReceived;
    @Element(name = "CMAC_user_location_displayed")
    private String locationDisplayed;
    @Element(name = "CMAC_message_number")
    private String messageNumber;
    @Element(name = "CMAC_cap_identifier")
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
    }*/
}
