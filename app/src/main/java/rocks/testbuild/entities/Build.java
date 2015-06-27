package rocks.testbuild.entities;

import android.os.Parcel;
import android.os.Parcelable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by nnet on 6/27/15.
 */
public class Build implements Parcelable {
	private long id;
	private String name;
	private String type;
	private String url;
	private String buildUrl;
	private String plist;
	private String version;
	private String build;
	private String qrcode;
	private String comment;
	@JsonProperty("bundleid")
	private String bundleId;
	private String createdName;
	@JsonProperty("projectid")
	private long projectId;
	private long createdId;
	@JsonProperty("iconurl")
	private String picture;

	public Build() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBuildUrl() {
		return buildUrl;
	}

	public void setBuildUrl(String buildUrl) {
		this.buildUrl = buildUrl;
	}

	public String getPlist() {
		return plist;
	}

	public void setPlist(String plist) {
		this.plist = plist;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getBundleId() {
		return bundleId;
	}

	public void setBundleId(String bundleId) {
		this.bundleId = bundleId;
	}

	public String getCreatedName() {
		return createdName;
	}

	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public long getCreatedId() {
		return createdId;
	}

	public void setCreatedId(long createdId) {
		this.createdId = createdId;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Build build1 = (Build) o;

		if (id != build1.id) return false;
		if (projectId != build1.projectId) return false;
		if (createdId != build1.createdId) return false;
		if (name != null ? !name.equals(build1.name) : build1.name != null) return false;
		if (type != null ? !type.equals(build1.type) : build1.type != null) return false;
		if (url != null ? !url.equals(build1.url) : build1.url != null) return false;
		if (buildUrl != null ? !buildUrl.equals(build1.buildUrl) : build1.buildUrl != null)
			return false;
		if (plist != null ? !plist.equals(build1.plist) : build1.plist != null) return false;
		if (version != null ? !version.equals(build1.version) : build1.version != null)
			return false;
		if (build != null ? !build.equals(build1.build) : build1.build != null) return false;
		if (qrcode != null ? !qrcode.equals(build1.qrcode) : build1.qrcode != null) return false;
		if (comment != null ? !comment.equals(build1.comment) : build1.comment != null)
			return false;
		if (bundleId != null ? !bundleId.equals(build1.bundleId) : build1.bundleId != null)
			return false;
		if (createdName != null ? !createdName.equals(build1.createdName) : build1.createdName != null)
			return false;
		return !(picture != null ? !picture.equals(build1.picture) : build1.picture != null);

	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (url != null ? url.hashCode() : 0);
		result = 31 * result + (buildUrl != null ? buildUrl.hashCode() : 0);
		result = 31 * result + (plist != null ? plist.hashCode() : 0);
		result = 31 * result + (version != null ? version.hashCode() : 0);
		result = 31 * result + (build != null ? build.hashCode() : 0);
		result = 31 * result + (qrcode != null ? qrcode.hashCode() : 0);
		result = 31 * result + (comment != null ? comment.hashCode() : 0);
		result = 31 * result + (bundleId != null ? bundleId.hashCode() : 0);
		result = 31 * result + (createdName != null ? createdName.hashCode() : 0);
		result = 31 * result + (int) (projectId ^ (projectId >>> 32));
		result = 31 * result + (int) (createdId ^ (createdId >>> 32));
		result = 31 * result + (picture != null ? picture.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Build{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				", url='" + url + '\'' +
				", buildUrl='" + buildUrl + '\'' +
				", plist='" + plist + '\'' +
				", version='" + version + '\'' +
				", build='" + build + '\'' +
				", qrcode='" + qrcode + '\'' +
				", comment='" + comment + '\'' +
				", bundleId='" + bundleId + '\'' +
				", createdName='" + createdName + '\'' +
				", projectId=" + projectId +
				", createdId=" + createdId +
				", picture='" + picture + '\'' +
				'}';
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(this.id);
		dest.writeString(this.name);
		dest.writeString(this.type);
		dest.writeString(this.url);
		dest.writeString(this.buildUrl);
		dest.writeString(this.plist);
		dest.writeString(this.version);
		dest.writeString(this.build);
		dest.writeString(this.qrcode);
		dest.writeString(this.comment);
		dest.writeString(this.bundleId);
		dest.writeString(this.createdName);
		dest.writeLong(this.projectId);
		dest.writeLong(this.createdId);
		dest.writeString(this.picture);
	}

	protected Build(Parcel in) {
		this.id = in.readLong();
		this.name = in.readString();
		this.type = in.readString();
		this.url = in.readString();
		this.buildUrl = in.readString();
		this.plist = in.readString();
		this.version = in.readString();
		this.build = in.readString();
		this.qrcode = in.readString();
		this.comment = in.readString();
		this.bundleId = in.readString();
		this.createdName = in.readString();
		this.projectId = in.readLong();
		this.createdId = in.readLong();
		this.picture = in.readString();
	}

	public static final Creator<Build> CREATOR = new Creator<Build>() {
		public Build createFromParcel(Parcel source) {
			return new Build(source);
		}

		public Build[] newArray(int size) {
			return new Build[size];
		}
	};
}
