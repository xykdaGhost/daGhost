package com.example.yikai.star;

import android.app.Application;

public class SettingContent extends Application {

    private int telescopeChoice;
    private int laserChoice;
    private int controlChoice;

    private void setTelescopeOne() {
        telescopeChoice = 0;
    }

    private void setTelescopeTwo() {
        telescopeChoice = 1;
    }

    private void setLaser() {
        laserChoice = 1;
    }

    private void shutLaser() {
        laserChoice = 0;
    }

    private void setControl() {
        controlChoice = 1;
    }

    private void shutControl() {
        controlChoice = 0;
    }
}
