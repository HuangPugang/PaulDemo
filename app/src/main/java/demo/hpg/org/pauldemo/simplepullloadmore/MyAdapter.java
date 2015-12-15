package demo.hpg.org.pauldemo.simplepullloadmore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import demo.hpg.org.pauldemo.R;

/**
 * Created by paul on 15/12/15.
 */
public class MyAdapter extends BaseAdapter {
    private List<String> mList = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater ;
    public MyAdapter(List<String> list ,Context context){
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }
    public void setDataChanged(List<String> list){
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder ;
        if (convertView==null) {
            holder = new Holder();
            convertView = mInflater.inflate(R.layout.content_main, null);
            holder.textView = (TextView) convertView.findViewById(R.id.text);
            holder.textView.setText(mList.get(position));
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        return convertView;
    }

    class Holder{
        TextView textView;
    }
}
