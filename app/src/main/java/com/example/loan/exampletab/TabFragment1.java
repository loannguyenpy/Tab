package com.example.loan.exampletab;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class TabFragment1 extends Fragment {
    ListView mListView;
    int mPosition;

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
             View root=inflater.inflate(R.layout.fragment_tab_fragment1, container, false);
            mListView=(ListView)root.findViewById(R.id.ListView);
            final List<Phone> phonesList=new ArrayList<>();
            //lay danh ba dien thoai
            Cursor cursor=getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            while (cursor.moveToNext())
            {
                String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNum=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Uri ImUri ;
                try {
                    ImUri = Uri.parse(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)));
                }
                catch (NullPointerException n){
                    ImUri = null;
                }

                phonesList.add(new Phone(name,phoneNum,ImUri,null));
            }
            ListViewAdapter listViewAdapter=new ListViewAdapter(getActivity(),phonesList);
            mListView.setAdapter(listViewAdapter);
            //call

            return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}