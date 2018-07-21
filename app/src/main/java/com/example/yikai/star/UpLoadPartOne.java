package com.example.yikai.star;

public class UpLoadPartOne {
    private String exp;
    private int expValue;
    private String gain;
    private int gainValue;
    private String gamma;
    private int gammaValue;

    public UpLoadPartOne() {
        this.exp = "exp_ms";
        this.expValue = 15;
        this.gain = "gain";
        this.gainValue = 40;
        this.gamma = "gamma";
        this.gammaValue = 40;
    }

    String getContent() {
        return exp + " = " + expValue + ";" +
                gamma + " = " + gammaValue + ";" +
                gain + " = " + gainValue + ";";
    }

    void changeExpValue(int value) {
        expValue = value;
    }

    void changeGainValue(int value) {
        gainValue = value;
    }
}
