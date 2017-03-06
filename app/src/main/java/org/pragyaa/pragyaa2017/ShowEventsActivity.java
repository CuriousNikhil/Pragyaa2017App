package org.pragyaa.pragyaa2017;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.pragyaa.pragyaa2017.fragments.CommitteesFragment;
import org.pragyaa.pragyaa2017.fragments.DepartmentalFragment;
import org.pragyaa.pragyaa2017.fragments.GamingZoneFragment;
import org.pragyaa.pragyaa2017.fragments.OnlineEventsFragment;
import org.pragyaa.pragyaa2017.fragments.RoboticsFragment;
import org.pragyaa.pragyaa2017.fragments.SpecialEventsFragment;

public class ShowEventsActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private int position;
    private CoordinatorLayout layout;
    private FloatingActionButton fab;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_events);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.dept_fab);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        layout = (CoordinatorLayout) findViewById(R.id.activity_show_events);

        position = getIntent().getIntExtra("event", 0);


        switch (position) {

            case 1:
                toolbar.setTitle("DEPARTMENTAL EVENTS");
                fab.setVisibility(View.VISIBLE);
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new DepartmentalFragment())
                        .commit();
                break;

            case 2:
                toolbar.setTitle("ROBOTICS");
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new RoboticsFragment())
                        .commit();
                break;

            case 3:
                toolbar.setTitle("SPECIAL EVENTS");
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new SpecialEventsFragment())
                        .commit();
                break;

            case 4:
                toolbar.setTitle("GAMING ZONE");
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new GamingZoneFragment())
                        .commit();
                break;
            case 5:
                toolbar.setTitle("ONLINE EVENTS & EXHIBITION");
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new OnlineEventsFragment())
                        .commit();
                break;
            case 6:
                toolbar.setTitle("COMMITTEES");
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new CommitteesFragment())
                        .commit();
        }


        if (!haveNetworkConnection()) {
            final Snackbar snackbar = Snackbar
                    .make(layout, "No internet connection!", 6000)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
            snackbar.setActionTextColor(Color.RED);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ShowEventsActivity.this, InfoDeptActivity.class));

            }
        });

    }

    public boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
