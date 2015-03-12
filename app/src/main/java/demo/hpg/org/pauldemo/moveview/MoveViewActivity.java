package demo.hpg.org.pauldemo.moveview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import demo.hpg.org.pauldemo.R;

/**
 * Author huarizhong
 * Date 2015/3/11
 * Time 15:40
 */
public class MoveViewActivity extends Activity {
    private Button clear;
    private Button save;
    private DrawBoardView boardView;
    private ImageView imageView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moveview);
        clear = (Button) findViewById(R.id.clear);
        save = (Button) findViewById(R.id.save);
        imageView = (ImageView) findViewById(R.id.image);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageBitmap(convertViewToBitmap(boardView));
            }
        });
        boardView = (DrawBoardView) findViewById(R.id.boardview);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardView.clear();
            }
        });
    }

    //将画布内容转成Bitmap
    private Bitmap convertViewToBitmap(View view){
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        //利用bitmap生成画布
        Canvas canvas = new Canvas(bitmap);

        view.draw(canvas);

        return bitmap;
    }
}
