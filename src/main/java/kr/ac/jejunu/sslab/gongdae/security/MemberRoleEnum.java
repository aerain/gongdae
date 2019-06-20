package kr.ac.jejunu.sslab.gongdae.security;

public enum MemberRoleEnum {
    user("USER"),
    company("COMPANY"),
    admin("ADMIN");

    private final String role;
    private MemberRoleEnum(String role) { this.role = role; }
    public String getRole() {
        return role;
    }
}
