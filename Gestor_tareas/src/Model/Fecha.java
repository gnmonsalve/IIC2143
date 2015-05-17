package Model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fecha implements Serializable {

	Calendar calendar = Calendar.getInstance();
	int d;
	int m;
	int y;

	public Fecha(int d, int m, int y) {
		this.d = d;
		this.m = m;
		this.y = y;
	}

	public Fecha() {
		d = calendar.get(Calendar.DAY_OF_MONTH);
		m = calendar.get(Calendar.MONTH)+1;
		y = calendar.get(Calendar.YEAR);
	}

	public int getD() {
		return d;
	}

	public int getM() {
		return m;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return atToString(d)+"/"+atToString(m)+"/"+Integer.toString(y);
	}
	public String atToString(int i)
	{
		if (i<10)
		{
			return "0"+Integer.toString(i);
		}
		else
		{
			return Integer.toString(i);
		}
	}

	public Calendar getCalendario() {
		return new GregorianCalendar(y,m,d);
	}
}