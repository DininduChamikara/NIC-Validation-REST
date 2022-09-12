
package Model;

import Utilities.DBconnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
}
