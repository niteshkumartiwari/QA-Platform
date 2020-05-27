package com.example.profileservices.userprofileservices.util.response;

public class InterestReputationResponse {
    private Long interestId;
    private Long reputation;

    public InterestReputationResponse() {
    }

    public InterestReputationResponse(Long interestId, Long reputation) {
        this.interestId = interestId;
        this.reputation = reputation;
    }

    public Long getInterestId() {
        return interestId;
    }

    public void setInterestId(Long interestId) {
        this.interestId = interestId;
    }

    public Long getReputation() {
        return reputation;
    }

    public void setReputation(Long reputation) {
        this.reputation = reputation;
    }
}
