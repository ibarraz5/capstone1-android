package com.wea.local.model;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

@Xml(name = "CMAC_Alert_Attributes")
public class CMACMessageModel {
    @PropertyElement(name = "CMAC_message_number")
    private String messageNumber;

    @PropertyElement(name = "CMAC_sender")
    private String sender;

    @PropertyElement(name = "CMAC_sent_date_time")
    private String sentDateTime;

    @PropertyElement(name = "CMAC_message_type")
    private String messageType;

    @PropertyElement(name = "CMAC_cap_identifier")
    private String capIdentifier;

    @Element(name = "CMAC_alert_info")
    private CMACMessageAlertInfo alertInfo;

    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setSentDateTime(String sentDateTime) {
        this.sentDateTime = sentDateTime;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public void setCapIdentifier(String capIdentifier) {
        this.capIdentifier = capIdentifier;
    }

    public void setAlertInfo(CMACMessageAlertInfo alertInfo) {
        this.alertInfo = alertInfo;
    }

    public String getMessageNumber() {
        return messageNumber;
    }

    public String getSender() {
        return sender;
    }

    public String getSentDateTime() {
        return sentDateTime;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getCapIdentifier() {
        return capIdentifier;
    }

    public CMACMessageAlertInfo getAlertInfo() {
        return alertInfo;
    }
}
