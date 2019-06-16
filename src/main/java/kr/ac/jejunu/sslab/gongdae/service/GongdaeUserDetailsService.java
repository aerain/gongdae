package kr.ac.jejunu.sslab.gongdae.service;

import kr.ac.jejunu.sslab.gongdae.model.Member;
import kr.ac.jejunu.sslab.gongdae.repository.UserRepository;
import kr.ac.jejunu.sslab.gongdae.vo.SessionIdVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GongdaeUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member = userRepository.findByusername(username);
        if(member.isEmpty()) throw new UsernameNotFoundException(username);
        return member.get();
    }


}
