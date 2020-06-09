package com.example.profileservices.userprofileservices.util.response;

public class UserReputationResponse {
    private Long userId;
    private Long reputation;

    public UserReputationResponse() {
    }

    public UserReputationResponse(Long userId, Long reputation) {
        this.userId = userId;
        this.reputation = reputation;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReputation() {
        return reputation;
    }

    public void setReputation(Long reputation) {
        this.reputation = reputation;
    }
}
