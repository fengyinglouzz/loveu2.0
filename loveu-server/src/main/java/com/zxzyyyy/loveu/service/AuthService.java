package com.zxzyyyy.loveu.service;

import com.zxzyyyy.loveu.dto.AuthDto;
import com.zxzyyyy.loveu.dto.UserDetailsDto;
import com.zxzyyyy.loveu.entity.Auth;
import com.zxzyyyy.loveu.model.AuthDetail;
import com.zxzyyyy.loveu.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AuthService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    //增加用户
    public Auth add(Auth auth) {
        return authRepository.saveAndFlush(auth);
    }

    //根据名字查用户
    public Auth findByUserName(String username) {
        return authRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Auth auth = authRepository.findByUsername(s);
        if (auth == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", s));
        }
        AuthDetail authDetail = new AuthDetail();
        authDetail.setUsername(auth.getUsername());
        authDetail.setPassword(auth.getPassword());
        authDetail.setAuthorities(auth.getRole().getRoleName());
        return create(authDetail);
    }

    public static UserDetails create(AuthDetail authDetail) {
        Collection<GrantedAuthority> authorities;
        try {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authDetail.getAuthorities());
        } catch (Exception e) {
            authorities = null;
        }
        return new UserDetailsDto(authDetail.getUsername(), authDetail.getPassword(), authorities);
    }
}
