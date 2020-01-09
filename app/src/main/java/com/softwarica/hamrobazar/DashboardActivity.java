package com.softwarica.hamrobazar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.softwarica.hamrobazar.Adapter.ImageSliderAdapter;

public class DashboardActivity extends AppCompatActivity {
    ViewPager viewPager;

    LinearLayout sliderDotsPanel;
    private int dotscount;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        viewPager = findViewById(R.id.imageSlider);

        //For Indicators
        sliderDotsPanel = (LinearLayout) findViewById(R.id.sliderDots);

        ImageSliderAdapter adapter = new ImageSliderAdapter(this);
        viewPager.setAdapter(adapter);

        //Indicator
        dotscount = adapter.getCount();
        dots = new  ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {
            dots[i] = new ImageView(this);

            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                    R.drawable.activedots));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotsPanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                R.drawable.activedots));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                            R.drawable.nonactivedots));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.activedots));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private boolean pagerMoved = false;
    private static final long ANIM_VIEWPAGER_DELAY = 5000;

    private Handler h = new Handler();

    private Runnable animateViewPager = new Runnable() {
        @Override
        public void run() {

            if (!pagerMoved){
                if (viewPager.getCurrentItem() == viewPager.getChildCount()){
                    viewPager.setCurrentItem(0, true);

                }
                else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1, true);
                }
                pagerMoved = false;
                h.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);
            }
        }
    };
    @Override
    protected void onPause() {
        super.onPause();

        if (h != null){
            h.removeCallbacks(animateViewPager);
        }
    }
    //changed

    @Override
    protected void onResume() {
        super.onResume();

        h.postDelayed(animateViewPager,ANIM_VIEWPAGER_DELAY);
    }
}
