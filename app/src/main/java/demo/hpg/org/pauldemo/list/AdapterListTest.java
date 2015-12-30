package demo.hpg.org.pauldemo.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import demo.hpg.org.pauldemo.R;

/**
 * Created by paul on 15/12/30.
 */
public class AdapterListTest extends BaseAdapter {
    private Context mContext;
    private List<String> mList;
    private LayoutInflater mInflater;
    public AdapterListTest(Context context,List<String> list){
        this.mContext = context;
        this.mList = list;
        this.mInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
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
    public boolean isEnabled(int position) {
        return (mList.get(position).length() != 1);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh = null;

        if(convertView == null) {

            vh = new ViewHolder();

            if(getItemViewType(position) == 1) {
                convertView = mInflater.inflate(R.layout.item_list1, parent, false);
                //convertView = mInflater.inflate(R.layout.first_letter_item, null);
                vh.tv = (TextView) convertView.findViewById(R.id.firstletter);

            } else {
                convertView = mInflater.inflate(R.layout.item_list2, parent, false);
                //convertView = mInflater.inflate(R.layout.word_item, null);
                vh.tv = (TextView) convertView.findViewById(R.id.word);
            }
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv.setText(mList.get(position));
        return convertView;
    }
    class ViewHolder{
        TextView tv;
    }

}
