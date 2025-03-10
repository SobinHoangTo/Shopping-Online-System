package models.DTOs;

public class UserDTO {

    private int id;
    private String email;
    private String fullname;
    private String role;

    public UserDTO() {
    }

    public UserDTO(int id, String email, String fullname, String role) {
        this.id = id;
        this.email = email;
        this.fullname = fullname;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
