package kr.ac.jejunu.sslab.gongdae.service;

import kr.ac.jejunu.sslab.gongdae.model.Member;
import kr.ac.jejunu.sslab.gongdae.payload.MemberPayLoad;
import kr.ac.jejunu.sslab.gongdae.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public Member saveUser(Member member) {
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        return userRepository.save(member);
    }

    public Long getCurrentSessionId() {
        Member member = getCurrentUser();
        if(member != null) return member.getId();
        return 0L;
    }

    public Member getCurrentUser() {
        Object member = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(member instanceof Member)
            return (Member) member;
        return null;
    }
}
