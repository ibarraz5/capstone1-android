package com.wea.local.model;


import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Xml;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@Xml(name = "CMAC_user_data")
public class CollectedUserData {
    //@Element(name = "CMAC_user_time_received")
    private String timeReceived;
    //@Element(name = "CMAC_user_time_displayed")
    private String timeDisplayed;
    //@Element(name = "CMAC_user_location_received")
    private String locationReceived;
    //@Element(name = "CMAC_user_location_displayed")
    private String locationDisplayed;
    //@Element(name = "CMAC_message_number")
    private String messageNumber;
    //@Element(name = "CMAC_cap_identifier")
    private String capIdentifier;

    public CollectedUserData() {}

    /**
     * Constructs the user's upload data with data that is to be collected
     * when the message is received and sets the time the message was
     * received using the current time
     *
     * @param locationReceived Geocode string for the location of receipt
     * @param message The message that was received
     */
    public CollectedUserData(String locationReceived, CMACMessageModel message) {
        this.timeReceived = LocalDateTime.now().toString();
        this.locationReceived = locationReceived;
        this.messageNumber = message.getMessageNumber();
        this.capIdentifier = message.getCapIdentifier();
    }

    public String getMessageNumber() {
        return messageNumber;
    }

    /**
     * Sets the current time as the time the device displayed the message
     *
     * Usage: This method should be called when the message is displayed to the user on the device.
     */
    public void setTimeDisplayed() {
        this.timeDisplayed = LocalDateTime.now().toString();
    }

    /**
     * Sets the location where the message was displayed on the device
     *
     * @param locationDisplayed Geocode string for the location of display
     */
    public void setLocationDisplayed(String locationDisplayed) {
        this.locationDisplayed = locationDisplayed;
    }
}
