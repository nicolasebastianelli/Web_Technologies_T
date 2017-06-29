package esame.utils;

import java.util.GregorianCalendar;
import java.util.Random;

public class SqlUtils {

	public static java.sql.Date newSqlDate(int year, int month, int day) {
		return new java.sql.Date(new GregorianCalendar(year, month, day).getTimeInMillis());
	}

	public static java.sql.Date randomSqlDate() {
		Random r = new Random();
		return new java.sql.Date(new GregorianCalendar(r.nextInt(50)+2000, r.nextInt(12), r.nextInt(29)).getTimeInMillis());
	}

}
