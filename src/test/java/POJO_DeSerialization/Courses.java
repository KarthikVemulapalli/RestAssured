package POJO_DeSerialization;

import java.util.List;

public class Courses {
	
	private List<WebAutomation> webAutomation;   //Because these are Array, make the variables which accepts multiple data Objects i.e. List
	private List<Api> api;
	private List<Mobile> mobile;
	
	
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<Api> getApi() {
		return api;
	}
	public void setApi(List<Api> api) {
		this.api = api;
	}
	public List<Mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}

}
