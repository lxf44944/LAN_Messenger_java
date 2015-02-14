package frame;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import listener.LaterListener;

import panel.PasswordPanel;
import tool.StaticTool;

public class PasswordFrame extends JFrame {
	private static final long serialVersionUID = 3590216045823216803L;
	LaterListener laterListener;
	private PasswordPanel passwordPanel = new PasswordPanel(this);

	public PasswordFrame(LaterListener laterListener) {
		init();
		this.laterListener = laterListener;
		this.setIconImage(StaticTool.imageIcon.getImage());
		this.setTitle("ÐÞ¸ÄÃÜÂë");
		this.setSize(new Dimension(300, 210));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				PasswordFrame.this.dispose();
			}
		});
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void init() {
		this.add(passwordPanel);
	}

	public LaterListener getLaterListener() {
		return laterListener;
	}

}
