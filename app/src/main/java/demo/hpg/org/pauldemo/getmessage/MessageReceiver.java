package demo.hpg.org.pauldemo.getmessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * 短信接收
 * Created by pau on 3/11/15.
 */
public class MessageReceiver extends BroadcastReceiver {
    private static final String TAG ="MessageReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG,"receiver");
        Bundle bundle = intent.getExtras();
        if (bundle!=null){
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i=0;i<pdus.length;i++){
                messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
            }

            for (SmsMessage message :messages){
                String strFrom = message.getDisplayOriginatingAddress();
                String strMsg = message.getDisplayMessageBody();
                Log.e(TAG,"From:"+strFrom+",Msg:"+strMsg);
            }
        }
    }
}
