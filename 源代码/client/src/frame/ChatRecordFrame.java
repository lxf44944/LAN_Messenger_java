package frame;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

import panel.ChatRecordPanel;
import tool.StaticTool;

public class ChatRecordFrame extends JFrame {

	private static final long serialVersionUID = 8259191195549378268L;
	LaterLoadFrame laterLoadFrame;

	public ChatRecordFrame(LaterLoadFrame laterLoadFrame) {
		this.laterLoadFrame = laterLoadFrame;
		ChatRecordPanel chatRecordPanel = new ChatRecordPanel(laterLoadFrame);
		this.add(chatRecordPanel);
		this.setTitle("QQÁÄÌì¼ÇÂ¼");
		this.setIconImage(StaticTool.imageIcon.getImage());
		this.setSize(new Dimension(400, 300));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ChatRecordFrame.this.dispose();
			}
		});
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

}
