package kr.ac.jejunu.sslab.gongdae.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Company implements Serializable {
    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @JsonIgnoreProperties({"password"})
    @MapsId
    private Member member;

    private String description;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CompanyReview> companyReview;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer score;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer chosenCount;
}
