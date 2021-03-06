/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer.Mappers;

import DataAccessLayer.Interfaces.FloorMapperInterface;
import DataAccessLayer.DBConnector;
import ServiceLayer.Entity.Floor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author philliphbrink
 */
public class FloorMapper implements FloorMapperInterface {
    
    //Made by Phillip - Add a floor to building by buildings id
    @Override
    public void addFloor(Floor floor) throws SQLException {
            PreparedStatement pstmt = DBConnector.getConnection().prepareStatement("INSERT INTO building_floors (floor_building_id, floor_no, floor_size, floor_arpartments, floor_rooms) VALUES (?, ?, ?, ?, ?);");
            pstmt.setInt(1, floor.getFloor_building_id());
            pstmt.setInt(2, floor.getFloor_no());
            pstmt.setInt(3, floor.getFloor_size());
            pstmt.setString(4, floor.getFloor_arpartments());
            pstmt.setString(5, floor.getFloor_rooms());
            pstmt.executeUpdate();   
    }
    //Made by Phillip - Returns the last Floor for the building by buildings id
    @Override
    public int getAllFloors(Floor floor) throws SQLException {
        int floor_no;
            PreparedStatement pstmt = DBConnector.getConnection().prepareStatement("SELECT floor_no FROM building_floors WHERE floor_building_id = ?");
            pstmt.setInt(1, floor.getFloor_building_id());
            ResultSet rs = pstmt.executeQuery();
            rs.last();
            floor_no = rs.getInt("floor_no");
            return floor_no;
    }
    //Made by Phillip - Returns a ArrayList of Floors for the building by buildings id
    public ArrayList<Floor> getAllFloorsArray(Floor floor) {
        ArrayList<Floor> list = new ArrayList();
        try {
            PreparedStatement pstmt = DBConnector.getConnection().prepareStatement("SELECT floor_no, floor_size, floor_arpartments, floor_rooms FROM building_floors WHERE floor_building_id = ?");
            pstmt.setInt(1, floor.getFloor_building_id());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
            list.add(new Floor(rs.getInt("floor_no"),
            rs.getInt("floor_size"), 
            rs.getString("floor_arpartments"),
            rs.getString("floor_rooms")));
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("FAIL");
        return list;
    }
    //Made by Phillip - Returns ArrayList of Floors
    @Override
    public ArrayList<Floor> getTotalSize(Floor floor) {
        try {
            ArrayList<Floor> list = new ArrayList<>();
            PreparedStatement pstmt = DBConnector.getConnection().prepareStatement("SELECT * FROM building_floors WHERE floor_building_id = ?");
            pstmt.setInt(1, floor.getFloor_building_id());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Floor(rs.getInt("floor_no"),
                        rs.getInt("floor_size"),
                        rs.getString("floor_arpartments"),
                        rs.getString("floor_rooms")));
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }        
    }
    //Made by Phillip - returns a specifik floor for a building
    @Override
    public Floor getFloor(Floor floor) {
        try {
            PreparedStatement pstmt = DBConnector.getConnection().prepareStatement("SELECT floor_size, floor_arpartments, floor_rooms from building_floors WHERE floor_no = ? and floor_building_id = ?");
            pstmt.setInt(1, floor.getFloor_no());
            pstmt.setInt(2, floor.getFloor_building_id());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
             Floor newFloor = new Floor(rs.getInt("floor_size"),
                        rs.getString("floor_arpartments"),
                        rs.getString("floor_rooms"));
            
            return newFloor;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        } 
    }
    //Made by Phillip - Updates a floor
    @Override
    public void updateFloor(Floor editFloor) throws SQLException {
            PreparedStatement pstmt = DBConnector.getConnection().prepareStatement("UPDATE building_floors set floor_size = ?, floor_arpartments = ?, floor_rooms = ? WHERE floor_no = ? and floor_building_id = ?;");
            pstmt.setInt(1, editFloor.getFloor_size());
            pstmt.setString(2, editFloor.getFloor_arpartments());
            pstmt.setString(3, editFloor.getFloor_rooms());
            pstmt.setInt(4, editFloor.getFloor_no());
            pstmt.setInt(5, editFloor.getFloor_building_id());
            pstmt.executeUpdate();
    }
    // Made by Phillip - Delete all the floors in a building
    @Override
    public void deleteFloor(Floor floor) {
        try {
            PreparedStatement pstmt = DBConnector.getConnection().prepareStatement("DELETE FROM building_floors WHERE floor_building_id = ?;");
            pstmt.setInt(1, floor.getFloor_building_id());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
