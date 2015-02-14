package thread;

import java.io.ObjectInputStream;
import java.util.Vector;

import javax.swing.JOptionPane;

import pub.PackType;
import pub.QQPackage;
import tool.StaticTool;

import frame.LaterLoadFrame;

public class CReadThread extends Thread {
	ObjectInputStream inputStream;
	LaterLoadFrame clientFrame;

	public CReadThread(ObjectInputStream inputStream, LaterLoadFrame clientFrame) {
		this.clientFrame = clientFrame;
		this.inputStream = inputStream;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		while (!this.isInterrupted()) {

			try {
				QQPackage qqPackage = (QQPackage) clientFrame
						.getObjectInputStream().readObject();
				// 在线用户列表刷新
				if (qqPackage.getPackType().equals(PackType.onlineuser)) {
					clientFrame.getLaterLoadPanel().refresh(
							(Vector<String>) qqPackage.getData());
				} else if (qqPackage.getPackType().equals(PackType.privateChat)) {
					// 私聊
					String message = qqPackage.getFrom().substring(6) + "  "
							+ StaticTool.SystemTime() + "  对 我 说：" + "\n"
							+ (String) qqPackage.getData() + "\n";
					clientFrame.getLaterLoadPanel().getTextArea1()
							.append(message);
					clientFrame.getLaterLoadPanel().writeLog(message,
							clientFrame.getTitle().substring(0, 5));
				} else if (qqPackage.getPackType().equals(PackType.publicChat)) {
					// 群聊
					String messageAll = qqPackage.getFrom().substring(6) + "  "
							+ StaticTool.SystemTime() + "  对 所有人 说：" + "\n"
							+ (String) qqPackage.getData() + "\n";
					clientFrame.getLaterLoadPanel().getTextArea1()
							.append(messageAll);
					clientFrame.getLaterLoadPanel().writeLog(messageAll,
							clientFrame.getTitle().substring(0, 5));
				} else if (qqPackage.getPackType().equals(
						PackType.updatePassword)) {
					// 修改密码
					String string = (String) qqPackage.getData();
					if (string.equals("密码出错")) {
						JOptionPane.showMessageDialog(clientFrame
								.getLaterLoadPanel().getLaterListener()
								.getPasswordFrame(), "原密码出错");
					} else if (string.equals("密码修改成功")) {
						JOptionPane.showMessageDialog(clientFrame
								.getLaterLoadPanel().getLaterListener()
								.getPasswordFrame(), "密码更改成功！");
						clientFrame.getLaterLoadPanel().getLaterListener()
								.getPasswordFrame().dispose();
					}
				} else if (qqPackage.getPackType().equals(PackType.post)) {
					// 公告
					String notice = (String) qqPackage.getData();
					clientFrame.getLaterLoadPanel().getTextArea3()
							.setText(notice);

				} else if (qqPackage.getPackType().equals(PackType.stopServer)) {
					// 服务器关闭
					interrupt();
					JOptionPane.showMessageDialog(clientFrame, "服务器关闭");
					System.exit(0);
					clientFrame.dispose();
					break;
				} else if (qqPackage.getPackType().equals(PackType.enforceDown)) {
					// 服务器强制下线
					interrupt();
					JOptionPane.showMessageDialog(clientFrame, "服务器强制您下线");
					System.exit(0);
					clientFrame.dispose();
					break;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(clientFrame, "与服务器连接中断");
				System.exit(0);
				break;
			}
		}
	}
}
