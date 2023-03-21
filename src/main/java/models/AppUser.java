package models;

import javax.persistence.*;

@Entity
@Table(schema = "public")
@NamedQueries({
        @NamedQuery(name="AppUser.getAllUsers",query = "SELECT count(u) from AppUser u where u.name not like 'Admin%'"),
        @NamedQuery(name = "AppUser.getUserByMail",query = "SELECT u FROM AppUser u where u.email=:email")
}
)

public class AppUser {

    private String name;
    @Id
    private String email;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
