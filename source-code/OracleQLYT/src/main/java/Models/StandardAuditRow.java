package Models;

public class StandardAuditRow {
    private String dbUsername;
    private String actionName;
    private String objectSchema;
    private String currentUser;
    private String targetUser;
    private String sqlText;
    private String auditOption;

    public StandardAuditRow(String dbUsername, String actionName, String objectSchema, String currentUser, String targetUser, String sqlText, String auditOption) {
        this.dbUsername = dbUsername;
        this.actionName = actionName;
        this.objectSchema = objectSchema;
        this.currentUser = currentUser;
        this.targetUser = targetUser;
        this.sqlText = sqlText;
        this.auditOption = auditOption;
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

    public String getObjectSchema() {
        return objectSchema;
    }

    public void setObjectSchema(String objectSchema) {
        this.objectSchema = objectSchema;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public String getAuditOption() {
        return auditOption;
    }

    public void setAuditOption(String auditOption) {
        this.auditOption = auditOption;
    }
}
