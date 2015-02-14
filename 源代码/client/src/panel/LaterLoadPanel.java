package panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tool.StaticTool;

import listener.LaterListener;

import frame.LaterLoadFrame;

public class LaterLoadPanel extends JPanel {

	private static final long serialVersionUID = 3590216045823216803L;
	private FlowLayout layout = null;
	private BorderLayout borderLayout = null;

	private LaterLoadFrame laterLoadFrame;
	private JButton button;
	private JTextArea textArea1;
	private JTextArea textArea2;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JTextArea textArea3;
	private LaterListener laterListener;
	private JPanel panel;
	private DefaultListModel defaultListModel;
	private JList list;
	private int row;
	private String name;

	private JPanel panel4;
	private JLabel jLabel1;

	public LaterLoadPanel(LaterLoadFrame mainFrame) {
		this.laterLoadFrame = mainFrame;
		this.setLayout(new GridLayout(1, 2));
		init();
	}

	private void init() {
		layout = new FlowLayout();
		borderLayout = new BorderLayout();
		Border border1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		JPanel panelA = new JPanel();
		JPanel panelB = new JPanel();
		panelA.setLayout(layout);
		panelB.setLayout(layout);
		jLabel1 = new JLabel();
		JPanel panel = new JPanel();
		Box box1 = new Box(BoxLayout.X_AXIS);
		box1.setPreferredSize(new Dimension(280, 50));
		button = new JButton("修改密码");
		button.setPreferredSize(new Dimension(100, 50));
		button.setMargin(new Insets(0, 2, 0, 2));
		box1.add(button, BorderLayout.WEST);
		box1.add(jLabel1, BorderLayout.EAST);
		box1.setBorder(border1);
		panel.setLayout(layout);
		panel.add(box1, BorderLayout.WEST);

		JPanel panel1 = new JPanel();
		panel1.setLayout(layout);
		panel1.setPreferredSize(new Dimension(280, 220));
		textArea1 = new JTextArea("", 10, 23);
		textArea1.setPreferredSize(new Dimension(260, 1000));
		textArea1.setLineWrap(true);
		textArea1.setSelectionStart(textArea1.getText().length());
		textArea1.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea1);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// 边框
		Border borderTitle1 = BorderFactory.createTitledBorder("当前聊天记录");
		panel1.setBorder(borderTitle1);
		panel1.add(scrollPane);

		JPanel panel2 = new JPanel();
		panel2.setLayout(layout);
		panel2.setPreferredSize(new Dimension(280, 110));

		textArea2 = new JTextArea("", 4, 23);
		JScrollPane scrollPane2 = new JScrollPane(textArea2);
		textArea2.setPreferredSize(new Dimension(260, 2000));

		textArea2.setLineWrap(true);
		scrollPane2
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// 边框
		Border borderTitle2 = BorderFactory.createTitledBorder("输入信息");
		panel2.setBorder(borderTitle2);
		panel2.add(scrollPane2);

		JPanel panelButton = new JPanel();
		Box box2 = new Box(BoxLayout.X_AXIS);
		button1 = new JButton("聊天记录");
		button1.setPreferredSize(new Dimension(60, 50));
		button1.setMargin(new Insets(0, 2, 0, 2));
		button2 = new JButton("发送");
		button2.setPreferredSize(new Dimension(70, 50));
		button2.setMargin(new Insets(0, 2, 0, 2));
		button3 = new JButton("关闭");
		button2.setPreferredSize(new Dimension(70, 50));
		button2.setEnabled(false);
		button3.setMargin(new Insets(0, 2, 0, 2));
		box2.add(button1, BorderLayout.WEST);
		box2.add(Box.createHorizontalStrut(100));
		box2.add(button2);
		box2.add(Box.createHorizontalStrut(5));
		box2.add(button3);
		box2.setBorder(border1);
		panelButton.setLayout(borderLayout);
		panelButton.add(box2, BorderLayout.WEST);

		JPanel panel3 = new JPanel();
		panel3.setLayout(layout);
		panel3.setPreferredSize(new Dimension(180, 110));
		textArea3 = new JTextArea();
		textArea3.setPreferredSize(new Dimension(180, 110));
		textArea3.setEditable(false);
		panel3.add(textArea3);
		// 标题面板
		JTabbedPane tabbedPane = new JTabbedPane();
		// 指定标题及显示的面板
		tabbedPane.add("公告信息", panel3);

		panel4 = new JPanel(new BorderLayout());
		panel4.setPreferredSize(new Dimension(180, 280));
		Border onlineBorder = BorderFactory.createTitledBorder("在线用户列表");
		panel4.setBorder(onlineBorder);

		list = new JList();
		list.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		list.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent e) {
						row = list.getSelectedIndex();
						if (row != -1) {
							name = (String) defaultListModel.getElementAt(row);
							jLabel1.setText("      		To:    " + name);
							button2.setEnabled(true);
						}
					}
				});

		JScrollPane onlineScrollPane = new JScrollPane(list);
		panel4.add(onlineScrollPane, BorderLayout.CENTER);

		panelA.add(panel);
		panelA.add(panel1);
		panelA.add(panel2);
		panelA.add(panelButton);
		panelB.add(tabbedPane);
		panelB.add(panel4);
		this.add(panelA);
		this.add(panelB);

		laterListener = new LaterListener(this, laterLoadFrame);
		button.addActionListener(laterListener);
		button1.addActionListener(laterListener);
		button2.addActionListener(laterListener);
		button3.addActionListener(laterListener);

	}

	public JPanel CenterPanel() {

		return panel;
	}

	public void refresh(Vector<String> onlineUserList) {
		defaultListModel = new DefaultListModel();
		defaultListModel.addElement("所有人");
		for (int i = 0; i < onlineUserList.size(); i++) {
			defaultListModel.addElement(onlineUserList.get(i));
		}
		list.setModel(defaultListModel);
	}

	/**
	 * 写日志
	 */
	public void writeLog(String message, String sid) {
		String fileName = "./log/" + sid + "/" + StaticTool.SystemDate()
				+ ".log";
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		File file = new File(fileName);
		if (!file.getParentFile().exists()) {
			@SuppressWarnings("static-access")
			File path = new File(file.getAbsolutePath().substring(0,
					file.getAbsolutePath().lastIndexOf(file.separator)));
			path.mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			fileWriter = new FileWriter(fileName, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(message + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedWriter.flush();
				fileWriter.close();
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public JButton getButton() {
		return button;
	}

	public JTextArea getTextArea1() {
		return textArea1;
	}

	public JTextArea getTextArea2() {
		return textArea2;
	}

	public JButton getButton1() {
		return button1;
	}

	public JButton getButton2() {
		return button2;
	}

	public JButton getButton3() {
		return button3;
	}

	public JTextArea getTextArea3() {
		return textArea3;
	}

	public LaterListener getLaterListener() {
		return laterListener;
	}

	public LaterLoadFrame getLaterLoadFrame() {
		return laterLoadFrame;
	}

	public String getName() {
		return name;
	}

	public JPanel getPanel() {
		return panel;
	}

	public DefaultListModel getDefaultListModel() {
		return defaultListModel;
	}

	public JList getList() {
		return list;
	}

	public int getRow() {
		return row;
	}

}
