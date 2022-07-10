package Models;

public class FineGrainedAuditRow {
    private String auditType;
    private String dbUsername;
    private String actionName;
    private String eventTimestamp;
    private String sqlText;
    private String fgaPolicyName;

    public FineGrainedAuditRow() {
    }

    public FineGrainedAuditRow(String auditType, String dbUsername, String actionName, String eventTimestamp, String sqlText, String fgaPolicyName) {
        this.auditType = auditType;
        this.dbUsername = dbUsername;
        this.actionName = actionName;
        this.eventTimestamp = eventTimestamp;
        this.sqlText = sqlText;
        this.fgaPolicyName = fgaPolicyName;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(String eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public String getFgaPolicyName() {
        return fgaPolicyName;
    }

    public void setFgaPolicyName(String fgaPolicyName) {
        this.fgaPolicyName = fgaPolicyName;
    }
}
