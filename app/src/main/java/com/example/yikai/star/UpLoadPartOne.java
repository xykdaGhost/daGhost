package com.example.yikai.star;

public class UpLoadPartOne {
    private String exp;
    private int expValue;
    private String gain;
    private int gainValue;

    public UpLoadPartOne() {
        this.exp = "exp_ms";
        this.expValue = 15;
        this.gain = "gain";
        this.gainValue = 40;
    }

    String getContent() {
        return exp + " = " + expValue + ";" +
                gain + " = " + gainValue + ";";
    }

    void changeExpValue(int value) {
        expValue = value;
    }

    void changeGainValue(int value) {
        gainValue = value;
    }
}
