package br.com.andrewsilva.customer.api.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.andrewsilva.customer.api.service.UserService;
import br.com.andrewsilva.customer.api.entity.User;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User usuario = userService.findByEmail(email);

		if (usuario == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);

		} else {
			return JwtUserFactory.create(usuario);
		}
	}

}
