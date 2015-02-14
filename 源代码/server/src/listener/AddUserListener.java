package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panel.AddUserPanel;
import panel.UserPanel;
import tool.Check;

public class AddUserListener implements ActionListener {
	AddUserPanel addUserPanel;

	public AddUserListener(AddUserPanel addUserPanel) {
		this.addUserPanel = addUserPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addUserPanel.getButton1()) {
			String cPassword = String.valueOf(addUserPanel.getTextField2()
					.getPassword());

			if (!Check.checkPassword(cPassword)) {
				addUserPanel.getjLabelMessage().setText(
						"输入的密码长度须在 3~16 之间(只允许是数字，字母 , _) ");
				return;
			} else {

				addUserPanel.getUser().setPassword(cPassword);
			}

			String cName = addUserPanel.getTextField3().getText();

			if (!Check.checkSname(cName)) {
				addUserPanel.getjLabelMessage().setText(
						"输入的真实姓名须是长度在 2~10 之间的中文");
				return;
			} else {

				addUserPanel.getUser().setName(cName);
			}

			String cAge = addUserPanel.getTextField4().getText();

			if (!Check.checkSage(cAge)) {
				addUserPanel.getjLabelMessage().setText("输入的年龄须是20~150之间的数字");
				return;
			} else {

				addUserPanel.getUser().setAge(cAge);
			}

			String cAddress = String.valueOf(addUserPanel.getTextField6()
					.getText());

			if (!Check.checkSaddress(cAddress)) {
				addUserPanel.getjLabelMessage().setText("输入的地址长度须为0~100");
				return;
			} else {

				addUserPanel.getUser().setAddress(cAddress);
			}

			addUserPanel.getUser().setSex(
					String.valueOf(addUserPanel.getTextField5()
							.getSelectedItem()));

			addUserPanel.getUserimp().addUser(addUserPanel.getUser());

			addUserPanel.getUserDialog().dispose();
			UserPanel.refresh(null, null);

		} else if (e.getSource() == addUserPanel.getButton2()) {
			addUserPanel.getUserDialog().dispose();
		}

	}

}
