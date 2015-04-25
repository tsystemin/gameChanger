package in.co.tsystem.gamechanger;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class CategoryActivity extends Activity {

    /*private static final int[] IMAGES = { R.drawable.button, R.drawable.pause };
    private int mPosition = 0;
    private ImageSwitcher mImageSwitcher;

    private GridViewAdapter ga;
    DBHelper.checkDbVer parent_tsk;
    int newCatDbVer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        String image_url = null;


        DBHelper dbHelper = new DBHelper(this);
        parent_tsk = dbHelper.checkDbVer();
        tsk = new myAsyncTask(this);
        parent_tsk.execute();

        mImageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(CategoryActivity.this);
                return imageView;
            }
        });
        mImageSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
        mImageSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);

        onSwitch(null);

        mImageSwitcher.postDelayed(new Runnable() {
            int i = 0;

            public void run() {
                mImageSwitcher.setBackgroundResource(IMAGES[mPosition]);
                mPosition = (mPosition + 1) % IMAGES.length;
                mImageSwitcher.postDelayed(this, 3000);
            }
        }, 3000);
    }

    public void onSwitch(View view) {
        mImageSwitcher.setBackgroundResource(IMAGES[mPosition]);
        mPosition = (mPosition + 1) % IMAGES.length;
    }

    public class GridViewAdapter extends BaseAdapter {
        private Context context;

        public GridViewAdapter(Context context) {
            this.context = context;
        }

        private int[] icons = {
                android.R.drawable.btn_star_big_off,
                android.R.drawable.btn_star_big_on,
                android.R.drawable.alert_light_frame,
                android.R.drawable.alert_dark_frame,
                android.R.drawable.arrow_down_float,
                android.R.drawable.gallery_thumb,
                android.R.drawable.ic_dialog_map,
                android.R.drawable.ic_popup_disk_full,
                android.R.drawable.star_big_on,
                android.R.drawable.star_big_off,
                android.R.drawable.star_big_on
        };

        @Override
        public int getCount() {
            // need to traverse database and get length
            // may be populate an array
            return icons.length;
            //return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(600,400));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(10, 10, 10, 10);
            } else {
                imageView = (ImageView) convertView;
            }
            //imageView.setImageResource(icons[position]);
            imageView.setImageBitmap(dbHelper.getBitmap(position + 1));
            return imageView;
        }


    }

        @Override
        protected Bitmap doInBackground(Void... arg0) {

            Bitmap bitmap=null;
            String url_new = null;
            JSONArray categories;
            JSONObject item;
            ArrayList<String> urls = new ArrayList<String>();

            urls.add("http://10.0.0.112/landing/images/groceries.png");

            for (String url_to_open : urls) {
                try {
                    // Download the image
                    URL url = new URL(url_to_open);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream is = connection.getInputStream();
                    // Decode image to get smaller image to save memory
                    final BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = false;
                    options.inSampleSize = 4;
                    bitmap = BitmapFactory.decodeStream(is, null, options);
                    is.close();
                    //dbHelper.insertBitmap(bitmap);
                } catch (IOException e) {
                    return null;
                }
            }
            //return url_new;
            //dbHelper.DATABASE_VERSION = newCatDbVer;
            return bitmap;


            //return url_new;
        }
    }*/
}
