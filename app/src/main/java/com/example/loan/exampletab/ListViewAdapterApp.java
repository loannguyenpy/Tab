package com.example.loan.exampletab;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by LOAN on 7/25/2016.
 */
public class ListViewAdapterApp extends BaseAdapter
{
    List<PackageInfo> appList;
    Context mContext;
    public ListViewAdapterApp(Context context, List<PackageInfo> numList)
    {
        super();
        mContext=context;
        this.appList=numList;
    }
       @Override
    public Object getItem(int position) {
        return appList.get(position);
    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        //goi Holder
        final ViewHolder listViewHolder;
        if(convertView==null)//chua nhận view
        {
            view = LayoutInflater.from(mContext).inflate(R.layout.listview_app,parent,false);
            listViewHolder=new ViewHolder(mContext, view);
            //set tag để lần sau không rỗng thì dùng lại
            view.setTag(listViewHolder);
        }
        else
        {
            view=convertView;
            listViewHolder = (ViewHolder)view.getTag();
        }
        if(listViewHolder!=null)
        {
            listViewHolder.setData(appList.get(position));
        }
        return view;
    }

    private class ViewHolder{
        ImageView mIcon;
        TextView mTextName;
        TextView mTextSub;
        Context mContext;
        View mView;
        public ViewHolder(Context context, View view){
            mContext = context;
            mIcon = (ImageView) view.findViewById(R.id.iconApp);
            mTextName = (TextView) view.findViewById(R.id.appName);
            mTextSub=(TextView) view.findViewById(R.id.appPackge);
            mView = view;
        }
        public void setData(final PackageInfo packageInfo){

            mTextName.setText(packageInfo.applicationInfo.loadLabel(mContext.getPackageManager()).toString());
            mTextSub.setText(packageInfo.packageName);
            mIcon.setImageDrawable(packageInfo.applicationInfo.loadIcon(mContext.getPackageManager()));

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        PackageManager manager = mContext.getPackageManager();
                        Intent intent = manager.getLaunchIntentForPackage(packageInfo.packageName);
                        if(intent!=null) {
                            intent.addCategory(Intent.CATEGORY_LAUNCHER);
                            mContext.startActivity(intent);
                        }
                    else Toast.makeText(mContext, "App not support open", Toast.LENGTH_LONG).show();
                    }

                //A bảo rồi, có những cái copy có đúng đâu :D

            });


        }
    }
}
