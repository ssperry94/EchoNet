package com.echonet.datahandling;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import com.echonet.exceptions.DataBaseNotFoundException;

public class DataWriter extends DataHandler {
    public void populatePreparedStatement(PreparedStatement pstmt, final Map <Integer, Object> map) throws SQLException {
        for(int i = 0; i < map.size(); i++) {
            Object data = map.get(i);

            if(data instanceof String string) {
                pstmt.setString(i + 1, string);
            }
            else {
                pstmt.setInt(i + 1, (int)data);
            }
        }
    }
    public DataWriter (final String database) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        super(database);
    }

    public void write(final Table t, final Map <Integer, Object> map) throws SQLException {
        String sql = sqlgen.insertStatement(t);
        PreparedStatement pstmt = c.prepareStatement(sql); 
        this.populatePreparedStatement(pstmt, map);
        pstmt.executeUpdate();
        pstmt.close(); 
    }
}
