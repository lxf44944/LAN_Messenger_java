package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import listener.UserListener;

import dao.imf.Userimp;

import frame.ServerFrame;

public class UserPanel extends JPanel {

	private static final long serialVersionUID = 3590216045823216803L;
	private FlowLayout layout = null;
	private ServerFrame serverFrame;
	public JTable jTable;
	private JButton buttonAlter;
	private JButton buttonClear;
	private JButton buttonDelete;
	private static Userimp userimp = new Userimp();
	private static DefaultTableModel defaultTableModel = new DefaultTableModel();
	private String sid = null;
	private String sname = null;
	private JPanel panel2;
	private JTextField textFieldNum;
	private JTextField textFieldName;
	private JButton button;
	private JLabel jLabelCount;
	private Box box1;
	private Box box2;
	private UserListener listioner;
	private JButton buttonAdd;
	private JButton buttonClearAll;

	private static String uName;
	private static String uAge;
	private static String uSex;
	private static String uIsonline;
	private static String uRegdate;
	private static String uAddress;
	private static String uId;

	private static int counts;

	public UserPanel(ServerFrame mainFrame) {

		this.serverFrame = mainFrame;
		this.setLayout(new FlowLayout());
		init();

	}

	private void init() {
		layout = new FlowLayout();
		new BorderLayout();
		box1 = new Box(BoxLayout.X_AXIS);
		box1.setPreferredSize(new Dimension(550, 25));
		box2 = new Box(BoxLayout.X_AXIS);
		box1.setPreferredSize(new Dimension(550, 25));

		JLabel jLabelNum = new JLabel("编号：");
		JLabel jLabelName = new JLabel("姓名：");

		button = new JButton("查询");
		button.setPreferredSize(new Dimension(50, 25));
		button.setMargin(new Insets(0, 2, 0, 2));

		buttonAdd = new JButton("添加用户");
		buttonAdd.setPreferredSize(new Dimension(70, 20));
		buttonAdd.setMargin(new Insets(0, 2, 0, 2));

		buttonAlter = new JButton("修改用户");
		buttonAlter.setPreferredSize(new Dimension(90, 25));
		buttonAlter.setMargin(new Insets(0, 2, 0, 2));

		buttonClear = new JButton("重置密码");
		buttonClear.setPreferredSize(new Dimension(90, 25));
		buttonClear.setMargin(new Insets(0, 2, 0, 2));

		buttonDelete = new JButton("删除用户");
		buttonDelete.setPreferredSize(new Dimension(90, 25));
		buttonDelete.setMargin(new Insets(0, 2, 0, 2));

		buttonClearAll = new JButton("重置所有密码");
		buttonClearAll.setPreferredSize(new Dimension(90, 25));
		buttonClearAll.setMargin(new Insets(0, 2, 0, 2));

		textFieldNum = new JTextField();
		textFieldNum.setPreferredSize(new Dimension(30, 10));
		textFieldName = new JTextField();
		textFieldName.setPreferredSize(new Dimension(50, 10));

		buttonAlter.setEnabled(false);
		buttonDelete.setEnabled(false);
		buttonClear.setEnabled(false);

		box1.add(jLabelNum);
		box1.add(textFieldNum);
		box1.add(jLabelName);
		box1.add(textFieldName);
		box1.add(button);
		box1.add(Box.createHorizontalStrut(40));

		box2.add(Box.createHorizontalStrut(80));
		box2.add(buttonAdd);
		box2.add(Box.createHorizontalStrut(10));
		box2.add(buttonAlter);
		box2.add(Box.createHorizontalStrut(10));
		box2.add(buttonDelete);
		box2.add(Box.createHorizontalStrut(10));
		box2.add(buttonClear);
		box2.add(Box.createHorizontalStrut(10));
		box2.add(buttonClearAll);

		panel2 = new JPanel();
		panel2.setLayout(layout);
		panel2.setPreferredSize(new Dimension(550, 370));

		// 边框
		Border borderTitle2 = BorderFactory.createTitledBorder("用户信息表");
		panel2.setBorder(borderTitle2);
		panel2.add(centerPanel());

		jLabelCount = new JLabel("总记录数：" + getCounts());
		jLabelCount.setForeground(Color.BLUE);
		box1.add(jLabelCount);

		this.add(box1);
		this.add(panel2);
		this.add(box2);
		listioner = new UserListener(serverFrame, this);

		button.addActionListener(listioner);

		buttonAdd.addActionListener(listioner);

		buttonDelete.addActionListener(listioner);

		buttonAlter.addActionListener(listioner);

		buttonClear.addActionListener(listioner);

		buttonClearAll.addActionListener(listioner);

	}

	public JPanel centerPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(520, 330));

		// 3加入数据
		defaultTableModel.setDataVector(userimp.selectUser(sid, sname, null),
				userimp.head());

		// 4设置模型
		jTable = new JTable(defaultTableModel) {
			private static final long serialVersionUID = -4243551819776364742L;

			// 设置表格内数据不可编辑
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		// 给选中行添加监听
		jTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting()) {

							if (jTable.getSelectedRow() != -1) {

								uId = (String) jTable.getValueAt(
										jTable.getSelectedRow(), 0);
								uName = (String) jTable.getValueAt(
										jTable.getSelectedRow(), 1);
								uAge = (String) jTable.getValueAt(
										jTable.getSelectedRow(), 3);
								uSex = (String) jTable.getValueAt(
										jTable.getSelectedRow(), 2);
								uIsonline = (String) jTable.getValueAt(
										jTable.getSelectedRow(), 4);
								uRegdate = (String) jTable.getValueAt(
										jTable.getSelectedRow(), 5);
								uAddress = (String) jTable.getValueAt(
										jTable.getSelectedRow(), 6);

								String sIsonline = (String) jTable.getValueAt(
										jTable.getSelectedRow(), 4);
								if (sIsonline.equals("离线")) {
									buttonAlter.setEnabled(true);

									buttonDelete.setEnabled(true);
									buttonClear.setEnabled(true);
								}

							} else {
								buttonAlter.setEnabled(false);
								buttonDelete.setEnabled(false);
								buttonClear.setEnabled(false);
							}

						}

					}
				});
		// 设置表头不可拖动
		jTable.getTableHeader().setReorderingAllowed(false);
		// 设置行单选
		jTable.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

		// 要显示表头一定要把表格加入到滚动面板
		JScrollPane jScrollPane = new JScrollPane(jTable);
		panel.add(jScrollPane);

		return panel;

	}

	/**
	 * 刷新表格
	 */
	public static void refresh(String rid, String rname) {

		Vector<Vector<String>> vecData = userimp.selectUser(rid, rname, null);
		defaultTableModel.setDataVector(vecData, userimp.head());
		defaultTableModel.fireTableDataChanged();

	}

	public static void setCounts(int counts) {
		UserPanel.counts = counts;
	}

	public static int getCounts() {
		return counts;
	}

	public static String getuName() {
		return uName;
	}

	public static String getuAge() {
		return uAge;
	}

	public static String getuSex() {
		return uSex;
	}

	public static String getuIsonline() {
		return uIsonline;
	}

	public static String getuRegdate() {
		return uRegdate;
	}

	public static String getuAddress() {
		return uAddress;
	}

	public static String getuId() {
		return uId;
	}

	public static Userimp getUserimp() {
		return userimp;
	}

	public JTable getjTable() {
		return jTable;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public void setTextFieldNum(JTextField textFieldNum) {
		this.textFieldNum = textFieldNum;
	}

	public void setTextFieldName(JTextField textFieldName) {
		this.textFieldName = textFieldName;
	}

	public String getSid() {
		return sid;
	}

	public String getSname() {
		return sname;
	}

	public JTextField getTextFieldNum() {
		return textFieldNum;
	}

	public JTextField getTextFieldName() {
		return textFieldName;
	}

	public JLabel getjLabelCount() {
		return jLabelCount;
	}

	public Box getBox1() {
		return box1;
	}

	public Box getBox2() {
		return box2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JButton getButtonAlter() {
		return buttonAlter;
	}

	public JButton getButtonClear() {
		return buttonClear;
	}

	public JButton getButtonDelete() {
		return buttonDelete;
	}

	public static DefaultTableModel getDefaultTableModel() {
		return defaultTableModel;
	}

	public JButton getButton() {
		return button;
	}

	public JButton getButtonAdd() {
		return buttonAdd;
	}

	public JButton getButtonClearAll() {
		return buttonClearAll;
	}

}
