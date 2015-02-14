package panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import listener.SetListener;

import frame.LoadFrame;

import tool.StaticTool;

public class LoadPanel extends JPanel {

	private static final long serialVersionUID = 3590216045823216803L;
	private FlowLayout layout = null;
	private BorderLayout borderLayout = null;

	LoadFrame loadMainFrame;
	private SetListener setListener;
	private JButton button1;
	private JButton button2;
	private JTextField textField3;
	private JTextField textField4;
	private JPasswordField textField2;
	private JTextField textField;

	public LoadPanel(LoadFrame mainFrame) {
		this.loadMainFrame = mainFrame;
		this.setLayout(new FlowLayout());
		init();
	}

	private void init() {
		layout = new FlowLayout();
		borderLayout = new BorderLayout();
		Border border1 = BorderFactory.createEmptyBorder(5, 60, 5, 5);
		JPanel panel = new JPanel();

		panel.setLayout(borderLayout);
		JLabel label = new JLabel(StaticTool.qqIcon1);
		panel.add(label, BorderLayout.NORTH);
		panel.setPreferredSize(new Dimension(330, 60));

		JPanel panel2 = new JPanel();
		panel2.setLayout(layout);
		textField = new JTextField();
		JLabel jLabel = new JLabel("’ ∫≈£∫");
		textField.setPreferredSize(new Dimension(150, 25));
		panel2.add(jLabel);
		panel2.add(textField);
		panel2.setPreferredSize(new Dimension(330, 50));

		JPanel panel3 = new JPanel();
		panel3.setLayout(layout);
		textField2 = new JPasswordField();
		JLabel jLabel2 = new JLabel("√‹¬Î£∫");
		textField2.setPreferredSize(new Dimension(150, 25));
		panel3.add(jLabel2);
		panel3.add(textField2);
		panel3.setPreferredSize(new Dimension(330, 30));

		JPanel panel4 = new JPanel();
		Box box = new Box(BoxLayout.X_AXIS);
		button1 = new JButton("…Ë÷√");
		button1.setPreferredSize(new Dimension(50, 50));
		button1.setMargin(new Insets(0, 2, 0, 2));
		setListener = new SetListener(loadMainFrame, this);
		button1.addActionListener(setListener);

		button2 = new JButton("µ«¬º");
		button2.setPreferredSize(new Dimension(50, 50));
		button2.setMargin(new Insets(0, 2, 0, 2));
		button2.addActionListener(setListener);

		box.add(button1);
		box.add(Box.createHorizontalStrut(100));
		box.add(button2);
		box.setBorder(border1);
		panel4.setLayout(layout);
		panel4.add(box, BorderLayout.CENTER);

		JPanel panel5 = new JPanel();
		panel5.setLayout(layout);
		textField3 = new JTextField("127.0.0.1");
		JLabel jLabel3 = new JLabel("ipµÿ÷∑£∫");
		textField3.setPreferredSize(new Dimension(180, 25));
		panel5.add(jLabel3);
		panel5.add(textField3);

		JPanel panel6 = new JPanel();
		panel6.setLayout(layout);
		textField4 = new JTextField("8088");
		JLabel jLabel4 = new JLabel("∂Àø⁄∫≈£∫");
		textField4.setPreferredSize(new Dimension(180, 25));
		panel6.add(jLabel4);
		panel6.add(textField4);

		this.add(panel);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
		this.add(panel6);

	}

	public JButton getButton1() {
		return button1;
	}

	public JButton getButton2() {
		return button2;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JTextField getTextField3() {
		return textField3;
	}

	public JTextField getTextField4() {
		return textField4;
	}

	public JPasswordField getTextField2() {
		return textField2;
	}

	public LoadFrame getLoadMainFrame() {
		return loadMainFrame;
	}

}
