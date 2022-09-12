package MyPack;

import MyPack.PersonInfo;
import java.util.*; 
import java.sql.*; 

public class ProductDao{ 

private Connection con; 

public ProductDao() throws ClassNotFoundException,SQLException{
establishConnection(); } 

private void establishConnection() throws ClassNotFoundException,SQLException{ 
    System.out.println("establishing connection "); 
    
    Class.forName("com.mysql.jdbc.Driver"); 
    String conUrl = "jdbc:mysql://127.0.0.1/virtual_store?characterEncoding=utf8"; 
    con = DriverManager.getConnection(conUrl,"root","root123");
    System.out.println("connection established"); 
    } 
    


public ArrayList<Product> getProductsList() throws SQLException { 

ArrayList<Product> productList = new ArrayList<Product>(); 

String sql = " SELECT * FROM products "; 
PreparedStatement pStmt = con.prepareStatement(sql); 
// pStmt.setString( 1, Name); 

ResultSet rs = pStmt.executeQuery(); 

String name; 
String color; 
String company;
String price;

while ( rs.next() ) { 
name = rs.getString("name"); 
color = rs.getString("color"); 
company = rs.getString("company"); 
price = rs.getString("price");

Product product = new Product(); 
product.setName(name); 
product.setColor(color); 
product.setCompany(company); 
product.setPrice(price);

productList.add(product); 

} 

return productList; 

}

public ArrayList<Product> getSearchProductsList(String productName) throws SQLException { 

    ArrayList<Product> productList = new ArrayList<Product>(); 
    
    String sql = " SELECT * FROM products WHERE name = ?"; 
    PreparedStatement pStmt = con.prepareStatement(sql); 
    pStmt.setString( 1, productName); 
    
    ResultSet rs = pStmt.executeQuery(); 
    
    String name; 
    String color; 
    String company;
    String price;
    
    while ( rs.next() ) { 
    name = rs.getString("name"); 
    color = rs.getString("color"); 
    company = rs.getString("company"); 
    price = rs.getString("price");
    
    Product product = new Product(); 
    product.setName(name); 
    product.setColor(color); 
    product.setCompany(company); 
    product.setPrice(price);
    
    productList.add(product); 
    
    } 
    
    return productList; 
    
    }
    
public void addProduct(Product product) throws SQLException{ 

    String sql = " INSERT INTO products VALUES (?, ?, ?, ?)"; 
    PreparedStatement pStmt = con.prepareStatement(sql); 
    
    String name = product.getName(); 
    String color = product.getColor(); 
    String company = product.getCompany(); 
    String price = product.getPrice(); 
    
    
    pStmt.setString( 1 , name ); 
    pStmt.setString( 2 , color ); 
    pStmt.setString( 3 , company );
    pStmt.setString( 4 , price );    
    
    pStmt.executeUpdate(); 
    
    } 

public void deleteProduct(String name) throws SQLException{ 

        String sql = " DELETE FROM products WHERE name = ? "; 
        PreparedStatement pStmt = con.prepareStatement(sql); 
                        
        pStmt.setString( 1 , name ); 
        
        pStmt.executeUpdate(); 
        
        } 
    

    
}
