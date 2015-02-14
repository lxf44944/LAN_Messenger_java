package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import panel.UpdateUserPanel;
import panel.UserPanel;
import tool.Check;

public class UpdateListener implements ActionListener {
	UpdateUserPanel updateUserPanel;

	public UpdateListener(UpdateUserPanel updateUserPanel) {
		this.updateUserPanel = updateUserPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == updateUserPanel.getButton1()) {
			String cName = updateUserPanel.getTextField3().getText();

			if (!Check.checkSname(cName)) {
				updateUserPanel.getjLabelMessage().setText(
						"输入的真实姓名须是长度在 2~10 之间的中文");
				return;
			} else {

				updateUserPanel.getUser().setName(cName);
			}

			String cAge = updateUserPanel.getTextField4().getText();

			if (!Check.checkSage(cAge)) {
				updateUserPanel.getjLabelMessage()
						.setText("输入的年龄须是20~150之间的数字");
				return;
			} else {

				updateUserPanel.getUser().setAge(cAge);
			}

			String cAddress = String.valueOf(updateUserPanel.getTextField6()
					.getText());

			if (!Check.checkSaddress(cAddress)) {
				updateUserPanel.getjLabelMessage().setText("输入的地址长度须为0~100");
				return;
			} else {

				updateUserPanel.getUser().setAddress(cAddress);
			}

			updateUserPanel.getUser().setSex(
					String.valueOf(updateUserPanel.getTextField5()
							.getSelectedItem()));

			updateUserPanel.getUserPanel();
			updateUserPanel.getUserimp().updateUser(updateUserPanel.getUser(),
					UserPanel.getuId());
			UserPanel.refresh(null, null);

			JOptionPane.showMessageDialog(
					updateUserPanel.getUpdateUserDialog(), "修改成功");
			updateUserPanel.getUpdateUserDialog().dispose();
		} else if (e.getSource() == updateUserPanel.getButton2()) {
			updateUserPanel.getUpdateUserDialog().dispose();
		}

	}

}
