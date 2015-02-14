package dao.imf;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import panel.UserPanel;

import dao.bean.User;
import dao.db.DB_Conn;
import dao.inf.UserInf;

public class Userimp implements UserInf {

	private ResultSet resultSet;
	private Connection connection;
	private Statement statement;

	@Override
	public boolean addUser(User user) {
		connection = DB_Conn.getDbConn().getConn();

		String sql = "insert into T_user(id,name,password,sex,age,address)"
				+ " values('" + user.getId() + "','" + user.getName() + "','"
				+ user.getPassword() + "','" + user.getSex() + "','"
				+ user.getAge() + "','" + user.getAddress() + "')";

		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_Conn.getDbConn().close(null, null, statement, connection);
		}

		return false;
	}

	@Override
	public Vector<Vector<String>> selectUser(String id, String name,
			String isOnline) {
		connection = DB_Conn.getDbConn().getConn();
		String sql = "select * from t_user where 1=1 ";
		if (id != null && !id.equals("")) {
			sql = sql + " and id='" + id + "'";
		}
		if (name != null && !name.equals("")) {
			sql = sql + " and name like '%" + name + "%'";
		}
		if (isOnline != null && !isOnline.equals("")) {
			sql = sql + " and isonline='" + isOnline + "'";
		}

		sql = sql + " order by id";
		Vector<Vector<String>> dates = null;
		int count = 0;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			dates = new Vector<Vector<String>>();
			while (resultSet.next()) {
				Vector<String> rows = new Vector<String>();
				rows.add(resultSet.getString("id"));
				rows.add(resultSet.getString("name"));

				rows.add(resultSet.getString("sex"));
				rows.add(String.valueOf(resultSet.getInt("age")));
				rows.add(resultSet.getString("isonline"));
				rows.add(resultSet.getString("regdate"));
				rows.add(resultSet.getString("address"));
				rows.add(resultSet.getString("password"));

				dates.add(rows);

				count = count + 1;

			}
			UserPanel.setCounts(count);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_Conn.getDbConn().close(resultSet, null, statement, connection);
		}
		return dates;
	}

	public User getSingleUser(String id) {
		User user = new User();
		return user;

	}

	public Vector<String> head() {
		// 编号，姓名，性别，年龄，地址，是否在线，注册时间
		Vector<String> head = new Vector<String>();
		head.add("账号");
		head.add("用户名");
		head.add("性别");
		head.add("年龄");
		head.add("是否在线");
		head.add("注册日期");
		head.add("地址");

		return head;
	}

	public String nextId() {
		connection = DB_Conn.getDbConn().getConn();
		String sql = "select max(id) maxId from T_user";
		int nextid = 0;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String maxId = resultSet.getString("maxId");
				// System.out.println(maxId);
				if (maxId == null) {
					return "00001";
				}
				nextid = 1 + Integer.parseInt(maxId);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (nextid >= 10 && nextid < 100) {
			return "000" + nextid;
		} else if (nextid >= 100 && nextid < 1000) {
			return "00" + nextid;
		} else if (nextid >= 1000 && nextid < 10000) {
			return "0" + nextid;
		} else if (nextid >= 10000) {
			return String.valueOf(nextid);
		} else {
			return "0000" + nextid;
		}

	}

	@Override
	public boolean delUser(String id) {
		connection = DB_Conn.getDbConn().getConn();

		String sql = "delete from T_user where id='" + id + "'";

		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_Conn.getDbConn().close(null, null, statement, connection);
		}
		return false;
	}

	@Override
	public boolean updateUser(User user, String id) {
		connection = DB_Conn.getDbConn().getConn();

		String sql = "update T_user set name='" + user.getName() + "',sex='"
				+ user.getSex() + "',age='" + user.getAge() + "',address='"
				+ user.getAddress() + "' where id='" + id + "'";

		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_Conn.getDbConn().close(null, null, statement, connection);
		}
		return false;
	}

	@Override
	public boolean clearUser(String id) {
		connection = DB_Conn.getDbConn().getConn();
		String sql = "update T_user set password='123456'";
		if (id != null && !id.equals("")) {
			sql = sql + " where id='" + id + "'";
		}

		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_Conn.getDbConn().close(null, null, statement, connection);
		}
		return false;
	}

	@Override
	public boolean isonline(String id) {
		connection = DB_Conn.getDbConn().getConn();

		String sql = "update T_user set isonline='离线'";

		if (id != null && !id.equals("")) {
			sql = sql + " where id='" + id + "'";
		}
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_Conn.getDbConn().close(null, null, statement, connection);
		}
		return false;
	}

	@Override
	public boolean getUserBySid(String sid) {
		String sql = "select * from T_user where id='" + sid + "'";
		connection = DB_Conn.getDbConn().getConn();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_Conn.getDbConn().close(resultSet, null, statement, connection);
		}

		return false;
	}

	@Override
	public User getUserBySidAndPsw(String sid, String password) {
		User user = null;
		String sql = "select * from T_user where id='" + sid
				+ "' and password = '" + password + "'";
		connection = DB_Conn.getDbConn().getConn();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				user = new User();
				user.setId(sid);
				user.setName(resultSet.getString("name"));
				user.setSex(resultSet.getString("sex"));
				user.setAge(String.valueOf(resultSet.getInt("age")));
				user.setIsonline(resultSet.getString("isonline"));
				user.setRegdate(resultSet.getString("regdate"));
				user.setAddress(resultSet.getString("address"));
				user.setPassword(resultSet.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_Conn.getDbConn().close(resultSet, null, statement, connection);
		}

		return user;
	}

	@Override
	public void updateOnline(String sid) {
		connection = DB_Conn.getDbConn().getConn();

		String sql = "update T_user set isonline='在线' where id = '" + sid + "'";

		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_Conn.getDbConn().close(null, null, statement, connection);
		}

	}

	@Override
	public void updateUserGo(String sid) {
		connection = DB_Conn.getDbConn().getConn();

		String sql = "update T_user set isonline='离线' where id = '" + sid + "'";

		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_Conn.getDbConn().close(null, null, statement, connection);
		}

	}

	@Override
	public void updatePassword(String sid, String password) {
		connection = DB_Conn.getDbConn().getConn();

		String sql = "update T_user set password='" + password
				+ "' where id = '" + sid + "' ";

		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_Conn.getDbConn().close(null, null, statement, connection);
		}
	}

	

	@Override
	public User getUserPswBySid(String sid) {
		User user = null;
		String sql = "select * from T_user where id='" + sid + "'";
		connection = DB_Conn.getDbConn().getConn();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				user = new User();
				user.setId(sid);
				user.setPassword(resultSet.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_Conn.getDbConn().close(resultSet, null, statement, connection);
		}

		return user;
	}
}
