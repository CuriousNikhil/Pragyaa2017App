package org.pragyaa.pragyaa2017;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

import io.saeid.fabloading.LoadingView;

public class AboutUs extends AppCompatActivity {

    private LoadingView loadFab;
    private HTextView aboutP, aboutS;
    private TextView linkCollegeSite;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        //finding id's
        loadFab = (LoadingView) findViewById(R.id.loading_view);
        aboutP = (HTextView) findViewById(R.id.about_pragya);
        aboutS = (HTextView) findViewById(R.id.about_sggs);
        linkCollegeSite = (TextView) findViewById(R.id.link_sggs);

        //end id's


        //loading fa animation
        loadFab.addAnimation(R.color.blue, R.drawable.p_one, LoadingView.FROM_TOP);
        loadFab.addAnimation(R.color.voilet, R.drawable.p_two, LoadingView.FROM_LEFT);
        loadFab.addAnimation(R.color.yellow, R.drawable.p_three, LoadingView.FROM_RIGHT);
        loadFab.addAnimation(R.color.green, R.drawable.p_four, LoadingView.FROM_BOTTOM);
        loadFab.addAnimation(R.color.colorAccent, R.drawable.pragyaa_logo, LoadingView.FROM_TOP);
        //end

        linkCollegeSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(AboutUs.this, Uri.parse("http://sggs.ac.in"));
            }
        });

        findViewById(R.id.link_pragyaa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(AboutUs.this, Uri.parse("http://www.pragyaa.org/"));
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.event_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.developer) {
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            View content = getLayoutInflater().inflate(R.layout.alert_layout, null);
            ad.setView(content);

            ad.setNeutralButton("About Nikhil", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
                    CustomTabsIntent customTabsIntent = builder.build();
                    customTabsIntent.launchUrl(AboutUs.this, Uri.parse("http://nikhyl.tk"));
                }
            });

            ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            ad.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFab.startAnimation();
        //hText animation
        aboutP.setAnimateType(HTextViewType.TYPER);
        aboutP.animateText("About Pragyaa");

        aboutS.setAnimateType(HTextViewType.TYPER);
        aboutS.animateText("About Institute");
    }
}
