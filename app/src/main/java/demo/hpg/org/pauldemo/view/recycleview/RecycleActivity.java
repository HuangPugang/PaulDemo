package demo.hpg.org.pauldemo.view.recycleview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.hpg.org.pauldemo.R;
import demo.hpg.org.pauldemo.base.BaseActivity;

/**
 * Created by Paul on 15/11/26.
 */
public class RecycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        initHorizontal();
        initVertical();
    }


    private void initHorizontal() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview_horizontal);

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        // 创建数据集
        String[] dataset = new String[100];
        for (int i = 0; i < dataset.length; i++) {
            dataset[i] = "item" + i;
        }
        // 创建Adapter，并指定数据集
        RecycleViewAdapter adapter = new RecycleViewAdapter(dataset);
        // 设置Adapter
        recyclerView.setAdapter(adapter);
    }

    public void initVertical() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_vertical);

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 默认是Vertical，可以不写
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        // 创建数据集
        String[] dataset = new String[100];
        for (int i = 0; i < dataset.length; i++) {
            dataset[i] = "item" + i;
        }
        // 创建Adapter，并指定数据集
        RecycleViewAdapter adapter = new RecycleViewAdapter(dataset);
        // 设置Adapter
        recyclerView.setAdapter(adapter);
    }
}
