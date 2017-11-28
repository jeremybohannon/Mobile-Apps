package com.example.android.group14_inclass10;

/**
 * Created by jeremybohannon on 11/27/17.
 */

//InClass10_Group14
//Jeremy Bohannon Elizabeth Thompson
//Point.java

public class Point {
    double latitude;
    double longitude;

    public Point(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
