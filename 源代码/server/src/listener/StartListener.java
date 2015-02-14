package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import panel.StartPanel;

import frame.ServerFrame;

public class StartListener implements ActionListener {
	StartPanel startPanel;
	private Connection connection;

	public StartListener(StartPanel startPanel) {
		this.startPanel = startPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == startPanel.getButton1()) {
			// 设置值

			// 从界面文本框分别获取数据库连接相关信息
			String cUrl = startPanel.getTextField2().getText();
			String cUser = startPanel.getTextField3().getText();
			String cPassword = String.valueOf(startPanel.getTextField4()
					.getPassword());
			String cDriver = startPanel.getTextField5().getText();
			String cPort = startPanel.getTextField6().getText();

			/**
			 * 连接url、用户名、密码、数据库连接驱动， 测试数据库连接是否正常，并给出相应提示，连接失败提示重新配置，重新测试； ；
			 * */
			try {
				Class.forName(cDriver);
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(startPanel.getStartFrame(),
						"驱动加载失败，请重新配置驱动并测试");
				return;
			}
			try {
				connection = DriverManager
						.getConnection(cUrl, cUser, cPassword);
			} catch (SQLException e2) {
				JOptionPane.showMessageDialog(startPanel.getStartFrame(),
						"url、用户名或密码有误，请重新配置并测试");
				return;
			}

			// 如果数据库连接正常，从界面端口号框获得端口号，测试端口号是否被占用，提示操作是否成功，不成功说明端口号被占用，提示用户修改端口号，重新测试

			try {

				String regex = "^[0-9]{4,5}$";
				if (cPort.matches(regex)) {

					int port = Integer.parseInt(cPort);
					if (1024 >= port || port >= 65535) {
						System.out.println(port);
						JOptionPane.showMessageDialog(
								startPanel.getStartFrame(),
								"端口号格式错误，请修改端口号并重新测试");

						return;
					}
				} else {
					JOptionPane.showMessageDialog(startPanel.getStartFrame(),
							"端口号格式错误，请修改端口号并重新测试");
					return;
				}

				new ServerSocket(Integer.parseInt(cPort));
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(startPanel.getStartFrame(),
						"端口号被占用，请修改端口号并重新测试");
				return;
			}

			// 当数据库和端口都测试通过了，将配置信息输出到配置文件提示操作成功与否。
			startPanel.getConfig().setValue("连接url", cUrl);
			startPanel.getConfig().setValue("用户名", cUser);
			startPanel.getConfig().setValue("密码", cPassword);
			startPanel.getConfig().setValue("驱动", cDriver);
			startPanel.getConfig().setValue("端口号", cPort);
			// 保存
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			startPanel.getConfig().save();
			JOptionPane
					.showMessageDialog(startPanel.getStartFrame(), "测试并保存成功");

			// 进入服务器按钮可以使用，即可以点击；
			startPanel.getButton2().setEnabled(true);

		} else if (e.getSource() == startPanel.getButton2()) {
			startPanel.getStartFrame().dispose();
			new ServerFrame();
		}

	}

}
