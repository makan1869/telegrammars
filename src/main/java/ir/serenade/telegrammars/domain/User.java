package ir.serenade.telegrammars.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by serenade on 8/1/17.
 */
@Entity
@Table(name = "user_")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = true)
    private Long chatId;
    @Column(unique = true)
    private String username;
    private String password;

    private String firstName;
    private String lastName;
    private String profilePhoto;
    private String phoneNumber;

    @Transient
    private String passwordConfirm;
    @Transient
    private boolean passwordReset = false;
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(String firstName, String lastName, String username, Long chatId, String profilePhoto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.chatId = chatId;
        this.profilePhoto = profilePhoto;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.passwordReset = true;
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public boolean isPasswordReset() {
        return passwordReset;
    }

    public void setPasswordReset(boolean passwordReset) {
        this.passwordReset = passwordReset;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
