package demo.hpg.org.pauldemo.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import demo.hpg.org.pauldemo.R;
import demo.hpg.org.pauldemo.base.BaseActivity;

/**
 * Created by paul on 15/12/30.
 */
public class ListTestActivity extends BaseActivity {



    public static void launch(Context context){
        Intent intent = new Intent(context,ListTestActivity.class);
        context.startActivity(intent);
    }
    private LayoutInflater mInflater = null;

    private static final String[] DATA = {"a","abnormal","acute","ambitious","b","bed","bad",
            "c","compare","communication","d","dad","e","element"};

    private LetterAdapter mLetterAdapter;
    private ListView listView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.listView);
        mInflater = LayoutInflater.from(this);
        mLetterAdapter = new LetterAdapter(DATA);
        listView.setAdapter(mLetterAdapter);
    }

    private class LetterAdapter extends BaseAdapter {

        private String[] letter = {};

        //定义两个int常量标记不同的Item视图
        public static final int FIRST_LETTER_ITEM = 0;
        public static final int WORD_ITEM = 1;

        public LetterAdapter(String[] data) {
            letter = data;
        }

        @Override
        public int getItemViewType(int position) {
            // TODO Auto-generated method stub

            if(letter[position].length() == 1) {
                return FIRST_LETTER_ITEM;
            } else {
                return WORD_ITEM;
            }
        }

        @Override
        public int getViewTypeCount() {
            // TODO Auto-generated method stub
            //因为有两种视图，所以返回2
            return 2;
        }

        @Override
        public boolean isEnabled(int position) {
            // TODO Auto-generated method stub
            return (letter[position].length() != 1);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return letter.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return letter[position];
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            ViewHolder vh = null;

            if(convertView == null) {

                vh = new ViewHolder();

                if(getItemViewType(position) == FIRST_LETTER_ITEM) {
                    convertView = getLayoutInflater().inflate(R.layout.item_list1, parent, false);
                    //convertView = mInflater.inflate(R.layout.first_letter_item, null);
                    vh.tv = (TextView) convertView.findViewById(R.id.firstletter);

                } else {
                    convertView = getLayoutInflater().inflate(R.layout.item_list2, parent, false);
                    //convertView = mInflater.inflate(R.layout.word_item, null);
                    vh.tv = (TextView) convertView.findViewById(R.id.word);
                }
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.tv.setText(letter[position]);
            return convertView;
        }
        class ViewHolder{
            TextView tv;
        }
    }
}
