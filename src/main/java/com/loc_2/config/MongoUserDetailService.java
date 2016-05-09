package com.loc_2.config;

import com.google.common.collect.Sets;
import com.loc_2.daos.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

/**
 * Created by Rafal on 2016-03-20.
 */
@Configuration
public class MongoUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        userRepository = new UserRepository();
        com.loc_2.entities.User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        Collection<SimpleGrantedAuthority> authorities = Sets.newHashSet(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
