package kr.ac.jejunu.sslab.gongdae.api;

import kr.ac.jejunu.sslab.gongdae.model.Company;
import kr.ac.jejunu.sslab.gongdae.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompany(@PathVariable Long companyId) {
        try {
            return ResponseEntity.ok(companyService.findCompany(companyId));
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
