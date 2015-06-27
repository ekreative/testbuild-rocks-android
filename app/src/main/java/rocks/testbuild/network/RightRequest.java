package rocks.testbuild.network;

import com.rightutils.rightutils.net.BasicRightRequest;

/**
 * Created by nnet on 6/27/15.
 */
public class RightRequest extends BasicRightRequest {

	public RightRequest() {
		super(DEFAULT_MAX_TOTAL, DEFAULT_MAX_PER_ROUTE);
	}
}
