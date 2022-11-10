package com.wea.local.model;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

@Xml(name = "CMAC_alert_info")
public class CMACMessageAlertInfo {
    //I miss fasterxml.jackson...
    private static class CMACMessageAlertArea {
        @PropertyElement(name = "CMAC_area_description")
        private String areaDescription;

        @PropertyElement(name = "CMAC_polygon")
        private String polygon;

        @PropertyElement(name = "CMAC_circle")
        private String circle;

        @Element(name = "CMAC_cmas_geocode")
        private List<String> geocodeList;

        public String getAreaDescription() {
            return areaDescription;
        }

        public String getPolygon() {
            return polygon;
        }

        public String getCircle() {
            return circle;
        }

        public List<String> getGeocodeList() {
            return geocodeList;
        }
    }

    private static class CMACMessageAlertText {
        @PropertyElement(name = "CMAC_text_language")
        private String language;
        @PropertyElement(name = "CMAC_short_text_alert_message")
        private String shortMessage;
        @PropertyElement(name = "CMAC_long_text_alert_message")
        private String longMessage;

        public String getLanguage() {
            return language;
        }
    }

    @PropertyElement(name = "CMAC_category")
    private String category;
    @PropertyElement(name = "CMAC_severity")
    private String severity;
    @PropertyElement(name = "CMAC_urgency")
    private String urgency;
    @PropertyElement(name = "CMAC_certainty")
    private String certainty;
    @PropertyElement(name = "CMAC_expires_date_time")
    private String expires;

    @PropertyElement(name = "CMAC_sender_name")
    private String senderName;

    //@Element(name = "CMAC_Alert_Area")
    ///@Path("CMAC_alert_info")
    private List<CMACMessageAlertArea> alertAreaList;

    //@Element(name = "CMAC_Alert_Text")
    //@Path("CMAC_alert_info")
    private List<CMACMessageAlertText> alertTextList;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getCertainty() {
        return certainty;
    }

    public void setCertainty(String certainty) {
        this.certainty = certainty;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public List<CMACMessageAlertArea> getAlertAreaList() {
        return alertAreaList;
    }

    public void setAlertAreaList(List<CMACMessageAlertArea> alertAreaList) {
        this.alertAreaList = alertAreaList;
    }

    public List<CMACMessageAlertText> getAlertTextList() {
        return alertTextList;
    }

    public void setAlertTextList(List<CMACMessageAlertText> alertTextList) {
        this.alertTextList = alertTextList;
    }
}
