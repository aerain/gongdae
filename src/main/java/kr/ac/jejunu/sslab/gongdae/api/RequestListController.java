package kr.ac.jejunu.sslab.gongdae.api;

import kr.ac.jejunu.sslab.gongdae.dao.RequestDetailRepository;
import kr.ac.jejunu.sslab.gongdae.dao.RequestRepository;
import kr.ac.jejunu.sslab.gongdae.model.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
            List<Request> requestList = requestRepository.findAll();
            requestList.parallelStream().forEach(request -> {
                request.setCompanySize((long) request.getReverseAuctionList().size());
            });
            put("data", requestRepository.findAll());
        }};
    }

    @GetMapping("/{id}")
    public Map<String, Object> getRequestById(@PathVariable Long id) {
        return new HashMap<>() {{
            Optional<Request> request = requestRepository.findById(id);
            if(request.isPresent()) {
                Request presentRequest = request.get();
                presentRequest.setCompanySize((long) presentRequest.getReverseAuctionList().size());
                put("data", presentRequest);
            } else {
                put("data", null);
            }

        }};
    }
}
