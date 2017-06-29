package beans;

import java.io.Serializable;

public class Operazione implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int op1;
	private int op2;
	private char operator;
	private int res;
	public Operazione() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Operazione(int op1, int op2, char operator) {
		super();
		this.op1 = op1;
		this.op2 = op2;
		this.operator = operator;
	}
	public int getOp1() {
		return op1;
	}
	public void setOp1(int op1) {
		this.op1 = op1;
	}
	public int getOp2() {
		return op2;
	}
	public void setOp2(int op2) {
		this.op2 = op2;
	}
	public char getOperator() {
		return operator;
	}
	public void setOperator(char operator) {
		this.operator = operator;
	}
	public int getRes() {
		return res;
	}
	public void setRes(int res) {
		this.res = res;
	}
	
	

}
