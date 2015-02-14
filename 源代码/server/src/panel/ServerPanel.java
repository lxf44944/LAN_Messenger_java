package panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import listener.ServerListener;

import frame.ServerFrame;

import tool.StaticTool;

public class ServerPanel extends JPanel {

	private static final long serialVersionUID = 3590216045823216803L;
	private FlowLayout layout = null;
	ServerFrame serverFrame;
	private JButton buttonSend;
	private JButton buttonStart;
	private JButton buttonStop;
	private JButton buttonForce;
	private JLabel label;
	private JTextArea textArea4;
	private JPanel panel2;
	private JTextArea textArea5;

	public ServerPanel(ServerFrame mainFrame) {
		this.serverFrame = mainFrame;
		this.setLayout(new GridLayout(2, 1));
		init();
	}

	private void init() {
		layout = new FlowLayout();
		panel2 = new JPanel();
		panel2.setLayout(layout);
		panel2.setPreferredSize(new Dimension(550, 370));
		Border borderTitle2 = BorderFactory.createTitledBorder("在线用户列表");
		panel2.setBorder(borderTitle2);

		JPanel panelA = new JPanel();
		panelA.setLayout(new GridLayout(2, 1));
		JPanel panelB = new JPanel();
		panelB.setLayout(new GridLayout(1, 2));

		label = new JLabel(StaticTool.imageIcon);

		buttonSend = new JButton("公告发送");
		buttonSend.setPreferredSize(new Dimension(70, 20));
		buttonSend.setMargin(new Insets(0, 2, 0, 2));
		buttonSend.setEnabled(false);

		buttonStart = new JButton("启动通讯服务");
		buttonStart.setPreferredSize(new Dimension(90, 25));
		buttonStart.setMargin(new Insets(0, 2, 0, 2));

		buttonStop = new JButton("停止通讯服务");
		buttonStop.setPreferredSize(new Dimension(90, 25));
		buttonStop.setMargin(new Insets(0, 2, 0, 2));
		buttonStop.setEnabled(false);

		buttonForce = new JButton("强制用户下线");
		buttonForce.setPreferredSize(new Dimension(90, 25));
		buttonForce.setMargin(new Insets(0, 2, 0, 2));
		buttonForce.setEnabled(false);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1, 2));
		panel3.setPreferredSize(new Dimension(300, 220));
		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(buttonStart);
		box.add(buttonStop);
		box.add(Box.createHorizontalStrut(10));
		box.add(buttonForce);

		JPanel panel = new JPanel();
		panel.setLayout(layout);
		panel.setPreferredSize(new Dimension(150, 220));
		panel.add(label);
		// 边框
		Border borderTitle3 = BorderFactory.createTitledBorder("服务器管理");
		panel3.setBorder(borderTitle3);
		panel3.add(panel);
		panel3.add(box);

		JPanel panel4 = new JPanel();
		panel4.setLayout(layout);
		panel4.setPreferredSize(new Dimension(200, 50));
		textArea4 = new JTextArea(4, 23);
		textArea4.setPreferredSize(new Dimension(270, 1000));
		textArea4.setLineWrap(true);
		textArea4.setSelectionStart(textArea4.getText().length());
		textArea4.setEditable(false);
		JScrollPane jScrollPane = new JScrollPane(textArea4);
		// 边框
		Border borderTitle4 = BorderFactory.createTitledBorder("通讯信息提示");
		panel4.setBorder(borderTitle4);
		panel4.add(jScrollPane);

		JPanel panel5 = new JPanel();
		panel5.setLayout(layout);
		panel5.setPreferredSize(new Dimension(200, 50));
		textArea5 = new JTextArea();
		textArea5.setPreferredSize(new Dimension(270, 50));
		textArea5.setLineWrap(true);
		// 边框
		Border borderTitle5 = BorderFactory.createTitledBorder("公告发送");
		panel5.setBorder(borderTitle5);
		Box box1 = new Box(BoxLayout.X_AXIS);
		box1.setPreferredSize(new Dimension(270, 25));
		box1.add(Box.createHorizontalStrut(205));
		box1.add(buttonSend);
		panel5.add(textArea5);
		panel5.add(box1);

		panelA.add(panel4);
		panelA.add(panel5);
		panelB.add(panelA);
		panelB.add(panel3);
		this.add(panel2);
		this.add(panelB);

		ServerListener serverListener = new ServerListener(this, serverFrame);
		buttonStart.addActionListener(serverListener);
		buttonStop.addActionListener(serverListener);
		buttonSend.addActionListener(serverListener);
		buttonForce.addActionListener(serverListener);

	}

	public JButton getButtonSend() {
		return buttonSend;
	}

	public JButton getButtonStart() {
		return buttonStart;
	}

	public JButton getButtonStop() {
		return buttonStop;
	}

	public JButton getButtonForce() {
		return buttonForce;
	}

	public JLabel getLabel() {
		return label;
	}

	public JTextArea getTextArea4() {
		return textArea4;
	}

	public JTextArea getTextArea5() {
		return textArea5;
	}

	public JPanel getPanel2() {
		return panel2;
	}

}
