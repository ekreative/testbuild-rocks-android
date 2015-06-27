package rocks.testbuild.entities;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by nnet on 6/27/15.
 */
public class Build {
	private long id;
	private String type;
	private String url;
	private String plist;
	private String version;
	private String build;
	private String qrcode;
	private String bundleid;
	private String createdName;
	private long projectid;
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

	public String getBundleid() {
		return bundleid;
	}

	public void setBundleid(String bundleid) {
		this.bundleid = bundleid;
	}

	public String getCreatedName() {
		return createdName;
	}

	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}

	public long getProjectid() {
		return projectid;
	}

	public void setProjectid(long projectid) {
		this.projectid = projectid;
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
		if (projectid != build1.projectid) return false;
		if (type != null ? !type.equals(build1.type) : build1.type != null) return false;
		if (url != null ? !url.equals(build1.url) : build1.url != null) return false;
		if (plist != null ? !plist.equals(build1.plist) : build1.plist != null) return false;
		if (version != null ? !version.equals(build1.version) : build1.version != null)
			return false;
		if (build != null ? !build.equals(build1.build) : build1.build != null) return false;
		if (qrcode != null ? !qrcode.equals(build1.qrcode) : build1.qrcode != null) return false;
		if (bundleid != null ? !bundleid.equals(build1.bundleid) : build1.bundleid != null)
			return false;
		if (createdName != null ? !createdName.equals(build1.createdName) : build1.createdName != null)
			return false;
		return !(picture != null ? !picture.equals(build1.picture) : build1.picture != null);

	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (url != null ? url.hashCode() : 0);
		result = 31 * result + (plist != null ? plist.hashCode() : 0);
		result = 31 * result + (version != null ? version.hashCode() : 0);
		result = 31 * result + (build != null ? build.hashCode() : 0);
		result = 31 * result + (qrcode != null ? qrcode.hashCode() : 0);
		result = 31 * result + (bundleid != null ? bundleid.hashCode() : 0);
		result = 31 * result + (createdName != null ? createdName.hashCode() : 0);
		result = 31 * result + (int) (projectid ^ (projectid >>> 32));
		result = 31 * result + (picture != null ? picture.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Build{" +
				"id=" + id +
				", type='" + type + '\'' +
				", url='" + url + '\'' +
				", plist='" + plist + '\'' +
				", version='" + version + '\'' +
				", build='" + build + '\'' +
				", qrcode='" + qrcode + '\'' +
				", bundleid='" + bundleid + '\'' +
				", createdName='" + createdName + '\'' +
				", projectid=" + projectid +
				", picture='" + picture + '\'' +
				'}';
	}
}
