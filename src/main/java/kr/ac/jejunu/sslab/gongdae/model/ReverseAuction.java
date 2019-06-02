package kr.ac.jejunu.sslab.gongdae.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="reverse_auction")
public class ReverseAuction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "request_id")
    @JsonIgnore
    private Request request;
    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany
    @JoinColumn(name = "request_id")
    @JsonIgnore
    private List<Estimate> estimateList;
}