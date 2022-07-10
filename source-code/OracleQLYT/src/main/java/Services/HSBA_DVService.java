package Services;

import Entities.HSBA_DV;
import Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HSBA_DVService {
    private Connection connection;

    public HSBA_DVService() {
        connection= JDBCUtils.getConnection();
    }
    public HSBA_DV getHSBADVById(String id) throws SQLException {
        String query="SELECT * FROM DB.HSBA_DV WHERE MAHSBA='"+id+"'";
        HSBA_DV hsba_dv=null;
        Statement stmt = connection.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next()){
            hsba_dv=new HSBA_DV(rs.getString("MAHSBA"),
                    rs.getString("MADV"),
                    rs.getDate("NGAY"),
                    rs.getString("MAKTV"),
                    rs.getString("KETQUA"));
        }

        return hsba_dv;
    }
}
