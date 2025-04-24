package  fi.haaga.movie.request;
import java.util.List;

import  fi.haaga.movie.enums.Gender;

import lombok.Data;
@Data
public class UserRequest {
    private String name;
    private Integer age;
    private String address;
    private String mobileNo;
    private String emailId;
    private Gender gender;
    private String roles;
    private String password;
    
    // Add these getter/setter methods to handle the alternate field names
    public String getPhoneNumber() {
        return mobileNo;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.mobileNo = phoneNumber;
    }
    
    public String getRole() {
        return roles;
    }
    
    public void setRole(String role) {
        this.roles = role;
    }
}