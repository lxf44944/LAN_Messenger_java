package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import listener.AddUserListener;

import dao.bean.User;
import dao.imf.Userimp;

import frame.AddUserDialog;
import frame.ServerFrame;

public class AddUserPanel extends JPanel {

	private static final long serialVersionUID = 3590216045823216803L;
	private FlowLayout layout = null;
	private AddUserDialog userDialog;

	private ServerFrame serverFrame;

	private User user = new User();
	private Userimp userimp = new Userimp();
	private JLabel jLabelMessage = new JLabel("    ");

	public JLabel getjLabelMessage() {
		return jLabelMessage;
	}

	private JPasswordField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JComboBox textField5;
	private JTextField textField6;
	private JButton button2;
	private JButton button1;

	public AddUserPanel(AddUserDialog dialog) {
		this.userDialog = dialog;

		this.setLayout(new FlowLayout());
		init();

	}

	private void init() {
		setdate();
		layout = new FlowLayout();
		new BorderLayout();
		Border border1 = BorderFactory.createEmptyBorder(5, 60, 5, 5);

		jLabelMessage.setFont(new Font("楷书", Font.PLAIN, 12));
		jLabelMessage.setForeground(Color.red);

		JPanel panel1 = new JPanel();
		panel1.setLayout(layout);
		JTextField textField1 = new JTextField();
		JLabel jLabel1 = new JLabel("编        号： ");
		textField1.setPreferredSize(new Dimension(110, 20));
		user.setId(userimp.nextId());
		textField1.setText(user.getId());
		textField1.setEditable(false);
		panel1.add(jLabel1);
		panel1.add(textField1);

		JPanel panel2 = new JPanel();
		panel2.setLayout(layout);
		textField2 = new JPasswordField();
		JLabel jLabel2 = new JLabel("密        码： ");
		textField2.setPreferredSize(new Dimension(110, 20));

		panel2.add(jLabel2);
		panel2.add(textField2);

		JPanel panel3 = new JPanel();
		panel3.setLayout(layout);
		textField3 = new JTextField();
		JLabel jLabel3 = new JLabel("姓        名： ");
		textField3.setPreferredSize(new Dimension(110, 20));

		panel3.add(jLabel3);
		panel3.add(textField3);

		JPanel panel4 = new JPanel();
		panel4.setLayout(layout);
		textField4 = new JTextField();
		JLabel jLabel4 = new JLabel("年        龄： ");
		textField4.setPreferredSize(new Dimension(110, 20));

		panel4.add(jLabel4);
		panel4.add(textField4);

		JPanel panel5 = new JPanel();
		panel5.setLayout(layout);
		textField5 = new JComboBox();
		JLabel jLabel5 = new JLabel("性        别： ");
		textField5.setPreferredSize(new Dimension(110, 20));

		textField5.addItem("男");
		textField5.addItem("女");
		panel5.add(jLabel5);
		panel5.add(textField5);

		panel5.add(jLabel5);
		panel5.add(textField5);

		JPanel panel6 = new JPanel();
		panel6.setLayout(layout);
		textField6 = new JTextField();
		JLabel jLabel6 = new JLabel("地        址： ");
		textField6.setPreferredSize(new Dimension(110, 20));

		panel6.add(jLabel6);
		panel6.add(textField6);

		JPanel panel7 = new JPanel();
		panel6.setLayout(layout);
		JTextField textField7 = new JTextField();
		JLabel jLabel7 = new JLabel("在线状态： ");
		textField7.setPreferredSize(new Dimension(110, 20));

		textField7.setText(user.getIsonline());
		textField7.setEditable(false);
		panel7.add(jLabel7);
		panel7.add(textField7);

		JPanel panel8 = new JPanel();
		panel6.setLayout(layout);
		JTextField textField8 = new JTextField();
		JLabel jLabel8 = new JLabel("注册时间： ");
		textField8.setPreferredSize(new Dimension(140, 20));
		textField8.setText(user.getRegdate());
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

		AddUserListener addUserListener = new AddUserListener(this);
		button1.addActionListener(addUserListener);
		button2.addActionListener(addUserListener);

		this.add(jLabelMessage, BorderLayout.NORTH);
		this.add(Box.createHorizontalStrut(400));
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
		this.add(panel6);
		this.add(panel7);
		this.add(panel8);
		this.add(panelButton);
	}

	private void setdate() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		String strDate = year + "-" + month + "-" + day + " " + hour + ":"
				+ minute + ":" + second;

		user.setRegdate(strDate);
		user.setIsonline("离线");

	}

	public FlowLayout getLayout() {
		return layout;
	}

	public AddUserDialog getUserDialog() {
		return userDialog;
	}

	public ServerFrame getServerFrame() {
		return serverFrame;
	}

	public User getUser() {
		return user;
	}

	public Userimp getUserimp() {
		return userimp;
	}

	public JPasswordField getTextField2() {
		return textField2;
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
