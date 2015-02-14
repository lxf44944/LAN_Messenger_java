package panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

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

import listener.StartListener;

import config.Config;

import frame.StartFrame;

public class StartPanel extends JPanel {

	private static final long serialVersionUID = 3590216045823216803L;
	private FlowLayout layout = null;
	StartFrame startFrame;
	private JTextField textField2;
	private JTextField textField3;
	private JPasswordField textField4;
	private JTextField textField5;
	private JTextField textField6;
	Config config = new Config();
	private JButton button2;
	private JButton button1;

	public StartPanel(StartFrame mainFrame) {
		this.startFrame = mainFrame;
		this.setLayout(new FlowLayout());
		init();
	}

	private void init() {
		layout = new FlowLayout();
		new BorderLayout();
		Border border1 = BorderFactory.createEmptyBorder(5, 60, 5, 5);

		JPanel panel1 = new JPanel();
		panel1.setLayout(layout);
		JComboBox textField1 = new JComboBox();
		textField1.addItem("ORACLE");
		textField1.addItem("PL");
		textField1.addItem("SQL");
		JLabel jLabel1 = new JLabel("连接方式：");
		textField1.setPreferredSize(new Dimension(300, 22));
		panel1.add(jLabel1);
		panel1.add(textField1);

		JPanel panel2 = new JPanel();
		panel2.setLayout(layout);
		textField2 = new JTextField();
		JLabel jLabel2 = new JLabel("连接url  ： ");
		textField2.setPreferredSize(new Dimension(300, 25));
		panel2.add(jLabel2);
		panel2.add(textField2);

		JPanel panel3 = new JPanel();
		panel3.setLayout(layout);
		textField3 = new JTextField();
		JLabel jLabel3 = new JLabel("用户名  ： ");
		textField3.setPreferredSize(new Dimension(300, 25));
		panel3.add(jLabel3);
		panel3.add(textField3);

		JPanel panel4 = new JPanel();
		panel4.setLayout(layout);
		textField4 = new JPasswordField();
		textField4.setEchoChar('●');
		JLabel jLabel4 = new JLabel("密    码  ： ");
		textField4.setPreferredSize(new Dimension(300, 25));
		panel4.add(jLabel4);
		panel4.add(textField4);

		JPanel panel5 = new JPanel();
		panel5.setLayout(layout);
		textField5 = new JTextField();
		JLabel jLabel5 = new JLabel("驱    动  ： ");
		textField5.setPreferredSize(new Dimension(300, 25));
		panel5.add(jLabel5);
		panel5.add(textField5);

		JPanel panel6 = new JPanel();
		panel6.setLayout(layout);
		textField6 = new JTextField();
		JLabel jLabel6 = new JLabel("端口号  ： ");
		textField6.setPreferredSize(new Dimension(300, 25));
		panel6.add(jLabel6);
		panel6.add(textField6);

		JPanel panelButton = new JPanel();
		Box box = new Box(BoxLayout.X_AXIS);
		button1 = new JButton("测试并保存");
		button1.setPreferredSize(new Dimension(100, 50));
		button2 = new JButton("进入服务器");
		button2.setPreferredSize(new Dimension(100, 50));
		button2.setEnabled(false);
		// button2.setMargin(new Insets(0, 2, 0, 2));
		box.add(button1);
		box.add(Box.createHorizontalStrut(70));
		box.add(button2);
		box.setBorder(border1);
		panelButton.setLayout(layout);
		panelButton.add(box, BorderLayout.CENTER);

		// Config

		textField2.setText(config.getValue("连接url",
				"jdbc:oracle:thin:@127.0.0.1:1521:orcl"));
		textField3.setText(config.getValue("用户名", "king"));
		textField4.setText(config.getValue("密码", "king"));
		textField5.setText(config.getValue("驱动",
				"oracle.jdbc.driver.OracleDriver"));
		textField6.setText(config.getValue("端口号", "8088"));

		StartListener startListener = new StartListener(this);

		button2.addActionListener(startListener);
		button1.addActionListener(startListener);

		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
		this.add(panel6);
		this.add(panelButton);

	}

	public StartFrame getStartFrame() {
		return startFrame;
	}

	public Config getConfig() {
		return config;
	}

	public JButton getButton2() {
		return button2;
	}

	public JButton getButton1() {
		return button1;
	}

	public JTextField getTextField2() {
		return textField2;
	}

	public JTextField getTextField3() {
		return textField3;
	}

	public JPasswordField getTextField4() {
		return textField4;
	}

	public JTextField getTextField5() {
		return textField5;
	}

	public JTextField getTextField6() {
		return textField6;
	}
}
