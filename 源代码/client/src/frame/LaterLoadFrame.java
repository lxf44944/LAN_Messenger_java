package frame;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import panel.LaterLoadPanel;
import pub.PackType;
import pub.QQPackage;
import tool.StaticTool;

public class LaterLoadFrame extends JFrame {
	private static final long serialVersionUID = 3590216045823216803L;
	private LaterLoadPanel laterLoadPanel = new LaterLoadPanel(this);
	Socket socket;
	ObjectOutputStream objectOutputStream;
	ObjectInputStream objectInputStream;

	public LaterLoadFrame(Socket socket, ObjectOutputStream objectOutputStream,
			ObjectInputStream objectInputStream) {
		this.socket = socket;
		this.objectOutputStream = objectOutputStream;
		this.objectInputStream = objectInputStream;
		init();
		this.setIconImage(StaticTool.imageIcon.getImage());
		this.setSize(new Dimension(580, 500));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(LaterLoadFrame.this,
						"确定要退出QQ吗？");
				if (i == 0) {
					QQPackage qqPackage = new QQPackage();
					qqPackage.setPackType(PackType.userDownLine);
					qqPackage.setFrom(LaterLoadFrame.this.getTitle());
					try {
						ObjectOutputStream objectOutputStream = LaterLoadFrame.this
								.getObjectOutputStream();
						objectOutputStream.writeObject(qqPackage);
						objectOutputStream.flush();
						System.exit(0);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void init() {
		this.add(laterLoadPanel);
	}

	public Socket getSocket() {
		return socket;
	}

	public ObjectOutputStream getObjectOutputStream() {
		return objectOutputStream;
	}

	public ObjectInputStream getObjectInputStream() {
		return objectInputStream;
	}

	public LaterLoadPanel getLaterLoadPanel() {
		return laterLoadPanel;
	}

}
