package types;

public class Config {
    private static String DB_URL = "jdbc:mysql://localhost/";
    private static String DB_NAME = "online_store";
    private static String DB_USER = "root";
    private static String DB_PASS = "";

    public static String getDbUrl() {
        return DB_URL;
    }

    public static void setDbUrl(String dbUrl) {
        DB_URL = dbUrl;
    }

    public static String getDbName() {
        return DB_NAME;
    }

    public static void setDbName(String dbName) {
        DB_NAME = dbName;
    }

    public static String getDbUser() {
        return DB_USER;
    }

    public static void setDbUser(String dbUser) {
        DB_USER = dbUser;
    }

    public static String getDbPass() {
        return DB_PASS;
    }

    public static void setDbPass(String dbPass) {
        DB_PASS = dbPass;
    }
}
