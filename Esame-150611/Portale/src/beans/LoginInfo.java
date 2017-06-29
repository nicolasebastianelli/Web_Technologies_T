package beans;

public class LoginInfo {
	public static String LOGIN_INFO = "loginInfo";
	private String name;

	public LoginInfo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
