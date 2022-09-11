package MyPack;

import java.io.*; 
public class Product implements Serializable{ 

private String name; 
private String price; 
private String color;
private String company;

public Product() { 
name = ""; 
price = ""; 
color = ""; 
company="";
} 


public void setName(String n){ 
name = n; } 

public void setPrice(String a){ 
price = a; } 

public void setColor(String pNo){ 
color = pNo;} 

public void setCompany(String type){ 
    company = type;} 


public String getName( ){ 
return name;} 

public String getColor( ){ 
return color; } 

public String getPrice( ){ 
return price;} 

public String getCompany( ){ 
    return company;} 
    
} // end class PersonInfo 