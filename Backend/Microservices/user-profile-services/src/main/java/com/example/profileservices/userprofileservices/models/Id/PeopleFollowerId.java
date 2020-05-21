package com.example.profileservices.userprofileservices.models.Id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PeopleFollowerId implements Serializable {
    @Column(name = "follower_id")
    private Long followerId;

    @Column(name = "followee_id")
    private Long followeeId;

    public PeopleFollowerId() {
    }

    public PeopleFollowerId(Long followerId, Long followeeId) {
        this.followerId = followerId;
        this.followeeId = followeeId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getFolloweeId() {
        return followeeId;
    }

    public void setFolloweeId(Long followeeId) {
        this.followeeId = followeeId;
    }

    @Override
    public boolean equals(Object obj) {
        PeopleFollowerId peopleFollowerId= (PeopleFollowerId) obj;
        return peopleFollowerId.getFolloweeId()==followeeId && peopleFollowerId.getFollowerId()==followerId;
    }
}
