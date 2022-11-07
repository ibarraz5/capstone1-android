package com.wea.local.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
@Element(name = "CMAC_Alert_Area")
public class WEAMessageAlertArea {
    @Element(name = "CMAC_area_description")
    private String areaDescription;

    @Element(name = "CMAC_polygon")
    private String polygon;

    @Element(name = "CMAC_circle")
    private String circle;

    @Element(name = "CMAC_cmas_geocode")
    //@JacksonXmlElementWrapper(useWrapping = false)
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
