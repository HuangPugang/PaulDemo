package demo.hpg.org.pauldemo.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import demo.hpg.org.pauldemo.R;

/**
 * Created by Paul on 15/10/6.
 */
public class DialogActivity extends Activity implements View.OnClickListener {
    private TextView resultText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        resultText = (TextView) findViewById(R.id.result);
        Button showDialogBtn = (Button) findViewById(R.id.showdialog);
        showDialogBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog, null);
        Button button = (Button) view.findViewById(R.id.clickbtn);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(DialogActivity.this);
                View view = LayoutInflater.from(DialogActivity.this).inflate(R.layout.dialog2, null);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(view);
                dialog.show();
            }
        });
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.show();
//		  MyCustomDialog dialog = new MyCustomDialog(this,"Enter your name",new MyCustomDialog.OnCustomDialogListener() {
//
//				@Override
//				public void back(String name) {
//					resultText.setText("Enter name is "+name);
//
//				}
//			});
//			dialog.show();

    }




}
