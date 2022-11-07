package com.wea.local.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.List;
@Root
@Element(name = "CMAC_alert_info")
public class WEAMessageAlertInfo {
    @Element(name = "CMAC_expires_date_time")
    private String expires;

    @Element(name = "CMAC_sender_name")
    private String senderName;

    @Element(name = "CMAC_Alert_Area")
    //@JacksonXmlElementWrapper(useWrapping = false)
    private List<com.wea.local.model.WEAMessageAlertArea> alertAreaList;

    public String getExpires() {
        return expires;
    }

    public String getSenderName() {
        return senderName;
    }

    public List<com.wea.local.model.WEAMessageAlertArea> getAlertArea() {
        return alertAreaList;
    }
}
