package fi.haaga.movie.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import fi.haaga.movie.entities.User;
import fi.haaga.movie.repositories.UserRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("Loading user details for username: " + username);
    Optional<User> userInfo = repository.findByEmailId(username);
    
    if (userInfo.isPresent()) {
        System.out.println("User found with roles: " + userInfo.get().getRoles());
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    } else {
        System.out.println("User not found: " + username);
        throw new UsernameNotFoundException("user not found " + username);
    }
}


}