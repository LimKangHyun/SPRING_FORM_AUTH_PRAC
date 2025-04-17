package io.kanghyun.form_auth_prac.dto;

import io.kanghyun.form_auth_prac.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MemberDetails implements UserDetails {

    private final String username;
    private final String password;
    private final String role;

    public MemberDetails(Member member) {
        this.username = member.getName();
        this.password = member.getPassword();
        this.role = member.getRole();
    }

    // 로그인할 때 입력한 비밀번호랑 DB에 저장된 암호화된 비밀번호를 비교할 때 사용
    @Override
    public String getPassword() {
        return this.password;
    }

    // 로그인할 때 사용자가 입력한 아이디(username) 와 비교할 때 사용
    @Override
    public String getUsername() {
        return this.username;
    }

    // 로그인한 사용자가 어떤 권한(예: ROLE_USER, ROLE_ADMIN)을 갖고 있는지 Spring Security에게 알려주는 메서드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

    // 계정의 만료 여부 확인 (true면 유효)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠김 여부 확인 (true면 유효)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 여부 확인 (ture면 유효)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부 확인 (true면 유효)
    @Override
    public boolean isEnabled() {
        return true;
    }
}