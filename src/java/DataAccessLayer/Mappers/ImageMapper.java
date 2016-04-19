/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer.Mappers;

import DataAccessLayer.DBConnector;
import ServiceLayer.Entity.Image;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oliver
 */
public class ImageMapper {
    public void setImage(InputStream inputstream) {
        try {
            System.out.println("mapper start");
            PreparedStatement pstmt = DBConnector.getConnection().prepareStatement("INSERT INTO images values (?, ?, ?)");
            pstmt.setInt(1, 003);
            pstmt.setString(2, "1");
            pstmt.setBlob(3, inputstream);
            System.out.println("mapper før execute");
            pstmt.executeUpdate();
            System.out.println("mapper slut");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private InputStream getPhotos() throws
            IllegalArgumentException, SQLException, ClassNotFoundException {

        Connection connection = DBConnector.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        InputStream binaryStream = null;

        try {

            preparedStatement = connection.prepareStatement("SELECT photo FROM images WHERE image_id=?");
            preparedStatement.setString(1, "3");
            resultset = preparedStatement.executeQuery();

            while (resultset.next()) {
                binaryStream = resultset.getBinaryStream("photo");
            }

        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
        }
        return binaryStream;
    }
    
    public void getImage(InputStream inputstream) {
        
    }
}