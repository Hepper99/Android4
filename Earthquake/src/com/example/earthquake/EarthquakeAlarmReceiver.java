package com.example.earthquake;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class EarthquakeAlarmReceiver extends BroadcastReceiver {
  
  public static final String ACTION_REFRESH_EARTHQUAKE_ALARM = 
      "com.example.earthquake.ACTION_ALARM";

  @Override
  public void onReceive(Context context, Intent intent) {
    Intent startIntent = new Intent(context, EarthquakeUpdateService.class);
    Log.i("zyy", "receive !!");
    context.startService(startIntent);
  }

}