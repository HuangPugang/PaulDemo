package demo.hpg.org.pauldemo.moveview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import demo.hpg.org.pauldemo.R;

/**
 * Author huarizhong
 * Date 2015/3/11
 * Time 15:40
 */
public class MoveViewActivity extends Activity {
    private Button clear;
    private DrawBoardView boardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moveview);
        clear = (Button) findViewById(R.id.clear);
        boardView = (DrawBoardView) findViewById(R.id.boardview);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardView.clear();
            }
        });
    }
}
