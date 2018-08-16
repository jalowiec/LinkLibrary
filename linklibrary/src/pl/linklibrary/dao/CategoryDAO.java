package pl.linklibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.linklibrary.datasource.util.ConnectionProvider;
import pl.linklibrary.model.Category;
import pl.linklibrary.model.Link;

public class CategoryDAO {

	private final static String READ_ALL = "SELECT category_id, name FROM category ORDER BY category_id DESC;";
	private final static String READ = "SELECT link_id, link_name, url, description FROM link WHERE link_id = ?;";
	private final static String CREATE = "INSERT INTO link(link_name, url, description, user_id) VALUES(?, ?, ?, ?);";
	private final static String DELETE = "DELETE FROM link WHERE link_id=?;";
	private final static String UPDATE = "UPDATE link SET link_name=?, url=?, description=? WHERE link_id = ?;";

	public List<Category> readAll(int user_id) {

		List<Category> categoryList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement prepStmt = null;
		ResultSet resultSet = null;
		Category resultCategory = null;
		try {
			conn = ConnectionProvider.getConnection();
			prepStmt = conn.prepareStatement(READ_ALL);
	//		prepStmt.setInt(1, id);
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

	public Link read(int linkId) {
		Connection conn = null;
		PreparedStatement prepStmt = null;
		ResultSet resultSet = null;
		Link resultLink = null;
		try {
			conn = ConnectionProvider.getConnection();
			prepStmt = conn.prepareStatement(READ);
			prepStmt.setInt(1, linkId);
			resultSet = prepStmt.executeQuery();
			if (resultSet.next()) {
				resultLink = new Link();
				resultLink.setId(resultSet.getInt("link_id"));
				resultLink.setName(resultSet.getString("link_name"));
				resultLink.setUrl(resultSet.getString("url"));
				resultLink.setDescription(resultSet.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources(prepStmt, resultSet, conn);
		}
		return resultLink;
	}

	public boolean create(Link link) {
		Connection conn = null;
		PreparedStatement prepStmt = null;
		boolean result = false;
		try {
			conn = ConnectionProvider.getConnection();
			prepStmt = conn.prepareStatement(CREATE);
			prepStmt.setString(1, link.getName());
			prepStmt.setString(2, link.getUrl());
			prepStmt.setString(3, link.getDescription());
			prepStmt.setInt(4, 0);
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

	public boolean delete(int linkId) {
		Connection conn = null;
		PreparedStatement prepStmt = null;
		boolean result = false;
		try {
			conn = ConnectionProvider.getConnection();
			prepStmt = conn.prepareStatement(DELETE);
			prepStmt.setInt(1, linkId);
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
	
	public boolean update(Link link) {
		Connection conn = null;
		PreparedStatement prepStmt = null;
		boolean result = false;
		try {
			conn = ConnectionProvider.getConnection();
			conn = ConnectionProvider.getConnection();
			prepStmt = conn.prepareStatement(UPDATE);
			prepStmt.setString(1, link.getName());
			prepStmt.setString(2, link.getUrl());
			prepStmt.setString(3, link.getDescription());
			prepStmt.setInt(4, link.getId());			
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