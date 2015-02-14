package frame;

import java.awt.Dimension;
import javax.swing.JFrame;

import panel.LoadPanel;
import tool.StaticTool;

public class LoadFrame extends JFrame {
	private static final long serialVersionUID = 3590216045823216803L;
	private LoadPanel loadPanel = new LoadPanel(this);

	public LoadFrame() {
		init();
		this.setIconImage(StaticTool.imageIcon.getImage());
		this.setTitle("用户登录界面");
		this.setSize(new Dimension(330, 250));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void init() {
		this.add(loadPanel);
	}

	public static void main(String[] args) {
		new LoadFrame();

	}
}
