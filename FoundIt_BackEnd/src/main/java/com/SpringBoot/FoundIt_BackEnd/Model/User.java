package com.SpringBoot.FoundIt_BackEnd.Model;

import com.SpringBoot.FoundIt_BackEnd.Model.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true)
    @NotBlank(message = " UserName is Mandatory ......! ")
    @Size(min = 2,message = " UserName Must Be Between 2 & 30 Characters .......! ",max = 40)
    private String userName;

    @NotBlank(message = " Email is Mandatory ......! ")
    @Email(message = " Email is Invalid .......! ")
    private String email;

    @NotBlank(message = " Password is Mandatory ......! ")
    @Size(min = 8,message = " Too Weak .......! ")
    private String password;

    @NotNull(message = " Phone Number is Mandatory ......! ")
    @Size(min = 10,max = 10,message = " Phone Number is Invalid ......! ")
    private String contact;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}

