package tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckData {
	public static boolean isIp(String str) {
		boolean isOk = false;
		Pattern pattern = null;

		pattern = Pattern // 12 199 249 255
				.compile("\\b(((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])(\\b|\\.)){3}\\b(((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b)");

		Matcher matcher = pattern.matcher(str);

		if (matcher.matches()) {
			isOk = true;
		}

		return isOk;

	}

	public static boolean isPort(String str) {
		boolean isOk = false;
		String regex = "^[0-9]{4,5}$";
		if (str != null) {
			if (str.matches(regex)) {
				int port = Integer.parseInt(str);
				if (1024 <= port && port <= 65535) {
					isOk = true;
				}
			}
		}

		return isOk;
	}

	/**
	 * 验证密码
	 * 
	 * @param pass
	 * @return 是否合法
	 */
	public static boolean checkPassword(String pass) {
		boolean f = false;
		String regex = "^([0-9]|[a-zA-Z]|_){3,16}$";// \\w--数字字母下划线
		if (pass != null) {
			if (pass.matches(regex)) {
				f = true;
			}
		}

		return f;
	}

}
