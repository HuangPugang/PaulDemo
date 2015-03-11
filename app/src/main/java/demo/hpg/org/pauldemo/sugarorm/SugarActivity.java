package demo.hpg.org.pauldemo.sugarorm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import demo.hpg.org.pauldemo.R;

/**
 * sugar orm使用
 * Author huarizhong
 * Date 2015/3/11
 * Time 13:50
 */
public class SugarActivity extends Activity {
    private EditText nameText, ageText, desText;
    private Button saveButton, getSaveButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugarorm);
        nameText = (EditText) findViewById(R.id.name);
        ageText = (EditText) findViewById(R.id.age);
        desText = (EditText) findViewById(R.id.description);
        saveButton = (Button) findViewById(R.id.save);
        getSaveButton = (Button) findViewById(R.id.getsave);
        textView = (TextView) findViewById(R.id.content);


        //保存到数据库
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ageText.getText().toString().trim().equals("")){
                    Toast.makeText(SugarActivity.this,"年龄不能为空",Toast.LENGTH_SHORT).show();
                    return ;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        User user = new User(nameText.getText().toString().trim(),
                                Integer.parseInt(ageText.getText().toString().trim()),
                                desText.getText().toString().trim());
                        user.save();
                    }
                }).start();

            }
        });

        //从数据库获取
        getSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> users = User.listAll(User.class);
                StringBuilder stringBuilder = new StringBuilder();
                for (User user:users){
                    stringBuilder.append(user.toString());
                }
                textView.setText(stringBuilder);
            }
        });
    }
}
