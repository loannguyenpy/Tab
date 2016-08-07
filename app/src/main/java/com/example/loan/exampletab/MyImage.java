package com.example.loan.exampletab;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.util.AttributeSet;
import android.util.Log;
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
    public void setImageAsyncTask(String path) {
        //Trong này thì gọi cái class ast
        new LoadImage().execute(path);
    }
    //=====================class BitmapCache===================


    //Giờ a viết 1 lớp asynctask nhá
//    final BitmapCache bitmapCache = new BitmapCache();

    private class LoadImage extends AsyncTask<String, Void, Bitmap> {
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
            //A sẽ khởi tạo 1 cache tại đây
            BitCache bitCache = BitCache.get();
            Bitmap bitmap = bitCache.get(path[0]); //Khởi tạo bitmap bằng cách a nhận từ cache
//Hình nào đã được load thì sẽ add, rồi sau đó nếu trùng link vs cái đã add thì nó gọi từ cache chớ ko load lại
            //Đây là task tiếp theo nha

            //Recycler View trong android nó là cái gì? Sử dụng ra sao?
            //Nó kết với card view như thế nào?
            //Hãy chuyển toàn bộ 2 tab, 1 và 2 về recycler view nha e
            //bai tap ngay mai ha a
            //Uh, làm trên chính app này luôn, hổm giờ tab 1 và 2 dùng listview, giờ chuyển nó về recycler view cho a


            /*
            Tiếp theo a kiểm tra nó khác khác null ko, nếu khác thì a trả nó về, nếu ko thì a get bitmap từ
            bộ nhớ rồi trả bitmap về, đồng thơi lưu lại bitmap đó
             */
            if(bitmap!=null) Log.e("TAG", "cache hit: "+path[0]);
            else {
                Log.e("TAG", "Load: "+path[0]);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 32;
                bitmap = BitmapFactory.decodeFile(path[0], options);
                if(bitmap!=null) bitCache.add(path[0], bitmap);
            }
            //Để a show cái log cho e thấy nó nhận từ cache nhá
            //Xong rồi
            //Hiểu code ko e?
            //tam tam aj
            //Gio xem no chay sao nha.
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
            if (result != null) {
                MyImage.this.setImageBitmap(result);
            }
        }
    }
}
