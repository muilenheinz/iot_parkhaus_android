package com.example.iotparkhaus.customDataStructs;

public class constants {
    private static String SVG_URL = "https://iot-parkhaus.midb.medien.hs-duesseldorf.de/parking.svg";
    private static String backgroundColorSpotAvilable = "rgb(19, 97, 194)";
    private static String textColorSpotAvilable = "rgb(255, 255, 255)";
    private static String backgroundColorSpotOccupied = "rgb(185,185,185)";
    private static String textColorSpotOccupied = "rgb(139, 139, 139)";
    private static String URL_parkingRules = "https://hs-duesseldorf.de/hochschule/verwaltung/parkraumordnung";
    private static String URL_impressum = "https://hs-duesseldorf.de/impressum";

    public static String getSvgUrl() {
        return SVG_URL;
    }

    public static String getBackgroundColorSpotAvilable() {
        return backgroundColorSpotAvilable;
    }

    public static String getTextColorSpotAvilable() {
        return textColorSpotAvilable;
    }

    public static String getTextColorSpotOccupied() {
        return textColorSpotOccupied;
    }

    public static String getBackgroundColorSpotOccupied() {
        return backgroundColorSpotOccupied;
    }

    public static String getURL_parkingRules() {
        return URL_parkingRules;
    }

    public static String getURL_impressum() {
        return URL_impressum;
    }
}
