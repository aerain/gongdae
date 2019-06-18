package kr.ac.jejunu.sslab.gongdae.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kr.ac.jejunu.sslab.gongdae.MemberRoleEnum;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "member")
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @JsonIgnore
    private String password;
    @Column(name = "name")
    private String username;
    @Column(insertable = false, columnDefinition = "DEFAULT 1 NOT NULL")
    @JsonIgnore
    private Boolean enabled;
    @JsonIgnore
    private Integer type;
    @Transient
    @JsonIgnore
    private List<CompanyReview> companyReviewList;
    @JsonIgnore
    @Transient
    private List<Request> requestList;
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role;
        switch(type) {
            case 0:
                role = MemberRoleEnum.user.getRole();
                break;
            case 1:
                role = MemberRoleEnum.company.getRole();
                break;
            case 2:
                role = MemberRoleEnum.admin.getRole();
                break;
            default:
                role = "NO";
                break;
        }
        Collection<GrantedAuthority> auth = new ArrayList<>() {{
            add(new SimpleGrantedAuthority(role));
        }};
        return auth;
    }
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
