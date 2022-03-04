package quoctuan.com.RestfullAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import quoctuan.com.RestfullAPI.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userServiceImpl.findByUser(username);
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if(userEntity == null){
            System.out.println("User not found! " + username);

            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        grantList = userEntity.getAuthorities();

        System.out.println("Found User: " + userEntity);

        UserDetails userDetails;
        userDetails = (UserDetails) new User(userEntity.getUsername(), //
                userEntity.getPassword(), grantList);

        return userDetails;
    }
}
