package com.wea.local.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class CMACMessageAlertArea {
    @Element(name = "CMAC_area_description")
    private String areaDescription;

    @Element(name = "CMAC_polygon")
    private String polygon;

    @Element(name = "CMAC_circle")
    private String circle;

    @ElementList(name = "CMAC_cmas_geocode")
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
