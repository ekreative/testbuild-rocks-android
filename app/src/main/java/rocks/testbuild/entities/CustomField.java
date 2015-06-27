package rocks.testbuild.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nnet on 6/27/15.
 */
public class CustomField implements Parcelable {
	private long id;
	private String name;
	private String value;

	public CustomField() {
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CustomField that = (CustomField) o;

		if (id != that.id) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		return !(value != null ? !value.equals(that.value) : that.value != null);

	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (value != null ? value.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "CustomFields{" +
				"id=" + id +
				", name='" + name + '\'' +
				", value='" + value + '\'' +
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
		dest.writeString(this.value);
	}

	protected CustomField(Parcel in) {
		this.id = in.readLong();
		this.name = in.readString();
		this.value = in.readString();
	}

	public static final Creator<CustomField> CREATOR = new Creator<CustomField>() {
		public CustomField createFromParcel(Parcel source) {
			return new CustomField(source);
		}

		public CustomField[] newArray(int size) {
			return new CustomField[size];
		}
	};
}
