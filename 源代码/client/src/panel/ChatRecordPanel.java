package panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import frame.LaterLoadFrame;

public class ChatRecordPanel extends JPanel {

	private static final long serialVersionUID = 6542396080460762286L;
	private JPanel leftUpPanel, leftDownPanel, rightUpPanel, rightDownPanel;
	private JLabel toLabel, yearLabel, monthLabel, dateLabel;
	private JButton alterPswButton, chatButton, sendButton, closeButton,
			seekButton;
	private JTextArea chatTextArea, inputTextArea, noticeTextArea,
			onlineTextArea, historyTextArea;
	private LaterLoadFrame laterLoadFrame;

	private JComboBox yearComboBox, monthComboBox, dateComboBox;

	public ChatRecordPanel(LaterLoadFrame laterLoadFrame) {
		this.laterLoadFrame = laterLoadFrame;
		this.setLayout(new FlowLayout());
		getChatRecordPanel();
	}

	public void getChatRecordPanel() {

		JPanel selectPanel = new JPanel();
		Border selectBorder = BorderFactory.createTitledBorder("日志日期选择");
		selectPanel.setBorder(selectBorder);
		selectPanel.setPreferredSize(new Dimension(380, 60));
		String[] year = { "2012", "2013", "2014", "2015", "2016", "2017",
				"2018" };
		yearComboBox = new JComboBox(year);
		yearComboBox.setPreferredSize(new Dimension(80, 20));
		yearLabel = new JLabel("年");
		String[] month = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12" };
		monthComboBox = new JComboBox(month);
		monthComboBox.setPreferredSize(new Dimension(50, 20));
		monthLabel = new JLabel("月");
		String[] date = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
				"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
				"31" };
		dateComboBox = new JComboBox(date);
		dateComboBox.setPreferredSize(new Dimension(50, 20));
		dateLabel = new JLabel("日");
		seekButton = new JButton("查询");
		seekButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String year = yearComboBox.getSelectedItem().toString();
				String month = monthComboBox.getSelectedItem().toString();
				String date = dateComboBox.getSelectedItem().toString();
				String all_string = year + "-" + month + "-" + date;

				File file = new File("./log" + "/"
						+ laterLoadFrame.getTitle().substring(0, 5) + "/"
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
		historyTextArea.setPreferredSize(new Dimension(380, 1000));
		historyTextArea.setEditable(false);
		historyTextArea.setLineWrap(true);

		JScrollPane historyScrollPane = new JScrollPane(historyTextArea);
		historyScrollPane.setPreferredSize(new Dimension(380, 200));
		historyScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(selectPanel);
		this.add(historyScrollPane);

	}

	public JPanel getLeftUpPanel() {
		return leftUpPanel;
	}

	public JPanel getLeftDownPanel() {
		return leftDownPanel;
	}

	public JPanel getRightUpPanel() {
		return rightUpPanel;
	}

	public JPanel getRightDownPanel() {
		return rightDownPanel;
	}

	public JLabel getToLabel() {
		return toLabel;
	}

	public JLabel getYearLabel() {
		return yearLabel;
	}

	public JLabel getMonthLabel() {
		return monthLabel;
	}

	public JLabel getDateLabel() {
		return dateLabel;
	}

	public JButton getAlterPswButton() {
		return alterPswButton;
	}

	public JButton getChatButton() {
		return chatButton;
	}

	public JButton getSendButton() {
		return sendButton;
	}

	public JButton getCloseButton() {
		return closeButton;
	}

	public JButton getSeekButton() {
		return seekButton;
	}

	public JTextArea getChatTextArea() {
		return chatTextArea;
	}

	public JTextArea getInputTextArea() {
		return inputTextArea;
	}

	public JTextArea getNoticeTextArea() {
		return noticeTextArea;
	}

	public JTextArea getOnlineTextArea() {
		return onlineTextArea;
	}

	public JTextArea getHistoryTextArea() {
		return historyTextArea;
	}

	public JComboBox getYearComboBox() {
		return yearComboBox;
	}

	public JComboBox getMonthComboBox() {
		return monthComboBox;
	}

	public JComboBox getDateComboBox() {
		return dateComboBox;
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
