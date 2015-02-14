package listener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.imf.Userimp;

import panel.ServerPanel;
import panel.UserPanel;
import pub.PackType;
import pub.QQPackage;
import thread.AcceptThread;
import thread.SReadThread;
import tool.StaticTool;
import frame.ServerFrame;

public class ServerListener implements ActionListener {
	ServerFrame serverFrame;
	ServerPanel serverPanel;
	private AcceptThread acceptThread;
	private JTable jTable;
	private JPanel panel;
	private String uId;
	private JScrollPane jScrollPane;
	private ServerSocket serverSocket;
	private SReadThread serverReadClientThread;
	private String uName;
	private static Userimp userimp = new Userimp();
	private static DefaultTableModel defaultTableModel = new DefaultTableModel();

	public ServerListener(ServerPanel serverPanel, ServerFrame serverFrame) {
		this.serverFrame = serverFrame;
		this.serverPanel = serverPanel;
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == serverPanel.getButtonStart()) {
			// 1、启动服务器：

			// a、创建serverSocket：

			try {
				serverSocket = new ServerSocket(8088);

				// f、创建acceptThread,并启动线程
				acceptThread = new AcceptThread(serverSocket, serverFrame);
				acceptThread.start();

			} catch (IOException e1) {
				System.out.println("端口号被占用");
			}

			// b、改按钮等控件是否可用
			serverPanel.getButtonStart().setEnabled(false);
			serverPanel.getButtonStop().setEnabled(true);
			serverPanel.getButtonSend().setEnabled(true);
			// serverPanel.getButtonForce().setEnabled(true);

			// c、写日志
			serverFrame.getLogPanel().writeLog("启动服务器成功");
			// d、写通讯信息提示
			serverPanel.getTextArea4().append(
					"【" + StaticTool.SystemTime() + "】启动服务器成功\n");

			// e、改图片
			serverPanel.getLabel().setIcon(StaticTool.startIcon);

			serverPanel.getPanel2().add(centerpanel());

			// 给选中行添加监听
			jTable.getSelectionModel().addListSelectionListener(
					new ListSelectionListener() {

						public void valueChanged(ListSelectionEvent e) {
							if (e.getValueIsAdjusting()) {

								if (jTable.getSelectedRow() != -1) {

									uId = (String) jTable.getValueAt(
											jTable.getSelectedRow(), 0);
									uName = (String) jTable.getValueAt(
											jTable.getSelectedRow(), 1);
									serverPanel.getButtonForce().setEnabled(
											true);
								} else {
									serverPanel.getButtonForce().setEnabled(
											false);
								}
							}
						}
					});

		} else if (e.getSource() == serverPanel.getButtonStop()) {
			// 停止服务器：
			// a、写日志
			serverFrame.getLogPanel().writeLog("关闭服务器成功");
			// b、写通讯信息提示
			serverPanel.getTextArea4().append(
					"【" + StaticTool.SystemTime() + "】服务器已停止\n");

			// c、修改图片
			serverPanel.getLabel().setIcon(StaticTool.imageIcon);

			// d、设置按钮等组件可用与否
			serverPanel.getButtonStart().setEnabled(true);
			serverPanel.getButtonStop().setEnabled(false);
			serverPanel.getButtonSend().setEnabled(false);
			serverPanel.getButtonForce().setEnabled(false);

			// e、修改所有用户为离线状态，并刷新服务器管理面板在线表格

			userimp.isonline(null);
			serverPanel.getPanel2().removeAll();
			//

			// 注：刷新服务器面板同时要刷新用户管理面板

			serverFrame.getUserPanel().refresh(null, null);
			// f、向所有在线用户发送停止服务器包

			Vector<String> onlineName = new Vector<String>();
			onlineName.removeAllElements();

			Collection<SReadThread> collection2 = serverFrame
					.getReadThreadMap().values();
			QQPackage package1 = new QQPackage();
			package1.setPackType(PackType.stopServer);
			package1.setData(onlineName);
			for (SReadThread serverReadClientThread2 : collection2) {
				try {
					serverReadClientThread2.getObjectOutputStream()
							.writeObject(package1);
					serverReadClientThread2.getObjectOutputStream().flush();
					serverReadClientThread2.interrupt();

				} catch (IOException e1) {
					System.out.println("服务器已关闭");
				}

			}
			// g、清空hashtable

			serverFrame.getReadThreadMap().clear();
			// h、中断线程

			// i、关闭serverSocket

			try {
				serverSocket.close();
			} catch (IOException e1) {
				System.out.println("服务已关闭");
			}

		} else if (e.getSource() == serverPanel.getButtonForce()) {

			int isDelete = JOptionPane.showConfirmDialog(serverFrame, "确定强制"
					+ uId + "用户下线吗？", "强制下线", 0);

			if (isDelete == 0) {
				userimp.isonline(uId);
				sRefresh(null, null, "在线");
				UserPanel.refresh(null, null);
				JOptionPane.showMessageDialog(serverFrame, "强制下线成功!");

				// a、写日志
				serverFrame.getLogPanel().writeLog(
						uName + "(" + uId + ")" + "已强制下线!");
				// b、写通讯信息提示
				serverPanel.getTextArea4().append(
						"【" + StaticTool.SystemTime() + "】" + uId + "已强制下线!\n");

				serverReadClientThread = serverFrame.getReadThreadMap().get(
						uId + " " + uName);
				serverFrame.getReadThreadMap().remove(uId + " " + uName);

				QQPackage package1 = new QQPackage();
				package1.setPackType(PackType.enforceDown);

				try {
					serverReadClientThread.getObjectOutputStream().writeObject(
							package1);
					serverReadClientThread.getObjectOutputStream().flush();
				} catch (IOException e1) {
					System.out.println("socket closed");
				}

				serverReadClientThread.interrupt();
				try {
					serverReadClientThread.getSocket().close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				Collection<String> collection = serverFrame.getReadThreadMap()
						.keySet();
				Vector<String> onlineName = new Vector<String>();
				onlineName.removeAllElements();
				for (String string : collection) {
					onlineName.add(string);
				}
				Collection<SReadThread> collection2 = serverFrame
						.getReadThreadMap().values();
				QQPackage package2 = new QQPackage();
				package2.setPackType(PackType.onlineuser);
				package2.setData(onlineName);
				for (SReadThread serverReadClientThread2 : collection2) {
					try {
						serverReadClientThread2.getObjectOutputStream()
								.writeObject(package2);
						serverReadClientThread2.getObjectOutputStream().flush();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}

			}

		} else if (e.getSource() == serverPanel.getButtonSend()) {

			// 发送公告
			String message = serverPanel.getTextArea5().getText();
			QQPackage qqPackage = new QQPackage();
			qqPackage.setPackType(PackType.post);
			qqPackage.setData(message);

			Collection<SReadThread> collection = serverFrame.getReadThreadMap()
					.values();
			for (SReadThread sReadThread : collection) {
				try {

					ObjectOutputStream objectOutputStream = sReadThread
							.getObjectOutputStream();
					objectOutputStream.writeObject(qqPackage);

					objectOutputStream.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			serverPanel.getTextArea5().setText("");
			JOptionPane.showMessageDialog(null, "已发送公告成功！");

		}

	}

	private JPanel centerpanel() {
		// 显示在线用户列表
		panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(484, 180));

		// 3加入数据
		defaultTableModel.setDataVector(userimp.selectUser(null, null, "在线"),
				userimp.head());

		// 4设置模型
		jTable = new JTable(defaultTableModel) {

			private static final long serialVersionUID = 3008651421845101749L;

			// 设置表格内数据不可编辑
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};

		// 设置表头不可拖动
		jTable.getTableHeader().setReorderingAllowed(false);
		// 设置行单选
		jTable.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

		// 要显示表头一定要把表格加入到滚动面板
		jScrollPane = new JScrollPane(jTable);
		panel.add(jScrollPane);
		return panel;

	}

	/**
	 * 刷新表格
	 */
	public static void sRefresh(String sId, String sName, String sIsOnline) {

		Vector<Vector<String>> vecData = userimp.selectUser(sId, sName,
				sIsOnline);
		defaultTableModel.setDataVector(vecData, userimp.head());
		defaultTableModel.fireTableDataChanged();

	}

}
