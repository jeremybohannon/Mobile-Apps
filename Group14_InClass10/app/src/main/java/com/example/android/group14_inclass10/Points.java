package com.example.android.group14_inclass10;

import java.util.List;

/**
 * Created by jeremybohannon on 11/27/17.
 */
//InClass10_Group14
//Jeremy Bohannon Elizabeth Thompson
//Points.java
public class Points {
    List<Point> points;
    String title;

    public Points(List<Point> points, String title) {
        this.points = points;
        this.title = title;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
