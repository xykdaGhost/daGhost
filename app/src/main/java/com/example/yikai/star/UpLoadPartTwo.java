package com.example.yikai.star;

public class UpLoadPartTwo {

    private String laser;
    private int laserValue;
    private String efocus;
    private int efocusValue;
    private String settem;
    private int settemValue;

    public UpLoadPartTwo() {
        laser = "laser";
        laserValue = 0;
        efocus = "efocus";
        efocusValue = 0;
        settem = "settem";
        settemValue = 40;
    }

    String getContent() {
        return laser + " : " + laserValue + ";" +
                efocus + " : " + efocusValue + ";" +
                "m_read:1:" +
                settem + " : " + settemValue + ";" +
                "restart:0:" ;
    }

    void changeLaserValue(int value) {
        laserValue = value;
    }

    void changeEFocusValue(int value) {
        efocusValue = value;
    }

    void changeSettemValue(int value) {
        settemValue = value;
    }
}
