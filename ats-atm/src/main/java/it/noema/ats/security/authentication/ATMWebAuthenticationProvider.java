package it.noema.ats.security.authentication;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Perform the authentication process, authenticating with fixed username and password.
 * @author Noema STI
 *
 */
public class ATMWebAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		_log.debug(String.format("Try to authenticate user with user: %s and password: %s", name, "********"));

		if (StringUtils.equals("davidep", name) && StringUtils.equals("123456", password)) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			
			return new UsernamePasswordAuthenticationToken(name, password, authorities);
		}else {
			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	private static final Logger _log = LoggerFactory.getLogger(ATMWebAuthenticationProvider.class);
}
