package kr.ac.jejunu.sslab.gongdae.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    @OneToMany
    @JoinColumn(name="client_id")
    @JsonIgnore
    private List<CompanyReview> companyReviewList;
    @OneToMany
    @JoinColumn(name="client_id")
    @JsonIgnore
    private List<Request> requestList;

}
