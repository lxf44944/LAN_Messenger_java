package frame;

import java.awt.Dimension;
import javax.swing.JFrame;

import panel.StartPanel;
import tool.StaticTool;

public class StartFrame extends JFrame {
	private static final long serialVersionUID = 3590216045823216803L;
	private StartPanel startPanel = new StartPanel(this);

	public StartFrame() {
		init();
		this.setIconImage(StaticTool.imageIcon.getImage());
		this.setTitle("·þÎñÆ÷Æô¶¯ÅäÖÃ");
		this.setSize(new Dimension(400, 340));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void init() {
		this.add(startPanel);
	}

	public static void main(String[] args) {
		new StartFrame();

	}
}
