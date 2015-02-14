package tool;

public class Check {

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

	/**
	 * 验证名字
	 * 
	 * @param sname
	 * @return 是否合法
	 */
	public static boolean checkSname(String sname) {
		boolean f = false;
		String regex = "^[一-龥]{2,10}$";// 全中文或全英文
		if (sname != null) {
			if (sname.matches(regex)) {
				f = true;
			}
		}

		return f;
	}

	/**
	 * 验证年龄
	 * 
	 * @param nage
	 * @return 是否合法
	 */
	public static boolean checkSage(String age) {
		boolean f = false;
		String regex = "^[1-9][0-9]{1,2}$";
		if (age != null) {
			age = age.trim();
			if (age.matches(regex)) {
				int i = Integer.parseInt(age);
				if (20 <= i && i <= 150) {
					f = true;
				}
			}
		}
		return f;
	}

	/**
	 * 验证地址 不可以输入包含逗号(以便后面存入文本分隔使用)
	 * 
	 * @param saddress
	 * @return 是否合法
	 */
	public static boolean checkSaddress(String saddress) {
		boolean f = false;
		String regex = "[^,，]{0,100}";// 可以不输入但是不可以输入逗号
		if (saddress != null) {
			if (saddress.matches(regex)) {
				f = true;
			}
		}
		return f;
	}

}
