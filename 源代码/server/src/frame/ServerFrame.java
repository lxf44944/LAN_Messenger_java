package frame;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import dao.imf.Userimp;

import panel.LogPanel;
import panel.ServerPanel;
import panel.UserPanel;
import thread.SReadThread;
import tool.StaticTool;

public class ServerFrame extends JFrame {

	private static final long serialVersionUID = 8259191195549378268L;
	ServerPanel serverPanel = new ServerPanel(this);
	UserPanel userPanel = new UserPanel(this);
	LogPanel logPanel = new LogPanel(this);
	private Map<String, SReadThread> readThreadMap = new Hashtable<String, SReadThread>();
	private JTabbedPane tabbedPane;
	private Userimp userimp = new Userimp();

	public ServerFrame() {
		// 标题面板
		tabbedPane = new JTabbedPane();

		// 指定标题及显示的面板
		tabbedPane.add("服务器管理", serverPanel);
		tabbedPane.add("用户管理", userPanel);
		tabbedPane.add("日志管理", logPanel);

		this.add(tabbedPane);
		this.setTitle("QQ服务端");
		this.setIconImage(StaticTool.imageIcon.getImage());
		this.setSize(new Dimension(600, 500));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				int i = JOptionPane.showConfirmDialog(ServerFrame.this,
						"确定要退出服务器吗？");
				if (i == 0) {
					userimp.isonline(null);
					System.exit(0);
				}
			}
		});
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	public ServerPanel getServerPanel() {
		return serverPanel;
	}

	public UserPanel getUserPanel() {
		return userPanel;
	}

	public LogPanel getLogPanel() {
		return logPanel;
	}

	public Map<String, SReadThread> getReadThreadMap() {
		return readThreadMap;
	}

}
