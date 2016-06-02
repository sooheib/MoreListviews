package com.vivianaranha.morelistviews;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    String[] theTitles;
    String[] theDesc;
    int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
            R.drawable.img5, R.drawable.img6, R.drawable.img7,
            R.drawable.img8, R.drawable.img9, R.drawable.img10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        Resources r = getResources();
        theTitles = r.getStringArray(R.array.titles);
        theDesc = r.getStringArray(R.array.desc);


//        MyAdapter adapter = new MyAdapter(this, theTitles, images, theDesc);
        listView.setAdapter(new TheAdapater(this));


    }

    class SingleRow {
        String title;
        String description;
        int image;

        SingleRow(String title, String desc, int image){
            this.title = title;
            this.description = desc;
            this.image = image;
        }
    }


    class TheAdapater extends BaseAdapter {

        ArrayList<SingleRow> list;
        Context context;

        TheAdapater(Context c){
            this.context = c;
            list = new ArrayList<SingleRow>();
            Resources r = getResources();
            String[] titles = r.getStringArray(R.array.titles);
            String[] desc = r.getStringArray(R.array.desc);
            int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10};

            for (int i=0; i<titles.length; i++){
                list.add(new SingleRow(titles[i], desc[i], images[i]));
            }


        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.single_row, parent, false);

            ImageView myImage = (ImageView) row.findViewById(R.id.imageView);
            TextView myTitle = (TextView) row.findViewById(R.id.textView);
            TextView myDesc = (TextView) row.findViewById(R.id.textView2);

            SingleRow temp = list.get(position);
            myImage.setImageResource(temp.image);
            myTitle.setText(temp.title);
            myDesc.setText(temp.description);

            return row;


        }
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        int[] imgs;
        String[] titleArray;
        String[] descArray;

        int count = 0;


        public MyAdapter(Context c, String[] titles, int imgs[], String[] desc){
            super(c, R.layout.single_row, R.id.textView, titles);
            this.context = c;
            this.imgs = imgs;
            this.titleArray = titles;
            this.descArray = desc;
        }


        class MyViewHolder {
            ImageView myImage;
            TextView myTitle;
            TextView myDesc;

            MyViewHolder(View v){
                myImage = (ImageView) v.findViewById(R.id.imageView);
                myTitle = (TextView) v.findViewById(R.id.textView);
                myDesc = (TextView) v.findViewById(R.id.textView2);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;
            MyViewHolder holder = null;
            if(row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_row, parent, false);
                holder = new MyViewHolder(row);
                row.setTag(holder);

            } else {
                holder = (MyViewHolder) row.getTag();
            }

//            ImageView myImage = (ImageView) row.findViewById(R.id.imageView);
//            TextView myTitle = (TextView) row.findViewById(R.id.textView);
//            TextView myDesc = (TextView) row.findViewById(R.id.textView2);

            holder.myImage.setImageResource(imgs[position]);
            holder.myTitle.setText(titleArray[position]);
            holder.myDesc.setText(descArray[position]);

            return row;

        }
    }
}
