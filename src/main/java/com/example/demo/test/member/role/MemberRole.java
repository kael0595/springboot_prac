package com.example.demo.test.member.role;

public enum MemberRole {

    MEMBER("ROLE_MEMBER"),
    ADMIN("ROLE_ADMIN");

    private String role;

    MemberRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
