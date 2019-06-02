package kr.ac.jejunu.sslab.gongdae.api;

import kr.ac.jejunu.sslab.gongdae.dao.RequestDetailRepository;
import kr.ac.jejunu.sslab.gongdae.model.RequestDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/request-detail")
@RequiredArgsConstructor
public class RequestSubListController {
    private final RequestDetailRepository requestDetailRepository;

    @GetMapping("/{id}")
    public Map<String, Object> findRequestDetailListByRequestId(@PathVariable Long id) {
        return new HashMap<>() {{
            put("data", requestDetailRepository.findAllByRequestId(id));
        }};
    }
}
