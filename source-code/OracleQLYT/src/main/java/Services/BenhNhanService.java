package Services;

import Entities.BenhNhan;
import Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class BenhNhanService {

    private Connection connection;

    public BenhNhanService() {
        connection= JDBCUtils.getConnection();
    }
    public BenhNhan getBenhNhanById(String id) throws SQLException {

        String query="SELECT * FROM DB.BENHNHAN BN WHERE BN.MABN= '"+id+"'";
        BenhNhan benhNhan=null;
        Statement stmt = connection.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next()){
            benhNhan=new BenhNhan(rs.getString("MABN"),
                    rs.getString("MACSYT"),
                    rs.getString("TENBN"),
                    rs.getString("CMND"),
                    rs.getDate("NGAYSINH"),
                    rs.getString("SONHA"),
                    rs.getString("TENDUONG"),
                    rs.getString("QUANHUYEN"),
                    rs.getString("TINHTP"),
                    rs.getString("TIEUSUBENH"),
                    rs.getString("TIEUSUBENHGD"),
                    rs.getString("DIUNGTHUOC"));
        }

        return benhNhan;
    }
    public BenhNhan getBenhNhanByCMND(String cmnd) throws SQLException {
        String query="SELECT * FROM DB.BENHNHAN WHERE CMND= '"+cmnd+"'";
        BenhNhan benhNhan=null;
        Statement stmt = connection.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next()){
            benhNhan=new BenhNhan(rs.getString("MABN"),
                    rs.getString("MACSYT"),
                    rs.getString("TENBN"),
                    rs.getString("CMND"),
                    rs.getDate("NGAYSINH"),
                    rs.getString("SONHA"),
                    rs.getString("TENDUONG"),
                    rs.getString("QUANHUYEN"),
                    rs.getString("TINHTP"),
                    rs.getString("TIEUSUBENH"),
                    rs.getString("TIEUSUBENHGD"),
                    rs.getString("DIUNGTHUOC"));
        }

        return benhNhan;
    }

    public boolean updateBenhNhanById(String id,BenhNhan benhNhan) throws SQLException {
        String query="UPDATE DB.BENHNHAN SET " +
                "macsyt ='" +benhNhan.getCsytId()+"',"+
                "tenbn ='" +benhNhan.getTen()+"',"+
                "cmnd ='" +benhNhan.getCmnd()+"',"+
                "ngaysinh =to_date('" +new SimpleDateFormat("dd/MM/yyyy").format(benhNhan.getNgaySinh())+"','DD/MM/YYYY'),"+
                "sonha ='" +benhNhan.getSoNha()+"',"+
                "tenduong ='" +benhNhan.getTenDuong()+"',"+
                "quanhuyen ='" +benhNhan.getQuanHuyen()+"',"+
                "tinhtp ='" +benhNhan.getTinhTP()+"',"+
                "tieusubenh ='" +benhNhan.getTieuSuBenh()+"',"+
                "tieusubenhgd ='" +benhNhan.getTieuSuBenhGD()+"',"+
                "diungthuoc ='" +benhNhan.getDiUngThuoc()+"'"+
                " WHERE MABN='"+id+"'";
        Statement stmt = connection.createStatement();
        return stmt.execute(query);
    }

}
