package frame;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;

import dao.bean.User;
import dao.imf.Userimp;

import panel.AddUserPanel;
import panel.UserPanel;
import tool.StaticTool;

public class AddUserDialog extends JDialog {
	private static final long serialVersionUID = 3590216045823216803L;
	Userimp userimp;
	User user;
	UserPanel userPanel;
	private AddUserPanel addUserPanel = new AddUserPanel(this);

	public AddUserDialog(JFrame frame) {
		init();
		this.setTitle("添加用户对话框");
		this.setIconImage(StaticTool.imageIcon.getImage());
		this.setSize(new Dimension(320, 400));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setModal(true);
		this.setLocationRelativeTo(frame);
		this.setVisible(true);
	}

	private void init() {
		this.add(addUserPanel);
	}

}
