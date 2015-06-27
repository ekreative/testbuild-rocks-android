package rocks.testbuild.entities;

/**
 * Created by nnet on 6/27/15.
 */
public class ResponseError {
	private int code;
	private String message;

	public ResponseError() {
	}

	public ResponseError(String message) {
		this.message = message;
	}

	public ResponseError(int code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponseError{" +
				"message='" + message + '\'' +
				'}';
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
