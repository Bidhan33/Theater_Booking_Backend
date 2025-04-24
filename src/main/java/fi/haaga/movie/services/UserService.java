package fi.haaga.movie.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fi.haaga.movie.repositories.UserRepository;
import fi.haaga.movie.entities.User;
import fi.haaga.movie.exceptions.UserExist;

import fi.haaga.movie.request.UserRequest;
import fi.haaga.movie.convertor.UserConvertor;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public String addUser(UserRequest userRequest) {
		Optional<User> users = userRepository.findByEmailId(userRequest.getEmailId());
	
		if (users.isPresent()) {
			throw new UserExist();
		}
	
		// Encode the actual password from the request
		String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
		if (userRequest.getRoles() == null || userRequest.getRoles().isEmpty()) {
			// Check if the alternative role field is set
			if (userRequest.getRole() != null && !userRequest.getRole().isEmpty()) {
				userRequest.setRoles(userRequest.getRole());
			} else {
				userRequest.setRoles("USER");  // Set default role
			}
		}
	
		// Pass encoded password to the converter
		User user = UserConvertor.userDtoToUser(userRequest, encodedPassword);
		System.out.println("Saving user with email: " + user.getEmailId() + " and roles: " + user.getRoles());
	
		userRepository.save(user);
		return "User Saved Successfully";
	}

}
