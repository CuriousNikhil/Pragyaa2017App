package org.pragyaa.pragyaa2017;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;
import com.koushikdutta.ion.Ion;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;
import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.utils.SpotlightListener;

import org.pragyaa.pragyaa2017.notifications.Config;

import java.util.HashMap;

import su.levenetc.android.textsurface.Text;
import su.levenetc.android.textsurface.TextBuilder;
import su.levenetc.android.textsurface.TextSurface;
import su.levenetc.android.textsurface.animations.Alpha;
import su.levenetc.android.textsurface.animations.ChangeColor;
import su.levenetc.android.textsurface.animations.Delay;
import su.levenetc.android.textsurface.animations.Parallel;
import su.levenetc.android.textsurface.animations.Rotate3D;
import su.levenetc.android.textsurface.animations.Sequential;
import su.levenetc.android.textsurface.animations.ShapeReveal;
import su.levenetc.android.textsurface.animations.SideCut;
import su.levenetc.android.textsurface.animations.Slide;
import su.levenetc.android.textsurface.animations.TransSurface;
import su.levenetc.android.textsurface.contants.Align;
import su.levenetc.android.textsurface.contants.Pivot;
import su.levenetc.android.textsurface.contants.Side;

import static org.pragyaa.pragyaa2017.R.id.gaming;

public class MainActivity extends AppCompatActivity {

    private static boolean flag = false;
    private SliderLayout slider;
    private TextSurface headerText;
    private AppBarLayout appBar;
    private ImageView appImage;
    private Toolbar toolbar;
    private ShimmerTextView eventOne, eventTwo, eventThree, eventFour, eventFive;
    private Typeface fireFont;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private Fab fab;
    private View sheetView, overlay;
    private MaterialSheetFab materialSheetFab;
    private DatabaseReference urlData;
    private String url;
    private View v;


    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        //binding id's
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appBar = (AppBarLayout) findViewById(R.id.appbar);
        appImage = (ImageView) findViewById(R.id.fire_back);
        slider = (SliderLayout) findViewById(R.id.slider);
        headerText = (TextSurface) findViewById(R.id.header);

        eventOne = (ShimmerTextView) findViewById(R.id.event_one);
        eventTwo = (ShimmerTextView) findViewById(R.id.event_two);
        eventThree = (ShimmerTextView) findViewById(R.id.event_three);
        eventFour = (ShimmerTextView) findViewById(gaming);
        eventFive = (ShimmerTextView) findViewById(R.id.online_eve);


        fab = (Fab) findViewById(R.id.fab);
        sheetView = findViewById(R.id.fab_sheet);
        overlay = findViewById(R.id.overlay);

        //end binding id's
        //http://bestanimations.com/Earth&Space/Sun/sunset-animation-2.gif
        //http://68.media.tumblr.com/ca513146790e0e22a555ef11d9c40be1/tumblr_mpagdiWFpo1sr77jco1_500.gif

        //https://media.giphy.com/media/3KqZp8MBaf1Ty/giphy.gif
        //"http://bestanimations.com/Nature/Fire/fire-animated-gif-10.gif"
        Ion.with(appImage).load("https://media.giphy.com/media/3KqZp8MBaf1Ty/giphy.gif");


        //getting url data for registration
        urlData = FirebaseDatabase.getInstance().getReference().child("registrationlink");
        urlData.keepSynced(true);
        urlData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                url = (String) dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //image slider
        HashMap<String, Integer> epicPics = new HashMap<>();
        epicPics.put("Theme : Aagneya", R.drawable.pragyaa_header);
        epicPics.put("Tagline", R.drawable.entrepreneur);

        for (String name : epicPics.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(epicPics.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            slider.addSlider(textSliderView);
        }

        slider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setDuration(3000);
        //end image slider

        //header texSurfaceView
        final Typeface batmanForever = Typeface.createFromAsset(getAssets(), "fonts/batmanforeveralternate.ttf");
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTypeface(batmanForever);

        Text textMain = TextBuilder
                .create("SGGSIE&T,Nanded ")
                .setPaint(paint)
                .setSize(25)
                .setAlpha(0)
                .setColor(Color.parseColor("#9C27B0"))
                .setPosition(Align.SURFACE_CENTER).build();

        Text textSub = TextBuilder
                .create("Presents...")
                .setPaint(paint)
                .setSize(15)
                .setAlpha(0)
                .setColor(Color.YELLOW)
                .setPosition(Align.BOTTOM_OF, textMain).build();

        Text textDesc = TextBuilder
                .create("National-level Technical Fiesta")
                .setPaint(paint)
                .setSize(13)
                .setAlpha(0)
                .setColor(Color.MAGENTA)
                .setPosition(Align.RIGHT_OF, textSub).build();

        Text textP = TextBuilder
                .create("PRAGYAA 2017")
                .setPaint(paint)
                .setSize(30)
                .setAlpha(0)
                .setColor(Color.parseColor("#d50000"))
                .setPadding(0, 0, 0, 12)
                .setPosition(Align.BOTTOM_OF, textDesc).build();

        headerText.play(new Sequential(
                ShapeReveal.create(textMain, 750, SideCut.show(Side.LEFT), false),
                new Parallel(ShapeReveal.create(textMain, 750, SideCut.hide(Side.LEFT), false), new Sequential(Delay.duration(300), ShapeReveal.create(textMain, 600, SideCut.show(Side.LEFT), false))),
                new Parallel(new TransSurface(600, textSub, Pivot.CENTER), ShapeReveal.create(textSub, 1300, SideCut.show(Side.LEFT), false)),
                Delay.duration(900),
                new Parallel(new TransSurface(800, textDesc, Pivot.CENTER), Slide.showFrom(Side.LEFT, textDesc, 750), ChangeColor.to(textDesc, 750, Color.WHITE)),
                Delay.duration(800),
                new Parallel(TransSurface.toCenter(textP, 500), Rotate3D.showFromSide(textP, 750, Pivot.TOP), Alpha.hide(textDesc, 500), Alpha.hide(textMain, 500), Alpha.hide(textSub, 500)),
                Delay.duration(600)

        ));


        //animating below layout with Shimmer
        Shimmer shimmer = new Shimmer();
        shimmer.setDirection(Shimmer.ANIMATION_DIRECTION_RTL);
        shimmer.setDuration(2000);
        Shimmer shimmer1 = new Shimmer();
        shimmer1.setDirection(Shimmer.ANIMATION_DIRECTION_RTL);
        shimmer1.setDuration(3000);
        fireFont = Typeface.createFromAsset(getAssets(), "fonts/batmanforeveralternate.ttf");

        eventOne.setTypeface(fireFont);
        shimmer.start(eventOne);

        eventTwo.setTypeface(fireFont);
        shimmer1.start(eventTwo);

        eventThree.setTypeface(fireFont);
        shimmer.start(eventThree);

        eventFour.setTypeface(fireFont);
        shimmer1.start(eventFour);

        eventFive.setTypeface(fireFont);
        shimmer1.start(eventFive);


        //setting click listeners to textViews
        //departmental events
        eventOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deptEvent = new Intent(MainActivity.this, ShowEventsActivity.class);
                deptEvent.putExtra("event", 1);
                startActivity(deptEvent);
            }
        });

        //robotics events
        eventTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent roboEvent = new Intent(MainActivity.this, ShowEventsActivity.class);
                roboEvent.putExtra("event", 2);
                startActivity(roboEvent);
            }
        });

        //special events
        eventThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent specialEvent = new Intent(MainActivity.this, ShowEventsActivity.class);
                specialEvent.putExtra("event", 3);
                startActivity(specialEvent);
            }
        });

        //gaming zone
        eventFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gaming = new Intent(MainActivity.this, ShowEventsActivity.class);
                gaming.putExtra("event", 4);
                startActivity(gaming);
            }
        });

        //online events
        eventFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent onlineEve = new Intent(MainActivity.this, ShowEventsActivity.class);
                onlineEve.putExtra("event", 5);
                startActivity(onlineEve);
            }
        });


        //bottom fab sheet

        int sheetColor = getResources().getColor(R.color.background_card);
        int fabColor = getResources().getColor(R.color.colorAccent);

        materialSheetFab = new MaterialSheetFab<>(fab, sheetView, overlay, sheetColor, fabColor);

        materialSheetFab.setEventListener(new MaterialSheetFabEventListener() {
            @Override
            public void onHideSheet() {
                //statusBarColor = getStatusBarColor();
                //setStatusBarColor(getResources().getColor(R.color.theme_primary_dark2));
            }

            @Override
            public void onShowSheet() {
                //setStatusBarColor(statusBarColor);
            }
        });

        //setting the listeners in sheet

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (url != null) {
                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
                    CustomTabsIntent customTabsIntent = builder.build();
                    customTabsIntent.launchUrl(MainActivity.this, Uri.parse(url));
                } else {
                    Snackbar.make(v, "Coming Soon...", Snackbar.LENGTH_LONG);
                }

            }
        });

        findViewById(R.id.schedule).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ScheduleActivity.class));

            }
        });

        findViewById(R.id.about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AboutUs.class));
            }
        });

        findViewById(R.id.contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contact = new Intent(MainActivity.this, ShowEventsActivity.class);
                contact.putExtra("event", 6);
                startActivity(contact);
            }
        });

        findViewById(R.id.hospitality).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Hospitality.class));
            }
        });


        //end bottom fab sheet

        //all about notification receiver
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                }
            }
        };

    }


    @Override
    protected void onStart() {
        super.onStart();

        if (!flag) {

            new SpotlightView.Builder(MainActivity.this)
                    .introAnimationDuration(400)
                    .enableRevalAnimation(true)
                    .performClick(true)
                    .fadeinTextDuration(400)
                    .headingTvColor(Color.parseColor("#eb273f"))
                    .headingTvSize(32)
                    .headingTvText("Events")
                    .subHeadingTvColor(Color.parseColor("#ffffff"))
                    .subHeadingTvSize(16)
                    .subHeadingTvText("Click for more!")
                    .maskColor(Color.parseColor("#dc000000"))
                    .target(eventOne)
                    .lineAnimDuration(400)
                    .lineAndArcColor(Color.parseColor("#e67e22"))
                    .dismissOnTouch(true)
                    .dismissOnBackPress(true)
                    .enableDismissAfterShown(true)
                    .usageId("category_text")
                    .setListener(new SpotlightListener() {
                        @Override
                        public void onUserClicked(String s) {
                            new SpotlightView.Builder(MainActivity.this)
                                    .introAnimationDuration(400)
                                    .enableRevalAnimation(true)
                                    .performClick(true)
                                    .fadeinTextDuration(400)
                                    .headingTvColor(Color.parseColor("#eb273f"))
                                    .headingTvSize(32)
                                    .headingTvText("MORE")
                                    .subHeadingTvColor(Color.parseColor("#ffffff"))
                                    .subHeadingTvSize(16)
                                    .subHeadingTvText("Click here!")
                                    .maskColor(Color.parseColor("#dc000000"))
                                    .target(fab)
                                    .lineAnimDuration(400)
                                    .lineAndArcColor(Color.parseColor("#e67e22"))
                                    .dismissOnTouch(true)
                                    .dismissOnBackPress(true)
                                    .enableDismissAfterShown(true)
                                    .usageId("bottom_fab")
                                    .show();

                        }
                    })
                    .show();

            flag = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        headerText.clearAnimation();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        // NotificationUtils.clearNotifications(getApplicationContext());


    }

    @Override
    public void onBackPressed() {
        if (materialSheetFab.isSheetVisible()) {
            materialSheetFab.hideSheet();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        headerText.clearAnimation();
        super.onRestoreInstanceState(savedInstanceState);
    }
}
