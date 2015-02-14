package panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

import listener.PasswordListener;

import frame.PasswordFrame;

public class PasswordPanel extends JPanel {

	private static final long serialVersionUID = 3590216045823216803L;
	private FlowLayout layout = null;
	PasswordFrame passwordMainFrame;
	private JButton button1;
	private JButton button2;
	private JPasswordField textField3;
	private JPasswordField textField2;
	private JPasswordField textField1;

	public PasswordPanel(PasswordFrame mainFrame) {
		this.passwordMainFrame = mainFrame;
		this.setLayout(new FlowLayout());
		init();
	}

	private void init() {
		layout = new FlowLayout();
		new BorderLayout();
		Border border1 = BorderFactory.createEmptyBorder(5, 60, 5, 5);

		JPanel panel1 = new JPanel();
		panel1.setLayout(layout);
		textField1 = new JPasswordField();
		JLabel jLabel1 = new JLabel("旧密码    ");
		textField1.setPreferredSize(new Dimension(180, 25));
		panel1.add(jLabel1);
		panel1.add(textField1);
		panel1.setPreferredSize(new Dimension(330, 40));

		JPanel panel2 = new JPanel();
		panel2.setLayout(layout);
		textField2 = new JPasswordField();
		JLabel jLabel2 = new JLabel("新密码    ");
		textField2.setPreferredSize(new Dimension(180, 25));
		panel2.add(jLabel2);
		panel2.add(textField2);
		panel2.setPreferredSize(new Dimension(330, 40));

		JPanel panel3 = new JPanel();
		panel3.setLayout(layout);
		textField3 = new JPasswordField();
		JLabel jLabel3 = new JLabel("重复密码");
		textField3.setPreferredSize(new Dimension(180, 25));
		panel3.add(jLabel3);
		panel3.add(textField3);
		panel3.setPreferredSize(new Dimension(330, 30));

		JPanel panel4 = new JPanel();
		Box box = new Box(BoxLayout.X_AXIS);
		button1 = new JButton("保存");
		button2 = new JButton("取消");
		box.add(button1);
		box.add(Box.createHorizontalStrut(40));
		box.add(button2);
		box.setBorder(border1);
		panel4.setLayout(layout);
		panel4.add(box, BorderLayout.CENTER);

		PasswordListener passwordListener = new PasswordListener(
				passwordMainFrame, this);
		button1.addActionListener(passwordListener);
		button2.addActionListener(passwordListener);

		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);

	}

	public PasswordFrame getPasswordMainFrame() {
		return passwordMainFrame;
	}

	public JButton getButton1() {
		return button1;
	}

	public JButton getButton2() {
		return button2;
	}

	public JPasswordField getTextField3() {
		return textField3;
	}

	public JPasswordField getTextField2() {
		return textField2;
	}

	public JPasswordField getTextField1() {
		return textField1;
	}

}
