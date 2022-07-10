package Services;

import Models.*;
import Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuditService {
    private Connection connection;

    public AuditService() {
        connection= JDBCUtils.getConnection();
    }

    public List<StandardAuditRow> getStandardAudit() throws SQLException {
        String query="select dbusername,action_name,object_schema,object_name,current_user,target_user,SQL_TEXT,audit_option from UNIFIED_AUDIT_TRAIL " +
                "where audit_type = 'Standard' " +
                "order by event_timestamp desc";
        Statement stmt = connection.createStatement();
        List<StandardAuditRow> result=new ArrayList<>();
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next()){
            StandardAuditRow row=new StandardAuditRow(rs.getString("dbusername"),
                    rs.getString("action_name"),
                    rs.getString("object_schema"),
                    rs.getString("current_user"),
                    rs.getString("target_user"),
                    rs.getString("SQL_TEXT"),
                    rs.getString("audit_option"));
            result.add(row);
        }
        return result;
    }

    public List<FineGrainedAuditRow> getFineGrainedAudit() throws SQLException {
        String query="select audit_type, dbusername, action_name, event_timestamp, sql_text, fga_policy_name " +
                "from unified_audit_trail " +
                "where audit_type = 'FineGrainedAudit' " +
                "order by event_timestamp desc";
        Statement stmt = connection.createStatement();
        List<FineGrainedAuditRow> result=new ArrayList<>();
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next()){
            FineGrainedAuditRow row=new FineGrainedAuditRow(
                    rs.getString("audit_type"),
                    rs.getString("dbusername"),
                    rs.getString("action_name"),
                    rs.getString("event_timestamp"),
                    rs.getString("sql_text"),
                    rs.getString("fga_policy_name"));
            result.add(row);
        }
        return result;
    }

}
