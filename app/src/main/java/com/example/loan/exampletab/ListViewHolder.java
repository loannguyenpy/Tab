package com.example.loan.exampletab;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by LOAN on 7/25/2016.
 */
public class ListViewHolder extends Activity
{
    TextView mName;
    TextView mNumber;
    ImageView mIcon;
    ImageView mPhoto;
    private Context mContex;
    public ListViewHolder(View view, Context context) {
        //get id
        mName = (TextView) view.findViewById(R.id.Name);
        mNumber = (TextView) view.findViewById(R.id.Number);
        mIcon = (ImageView) view.findViewById(R.id.imageIcon);
        mPhoto = (ImageView) view.findViewById(R.id.photo);
        mContex=context;
        mIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent=new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+mNumber.getText()));
                    //Hàm này chỉ được gọi bởi 1 đối tượng activity, 1 đối tượng thay thế activity hoặc là gọi ngay Activity
                    //E hiểu ko?.tuc la gọi trong class khac k dc ha a
                    mContex.startActivity(intent);
                }
                catch (ActivityNotFoundException e)
                {
                    Log.e("Sorry","call failed",e);
                }
            }
        });
    }
    public void setData(Phone phone) {
        mName.setText(phone.getName());
        mNumber.setText(phone.getNumber());
        //show icon call
        if(phone.getIcon()==null)
            mIcon.setImageResource(R.mipmap.call);
        //get photo from contacts
        if(phone.getIcon()!=null)
            mPhoto.setImageURI(phone.getIcon());
        else
            mPhoto.setImageResource(R.mipmap.people);


    }
//    //get id image icon
//    public int getIdImage(String name)
//    {
//        String pkName=mContex.getPackageName();
//        int ID=mContex.getResources().getIdentifier(name,"mipmap",pkName);
//        Log.i("listview_contact","Name: "+name+" id = "+ID);
//        return ID;
//    }
}
