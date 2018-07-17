package com.example.yikai.star;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class TelescopeActivity extends AppCompatActivity {
    private TextView result;
    private DrawerLayout mDrawerLayout;
    private String itemName;
    private TextView project;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private WebView webView;
    private UpLoadPartOne uploadone;
    private UpLoadPartTwo uploadtwo;
    private int menuItemId;
    private static Handler handler;
    private TextView debug;
    private Intent intent;
    private int intentValue;


    private static String endpoint = "oss-cn-shenzhen.aliyuncs.com";
    private static String accessKeyId = "LTAI2JfCvQVELtvU";
    private static String accessKeySecret = "odrAXQROwWjPzTmvBZ1zDzkBjxqjiX";
    private static String bucketName = "upboard";
    private static String firstKey = "get_upload_config";
    private static String secondKey = "serial_config";
    private static String webUrl = "https://upboard.oss-cn-shenzhen.aliyuncs.com/test1";

//    private static String endpoint = "oss-cn-hangzhou.aliyuncs.com";
//    private static String accessKeyId = "LTAI6U6DnyaE1Kn1";
//    private static String accessKeySecret = "8bwEzyXKIWGl4hfSTSVAIB22zlLjTV";
//    private static String bucketName = "androidaliyuntest";
//    private static String firstKey = "my_picture";
//    private static String webUrl = "https://androidaliyuntest.oss-cn-hangzhou.aliyuncs.com/picture.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telescope);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar);
        result = (TextView) findViewById(R.id.result);
        project = (TextView) findViewById(R.id.project);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                result.setText(progress + "% ");
                int value;
                switch (menuItemId) {

                    case R.id.nav_exposure:
                        value = (int) progress * 10;
                        uploadone.changeExpValue(value);
                        break;

                    case R.id.nav_buff:
                        uploadone.changeGainValue(progress);
                        break;

                    case R.id.nav_focus:
                        value = (int) progress / 33;
                        uploadtwo.changeEFocusValue(value);
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        intent = new Intent(TelescopeActivity.this, SettingActivity.class);

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                menuItemId = item.getItemId();
                itemName = item.toString();
                mDrawerLayout.closeDrawers();
                if (!itemName.equals("设置")) {
                    project.setText(itemName + ":");
                }
                return true;
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
//            webView.reload();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TelescopeActivity.this, "get picture", Toast.LENGTH_SHORT).show();
                webView.reload();
            }
        });

        webView = (WebView) findViewById(R.id.web_vi);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(webUrl);
        webView.setInitialScale(225);


        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(accessKeyId, accessKeySecret, "");
        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider);

        byte[] uploadDataOne = new byte[20 * 1024];
        byte[] uploadDataTwo = new byte[20 * 1024];
        uploadone = new UpLoadPartOne();
        uploadtwo = new UpLoadPartTwo();
        uploadDataOne = uploadone.getContent().getBytes();
        uploadDataTwo = uploadtwo.getContent().getBytes();

        PutObjectRequest putone = new PutObjectRequest(bucketName, firstKey, uploadDataOne);
        PutObjectRequest puttwo = new PutObjectRequest(bucketName, secondKey, uploadDataTwo);

        try {
            PutObjectResult putObjectResultone = oss.putObject(putone);
            PutObjectResult putObjectResulttwo = oss.putObject(puttwo);
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_exposure:
                Toast.makeText(TelescopeActivity.this, "exposure", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.shooting:
//                Toast.makeText(this, "get picture", Toast.LENGTH_SHORT).show();
//                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                webView.reload();
                break;
            default:
        }
        return true;
    }
}
