package com.example.loan.exampletab;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by LOAN on 7/31/2016.
 */
public class MyImage extends ImageView {
    public MyImage(Context context) {
        super(context);
    }

    public MyImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //Giờ phải có 1 phương thức để e đưa cái path vào nà
    public void setImageAsyncTask(String path){
        //Trong này thì gọi cái class ast
        new LoadImage().execute(path);
        //Vậy là xong nhá.
    }
    //=====================
    //Giờ a viết 1 lớp asynctask nhá
    private class LoadImage extends AsyncTask<String,Void,Bitmap>
    {
        //AsyncTask chỉ cần mấy hàm này thôi, đừng có cho vào 1 đống nhá
        //Hàm 1
        //Hàm này thực hiện trước khi quá trình chạy nền diễn ra
        @Override
        protected void onPreExecute() {
            MyImage.this.setImageResource(R.drawable.loading_photo);
            super.onPreExecute();
        }

        //Hàm 2
        //Hàm này thực hiện chạy nền, nó ko tương tác được với UI
        @Override
        protected Bitmap doInBackground(String[] path) {
            Bitmap bitmap=null;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 32;
            bitmap=BitmapFactory.decodeFile(path[0],options);
            return bitmap;
        }

        //Hàm 3
        //Hàm này là hàm tương tác với UI khi tiến trình nền vẫn còn
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        //Hàm 4
        //Hàm này là hàm thực hiện khi kết thúc tiến trình chạy nền
        @Override
        protected void onPostExecute(Bitmap result) {
            if(result!=null) {
                MyImage.this.setImageBitmap(result);
            }
        }
    }
}
