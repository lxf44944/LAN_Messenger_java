package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import listener.UpdateListener;

import dao.bean.User;
import dao.imf.Userimp;

import frame.ServerFrame;
import frame.UpdateUserDialog;

public class UpdateUserPanel extends JPanel {

	private static final long serialVersionUID = 3590216045823216803L;
	private FlowLayout layout = null;
	private UpdateUserDialog updateUserDialog;

	private ServerFrame serverFrame;
	private UserPanel userPanel;

	private User user = new User();
	private Userimp userimp = new Userimp();
	private JLabel jLabelMessage = new JLabel("    ");

	public JLabel getjLabelMessage() {
		return jLabelMessage;
	}

	private JTextField textField3;
	private JTextField textField4;
	private JComboBox textField5;
	private JTextField textField6;
	private JButton button2;
	private JButton button1;
	private JTextField textField1;

	public UpdateUserPanel(UpdateUserDialog updateUserDialog) {
		this.updateUserDialog = updateUserDialog;
		this.setLayout(new FlowLayout());
		init();

	}

	@SuppressWarnings("static-access")
	private void init() {

		layout = new FlowLayout();
		new BorderLayout();
		Border border1 = BorderFactory.createEmptyBorder(5, 60, 5, 5);

		jLabelMessage.setFont(new Font("楷书", Font.PLAIN, 12));
		jLabelMessage.setForeground(Color.red);

		JPanel panel1 = new JPanel();
		panel1.setLayout(layout);
		textField1 = new JTextField();
		JLabel jLabel1 = new JLabel("编        号： ");
		textField1.setPreferredSize(new Dimension(110, 20));
		textField1.setText(userPanel.getuId());
		textField1.setEditable(false);
		panel1.add(jLabel1);
		panel1.add(textField1);

		JPanel panel3 = new JPanel();
		panel3.setLayout(layout);
		textField3 = new JTextField();
		JLabel jLabel3 = new JLabel("姓        名： ");
		textField3.setPreferredSize(new Dimension(110, 20));
		textField3.setText(userPanel.getuName());
		panel3.add(jLabel3);
		panel3.add(textField3);

		JPanel panel4 = new JPanel();
		panel4.setLayout(layout);
		textField4 = new JTextField();
		JLabel jLabel4 = new JLabel("年        龄： ");
		textField4.setPreferredSize(new Dimension(110, 20));
		textField4.setText(userPanel.getuAge());
		panel4.add(jLabel4);
		panel4.add(textField4);

		JPanel panel5 = new JPanel();
		panel5.setLayout(layout);
		textField5 = new JComboBox();
		JLabel jLabel5 = new JLabel("性        别： ");
		textField5.setPreferredSize(new Dimension(110, 20));

		if (userPanel.getuSex().equals("男")) {
			textField5.addItem("男");
			textField5.addItem("女");
		} else {
			textField5.addItem("女");
			textField5.addItem("男");
		}

		panel5.add(jLabel5);
		panel5.add(textField5);

		panel5.add(jLabel5);
		panel5.add(textField5);

		JPanel panel6 = new JPanel();
		panel6.setLayout(layout);
		textField6 = new JTextField();
		JLabel jLabel6 = new JLabel("地        址： ");
		textField6.setPreferredSize(new Dimension(110, 20));
		textField6.setText(userPanel.getuAddress());
		panel6.add(jLabel6);
		panel6.add(textField6);

		JPanel panel7 = new JPanel();
		panel6.setLayout(layout);
		JTextField textField7 = new JTextField();
		JLabel jLabel7 = new JLabel("在线状态： ");
		textField7.setPreferredSize(new Dimension(110, 20));
		textField7.setText(userPanel.getuIsonline());
		textField7.setEditable(false);
		panel7.add(jLabel7);
		panel7.add(textField7);

		JPanel panel8 = new JPanel();
		panel6.setLayout(layout);
		JTextField textField8 = new JTextField();
		JLabel jLabel8 = new JLabel("注册时间： ");
		textField8.setPreferredSize(new Dimension(140, 20));
		textField8.setText(userPanel.getuRegdate());
		textField8.setEditable(false);
		panel8.add(jLabel8);
		panel8.add(textField8);

		JPanel panelButton = new JPanel();
		Box box = new Box(BoxLayout.X_AXIS);
		button1 = new JButton("保存");
		button1.setPreferredSize(new Dimension(100, 50));
		button2 = new JButton("取消");
		button2.setPreferredSize(new Dimension(100, 50));
		box.add(button1);
		box.add(Box.createHorizontalStrut(70));
		box.add(button2);
		box.setBorder(border1);
		panelButton.setLayout(layout);
		panelButton.add(box, BorderLayout.CENTER);

		UpdateListener updateListener = new UpdateListener(this);
		button1.addActionListener(updateListener);
		button2.addActionListener(updateListener);

		this.add(jLabelMessage, BorderLayout.NORTH);
		this.add(Box.createHorizontalStrut(400));
		this.add(panel1);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
		this.add(panel6);
		this.add(panel7);
		this.add(panel8);
		this.add(panelButton);

	}

	public UpdateUserDialog getUpdateUserDialog() {
		return updateUserDialog;
	}

	public ServerFrame getServerFrame() {
		return serverFrame;
	}

	public UserPanel getUserPanel() {
		return userPanel;
	}

	public User getUser() {
		return user;
	}

	public Userimp getUserimp() {
		return userimp;
	}

	public JTextField getTextField3() {
		return textField3;
	}

	public JTextField getTextField4() {
		return textField4;
	}

	public JComboBox getTextField5() {
		return textField5;
	}

	public JTextField getTextField6() {
		return textField6;
	}

	public JButton getButton2() {
		return button2;
	}

	public JButton getButton1() {
		return button1;
	}

}
