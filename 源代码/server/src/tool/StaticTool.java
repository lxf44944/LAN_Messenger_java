package tool;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import pub.PackType;
import pub.QQPackage;
import thread.SReadThread;

import frame.ServerFrame;

public class StaticTool {

	public static ImageIcon imageIcon = new ImageIcon(
			"./image/total/serverstop.gif");
	public static ImageIcon startIcon = new ImageIcon(
			"./image/total/serverstart.gif");
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

	public static void sendOnLineUsers(ServerFrame frame) {
		Collection<String> collection = frame.getReadThreadMap().keySet();
		Vector<String> onlineName = new Vector<String>();
		for (String string : collection) {
			onlineName.add(string);

		}
		QQPackage package1 = new QQPackage();
		package1.setPackType(PackType.onlineuser);
		package1.setData(onlineName);
		Collection<SReadThread> collection1 = frame.getReadThreadMap().values();
		for (SReadThread sReadThread : collection1) {
			try {
				sReadThread.getObjectOutputStream().writeObject(package1);
				sReadThread.getObjectOutputStream().flush();
			} catch (IOException e) {
				System.out.println("Socket closed");
			}
		}

	}

}
