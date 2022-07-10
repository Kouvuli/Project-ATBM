package Services;

import Entities.BenhNhan;
import Entities.HSBA;
import Entities.HSBA_DV;
import Entities.NhanVien;
import Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NhanVienService {
    private Connection connection;

    public NhanVienService() {
        connection= JDBCUtils.getConnection();
    }

    public boolean deleteHSBAById(String maNV,String maHSBA) throws SQLException {
        String query="delete from db."+maNV+"_hsba where mahsba = '"+maHSBA+"'";
        Statement stmt=connection.createStatement();
        return stmt.execute(query);
    }

    public boolean deleteHSBADVById(String maNV,String maHSBA) throws SQLException {
        String query="delete from db."+maNV+"_HSBA_DV where mahsba = '"+maHSBA+"'";
        Statement stmt=connection.createStatement();
        return stmt.execute(query);
    }

    public boolean addHSBA(String maNV,HSBA hsba) throws SQLException {
        String query = "insert into db." + maNV + "_HSBA values ('" + hsba.getId() + "','" + hsba.getMaBN() + "',TO_DATE('" + new SimpleDateFormat("dd/MM/yyyy").format(hsba.getNgay()) + "', 'dd/MM/yyyy'),'" + hsba.getChanDoan() + "','" + hsba.getMaBS() + "','" + hsba.getMaKhoa() + "','" + hsba.getMaCSYT() + "','" + hsba.getKetLuan() + "')";
        Statement stmt=connection.createStatement();
        return stmt.execute(query);
    }

    public boolean addHSBADV(String maNV,HSBA_DV hsba_dv) throws SQLException {
        String query = "insert into db."+maNV+"_HSBA_DV values ('"+hsba_dv.getId()+"','"+hsba_dv.getMaDV()+"',TO_DATE('"+new SimpleDateFormat("dd/MM/yyyy").format(hsba_dv.getNgay())+"', 'dd/MM/yyyy'),'"+hsba_dv.getMaKTV()+"','"+hsba_dv.getKetQua()+"')";
        Statement stmt=connection.createStatement();
        return stmt.execute(query);
    }



    public String getRoleById(String id) throws SQLException {
        String query="SELECT VAITRO FROM DB.NHANVIEN  WHERE MANV= '"+id+"'";
//        BenhNhan benhNhan=null;
        String result=null;
        Statement stmt = connection.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        if(rs.next()){
            result=rs.getString("VAITRO");
        }
        return result;

    }
    public BenhNhan getBenhNhanByCMND(String maNV,String cmnd) throws SQLException {
        String query="SELECT * FROM DB."+maNV+"_BN WHERE CMND= '"+cmnd+"'";
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
    public boolean updateNhanVienById(String id, NhanVien nhanVien) throws SQLException {
        String query="UPDATE DB.NHANVIEN SET hoten='"+nhanVien.getHoTen()+"'," +
                "phai='" +nhanVien.getPhai()+"',"+
                "ngaysinh=to_date('" +new SimpleDateFormat("dd/MM/yyyy").format(nhanVien.getNgaySinh())+"','DD/MM/YYYY'),"+
                "cmnd='" +nhanVien.getCmnd()+"',"+
                "quequan='" +nhanVien.getQueQuan()+"',"+
                "sdt='" +nhanVien.getSdt()+"',"+
                "csyt='" +nhanVien.getCsyt()+"',"+
                "vaitro='" +nhanVien.getVaiTro()+"',"+
                "chuyenkhoa='" +nhanVien.getChuyenKhoa()+"'"+
                " WHERE MANV='"+id+"'";
        Statement stmt = connection.createStatement();
        return stmt.execute(query);
    }
    public HSBA_DV getHSBADVById(String id) throws SQLException {
        String query="SELECT * FROM DB."+id+"_HSBA_DV";
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
    public HSBA getHSBAById(String maNV, String maHSBA) throws SQLException {
        String query="select * from DB."+maNV+"_HSBA WHERE MAHSBA='"+maHSBA+"'";
//        BenhNhan benhNhan=null;
        HSBA result=null;
        Statement stmt = connection.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next()){
            result=new HSBA(rs.getString("MAHSBA"),rs.getString("MABN"),rs.getDate("NGAY"),rs.getString("CHANDOAN"),rs.getString("MABS"),rs.getString("MAKHOA"),rs.getString("MACSYT"),rs.getString("KETLUAN"));

        }
        return result;
    }
    public List<String> getDanhSachHSBAIdById(String id) throws SQLException {
        String query="SELECT MAHSBA FROM DB."+id+"_HSBA ";
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

    public BenhNhan getBenhNhanById(String maNV,String maBN) throws SQLException {
        String query="SELECT * FROM DB."+maNV+"_BN WHERE MABN= '"+maBN+"'";
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
    public NhanVien getNhanVienById(String id) throws SQLException {
        String query="SELECT * FROM DB.NHANVIEN  WHERE MANV= '"+id+"'";
        NhanVien nhanVien=null;
        Statement stmt = connection.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next()){
            nhanVien=new NhanVien(rs.getString("MANV"),
                    rs.getString("HOTEN"),
                    rs.getString("PHAI"),
                    rs.getDate("NGAYSINH"),
                    rs.getString("CMND"),
                    rs.getString("QUEQUAN"),
                    rs.getString("SDT"),
                    rs.getString("CSYT"),
                    rs.getString("VAITRO"),
                    rs.getString("CHUYENKHOA"));
        }

        return nhanVien;
    }





}
