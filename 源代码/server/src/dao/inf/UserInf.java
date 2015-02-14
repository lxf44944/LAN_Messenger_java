package dao.inf;

import java.util.Vector;

import dao.bean.User;

public interface UserInf {
	public Vector<Vector<String>> selectUser(String id, String name,
			String isOnline);

	public boolean addUser(User user);

	public boolean delUser(String id);

	public boolean updateUser(User user, String id);

	public boolean isonline(String id);

	public boolean clearUser(String id);

	public boolean getUserBySid(String sid);

	public User getUserBySidAndPsw(String sid, String password);

	/**
	 * 修改用户在线
	 */
	public void updateOnline(String sid);

	/**
	 * 修改用户离线
	 */
	public void updateUserGo(String sid);

	/**
	 * 修改密码
	 * 
	 */
	public void updatePassword(String sid, String password);

	public User getUserPswBySid(String sid);

}
