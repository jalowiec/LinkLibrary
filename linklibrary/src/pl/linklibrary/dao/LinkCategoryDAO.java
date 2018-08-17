package pl.linklibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.linklibrary.datasource.util.ConnectionProvider;
import pl.linklibrary.model.Link;

public class LinkCategoryDAO {

	private final static String CREATE = "INSERT INTO link_category(link_id, category_id) VALUES(?, ?);";
	private final static String DELETE = "DELETE FROM link_category WHERE link_id=?;";
	private final static String READ_CATEGORY = "SELECT category_id FROM link_category WHERE link_id = ?;";	

	private final static String READ_ALL = "SELECT link_id, link_name, url, description FROM link WHERE user_id = ? ORDER BY link_id DESC;";


	private final static String UPDATE = "UPDATE link SET link_name=?, url=?, description=? WHERE link_id = ?;";

	public Set<Integer> readLinkCategories(int id) {

		Set<Integer> categorySet = new HashSet<>();
		Connection conn = null;
		PreparedStatement prepStmt = null;
		ResultSet resultSet = null;
		Link resultLink = null;
		try {
			conn = ConnectionProvider.getConnection();
			prepStmt = conn.prepareStatement(READ_CATEGORY);
			prepStmt.setInt(1, id);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				categorySet.add(resultSet.getInt("category_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources(prepStmt, resultSet, conn);
		}
		return categorySet;
	}

	public Link read(int linkId) {
		Connection conn = null;
		PreparedStatement prepStmt = null;
		ResultSet resultSet = null;
		Link resultLink = null;
		try {
			conn = ConnectionProvider.getConnection();
			prepStmt = conn.prepareStatement(READ_ALL);
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

	public boolean create(int linkId,int[] categoriesList ) {
		Connection conn = null;
		PreparedStatement prepStmt = null;
		boolean result = false;
		try {
			conn = ConnectionProvider.getConnection();
			prepStmt = conn.prepareStatement(CREATE);
			int rowsAffected = 0;
			for(int category : categoriesList) {
				prepStmt.setInt(1, linkId);
				prepStmt.setInt(2, category);
			rowsAffected = prepStmt.executeUpdate();			
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