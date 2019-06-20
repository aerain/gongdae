package kr.ac.jejunu.sslab.gongdae.api;

import kr.ac.jejunu.sslab.gongdae.model.Company;
import kr.ac.jejunu.sslab.gongdae.model.CompanyReview;
import kr.ac.jejunu.sslab.gongdae.payload.CompanyReviewPayLoad;
import kr.ac.jejunu.sslab.gongdae.service.CompanyService;
import kr.ac.jejunu.sslab.gongdae.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final ReviewService reviewService;
    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompany(@PathVariable Long companyId) {
        try {
            return ResponseEntity.ok(companyService.findCompany(companyId));
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping(value = "/{companyId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addReview(@PathVariable Long companyId, @RequestBody CompanyReview review) {
        try {
            reviewService.addReview(companyId, review);
            return ResponseEntity.ok().build();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
