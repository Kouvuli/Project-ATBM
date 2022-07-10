package Services;

import Entities.BenhNhan;
import Entities.HSBA;
import Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HSBAService {
    private Connection connection;

    public HSBAService() {
        connection= JDBCUtils.getConnection();
    }



    public HSBA getHSBAById(String maHSBA) throws SQLException {
        String query="select * from DB.HSBA WHERE MAHSBA='"+maHSBA+"'";
//        BenhNhan benhNhan=null;
        HSBA result=null;
        Statement stmt = connection.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next()){
            result=new HSBA(rs.getString("MAHSBA"),rs.getString("MABN"),rs.getDate("NGAY"),rs.getString("CHANDOAN"),rs.getString("MABS"),rs.getString("MAKHOA"),rs.getString("MACSYT"),rs.getString("KETLUAN"));

        }
        return result;
    }

    public List<HSBA> getDanhSachHSBAById(String id) throws SQLException {
        String query="SELECT * FROM DB."+id+"_HSBA ";
//        BenhNhan benhNhan=null;
        List<HSBA> result=new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next()){
            HSBA newHSBA=new HSBA(rs.getString("MABN"),rs.getDate("NGAY"),rs.getString("CHANDOAN"),rs.getString("MABS"),rs.getString("MAKHOA"),rs.getString("MACSYT"),rs.getString("KETLUAN"));
            result.add(newHSBA);
        }
        return result;
    }
    public List<String> getDanhSachHSBAIdById(String id) throws SQLException {
        String query="SELECT MAHSBA FROM DB.HSBA ";
//        BenhNhan benhNhan=null;
        List<String> result=new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next()){
            String maHSBA=rs.getString("MAHSBA");
            result.add(maHSBA);
        }
        return result;
    }

}
