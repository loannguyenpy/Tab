package com.example.loan.exampletab;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by LOAN on 7/28/2016.
 */
public class AdapterPhoto extends BaseAdapter
{
    List<Photo> photoList;
    Context mContext;
    public AdapterPhoto(Context context, List<Photo> photoList)
    {
        super();
        mContext=context;
        this.photoList=photoList;
    }

    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public Object getItem(int position) {
        return photoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolderPhoTo viewHolderPhoTo;
        View view;
        if(convertView==null)
        {
            view= LayoutInflater.from(mContext).inflate(R.layout.listview_photo,parent,false);
            viewHolderPhoTo=new ViewHolderPhoTo(mContext,view);
            view.setTag(viewHolderPhoTo);
        }
        else
        {
            view=convertView;
            viewHolderPhoTo=(ViewHolderPhoTo)view.getTag();
        }
        if(viewHolderPhoTo!=null)
        {
            viewHolderPhoTo.setData(photoList.get(position));
        }
        return view;
    }

    //class ViewHolder
    private  class ViewHolderPhoTo
    {
        //Lớp mình dùng bây giờ là MyImage
        MyImage mPhoto; //Thằng này là cái cần hiển thị nà
        TextView mHinh;
        String path;
        public ViewHolderPhoTo(Context context, View view)
        {
            mPhoto=(MyImage) view.findViewById(R.id.image);
            mHinh=(TextView) view.findViewById(R.id.hinh);
        }
        public void setData(Photo photo)
        {
            //Dùng lớp myimage nà
            mPhoto.setImageAsyncTask(photo.getPhoto());

        //    mHinh.setText(photo.getPhoto()+"");
//            BitmapFactory.Options options = new BitmapFactory.Options();
            //fix ngoai le outOfMemoryError
//            options.inSampleSize = 16;
          //  mPhoto.setTag(photo.getPhoto()); //Ai bày e kiểu nlamf này đây?. tren mạng ạ
            //Chơi kiểu này sao mà được
            //:(
       //     new LoadImage().execute(photo.getPhoto());
           // mPhoto.setImageBitmap(BitmapFactory.decodeFile(photo.getPhoto(), options));
        }
    }
}