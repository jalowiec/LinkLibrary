package pl.linklibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import pl.linklibrary.datasource.util.ConnectionProvider;
import pl.linklibrary.model.Category;
import pl.linklibrary.model.Link;

public class CategoryDAO {

	private final static String READ_ALL = "SELECT category_id, name FROM category ORDER BY category_id DESC;";
	private final static String CREATE = "INSERT INTO category(name) VALUES(?);";
	private final static String DELETE = "DELETE FROM category WHERE category_id=?;";

	public Set<Category> readAll(int user_id) {

		Set<Category> categoryList = new TreeSet<>();
		Connection conn = null;
		PreparedStatement prepStmt = null;
		ResultSet resultSet = null;
		Category resultCategory = null;
		try {
			conn = ConnectionProvider.getConnection();
			prepStmt = conn.prepareStatement(READ_ALL);
			// prepStmt.setInt(1, id);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				resultCategory = new Category();
				resultCategory.setCategoryId(resultSet.getInt("category_id"));
				resultCategory.setCategoryName(resultSet.getString("name"));

				categoryList.add(resultCategory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources(prepStmt, resultSet, conn);
		}
		return categoryList;
	}

	public boolean create(String categoryName) {
		Connection conn = null;
		PreparedStatement prepStmt = null;
		boolean result = false;
		try {
			conn = ConnectionProvider.getConnection();
			prepStmt = conn.prepareStatement(CREATE);
			prepStmt.setString(1, categoryName);
			int rowsAffected = prepStmt.executeUpdate();
			if (rowsAffected > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources(prepStmt, null, conn);
		}
		return result;
	}

	public boolean delete(int[] categories) {
		Connection conn = null;
		PreparedStatement prepStmt = null;
		boolean result = false;
		try {
			conn = ConnectionProvider.getConnection();
			prepStmt = conn.prepareStatement(DELETE);
			int rowsAffected = 0;
			for (int categoryId : categories) {
				prepStmt.setInt(1, categoryId);
				rowsAffected += prepStmt.executeUpdate();
			}
			if (rowsAffected > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources(prepStmt, null, conn);
		}
		return result;
	}

	
	private void releaseResources(PreparedStatement prepStmt, ResultSet res, Connection conn) {
		try {
			if (prepStmt != null && !prepStmt.isClosed()) {
				prepStmt.close();
			}
			if (res != null && !res.isClosed()) {
				res.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}