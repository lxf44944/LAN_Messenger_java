package listener;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import frame.LaterLoadFrame;
import frame.LoadFrame;

import panel.LoadPanel;
import pub.PackType;
import pub.QQPackage;
import thread.CReadThread;
import tool.CheckData;

public class SetListener implements ActionListener {
	LoadFrame loadFrame;
	LoadPanel loadPanel;
	LaterLoadFrame laterLoadFrame;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	private Socket socket;
	private int j;
	private int i;

	public SetListener(LoadFrame loadFrame, LoadPanel loadPanel) {
		this.loadFrame = loadFrame;
		this.loadPanel = loadPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loadPanel.getButton1()) {
			if (i == 0) {
				loadFrame.setSize(new Dimension(330, 340));
				i = 1;
			} else if (i == 1) {
				loadFrame.setSize(new Dimension(330, 250));
				i = 0;
			}
		} else if (e.getSource() == loadPanel.getButton2()) {

			// a、验证ip，port，userid，pwd是否合格

			String id = loadPanel.getTextField().getText();
			String ip = loadPanel.getTextField3().getText();
			String port = loadPanel.getTextField4().getText();
			String pass = String.valueOf(loadPanel.getTextField2()
					.getPassword());
			CheckData.isIp(ip);
			CheckData.isPort(port);

			if (loadPanel.getTextField().getText() == null
					|| loadPanel.getTextField().getText() == "") {
				JOptionPane.showMessageDialog(loadFrame, "帐号不能为空");
				return;
			} else if (!CheckData.checkPassword(pass)) {
				JOptionPane.showMessageDialog(loadFrame,
						"输入的密码长度须在 3~16 之间(只允许是数字，字母 , _) ");
				return;
			} else if (!CheckData.isIp(ip)) {
				JOptionPane.showMessageDialog(loadFrame, "ip格式不正确");

				return;
			} else if (!CheckData.isPort(port)) {
				JOptionPane.showMessageDialog(loadFrame, "端口号格式不正确");
				return;
			} else {
				// b、验证合格，则连接服务器，发送登录请求包：
				
				try {
					socket = new Socket(ip, Integer.parseInt(port));
					objectOutputStream = new ObjectOutputStream(
							socket.getOutputStream());
					QQPackage qqPackage = new QQPackage();

					qqPackage.setPackType(PackType.loginApply);
					qqPackage.setFrom(id);
					qqPackage.setData(pass);
					objectOutputStream.writeObject(qqPackage);
					objectOutputStream.flush();

					/**
					 * 接收验证成功与否
					 */

					/**
					 * 当收到登陆失败包时，给出响应的提示，并马上关闭 socket，oos，ois(记录失败次数3次销毁
					 * 登陆界面，退出应用程序)
					 */

					objectInputStream = new ObjectInputStream(
							socket.getInputStream());
					QQPackage qqPackage2 = (QQPackage) objectInputStream
							.readObject();
					if (qqPackage2.getPackType().equals(PackType.loginSuccess)) {
						loadPanel.getLoadMainFrame().dispose();
						laterLoadFrame = new LaterLoadFrame(socket,
								objectOutputStream, objectInputStream);
						laterLoadFrame.setTitle((String) qqPackage2.getData());

					} else {
						j++;
						if (j == 3) {
							loadPanel.getLoadMainFrame().dispose();
							JOptionPane
									.showMessageDialog(
											loadPanel.getLoadMainFrame(),
											"三次不成功，请重启程序");
							return;
						}
						JOptionPane.showMessageDialog(
								loadPanel.getLoadMainFrame(), "登入不成功");
						try {
							socket.close();
							return;
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} catch (UnknownHostException e1) {
					JOptionPane.showMessageDialog(loadPanel.getLoadMainFrame(),
							"连接失败，请确认服务器地址和端口号是否正确！");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(loadPanel.getLoadMainFrame(),
							"创建连接失败，请与客服联系!");
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}

				/**
				 * 当收到登陆成功包时，销毁登陆界面，创建聊天面板(在构造 函数
				 * 里面把socket，oos，ois传递过去)，同时启动客户端读取数据 线程
				 */
				// 接收消息
				CReadThread readThread = new CReadThread(objectInputStream,
						laterLoadFrame);
				readThread.start();
			}

		}
	}

}
