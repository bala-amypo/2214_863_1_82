package com.example.demo.dto;

public class CredentialStatusDto {

    private Long credentialId;
    private String status;

    public CredentialStatusDto() {}

    public CredentialStatusDto(Long credentialId, String status) {
        this.credentialId = credentialId;
        this.status = status;
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}