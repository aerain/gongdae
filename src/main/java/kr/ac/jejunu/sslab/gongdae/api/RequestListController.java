package kr.ac.jejunu.sslab.gongdae.api;

import kr.ac.jejunu.sslab.gongdae.dao.RequestDetailRepository;
import kr.ac.jejunu.sslab.gongdae.dao.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/request-list")
@RequiredArgsConstructor
public class RequestListController {

    private final RequestRepository requestRepository;
    private final RequestDetailRepository requestDetailRepository;
    @GetMapping
    public Map<String, Object> getRequestList() {
        Long clientId = 1L;
        return new HashMap<>() {{
            put("data", requestRepository.findAll());
        }};
    }

    @GetMapping("/{id}")
    public Map<String, Object> getRequestById(@PathVariable Long id) {
        return new HashMap<>() {{
            put("data", requestDetailRepository.findById(id));
        }};
    }
}
