package kr.ac.jejunu.sslab.gongdae.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Getter(AccessLevel.NONE)
    private String password;
    private String name;
    @Column(insertable = false, columnDefinition = "DEFAULT 1 NOT NULL")
    private Boolean enabled;
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="user_id")
//    @JsonIgnore
    @Transient
    private List<CompanyReview> companyReviewList;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    @JsonIgnore
    private List<Request> requestList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Todo USER
        Collection<GrantedAuthority> auth = new ArrayList<>() {{
            add(new SimpleGrantedAuthority(MemberRoleEnum.user.getRole()));
        }};
        return auth;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
