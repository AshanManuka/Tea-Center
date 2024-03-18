package com.designCenter.designCenter.service;

import com.designCenter.designCenter.entity.User;
import com.designCenter.designCenter.repository.UserRepository;
import com.designCenter.designCenter.util.MyUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUser_name(username);
        if (user == null){
            throw new UsernameNotFoundException("Not found :"+ username);
        }
        log.info("UserName : {}, password : {}, Role : {}",user.getUser_name(),user.getPassword(),user.getRoles());

        return new MyUserDetails(user);
    }
}
