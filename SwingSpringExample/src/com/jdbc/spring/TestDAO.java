package com.jdbc.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TestDAO implements ActionListener {
	public static JLabel lblFname, lblLname, lblAddress, lblSalary, lblf, lbll, lbla, lbls;
	public static JLabel lblfVal, lbllVal, lblaVal, lblsVal;
	public static JTextField txtFname, txtLname, txtAddress, txtSalary;
	JButton btnInsert, btnUpdate, btnDelete, btnPrev, btnNext, btnClear, btnCreate;
	static ApplicationContext applicationcontext;
	static JdbcBean bean;

	public static void main(String[] args) {
		applicationcontext = new ClassPathXmlApplicationContext("jdbcctxt.xml");
		bean = (JdbcBean) applicationcontext.getBean(JdbcBean.class);
		TestDAO test = new TestDAO();
		test.createUI();

	}

	private void createUI() {
		// TODO Auto-generated method stub

		// bean.createTable();
		// bean.insert(new Recruitment("Nex-Gen Corp", "Data Stadium", 12.0));
		// System.out.println(bean.getRowStatus());
		// System.out.println(bean.getAll());
		// System.out.println(bean.getCompany(10));
		// System.out.println(bean.getCompanies(4.0, 6.0));
		// System.out.println("Row deleted : " + bean.deleteRow(3));
		JFrame frame = new JFrame("JDBC Spring GUI");

		// Layout of Main Window
		Container c = frame.getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

		lblFname = new JLabel("First Name :");
		lblLname = new JLabel("Last Name :");
		lblAddress = new JLabel("Address :");
		lblSalary = new JLabel("Salary :");

		txtFname = new JTextField("", 15);// To adjust width
		txtLname = new JTextField();
		txtAddress = new JTextField();
		txtSalary = new JTextField();

		JPanel pnlInput = new JPanel(new GridLayout(4, 2));

		pnlInput.add(lblFname);
		pnlInput.add(txtFname);

		pnlInput.add(lblLname);
		pnlInput.add(txtLname);

		pnlInput.add(lblAddress);
		pnlInput.add(txtAddress);

		pnlInput.add(lblSalary);
		pnlInput.add(txtSalary);

		btnCreate = new JButton("Create");
		btnCreate.addActionListener(this);

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(this);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(this);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(this);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(this);

		JPanel pnlButton = new JPanel(new GridLayout(1, 4));
		pnlButton.add(btnCreate);
		pnlButton.add(btnInsert);
		pnlButton.add(btnUpdate);
		pnlButton.add(btnDelete);
		pnlButton.add(btnClear);

		JPanel pnlAns = new JPanel(new GridLayout(4, 2));

		lblf = new JLabel("First Name :");
		lbll = new JLabel("Last Name :");
		lbla = new JLabel("Address :");
		lbls = new JLabel("Salary :");

		lblfVal = new JLabel("");
		lbllVal = new JLabel("");
		lblaVal = new JLabel("");
		lblsVal = new JLabel("");

		pnlAns.add(lblf);
		pnlAns.add(lblfVal);

		pnlAns.add(lbll);
		pnlAns.add(lbllVal);

		pnlAns.add(lbla);
		pnlAns.add(lblaVal);

		pnlAns.add(lbls);
		pnlAns.add(lblsVal);

		btnPrev = new JButton(" << ");
		btnPrev.setActionCommand("Prev");
		btnPrev.addActionListener(this);

		btnNext = new JButton(" >> ");
		btnNext.setActionCommand("Next");
		btnNext.addActionListener(this);

		JPanel pnlNavigate = new JPanel(new GridLayout(1, 2));

		pnlNavigate.add(btnPrev);
		pnlNavigate.add(btnNext);

		frame.add(pnlInput);
		frame.add(pnlButton);
		frame.add(pnlAns);
		frame.add(pnlNavigate);

		frame.pack();
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String cmd = evt.getActionCommand();

		if (cmd.equals("Create")) {

			CreateTable();

		} else if (cmd.equals("Update")) {
			updateData();
		} else if (cmd.equals("Delete")) {
			deleteData();
		} else if (cmd.equals("Prev")) {
			previous();
		} else if (cmd.equals("Next")) {
			next();
		} else if (cmd.equals("Clear")) {
			clearControls();
		} else if (cmd.equals("Insert")) {
			insertData();

		}
	}

	private void CreateTable() {

		bean.createTable();
		createMessageBox("Table Created..!!");

	}

	private void insertData() {

		/*
		 * bean.insert(); createMessageBox("Datas Inserted..!!");
		 */
		System.out.println(bean.getAll());
		System.out.println(bean.getRowStatus());

	}

	private void deleteData() {
		bean.deleteRow(1);
		createMessageBox("Given number of Row Deleted..!!");

	}

	private void updateData() {
		bean.updateData();
		createMessageBox("Row Updated..!!");

	}

	private void clearControls() {
		String empty = "";

		txtFname.setText(empty);
		txtLname.setText(empty);
		txtAddress.setText(empty);
		txtSalary.setText(empty);

		lblfVal.setText(empty);
		lbllVal.setText(empty);
		lblaVal.setText(empty);
		lblsVal.setText(empty);
	}

	private void createMessageBox(String msg) {
		JFrame frame = new JFrame("Result");
		JLabel lbl = new JLabel(msg);
		frame.add(lbl);
		frame.setSize(200, 200);
		frame.setVisible(true);
	}

	private void next() {
		try {
			JdbcBean.next();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void previous() {
		try {
			JdbcBean.previous();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * private void populateControls() { try {
	 * lblfVal.setText(rs.getString("fName"));
	 * lbllVal.setText(rs.getString("lName"));
	 * lblaVal.setText(rs.getString("Address"));
	 * lblsVal.setText(rs.getString("Salary"));
	 * 
	 * txtFname.setText(lblfVal.getText()); txtLname.setText(lbllVal.getText());
	 * txtAddress.setText(lblaVal.getText()); txtSalary.setText(lblsVal.getText());
	 * } catch (SQLException e) { e.printStackTrace(); } }
	 */

}
