
import java.sql.*;
public class DB {
public Connection conn=null;
public DB(){
try{
Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://localhost:3306/crawler";
conn= DriverManager.getConnection(url,"root","");
}
catch(SQLException e){
e.printStackTrace();
}
catch(ClassNotFoundException e){
e.printStackTrace();
}
}

public ResultSet runsql(String sql) throws SQLException{
Statement stmt=conn.createStatement();
return stmt.executeQuery(sql);
}

public boolean runsql2(String sql) throws SQLException{
Statement stmt = conn.createStatement();
return stmt.execute(sql);
}
@Override
protected void finalize() throws Throwable{
if(conn !=null || !conn.isClosed()){
conn.close();
}
}


}


