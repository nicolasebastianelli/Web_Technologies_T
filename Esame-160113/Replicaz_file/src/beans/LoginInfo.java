package beans;

public class LoginInfo {
	public static String LOGIN_INFO = "loginInfo";
	private String name;
	private int couple;

	public LoginInfo(String name , int couple) {
		this.name = name;
		this.couple=couple;
	}

	public String getName() {
		return name;
	}

	public int getCouple() {
		return couple;
	}
	
}
