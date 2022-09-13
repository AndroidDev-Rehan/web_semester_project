package MyPack;

import java.io.*; 
public class PersonInfo implements Serializable{ 

private String name; 
private String address; 
private String phoneNum;
private String usertype;
private String password;
private String status;

public PersonInfo() { 
name = ""; 
address = ""; 
phoneNum = ""; 
usertype="";
password="";
status = "unblocked";
} 


public void setName(String n){ 
name = n; } 

public void setAddress(String a){ 
address = a; } 

public void setPhoneNum(String pNo){ 
phoneNum = pNo;} 

public void setUserType(String type){ 
    usertype = type;} 
    
public void setPassword(String type){ 
    password = type;}
    
public void setStatus(String type){ 
    status = type;}    



public String getName( ){ 
return name;} 

public String getAddress( ){ 
return address; } 

public String getPhoneNum( ){ 
return phoneNum;} 

public String getUserType( ){ 
    return usertype;} 

public String getPassword( ){ 
    return password;} 

public String getStatus( ){ 
        return status;} 
        

} // end class PersonInfo 