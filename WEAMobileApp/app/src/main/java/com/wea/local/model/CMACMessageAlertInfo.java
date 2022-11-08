package com.wea.local.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class CMACMessageAlertInfo {
    @Element(name = "CMAC_expires_date_time")
    private String expires;

    @Element(name = "CMAC_sender_name")
    private String senderName;

    @ElementList(name = "CMAC_Alert_Area")
    @Path("CMAC_Alert_Attributes/CMAC_alert_info/CMAC_Alert_Area")
    private List<CMACMessageAlertArea> alertAreaList;

    public String getExpires() {
        return expires;
    }

    public String getSenderName() {
        return senderName;
    }

    public List<CMACMessageAlertArea> getAlertArea() {
        return alertAreaList;
    }
}
