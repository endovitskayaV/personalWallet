package ru.vsu.personalWallet.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SpringSecurityUser implements UserDetails {

    private String id;
    private String username;
    private String password;
   // private Date lastPasswordReset;
    //private Collection<? extends GrantedAuthority> authorities;
//    private Boolean accountNonExpired = true;
//    private Boolean accountNonLocked = true;
//    private Boolean credentialsNonExpired = true;
//    private Boolean enabled = true;

//    public SpringSecurityUser() {
//        super();
//    }

    SpringSecurityUser(String id, String username, String password){
        //,
                            //  Date lastPasswordReset,
                             // Collection<? extends GrantedAuthority> authorities) {
       // super(id, username, password);
//        this.setId(id);
//        this.setUsername(username);
//        this.setPassword(password);
//        this.setLastPasswordReset(lastPasswordReset);
//        this.setAuthorities(authorities);

        this.id=id;
        this.username=username;
        this.password=password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

//    public String getId() {
//        return this.id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return this.username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    @JsonIgnore
//    public String getPassword() {
//        return this.password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @JsonIgnore
//    public Date getLastPasswordReset() {
//        return this.lastPasswordReset;
//    }
//
//    public void setLastPasswordReset(Date lastPasswordReset) {
//        this.lastPasswordReset = lastPasswordReset;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.authorities;
//    }
//
//    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
//        this.authorities = authorities;
//    }
//
//    @JsonIgnore
//    public Boolean getAccountNonExpired() {
//        return this.accountNonExpired;
//    }
//
//    public void setAccountNonExpired(Boolean accountNonExpired) {
//        this.accountNonExpired = accountNonExpired;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return this.getAccountNonExpired();
//    }
//
//    @JsonIgnore
//    public Boolean getAccountNonLocked() {
//        return this.accountNonLocked;
//    }
//
//    public void setAccountNonLocked(Boolean accountNonLocked) {
//        this.accountNonLocked = accountNonLocked;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return this.getAccountNonLocked();
//    }
//
//    @JsonIgnore
//    public Boolean getCredentialsNonExpired() {
//        return this.credentialsNonExpired;
//    }
//
//    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
//        this.credentialsNonExpired = credentialsNonExpired;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return this.getCredentialsNonExpired();
//    }
//
//    @JsonIgnore
//    public Boolean getEnabled() {
//        return this.enabled;
//    }
//
//    public void setEnabled(Boolean enabled) {
//        this.enabled = enabled;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return this.getEnabled();
//    }

}