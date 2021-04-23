package com.davish.nsscemag;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class DetailedArticle extends AppCompatActivity {

    TextView title, name, desc;
    String article_title, article_name, article_desc;
    LinearLayout bottom_bar;
    //ScrollView scroll;
    NestedScrollView nested_scroll;
    int timer = 0;
    String TAG = "Davish";

    public void slideUp(final View view) {

        TranslateAnimation animate = new TranslateAnimation(
                0,
                0,
                view.getHeight(),
                0);
        animate.setDuration(500);
        //Toast.makeText(DetailedArticle.this, String.valueOf(view.getHeight()),Toast.LENGTH_SHORT).show();
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.VISIBLE);

        new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                slideDown(view);
            }
        }.start();
    }

    // slide the view from its current position to below itself
    public void slideDown(final View view) {

        TranslateAnimation animate = new TranslateAnimation(
                0,
                0,
                0,
                view.getHeight());
        animate.setDuration(500);
        //animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_article);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        nested_scroll = findViewById(R.id.nested_scroll);
        title = findViewById(R.id.title);
        name = findViewById(R.id.author);
        bottom_bar = findViewById(R.id.bottom_navigation_bar);
        bottom_bar.setVisibility(View.INVISIBLE);
        WebView browser = findViewById(R.id.webview);


        if (bottom_bar.hasFocus())
            Toast.makeText(DetailedArticle.this, "one", Toast.LENGTH_SHORT).show();

        bottom_bar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

            }
        });

        if (b != null) {
            article_name = b.getString("article_name");
            article_title = b.getString("article_title");
            article_desc = b.getString("article_desc");
            title.setText(article_title);
            name.setText(article_name);
            //desc.setText(article_desc);
            String yourData = "<html><head><style type=\"text/css\">" +
                    "@font-face {font-family: times;src: url(\"file:///android_asset/meera.ttf\");}" +
                    "p{" +
                    "font-family:times;" +
                    "font-size:18px" +
                    "}" +
                    "p:first-of-type:first-letter{" +
                  //  "float: left;\n" +
                    "    font-size: 30px;\n" +
                  //  "    line-height: 1;\n" +
                    "    font-weight: bold;\n" +
                    "    margin-right: 9px;}" +
                    "</style></head><body><p style=\"text-align:justify;\">"+ article_desc +"</p></body></html>";
            browser.loadData(yourData, "text/html", "UTF-8");

        }

        nested_scroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > oldScrollY) {
                    Log.i(TAG, "Scroll DOWN");
                    if (bottom_bar.getVisibility() == View.INVISIBLE) {
                        slideUp(bottom_bar);

                    }

                }
                if (scrollY < oldScrollY) {
                    Log.i(TAG, "Scroll UP");
//                    if (bottom_bar.getVisibility() == View.VISIBLE)
//                        slideDown(bottom_bar);
                }

                if (scrollY == 0) {
                    Log.i(TAG, "TOP SCROLL");
                }

                if (scrollY == (v.getMeasuredHeight() - v.getChildAt(0).getMeasuredHeight())) {
                    Log.i(TAG, "BOTTOM SCROLL");
                }
            }
        });


/*
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_editorial:
                        Toast.makeText(DetailedArticle.this, "Recents", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_art:
                        Toast.makeText(DetailedArticle.this, "Favorites", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_excom:
                        Toast.makeText(DetailedArticle.this, "Nearby", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());*/

    }
}
