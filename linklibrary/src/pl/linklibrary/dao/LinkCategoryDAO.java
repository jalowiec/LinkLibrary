package pl.linklibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.linklibrary.datasource.util.ConnectionProvider;
import pl.linklibrary.model.Link;

public class LinkCategoryDAO {

	private final static String CREATE = "INSERT INTO link_category(link_id, category_id) VALUES(?, ?);";
	private final static String DELETE = "DELETE FROM link_category WHERE link_id=?;";
	private final static String READ_CATEGORY = "SELECT category_id FROM link_category WHERE link_id = ?;";
	private final static String READ_LINK = "SELECT link_id FROM link_category WHERE category_id = ?;";	
	private final static String READ_ALL = "SELECT link_id, category_id FROM link_category";

	public Set<Integer> readCategories(int id) {

		Set<Integer> categorySet = new HashSet<>();
		Connection conn = null;
		PreparedStatement prepStmt = null;
		ResultSet resultSet = null;
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
	
	public Set<Integer> readLinks(int id) {

		Set<Integer> linkSet = new HashSet<>();
		Connection conn = null;
		PreparedStatement prepStmt = null;
		ResultSet resultSet = null;
		try {
			conn = ConnectionProvider.getConnection();
			prepStmt = conn.prepareStatement(READ_LINK);
			prepStmt.setInt(1, id);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				linkSet.add(resultSet.getInt("link_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources(prepStmt, resultSet, conn);
		}
		return linkSet;
	}
	

	public Map<Integer, Integer> countLinksForCategories() {
		Map<Integer, Integer> result = new HashMap<>();
		Connection conn = null;
		PreparedStatement prepStmt = null;
		ResultSet resultSet = null;
		try {
			conn = ConnectionProvider.getConnection();
			prepStmt = conn.prepareStatement(READ_ALL);
			resultSet = prepStmt.executeQuery();
			while (resultSet.next()) {
				int key = resultSet.getInt("category_id");
				if (result.containsKey(key)) {
					int value = result.get(key);
					result.put(key, ++value);
				} else {
					result.put(key, 1);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources(prepStmt, resultSet, conn);
		}
		return result;
	}

	public boolean create(int linkId, int[] categoriesList) {
		Connection conn = null;
		PreparedStatement prepStmt = null;
		boolean result = false;
		try {
			conn = ConnectionProvider.getConnection();
			prepStmt = conn.prepareStatement(CREATE);
			int rowsAffected = 0;
			for (int category : categoriesList) {
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