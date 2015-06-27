package rocks.testbuild.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nnet on 4/6/15.
 */
public class Cache {
	private String pushId;
	private String deviceId;
	private User user;
	private Map<Integer, Long> lastUpdates;

	public Cache() {
		this.lastUpdates = new HashMap<Integer, Long>();
	}

	public Cache(String pushId, String deviceId, User user, Map<Integer, Long> lastUpdates) {
		this.pushId = pushId;
		this.deviceId = deviceId;
		this.user = user;
		this.lastUpdates = lastUpdates;
	}

	@Override
	public String toString() {
		return "Cache{" +
				"pushId='" + pushId + '\'' +
				", deviceId='" + deviceId + '\'' +
				", user=" + user +
				", lastUpdates=" + lastUpdates +
				'}';
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<Integer, Long> getLastUpdates() {
		return lastUpdates;
	}

	public void setLastUpdates(Map<Integer, Long> lastUpdates) {
		this.lastUpdates = lastUpdates;
	}
}
