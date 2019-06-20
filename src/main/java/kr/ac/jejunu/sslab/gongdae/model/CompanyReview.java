package kr.ac.jejunu.sslab.gongdae.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "review")
public class CompanyReview implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="company_id")
    @JsonIgnore
    private Company company;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="member_id")
    @JsonIgnore
    private Member member;
    private String description;
    private Integer score;
}
