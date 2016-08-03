package com.example.loan.exampletab;


import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    PagerAdapter adapterViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager=(ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.TabLayout);
        setupViewPagerAndFragmentTab(viewPager);
        tabLayout.setupWithViewPager(viewPager);
     }
    //===================
    //A viết hàm riêng cho dễ nhìn
    private void setupViewPagerAndFragmentTab(ViewPager viewPager)
    {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        //Thêm tab 1 nà
        adapter.addFragment(new TabFragment1(), "CONTACTS");
        //tab 2
        adapter.addFragment(new TabFragment2(), "APPS");
        //tab 3
        adapter.addFragment(new TabFragment3(), "PHOTO");
        viewPager.setAdapter(adapter);
        //Xong set tab
    }
    //call

}