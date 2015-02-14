package frame;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;

import dao.bean.User;
import dao.imf.Userimp;

import panel.UpdateUserPanel;
import panel.UserPanel;
import tool.StaticTool;

public class UpdateUserDialog extends JDialog {

	private static final long serialVersionUID = -9041625326819248038L;
	Userimp userimp;
	User user;
	UserPanel userPanel;
	private UpdateUserPanel updateUserPanel = new UpdateUserPanel(this);

	public UpdateUserDialog(JFrame frame) {
		init();

		this.setTitle("修改用户对话框");
		this.setIconImage(StaticTool.imageIcon.getImage());
		this.setSize(new Dimension(320, 380));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setModal(true);
		this.setLocationRelativeTo(frame);
		this.setVisible(true);
	}

	private void init() {
		this.add(updateUserPanel);
	}

}
