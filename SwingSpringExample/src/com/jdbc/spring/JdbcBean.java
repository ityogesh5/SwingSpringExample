package com.jdbc.spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JdbcBean {
	static ResultSet set;
	int arg;
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public static ResultSet Set;
	static int count;
	static int rs_count_day;
	MyMapper mapper = null;

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void createTable() {
		String create_qry = "create table Employee(day int not null AUTO_INCREMENT,FName varchar(50) "
				+ "NOT NULL,LName varchar(50) not null,Address varchar(50) not null,Salary varchar(50) not null,PRIMARY KEY(day))";
		jdbcTemplate.execute(create_qry);
		System.out.println("Table has been created");
	}

	public void insert() {
		String insert_qry = "insert into Employee(FName,LName,Address,Salary) values (?,?,?,?)";
		jdbcTemplate.update(insert_qry, new Object[] { TestDAO.txtFname.getText(), TestDAO.txtLname.getText(),
				TestDAO.txtAddress.getText(), TestDAO.txtSalary.getText() });
		System.out.println("Datas Inserted into table");
	}

	public void updateData() {
		String update_query = "Update Employee Set LName='" + TestDAO.txtLname.getText() + "'," + "Address='"
				+ TestDAO.txtAddress.getText() + "',Salary='" + TestDAO.txtSalary.getText() + "' Where FName='"
				+ TestDAO.txtFname.getText() + "'";
		jdbcTemplate.execute(update_query);
		System.out.println("Row Updated");
	}

	public int getRowStatus() {
		return jdbcTemplate.queryForObject("select count(*) from Employee", Integer.class);

	}

	private static final class MyMapper implements RowMapper<Recruitment> {

		@Override
		public Recruitment mapRow(ResultSet set, int arg1) throws SQLException {

			Recruitment req = new Recruitment();

			req.setDay(set.getInt("day"));
			req.setFName(set.getString("FName"));
			req.setLName(set.getString("LName"));
			req.setAddress(set.getString("Address"));
			req.setSalary(set.getString("Salary"));

			return req;
		}

	}

	public List<Recruitment> getAll() {

		return jdbcTemplate.query("select * from Employee", new MyMapper());

	}

	public List<Recruitment> getCompanies(String d1, String d2) {
		return jdbcTemplate.query("select * from Employee where Salary between " + d1 + " and " + d2, new MyMapper());

	}

	public String getCompany(int day) {
		return jdbcTemplate.queryForObject("select Salary from Employee where day = " + day, String.class);
	}

	public int deleteRow(int day) {
		return jdbcTemplate.update("delete from Employee where day=" + day);

	}

	public static void previous() throws ClassNotFoundException {
		Connection con;

		try {

			if (set == null) {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
				String sql = "Select FName,LName,Address,Salary from Employee";
				Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				set = statement.executeQuery(sql);
			}
			if (set.next() && !set.isAfterLast())// After Last was giving invalid cursor state error
			{
				populateControls();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	public static void next() throws ClassNotFoundException {
		Connection con;

		try {

			if (set == null) {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
				String sql = "Select FName,LName,Address,Salary from Employee";
				Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				set = statement.executeQuery(sql);
			}
			if (set.next() && !set.isAfterLast())// After Last was giving invalid cursor state error
			{
				populateControls();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void populateControls() {
		try {
			TestDAO.lblfVal.setText(set.getString("FName"));
			TestDAO.lbllVal.setText(set.getString("LName"));
			TestDAO.lblaVal.setText(set.getString("Address"));
			TestDAO.lblsVal.setText(set.getString("Salary"));

			TestDAO.txtFname.setText(TestDAO.lblfVal.getText());
			TestDAO.txtLname.setText(TestDAO.lbllVal.getText());
			TestDAO.txtAddress.setText(TestDAO.lblaVal.getText());
			TestDAO.txtSalary.setText(TestDAO.lblsVal.getText());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
