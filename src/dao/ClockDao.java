package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Clock;

public class ClockDao {

	private Connection connection;
	private List <Clock> clocks = new ArrayList<Clock>();
	private List <Integer> clockIds = new ArrayList<Integer>();
	private final String GET_CLOCKS_QUERY = "SELECT * FROM clocks";
	private final String CREATE_CLOCK_QUERY = "INSERT INTO clocks(id, type, brand) VALUES(?, ?, ?)";
	private final String DELETE_CLOCK_QUERY = "DELETE FROM clocks WHERE id = ?";
	private final String UPDATE_CLOCK_QUERY = "UPDATE clocks SET ? = ? WHERE id = ?";
	
	public ClockDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<Clock> getClocks() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_CLOCKS_QUERY).executeQuery();
		while(rs.next()) {
			clocks.add(completeClock(rs.getInt(1), rs.getString(2), rs.getString(3)));
		}
		return clocks;
	}
	
	public List<Integer> getClockId() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_CLOCKS_QUERY).executeQuery();
		while(rs.next()) {
			clockIds.add(rs.getInt(1));
		}
		return clockIds;
	}
	
	public void createClock(int clockId, String type, String brand) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_CLOCK_QUERY);
		ResultSet rs = connection.prepareStatement(GET_CLOCKS_QUERY).executeQuery();
		while(rs.next()) {
			if(rs.getInt(1) == clockId) {
				break;
			} else {
				continue;
			}
		}
		ps.executeUpdate();
	}
	
	public void removeClock(int clockId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_CLOCK_QUERY);
		ResultSet rs = connection.prepareStatement(GET_CLOCKS_QUERY).executeQuery();
		while(rs.next()) {
			if(rs.getInt(1) != clockId) {
				continue;
			} else if(!rs.next()) {
				break;
			} else {
				ps.executeUpdate();
			}
		}
	}
	
	public void updateClock(int clockId, String selection, String update) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_CLOCK_QUERY);
		ResultSet rs = connection.prepareStatement(GET_CLOCKS_QUERY).executeQuery();
		while(rs.next()) {
			if(rs.getInt(1) != clockId) {
				continue;
			} else if(!rs.next()) {
				break;
			} else {
				ps.executeUpdate();
			}
		}
	}
	
	private Clock completeClock(int clockId, String type, String brand) throws SQLException {
		return new Clock(clockId, type, brand);
	}
}
