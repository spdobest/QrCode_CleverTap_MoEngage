package com.example.root.myvolleydemo.callTracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by root on 5/24/17.
 */

public class PhoneStateReceiver extends BroadcastReceiver {

    private static final String TAG = "PhoneStateReceiver";

    @Override
    public void onReceive(final Context context, Intent intent) {
        try {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            Log.i(TAG, "onReceive: "+incomingNumber);



           /* if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                Toast.makeText(context,"Ringing State Number is - " + incomingNumber, Toast.LENGTH_SHORT).show();

            }*/

            TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            telephony.listen(new PhoneStateListener(){
                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                    super.onCallStateChanged(state, incomingNumber);

                    switch (state){
                        case TelephonyManager.CALL_STATE_RINGING :
                            System.out.println("incomingNumber : "+incomingNumber);
                            if(!TextUtils.isEmpty(incomingNumber)) {
                                Intent intentService = new Intent(context, SendMobileNumberIntentService.class);
                                intentService.putExtra(SendMobileNumberIntentService.KEY_PHONE_NUMBER, incomingNumber);
                                context.startService(intentService);
                            }
                            break;
                    }


                }
            },PhoneStateListener.LISTEN_CALL_STATE);


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
