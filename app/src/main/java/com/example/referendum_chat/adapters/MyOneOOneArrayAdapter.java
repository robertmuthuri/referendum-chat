package com.example.referendum_chat.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyOneOOneArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mBasics;
    private String[] mHeaders;

    public MyOneOOneArrayAdapter(Context mContext, int resource, String[] mHeaders, String[] mBasics){
        super(mContext, resource);
        this.mContext = mContext;
        this.mBasics = mBasics;
        this.mHeaders = mHeaders;
    }

    @Override
    public Object getItem(int position) {
        String header = mHeaders[position];
        String basic = mBasics[position];
        return String.format("%s \n %s", header, basic);
    }

    @Override
    public int getCount() {
        return mHeaders.length;
    }

}
