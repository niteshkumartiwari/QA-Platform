package com.example.profileservices.userprofileservices.models;

import com.example.profileservices.userprofileservices.models.Id.UserInterestId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_interest_reputation")
public class UserInterest implements Serializable {
    @EmbeddedId
    private UserInterestId id;

    private transient Long usrId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    private transient Long intrestId;

    @ManyToOne
    @JoinColumn(name = "interest_id", insertable = false, updatable = false)
    private Interest interest;

    @Column(name = "reputation")
    private Long reputation;

    public UserInterest() {
        this.reputation=Long.valueOf(0);
    }

    public UserInterest(UserInterestId id, User user, Interest interest, Long reputation) {
        this.id = id;
        this.user = user;
        this.interest = interest;
        this.reputation = reputation;
    }

    public UserInterestId getId() {
        return id;
    }

    public void setId(UserInterestId id) {
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

    public Long getIntrestId() {
        return intrestId;
    }

    public void setIntrestId(Long intrestId) {
        this.intrestId = intrestId;
    }

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }

    public Long getReputation() {
        return reputation;
    }

    public void setReputation(Long reputation) {
        this.reputation = reputation;
    }
}
