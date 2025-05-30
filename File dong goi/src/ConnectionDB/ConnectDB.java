package ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;

public class ConnectDB {
	public static Connection con = null;
    private static ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() {
        return instance;
    }

    public void connect() throws Exception {

        String url = "jdbc:sqlserver://localhost:1433;databaseName=QLyLuongSP";
        String user = "sa";
        String password = "sapassword";
        con = DriverManager.getConnection(url, user, password);

    }

    public void disconect() {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public static void main(String[] args) {
        Date temp = new Date();
        System.out.println(temp.compareTo(new Date()));
        try {
            ConnectDB.getInstance().connect();
            System.out.println("Yes");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
	
}
