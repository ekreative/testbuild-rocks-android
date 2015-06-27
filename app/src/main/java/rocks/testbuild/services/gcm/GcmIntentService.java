package rocks.testbuild.services.gcm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.util.Random;

import rocks.testbuild.R;
import rocks.testbuild.activities.SplashActivity;

/**
 * Created by nnet on 5/21/15.
 */
public class GcmIntentService extends IntentService {

	public static final String TAG = GcmIntentService.class.getName();
	public static final String DEVICE_UUID_KEY = "d";
	public static final String PUSH_TYPE = "type";
	private static final String MESSAGE = "message";
	private static final String PUSH_TYPE_EXTRA = "extra";
	private static final String PUSH_TYPE_INVITE = "invite";
	private static final String PUSH_TYPE_INVITE_ACCEPTED = "invite_accept";
	private static final String PUSH_TYPE_FLEET_DELETE_DRIVER = "fleet_delete_driver";
	private static final String PUSH_TYPE_FLEET_LEAVE_DRIVER = "fleet_leave_driver";
	private static final String PUSH_TYPE_HAIL_TAXI = "hail_taxi";
	private static final String PUSH_TYPE_HAIL_TAXI_DRIVER = "hail_taxi_driver";
	private static final String PUSH_TYPE_HAIL_REMEMBER_RATING= "hail_remember_rating";
	private static final String PUSH_TYPE_CHANGE_TRANSPORT = "change_transport";
	private static final String PUSH_TYPE_MESSAGE = "message";
	private Handler handler;
	private String pushType;
	private static Uri URI_NOTIFICATION_SOUND;

	public GcmIntentService() {
		super(GcmIntentService.class.getSimpleName());
		this.handler = new Handler();
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		String messageType = gcm.getMessageType(intent);

		if (!extras.isEmpty()) {
			for (String key : extras.keySet()) {
				Object value = extras.get(key);
				Log.d(TAG, String.format("%s %s (%s)", key, value.toString(), value.getClass().getName()));
			}

			if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
				if (extras.containsKey(PUSH_TYPE)) {
					dataPushMessage(extras);
				} else {
					defaultPushMessage(extras);
				}
			}
		}
		GcmBroadcastReceiver.completeWakefulIntent(intent);
	}

	private void dataPushMessage(Bundle extras) {
		try {
			pushType = String.valueOf(extras.getString(PUSH_TYPE));
			Log.i(TAG, "pushType  " + pushType);
			Bundle args = new Bundle();
			args.putString(PUSH_TYPE, pushType);

//			if (PUSH_TYPE_HAIL_TAXI.equals(pushType)) {
//				Long hailId = Long.valueOf(extras.getString("hailId"));
//				args.putLong(Hail.class.getSimpleName(), hailId);
////				if (MoNeedApp.startedActivities == 0) {
//					sendNotification(getString(R.string.app_name), extras.getString(MESSAGE, ""), args);
////				} else {
////					Toast.makeText(getBaseContext(), "Check incoming push!", Toast.LENGTH_SHORT).show();
////				}
//			} else {
//				sendNotification(getString(R.string.app_name), extras.getString(MESSAGE, ""), extras);
//			}
		} catch (Exception e) {
			Log.e(TAG, "dataPushMessage", e);
		}
	}

	private void defaultPushMessage(final Bundle extras) {
		try {
			final String message = extras.getString(MESSAGE, "");
			if (!message.isEmpty()) {
				sendNotification(getString(R.string.app_name), message, extras);
			}
		} catch (Exception e) {
			Log.e(TAG, "defaultPushMessage", e);
		}
	}

	private void sendNotification(String title, String message, final Bundle extras) {
		NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

		PendingIntent contentIntent = PendingIntent.getActivity(this, new Random().nextInt(10000), new Intent(this, SplashActivity.class).putExtras(extras), 0);
		if (URI_NOTIFICATION_SOUND == null) {
			URI_NOTIFICATION_SOUND = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sound);
		}

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
				.setSmallIcon(R.mipmap.ic_launcher)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
				.setContentTitle(title)
				.setSound(URI_NOTIFICATION_SOUND)
				.setContentText(message)
				.setAutoCancel(true);

		mBuilder.setContentIntent(contentIntent);
		notificationManager.notify(new Random().nextInt(10000), mBuilder.build());
	}

}
