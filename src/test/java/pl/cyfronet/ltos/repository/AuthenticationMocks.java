package pl.cyfronet.ltos.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import pl.cyfronet.ltos.bean.User;
import pl.cyfronet.ltos.security.OurUser;

public class AuthenticationMocks {

	private AuthenticationMocks() {
	}

	public static Authentication userAuthentication(Long id) {
		User user = User.builder().id(id).build();
		List<GrantedAuthority> auth = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),new SimpleGrantedAuthority("ROLE_USER"));
		OurUser pu = OurUser.builder().principal(user).authorities(auth).build();
		return new TestingAuthenticationToken(pu, null, auth);
	}

	public static Authentication adminAuthentication(Long id) {
		User user = User.builder().id(id).build();
		List<GrantedAuthority> auth = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),new SimpleGrantedAuthority("ROLE_USER")); 
		OurUser pu = OurUser.builder().principal(user).authorities(auth).build();
		return new TestingAuthenticationToken(pu, null, auth);
	}
	
}
