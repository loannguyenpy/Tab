package com.example.loan.exampletab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LOAN on 7/24/2016.
 */
public  class PagerAdapter extends FragmentStatePagerAdapter
{
    //A sẽ có 2 mảng, 1 mãng chứa tên các tab, 1 mãng chứa các fragment tương ứng với tab đó
    private final List<Fragment> mFragmentList = new ArrayList<>();//Mảng này chứa các fragment nhá
    private final List<String> mFragmentTabTitleList = new ArrayList<>(); //Cái này chứa tên các tab

    //Khởi tạo giữ nguyên
    public  PagerAdapter (FragmentManager fm)
    {
        super(fm);
    }

    //Hàm này là lấy ra fragment cho từng tab nà.
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
        //Xong
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    //Hàm này lấy teen cho từng tab
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTabTitleList.get(position);
    }
    //Giờ a thêm 1 hàm để thêm fragment cho adapter
    public void addFragment(Fragment fragment, String title){
        //Add fragment thì dĩ nhiên phải 2 thuộc tính, 1 là cái fragment cần add, thứ 2 là cái title của nó là gì(tên tab)
        mFragmentTabTitleList.add(title);
        mFragmentList.add(fragment);
    }
}
