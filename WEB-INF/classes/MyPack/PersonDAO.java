package MyPack;

import MyPack.PersonInfo;
import java.util.*; 
import java.sql.*; 

public class PersonDAO{ 

private Connection con; 

public PersonDAO() throws ClassNotFoundException,SQLException{
establishConnection(); } 

private void establishConnection() throws ClassNotFoundException,SQLException{ 
System.out.println("establishing connection "); 

Class.forName("com.mysql.jdbc.Driver"); 
String conUrl = "jdbc:mysql://127.0.0.1/virtual_store?characterEncoding=utf8"; 
con = DriverManager.getConnection(conUrl,"root","root123");
System.out.println("connection established"); 
} 


public void addPerson(PersonInfo person) throws SQLException{ 

String sql = " INSERT INTO users VALUES (?, ?, ?, ?, ?)"; 
PreparedStatement pStmt = con.prepareStatement(sql); 

String name = person.getName(); 
String add = person.getAddress(); 
String pNo = person.getPhoneNum(); 
String usertype = person.getUserType(); 
String password = person.getPassword(); 


pStmt.setString( 1 , name ); 
pStmt.setString( 2 , add ); 
pStmt.setString( 3 , pNo );
pStmt.setString( 4 , usertype );
pStmt.setString( 5 , password );


pStmt.executeUpdate(); 

} 

public PersonInfo login(String name, String ps)
throws SQLException {

System.out.println("into persondao login "); 


Statement st = con.createStatement();

String query = "Select * from users where name='" + name + "' and password='" + ps + "' ";
PersonInfo personInfo = null;
ResultSet rs = st.executeQuery(query);

if (rs.next()) {

  personInfo = new PersonInfo();
  personInfo.setName(rs.getString("name"));
  personInfo.setAddress(rs.getString("address"));
  personInfo.setPassword(rs.getString("password"));
  personInfo.setUserType(rs.getString("usertype"));
  personInfo.setPhoneNum(rs.getString("phonenum"));
  System.out.println("person filled"); 

}

st.close();
System.out.println("persondao login finished"); 

return personInfo;

}

}
