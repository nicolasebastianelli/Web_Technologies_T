package beans;

import java.io.Serializable;

public class ImuCF implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cf;
	private float imu;
	public float getImu() {
		return imu;
	}
	public void setImu(float imu) {
		this.imu = imu;
	}
	public ImuCF(String cf, float imu) {
		super();
		this.cf = cf;
		this.imu = imu;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	
	

}
