package hr.fer.ruazosa.network_memory_game.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    @NotBlank(message = "First name cannot be emtpy")
    @Column(name = "first_name")
    private String firstName;
    @NotBlank(message = "Last name cannot be emtpy")
    @Column(name = "last_name")
    private String lastName;
    @Email(message = "Email not in correct format")
    @Column(name = "e_mail")
    private String email;
    @NotBlank(message = "Username cannot be emtpy")
    private String username;
    @NotBlank(message = "Password cannot be emtpy")
    private String password;
    @Column(name = "token")
    private String token;
    @Column(name = "wins")
    private Long wins;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setWins(Long wins) {
        this.wins = wins;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public Long getWins() {
        return wins;
    }


}
