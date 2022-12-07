package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import commons.Constants;
import models.Product;

public class ProductRepositoryMySQL implements ProductRepository {

	@Override
	public void insert(Product product) {
		Connection con;
		PreparedStatement prepStmt;
		String sql = "INSERT INTO "
				+ "Contatos (first_name, last_name, email, phone) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASS);
			prepStmt = con.prepareStatement(sql);
			prepStmt.setString(1, product.getProductName());
			prepStmt.setString(2, product.getCategoria());
			prepStmt.setString(3, product.getValidade());
			prepStmt.setString(4, product.getFabricacao());
			prepStmt.executeUpdate();
			prepStmt.close();
		}catch(SQLException ex) {
		}
	}

	@Override
	public void remove(int id) {
		Connection con;
		PreparedStatement prepStmt;
		try {
			con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASS);
			prepStmt = con.prepareStatement("DELETE FROM Contatos WHERE id=?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
			prepStmt.close();
			con.close();
		} catch (SQLException e) {
		}
	}

	@Override
	public Product[] findBy(String fieldName, String value) {
		Connection con;
		PreparedStatement stmt;
		String sql = "SELECT * FROM Contatos WHERE";
		if(fieldName.equals("*")) {
			sql += "first_name like ? OR last_name like ? OR email like ? OR phone like ?";
		} else  {
			sql +=fieldName+" like ?";
		}
		
		try {

			con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASS);
			stmt = con.prepareStatement(sql);
			
			if(!fieldName.equals("*")) {
				stmt.setString(1, "%"+value+"%");
			}else {
				for (int i = 1; i <= 4; i++) {
					stmt.setString(i, "%"+value+"%");
				}
			}
			
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
			}

			Product[] products = new Product[count];
			rs = stmt.executeQuery(); // rs.beforeFirst();
			int index = 0;
			while (rs.next()) {
				int id = rs.getInt("id");
				String fname = rs.getString("first_name");
				String lname = rs.getString("last_name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");

				Product c = new Product(id, fname, lname, phone, email);
				products[index] = c;
				index++;
			}
			rs.close();
			stmt.close();
			con.close();

			return products;
			
		} catch (SQLException e) {

			return new Product[0];
		}
	}

	@Override
	public Product[] findAll() {
		Connection con;
		Statement stmt;
		String sql = "SELECT id, first_name, last_name, email, phone FROM Contatos";

		try {
			con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASS);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int count = 0;
			while (rs.next()) {
				count++;
			}

			Product[] products = new Product[count];
			rs = stmt.executeQuery(sql); // rs.beforeFirst();
			int index = 0;
			while (rs.next()) {
				int id = rs.getInt("id");
				String fname = rs.getString("first_name");
				String lname = rs.getString("last_name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");

				Product c = new Product(id, fname, lname, phone, email);
				products[index] = c;
				index++;
			}
			rs.close();
			stmt.close();
			con.close();

			return products;

		} catch (SQLException e) {

			return new Product[0];
		}
	}

	@Override
	public void update(Product product) {
		Connection con;
		PreparedStatement prepStmt;
		String sql = "UPDATE Contatos SET first_name=?, last_name=?, email=?, phone=? "
				+ "WHERE id=?";
		try {
			con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASS);
			prepStmt = con.prepareStatement(sql);
			prepStmt.setString(1, product.getProductName());
			prepStmt.setString(2, product.getCategoria());
			prepStmt.setString(3, product.getValidade());
			prepStmt.setString(4, product.getFabricacao());
			prepStmt.setInt(5, product.getId());
			prepStmt.executeUpdate();
			prepStmt.close();
			con.close();
		}catch(SQLException ex) {
		}           

	}

}
