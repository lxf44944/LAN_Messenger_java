package panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import tool.StaticTool;

import frame.ServerFrame;

public class LogPanel extends JPanel {

	private static final long serialVersionUID = 3590216045823216803L;
	ServerFrame serverFrame;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private boolean isOk;
	private File file;

	private JLabel yearLabel, monthLabel, dateLabel;
	private JButton seekButton;
	private JTextArea historyTextArea;

	private JComboBox yearComboBox, monthComboBox, dateComboBox;

	public LogPanel(ServerFrame mainFrame) {
		this.serverFrame = mainFrame;
		this.setLayout(new FlowLayout());
		init();
	}

	private void init() {
		JPanel selectPanel = new JPanel();
		Border selectBorder = BorderFactory.createTitledBorder("日志日期选择");
		selectPanel.setBorder(selectBorder);
		selectPanel.setPreferredSize(new Dimension(550, 60));
		String[] year = { "2012", "2013", "2014", "2015", "2016", "2017",
				"2018" };
		yearComboBox = new JComboBox(year);
		yearComboBox.setPreferredSize(new Dimension(100, 20));
		yearLabel = new JLabel("年");
		String[] month = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12" };
		monthComboBox = new JComboBox(month);
		monthComboBox.setPreferredSize(new Dimension(70, 20));
		monthLabel = new JLabel("月");
		String[] date = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
				"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
				"31" };
		dateComboBox = new JComboBox(date);
		dateComboBox.setPreferredSize(new Dimension(70, 20));
		dateLabel = new JLabel("日");
		seekButton = new JButton("查询");
		seekButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String year = yearComboBox.getSelectedItem().toString();
				String month = monthComboBox.getSelectedItem().toString();
				String date = dateComboBox.getSelectedItem().toString();
				String all_string = year + "-" + month + "-" + date;
				System.out.println("./log" + "/" + all_string + ".log");
				File file = new File("./log" + "/"

				+ all_string + ".log");
				if (file.exists()) {
					historyTextArea.setText("");
					FileReader fileReader = null;
					BufferedReader bufferedReader = null;
					try {
						fileReader = new FileReader(file);
						bufferedReader = new BufferedReader(fileReader);
						String read = bufferedReader.readLine();
						while (read != null) {
							historyTextArea.append(read + "\n");
							read = bufferedReader.readLine();
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					} finally {
						try {
							fileReader.close();
							bufferedReader.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} else {
					historyTextArea.setText("今天无聊天记录");
				}

			}
		});
		yearComboBox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				String month = monthComboBox.getSelectedItem().toString();
				if (month.equals("2")) {
					dateComboBox.removeAllItems();
					if (isNunYear(yearComboBox.getSelectedItem().toString())) {
						for (int i = 1; i <= 29; i++) {
							dateComboBox.addItem(i);
						}
					} else {
						for (int i = 1; i <= 28; i++) {
							dateComboBox.addItem(i);
						}
					}
				}

			}
		});
		monthComboBox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				dateComboBox.removeAllItems();
				int month = Integer.parseInt(monthComboBox.getSelectedItem()
						.toString());
				switch (month) {
				case 1:
					thirtyOneDate();
					break;
				case 2:
					if (isNunYear(yearComboBox.getSelectedItem().toString())) {
						for (int i = 1; i <= 29; i++) {
							dateComboBox.addItem(i);
						}
					} else {
						for (int i = 1; i <= 28; i++) {
							dateComboBox.addItem(i);
						}
					}
					break;
				case 3:
					thirtyOneDate();
					break;
				case 4:
					thirtyDate();
					break;
				case 5:
					thirtyOneDate();
					break;
				case 6:
					thirtyDate();
					break;
				case 7:
					thirtyOneDate();
					break;
				case 8:
					thirtyOneDate();
					break;
				case 9:
					thirtyDate();
					break;
				case 10:
					thirtyOneDate();
					break;
				case 11:
					thirtyDate();
					break;
				default:
					thirtyOneDate();
					break;
				}

			}
		});

		selectPanel.add(yearComboBox);
		selectPanel.add(yearLabel);
		selectPanel.add(monthComboBox);
		selectPanel.add(monthLabel);
		selectPanel.add(dateComboBox);
		selectPanel.add(dateLabel);
		selectPanel.add(seekButton);
		JPanel historyPanel = new JPanel();
		Border historyBorder = BorderFactory.createTitledBorder("日志日期选择");
		historyPanel.setBorder(historyBorder);
		historyTextArea = new JTextArea(18, 45);
		historyTextArea.setEditable(false);
		historyTextArea.setLineWrap(true);

		JScrollPane historyScrollPane = new JScrollPane(historyTextArea);
		historyScrollPane.setPreferredSize(new Dimension(550, 370));
		historyScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(selectPanel);
		this.add(historyScrollPane);
	}

	/**
	 * 写日志
	 * 
	 * @param content
	 * @return
	 */
	public boolean writeLog(String content) {

		String line = "【" + StaticTool.SystemTime() + "】 " + content;
		String path = "./log/" + StaticTool.SystemDate() + ".log";
		file = new File(path);
		new File(file.getParent()).mkdirs();
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			fileWriter = new FileWriter(file, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(line);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			isOk = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isOk;
	}

	/**
	 * 判断是否是闰年
	 * 
	 * @param year
	 * @return
	 */
	private boolean isNunYear(String yearStr) {
		int year = Integer.parseInt(yearStr);
		if (year % 4 == 0) {
			if (year % 100 != 0) {
				return true;
			}
		}
		if (year % 400 == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 31天的月份
	 */
	private void thirtyOneDate() {
		dateComboBox.addItem("01");
		dateComboBox.addItem("02");
		dateComboBox.addItem("03");
		dateComboBox.addItem("04");
		dateComboBox.addItem("05");
		dateComboBox.addItem("06");
		dateComboBox.addItem("07");
		dateComboBox.addItem("08");
		dateComboBox.addItem("09");
		for (int i = 10; i <= 31; i++) {
			dateComboBox.addItem(i);
		}
	}

	/**
	 * 30天的月份
	 */
	private void thirtyDate() {
		dateComboBox.addItem("01");
		dateComboBox.addItem("02");
		dateComboBox.addItem("03");
		dateComboBox.addItem("04");
		dateComboBox.addItem("05");
		dateComboBox.addItem("06");
		dateComboBox.addItem("07");
		dateComboBox.addItem("08");
		dateComboBox.addItem("09");
		for (int i = 10; i <= 30; i++) {
			dateComboBox.addItem(i);
		}
	}
}
