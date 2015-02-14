package tool;

import java.util.Calendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class StaticTool {
	public static ImageIcon imageIcon = new ImageIcon(
			"./image/total/qqicon.gif");
	public static ImageIcon qqIcon1 = new ImageIcon(
			"./image/total/qqbanner1.jpg");
	public static Icon bloodIcon = new ImageIcon("./image/blood.gif");

	public static String SystemTime() {

		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		String strDate = hour + ":" + minute + ":" + second;

		return strDate;

	}

	public static String SystemDate() {

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		String strDate = year + "-" + month + "-" + day;

		return strDate;

	}

}
