package rocks.testbuild.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.rightutils.rightutils.collections.RightList;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

/**
 * Created by nnet on 6/27/15.
 */
public class Project implements Parcelable{
	private long id;
	private String name;
	private String identifier;
	private String description;
	@JsonProperty("created_on")
	private Date createdAt;
	@JsonProperty("updated_on")
	private Date updatedAt;
	@JsonProperty("custom_fields")
	private RightList<CustomField> customFields;

	public Project() {
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

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public RightList<CustomField> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(RightList<CustomField> customFields) {
		this.customFields = customFields;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Project project = (Project) o;

		if (id != project.id) return false;
		if (name != null ? !name.equals(project.name) : project.name != null) return false;
		if (identifier != null ? !identifier.equals(project.identifier) : project.identifier != null)
			return false;
		if (description != null ? !description.equals(project.description) : project.description != null)
			return false;
		if (createdAt != null ? !createdAt.equals(project.createdAt) : project.createdAt != null)
			return false;
		return !(updatedAt != null ? !updatedAt.equals(project.updatedAt) : project.updatedAt != null);

	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
		result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Project{" +
				"id=" + id +
				", name='" + name + '\'' +
				", identifier='" + identifier + '\'' +
				", description='" + description + '\'' +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
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
		dest.writeString(this.identifier);
		dest.writeString(this.description);
		dest.writeLong(createdAt != null ? createdAt.getTime() : -1);
		dest.writeLong(updatedAt != null ? updatedAt.getTime() : -1);
		dest.writeList(this.customFields);
	}

	protected Project(Parcel in) {
		this.id = in.readLong();
		this.name = in.readString();
		this.identifier = in.readString();
		this.description = in.readString();
		long tmpCreatedAt = in.readLong();
		this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
		long tmpUpdatedAt = in.readLong();
		this.updatedAt = tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt);
		this.customFields = (RightList<CustomField>) RightList.asRightList(in.createTypedArray(CustomField.CREATOR));

	}

	public static final Creator<Project> CREATOR = new Creator<Project>() {
		public Project createFromParcel(Parcel source) {
			return new Project(source);
		}

		public Project[] newArray(int size) {
			return new Project[size];
		}
	};
}
