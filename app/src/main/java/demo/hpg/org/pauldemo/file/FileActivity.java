package demo.hpg.org.pauldemo.file;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import demo.hpg.org.pauldemo.R;

/**
 * Author huarizhong
 * Date 2015/3/18
 * Time 16:10
 */
public class FileActivity extends Activity {
    private Button getPath;
    private TextView showPath;
    private ImageView imageView;
    public static final String PATH = android.os.Environment.getExternalStorageDirectory()+"/PaulDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        getPath = (Button) findViewById(R.id.getpath);
        showPath = (TextView) findViewById(R.id.showpath);
        imageView = (ImageView) findViewById(R.id.image);
        getPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPath.setText(PATH);
                File file = new File(PATH);
                if (!file.exists()){
                    file.mkdir();
                }
            }
        });
    }
}
