package com.wea.local.model;

import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.time.LocalDateTime;

@Xml(name = "CMAC_user_data")
public class CollectedUserData {
    @PropertyElement(name = "CMAC_user_time_received")
    private String timeReceived;
    @PropertyElement(name = "CMAC_user_time_displayed")
    private String timeDisplayed;
    @PropertyElement(name = "CMAC_user_location_received")
    private String locationReceived;
    @PropertyElement(name = "CMAC_user_location_displayed")
    private String locationDisplayed;
    @PropertyElement(name = "CMAC_message_number")
    private String messageNumber;
    @PropertyElement(name = "CMAC_cap_identifier")
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

    public void setTimeReceived(String timeReceived) {
        this.timeReceived = timeReceived;
    }

    public void setTimeDisplayed(String timeDisplayed) {
        this.timeDisplayed = timeDisplayed;
    }

    public void setLocationReceived(String locationReceived) {
        this.locationReceived = locationReceived;
    }

    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
    }

    public void setCapIdentifier(String capIdentifier) {
        this.capIdentifier = capIdentifier;
    }

    public void setLocationDisplayed(String locationDisplayed) {
        this.locationDisplayed = locationDisplayed;
    }

    /**
     * Sets the current time as the time the device displayed the message
     *
     * Usage: This method should be called when the message is displayed to the user on the device.
     *
     * @param locationDisplayed Geocode string for the location of display
     */
    public void setDisplayData(String locationDisplayed) {
        this.locationDisplayed = locationDisplayed;
        timeDisplayed = LocalDateTime.now().toString();
    }

    public String getTimeReceived() {
        return timeReceived;
    }

    public String getTimeDisplayed() {
        return timeDisplayed;
    }

    public String getLocationReceived() {
        return locationReceived;
    }

    public String getLocationDisplayed() {
        return locationDisplayed;
    }

    public String getMessageNumber() {
        return messageNumber;
    }

    public String getCapIdentifier() {
        return capIdentifier;
    }
}
