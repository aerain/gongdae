package kr.ac.jejunu.sslab.gongdae.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="reverse_auction")
public class ReverseAuction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "chosen", insertable = false, columnDefinition = "DEFAULT 0")
    private Boolean chosen;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "request_id")
    @JsonIgnoreProperties(value={"companySize"})
    private Request request;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;
    @Transient
    private List<Estimate> estimateList;
    @Transient
    private Long price;
}
