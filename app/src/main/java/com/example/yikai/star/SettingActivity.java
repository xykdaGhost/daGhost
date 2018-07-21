package com.example.yikai.star;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import javax.xml.parsers.SAXParser;

public class SettingActivity extends AppCompatActivity {

    private int telescopeChoice;
    private int laserChoice;
    private int controlChoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Switch telescopeSwitch = (Switch) findViewById(R.id.switch_telescope);
        Switch laserSwitch = (Switch) findViewById(R.id.switch_laser);
        final Switch controlSwitch = (Switch) findViewById(R.id.switch_control);
        Button confirmButton = (Button) findViewById(R.id.confirm_button);

        telescopeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                TextView telecope_1 = (TextView) findViewById(R.id.telescope_1);
                TextView telecope_2 = (TextView) findViewById(R.id.telescope_2);
                if (b) {
                    telescopeChoice = 1;
                    telecope_1.setTextColor(Color.rgb(115, 115, 115));
                    telecope_2.setTextColor(Color.rgb(74, 35, 221));
                } else {
                    telescopeChoice = 0;
                    telecope_2.setTextColor(Color.rgb(115, 115, 115));
                    telecope_1.setTextColor(Color.rgb(74, 35, 221));
                }
            }
        });

        laserSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                TextView textView = (TextView) findViewById(R.id.laser);
                if (b) {
                    laserChoice = 1;
                    textView.setTextColor(Color.rgb(74, 35, 221));
                } else {
                    laserChoice = 0;
                    textView.setTextColor(Color.rgb(115, 115, 115));
                }
            }
        });

        controlSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                TextView textView = (TextView) findViewById(R.id.control);
                if (b) {
                    controlChoice = 1;
                    textView.setTextColor(Color.rgb(74, 35, 221));
                } else {
                    controlChoice = 0;
                    textView.setTextColor(Color.rgb(115, 115, 115));
                }
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("telescope_return", telescopeChoice);
                intent.putExtra("laser_return", laserChoice);
                intent.putExtra("control_return", controlChoice);
                startActivity(intent);
            }
        });
    }
}
