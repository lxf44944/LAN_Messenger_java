package thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import listener.ServerListener;

import dao.bean.User;
import dao.imf.Userimp;

import pub.PackType;
import pub.QQPackage;
import tool.StaticTool;

import frame.ServerFrame;

public class AcceptThread extends Thread {
	ObjectInputStream objectInputStream;
	ObjectOutputStream objectOutputStream;
	ServerSocket serverSocket;
	ServerFrame frame;
	private Userimp userimp;
	private User bean;

	public AcceptThread(ServerSocket serverSocket, ServerFrame frame) {
		this.serverSocket = serverSocket;
		this.frame = frame;
	}

	@Override
	public void run() {
		while (!this.isInterrupted()) {
			// 2-2.打开监听
			try {
				Socket socket = serverSocket.accept();
				// 获取io流
				objectInputStream = new ObjectInputStream(
						socket.getInputStream());

				QQPackage qqPackage = (QQPackage) objectInputStream
						.readObject();
				String gId = qqPackage.getFrom();
				String gPassword = (String) qqPackage.getData();
				userimp = new Userimp();
				bean = userimp.getUserBySidAndPsw(gId, gPassword);
				objectOutputStream = new ObjectOutputStream(
						socket.getOutputStream());

				// a、连接成功，解析客户端发过来的包 根据sid查询数据库，判断编号存在，
				// 密码正确，在线状态为离线
				if (bean != null) {
					if (gPassword.equals(bean.getPassword())) {
						if (bean.getIsonline().equals("在线")) {
							frame.getLogPanel().writeLog(
									bean.getName() + "(" + bean.getId() + ")"
											+ "登录失败!");
							QQPackage package1 = new QQPackage();
							package1.setPackType(PackType.loginFail);
							packageSend(package1, objectOutputStream);
						} else {
							bean.setIsonline("在线");
							userimp.updateOnline(bean.getId());
							frame.getServerPanel()
									.getTextArea4()
									.append("【" + StaticTool.SystemTime() + "】"
											+ bean.getId() + "登录成功\n");
							frame.getLogPanel().writeLog(
									bean.getName() + "(" + bean.getId() + ")"
											+ "登录成功!");
							/**
							 * 发送登录成功包
							 */

							QQPackage package1 = new QQPackage();
							String name = bean.getId() + " " + bean.getName();
							package1.setPackType(PackType.loginSuccess);
							package1.setData(name);
							packageSend(package1, objectOutputStream);
							/**
							 * 发送公告
							 */
							package1 = new QQPackage();
							package1.setData("欢迎登陆QQ");
							package1.setPackType(PackType.post);
							packageSend(package1, objectOutputStream);
							/**
							 * 刷新用户、列表
							 */
							ServerListener.sRefresh(null, null, "在线");// 刷新列表

							/**
							 * 添加日志
							 */
							frame.getLogPanel().writeLog(
									bean.getName() + "(" + bean.getId() + ")"
											+ "上线了!");
							/**
							 * 启动线程
							 */
							SReadThread sReadThread = new SReadThread(frame,
									name, socket, objectInputStream,
									objectOutputStream);

							frame.getReadThreadMap().put(name, sReadThread);
							// 群发在线用户列表
							StaticTool.sendOnLineUsers(frame);
							sReadThread.start();
						}
					} else {
						QQPackage package1 = new QQPackage();
						package1.setPackType(PackType.loginFail);
						this.packageSend(package1, objectOutputStream);
					}

				} else {
					QQPackage package1 = new QQPackage();
					package1.setPackType(PackType.loginFail);
					this.packageSend(package1, objectOutputStream);
				}

			} catch (IOException e) {
				System.out.println("服务已关闭");
				break;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public synchronized void packageSend(QQPackage qqPackage,
			ObjectOutputStream objectOutputStream) {
		try {
			objectOutputStream.writeObject(qqPackage);
			objectOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ObjectInputStream getObjectInputStream() {
		return objectInputStream;
	}

	public void setObjectInputStream(ObjectInputStream objectInputStream) {
		this.objectInputStream = objectInputStream;
	}

	public ObjectOutputStream getObjectOutputStream() {
		return objectOutputStream;
	}

	public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
		this.objectOutputStream = objectOutputStream;
	}

}
