package io.kanghyun.form_auth_prac.app;

import io.kanghyun.form_auth_prac.dao.MemberRepository;
import io.kanghyun.form_auth_prac.domain.Member;
import io.kanghyun.form_auth_prac.dto.MemberDetails;
import io.kanghyun.form_auth_prac.dto.SignUpForm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public void save(SignUpForm signUpForm) {
        Member member = Member.builder()
                .username(signUpForm.getUsername())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .email(signUpForm.getEmail())
                .build();
        memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> memberOptional = memberRepository.findByUsername(username);
        Member findMember = memberOptional.orElseThrow(
                () -> new UsernameNotFoundException("Username " + username + " not found")
        );

        return new MemberDetails(findMember);
    }
}