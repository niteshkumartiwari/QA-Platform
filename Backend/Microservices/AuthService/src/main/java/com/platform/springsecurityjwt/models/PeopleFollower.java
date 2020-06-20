package com.platform.springsecurityjwt.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platform.springsecurityjwt.models.id.PeopleFollowerId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "people_follower",schema = "public")
public class PeopleFollower implements Serializable {
    @EmbeddedId
    private PeopleFollowerId id;

    private transient Long followTo;

    @ManyToOne
    @JoinColumn(name = "follower_id", insertable = false, updatable = false)
    private User follower;

    private transient Long followFrom;

    @ManyToOne
    @JoinColumn(name = "followee_id", insertable = false, updatable = false)
    private User followee;

    public PeopleFollower() {
    }

    public PeopleFollower(PeopleFollowerId id, User follower, User followee) {
        this.id = id;
        this.follower = follower;
        this.followee = followee;
    }

    public PeopleFollowerId getId() {
        return id;
    }

    public void setId(PeopleFollowerId id) {
        this.id = id;
    }

    public Long getFollowTo() {
        return followTo;
    }

    public void setFollowTo(Long followTo) {
        this.followTo = followTo;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public Long getFollowFrom() {
        return followFrom;
    }

    public void setFollowFrom(Long followFrom) {
        this.followFrom = followFrom;
    }

    public User getFollowee() {
        return followee;
    }

    public void setFollowee(User followee) {
        this.followee = followee;
    }
}
