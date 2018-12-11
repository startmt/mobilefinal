package a59070038.kmitl.ac.th.mobilefinal.dto;

public class Post {
    private int id;
    private String username, email, phone, website;

    public Post(int id, String username, String email, String phone, String website) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }
}
