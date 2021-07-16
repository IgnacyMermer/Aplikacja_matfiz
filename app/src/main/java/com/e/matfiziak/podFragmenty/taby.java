package com.e.matfiziak.podFragmenty;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.e.matfiziak.Adaptery.SectionsPagerAdapter;
import com.e.matfiziak.Fragments.Tab1Fragment;
import com.e.matfiziak.Fragments.Tab2Fragment;
import com.e.matfiziak.R;
import com.google.android.material.tabs.TabLayout;
public class taby extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_taby);
        mSectionsStatePagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_prawo);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_lewo);
    }
    private void setupViewPager(ViewPager viewPager){
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "Fragment1");
        adapter.addFragment(new Tab2Fragment(), "Fragment2");
        viewPager.setAdapter(adapter);
    }
    public void setmViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    public void onBackPressed() {}
}
