
package Model;

import Utilities.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

public class User {

    private String id;
    private String nic;
    private String fullname;
    private String address;
    private String nationality;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public User(String id, String nic, String fullname, String address, String nationality) {
        this.id = id;
        this.nic = nic;
        this.fullname = fullname;
        this.address = address;
        this.nationality = nationality;
    }
    
    public static List<User> find(){
        List<User> users = new ArrayList<User>();
        User user;
        String sql = "SELECT * FROM nic_register.users";
        ResultSet resultSet;
        
        try{
            System.out.println("Try Started");
            Connection con = DBconnection.createConnection();
            System.out.println("Connection Created");
            Statement stmt = con.createStatement();
            System.out.println("stmt Created");
            resultSet = stmt.executeQuery(sql);
            
//            System.out.println("Result " + resultSet.getString("address"));
            
            while(resultSet.next()){
                user = new User();
                user.setId(resultSet.getString("id"));
                user.setNic(resultSet.getString("nic"));
                user.setFullname(resultSet.getString("fullname"));
                user.setAddress(resultSet.getString("address"));
                user.setNationality(resultSet.getString("nationality"));
                users.add(user);
                System.out.println(user);
            }
            
            
        }catch(Exception e){
            System.out.println("The Error is " + e.getMessage());
        }
        
        return users;
    }
    
    public static void save(User user){
        
        String sql = "INSERT INTO nic_register.users(nic, fullname, address, nationality) VALUES(?,?,?,?)";
        
        String nicNumber = user.getNic();
        String fullname = user.getFullname();
        String address = user.getAddress();
        String nationality = user.getNationality();
        
        try {
            Connection con = DBconnection.createConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, nicNumber);
            ps.setString(2, fullname);
            ps.setString(3, address);
            ps.setString(4, nationality);
            
            ps.executeUpdate();
            System.out.println("Successfully saved the values in db from User.java");
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
    public static User getUserData(User user){
        
        User u = new User();
        String id = user.getId();
        ResultSet resultSet;
        
        String sql = "SELECT * FROM nic_register.users WHERE id='" + id + "';";
        
        try {
            
            Connection con = DBconnection.createConnection();
            Statement stmt = con.createStatement();
            
            System.out.println("Statement Created");
            resultSet = stmt.executeQuery(sql);
            System.out.println("Resultset is ok");
            
            resultSet.next();
            
            System.out.println("Result set " + resultSet.getString("id"));
            
            u.setId(resultSet.getString("id"));
            u.setNic(resultSet.getString("nic"));
            u.setFullname(resultSet.getString("fullname"));
            u.setAddress(resultSet.getString("address"));
            u.setNationality(resultSet.getString("nationality"));
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
        return u;
    }
    
    
    public static void edit(User user){
        
        String sql = "UPDATE nic_register.users SET nic=?, fullname=?, address=?, nationality=? WHERE id=?";
        
        String id = user.getId();
        String nicNumber = user.getNic();
        String fullname = user.getFullname();
        String address = user.getAddress();
        String nationality = user.getNationality();
        
        try {
            Connection con = DBconnection.createConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, nicNumber);
            ps.setString(2, fullname);
            ps.setString(3, address);
            ps.setString(4, nationality);
            ps.setString(5, id);
            
            ps.executeUpdate();
            System.out.println("Successfully updated the values in db from User.java");
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void deleteUserData(User user){
        
        System.out.println("In delete user function");
        
        User deletedUser = new User();
        
        try{
            
            String id = user.getId();
            
            Connection con;
            con = DBconnection.createConnection();

            String sql = "DELETE FROM nic_register.users WHERE id=?";
             
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, id);
            ps.executeUpdate();
            
        }catch(Exception e){
            System.out.println("The Error is : " + e.getMessage());
        }
        
    }
    
    
}
