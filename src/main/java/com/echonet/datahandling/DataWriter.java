package com.echonet.datahandling;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import com.echonet.exceptions.DataBaseNotFoundException;

public class DataWriter extends DataHandler {
    private void populatePreparedStatement(PreparedStatement pstmt, Map <String, Object> map) throws SQLException {
        Iterator<Map.Entry<String, Object>> itr = map.entrySet().iterator();  //iterator to iterate through map
        int indexCount = 1; //keeps track of which argument is added
        while(itr.hasNext()) {
            Map.Entry<String, Object> entry = itr.next();
            Object data = entry.getValue();

            if(data instanceof String string) {
                pstmt.setString(indexCount, string);
            }

            else {
                pstmt.setInt(indexCount, (int) data);
            }

            indexCount++;
        }
    }
    public DataWriter (final String database) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        super(database);
    }

    public void write(final Table t, final Map <String, Object> map) throws SQLException {
        String sql = sqlgen.insertStatement(t);
        PreparedStatement pstmt = c.prepareStatement(sql); 
        this.populatePreparedStatement(pstmt, map);
        pstmt.executeUpdate();
        pstmt.close(); 
    }
}
