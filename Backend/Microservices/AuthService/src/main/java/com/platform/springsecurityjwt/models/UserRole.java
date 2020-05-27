package com.platform.springsecurityjwt.models;


import com.platform.springsecurityjwt.models.id.UserRoleId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_role",schema = "public",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id","role_id"})
})
public class UserRole implements Serializable {
    @EmbeddedId
    private UserRoleId id;

    private transient Long usrId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    private transient Long rleId;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    public UserRole() {
    }

    public UserRole(UserRoleId id, User user, Role role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public UserRoleId getId() {
        return id;
    }

    public void setId(UserRoleId id) {
        this.id = id;
    }

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getRleId() {
        return rleId;
    }

    public void setRleId(Long rleId) {
        this.rleId = rleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
