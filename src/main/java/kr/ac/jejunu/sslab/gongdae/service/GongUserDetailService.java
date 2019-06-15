package kr.ac.jejunu.sslab.gongdae.service;

import kr.ac.jejunu.sslab.gongdae.model.Member;
import kr.ac.jejunu.sslab.gongdae.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GongUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String param) throws UsernameNotFoundException {
        Optional<Member> member = null;
        if(param.contains("@"))
           member = userRepository.findByemail(param);
        else
            member = userRepository.findByName(param);

        if(member.isEmpty()) throw new UsernameNotFoundException(param);

        return member.get();
    }
}
