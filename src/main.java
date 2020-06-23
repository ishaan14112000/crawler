import java.sql.*;
import java.io.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class main {
    public static DB db =new DB();
    public static void main(String args[]) throws SQLException, IOException{

    db.runsql2("TRUNCATE records;");
    processpage("https://www.flipkart.com");
    }

    public static void processpage(String url) throws SQLException, IOException{
    String sql="select * from records where url='"+url+"'";
    ResultSet rs= db.runsql(sql);
    if(rs.next()){
    }
 else{
 sql = "INSERT INTO  `Crawler`.`records` " + "(`URL`) VALUES " + "(?);";
 PreparedStatement stmt=db.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
 stmt.setString(1,url);
 stmt.execute();

 Document doc=Jsoup.connect("https://www.flipkart.com/").get();

 if(doc.text().contains("smart")){
 System.out.println(url);
 }

 Elements questions = doc.select("a[href]");
 for(Element link : questions){
 if(link.attr("href").contains("flipkart.com"))
     processpage(link.attr("abs:href"));
 }
 }
    }

}
