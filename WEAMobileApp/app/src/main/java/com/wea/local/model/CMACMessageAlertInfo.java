package com.wea.local.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class CMACMessageAlertInfo {
    //I miss fasterxml.jackson...
    @Root(strict = false)
    private static class CMACMessageAlertArea {
        @Element(name = "CMAC_area_description")
        @Path("CMAC_Alert_Text")
        private String areaDescription;

        @Element(name = "CMAC_polygon")
        @Path("CMAC_Alert_Text")
        private String polygon;

        @Element(name = "CMAC_circle")
        @Path("CMAC_Alert_Text")
        private String circle;

        @ElementList(name = "CMAC_cmas_geocode")
        @Path("CMAC_Alert_Text")
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

    @Root(strict = false)
    private static class CMACMessageAlertText {
        @Element(name = "CMAC_text_language")
        @Path("CMAC_Alert_Text")
        private String language;
        @Element(name = "CMAC_short_text_alert_message")
        @Path("CMAC_Alert_Text")
        private String shortMessage;
        @Element(name = "CMAC_long_text_alert_message")
        @Path("CMAC_Alert_Text")
        private String longMessage;

        public String getLanguage() {
            return language;
        }
    }

    @Element(name = "CMAC_category")
    private String category;
    @Element(name = "CMAC_severity")
    private String severity;
    @Element(name = "CMAC_urgency")
    private String urgency;
    @Element(name = "CMAC_certainty")
    private String certainty;
    @Element(name = "CMAC_expires_date_time")
    private String expires;

    @Element(name = "CMAC_sender_name")
    private String senderName;

    @ElementList(name = "CMAC_Alert_Area")
    ///@Path("CMAC_alert_info")
    private List<CMACMessageAlertArea> alertAreaList;

    @ElementList(name = "CMAC_Alert_Text")
    //@Path("CMAC_alert_info")
    private List<CMACMessageAlertText> alertTextList;

    public String getExpires() {
        return expires;
    }

    public String getSenderName() {
        return senderName;
    }

    public List<CMACMessageAlertArea> getAlertAreaList() {
        return alertAreaList;
    }

    public List<CMACMessageAlertText> getAlertTextList() {
        return alertTextList;
    }

    public String getLanguage(int index) {
        return alertTextList.get(index).getLanguage();
    }
}
