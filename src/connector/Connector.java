package connector;

import java.sql.*;

public class Connector {
    static final String JdbcDriver = "org.postgresql.Driver";

    public Connection conn = null;

    public Connector(String url, Integer port, String dbname, String username, String passwd) throws ClassNotFoundException, SQLException {
        Class.forName(JdbcDriver);
        String _url = "jdbc:postgresql://" + url + ":" + port.toString() + "/" + dbname;
        System.out.println("connect to database...");
        conn = DriverManager.getConnection(_url, username, passwd);
        System.out.println("successfully connect!");
    }

    public ResultSet ExecSql(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    }
}
