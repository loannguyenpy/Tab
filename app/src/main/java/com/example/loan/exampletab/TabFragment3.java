package com.example.loan.exampletab;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TabFragment3 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_fragment3, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GridView listView=(GridView) view.findViewById(R.id.listPhoto);
        List<Photo> photoList=new ArrayList<>();
        Uri uri;
        Cursor cursor;
        int data, folder;
        String pathPhoto=null;
        uri=MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        String projection[]={MediaStore.Images.Media.DATA,MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
//        cursor=getActivity().getContentResolver().query(uri,projection,null,null,null);
//        data=cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//        folder=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        String filePathColum[]={MediaStore.MediaColumns.DATA};

        cursor=getActivity().getContentResolver().query(uri,filePathColum,null,null,null);
        data=cursor.getColumnIndex(filePathColum[0]);
        while (cursor.moveToNext())
        {
            pathPhoto=cursor.getString(data);
            photoList.add(new Photo(pathPhoto));
        }
        cursor.close();
        AdapterPhoto adapterPhoto=new AdapterPhoto(getActivity(),photoList);
        listView.setAdapter(adapterPhoto);

    }
//    public String getBucketID(String path)
//    {
//        return String.valueOf(path.toLowerCase().hashCode());
//    }

}
