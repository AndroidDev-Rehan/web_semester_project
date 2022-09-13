package MyPack;

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

String sql = " INSERT INTO users VALUES (?, ?, ?, ?, ?, ?)"; 
PreparedStatement pStmt = con.prepareStatement(sql); 

String name = person.getName(); 
String add = person.getAddress(); 
String pNo = person.getPhoneNum(); 
String usertype = person.getUserType(); 
String password = person.getPassword(); 

System.out.println("usertype of person in dao:");
System.out.println(person.getUserType());



pStmt.setString( 1 , name ); 
pStmt.setString( 2 , add ); 
pStmt.setString( 3 , pNo );
pStmt.setString( 4 , usertype );
pStmt.setString( 5 , password );
pStmt.setString( 6 , "unblocked" );


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
  personInfo.setStatus(rs.getString("status"));
  System.out.println("person filled"); 

}

st.close();
System.out.println("persondao login finished"); 

return personInfo;

}


public ArrayList<PersonInfo> getUsersList() throws SQLException { 

  ArrayList<PersonInfo> personList = new ArrayList<PersonInfo>(); 
    
    String sql = " SELECT * FROM users "; 
    PreparedStatement pStmt = con.prepareStatement(sql); 
    // pStmt.setString( 1, Name); 
    
    ResultSet rs = pStmt.executeQuery(); 
    
    String name; 
    String phoneNo; 
    String usertype;
    String address;
    String pass;

    while ( rs.next() ) { 
    name = rs.getString("name"); 
    phoneNo = rs.getString("phonenum"); 
    usertype = rs.getString("usertype"); 
    address = rs.getString("address");
    pass = rs.getString("password");

    PersonInfo person = new PersonInfo(); 
    person.setName(name); 
    person.setPassword(pass); 
    person.setUserType(usertype);
    person.setPhoneNum(phoneNo); 
    person.setAddress(address); 

    
    personList.add(person); 
    
    } 
    
    return personList; 
    
    }
    
public void blockUser(String name)
    throws SQLException {
    
    System.out.println("into block user "); 
        
    Statement st = con.createStatement();
    
    String query = "UPDATE users SET status = 'blocked' WHERE name = '" + name + "'" ;
    st.executeUpdate(query);
        
    st.close();
    System.out.println("persondao login finished"); 
        
    }

public void unblockUser(String name)
    throws SQLException {
    
    System.out.println("into unblock user "); 
        
    Statement st = con.createStatement();
    
    String query = "UPDATE users SET status = 'unblocked' WHERE name = '" + name + "'" ;
    st.executeUpdate(query);
        
    st.close();
    System.out.println("persondao login finished"); 
        
    }

public boolean userExists(String name)throws SQLException { 
    
    String sql = " SELECT * FROM users where name = ? "; 
    PreparedStatement pStmt = con.prepareStatement(sql); 
    pStmt.setString( 1, name); 
    
    ResultSet rs = pStmt.executeQuery(); 
    
    if ( rs.next() ) {    
      System.out.println("user exists already, dao");
 
      return true;
    }

    System.out.println("user does not exist already, dao");

    return false; 
    
    }


}
