package rocks.testbuild.services.gcm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by nnet on 5/20/15.
 */
public class DeviceBootRegGCMReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

			Intent alarmIntent = new Intent(context, RegGCMReceiver.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
			AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

			manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), RegGCMReceiver.REG_GCM_INTERVAL, pendingIntent);
		}
	}
}
