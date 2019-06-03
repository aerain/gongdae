package kr.ac.jejunu.sslab.gongdae.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CompanyReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="company_id")
    @JsonIgnore
    private Company company;
    @ManyToOne
    @JoinColumn(name="client_id")
    @JsonIgnore
    private User user;
    private String description;
    private Integer score;
}
