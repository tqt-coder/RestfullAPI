package quoctuan.com.RestfullAPI.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultBean {
	private long status;
	private String error;
	private boolean success;

}
