package com.wea.local.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class WEAMessageModel {
    @Element(name = "CMAC_message_number")
    private String messageNumber;

    @Element(name = "CMAC_sender")
    private String sender;

    @Element(name = "CMAC_sent_date_time")
    private String sentDateTime;

    @Element(name = "CMAC_message_type")
    private String messageType;

    @Element(name = "CMAC_cap_identifier")
    private String capIdentifier;

    @Element(name = "CMAC_alert_info")
    private WEAMessageAlertInfo alertInfo;

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

}
