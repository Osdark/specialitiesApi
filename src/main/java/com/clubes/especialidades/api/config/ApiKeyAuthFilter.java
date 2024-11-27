package com.clubes.especialidades.api.config;

import com.clubes.especialidades.api.dao.sec.User;
import com.clubes.especialidades.api.repository.UserRepo;
import com.clubes.especialidades.api.service.ApiKeyService;
import com.clubes.especialidades.api.utils.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {
	@Autowired
	private ApiKeyService apiKeyService;
	@Autowired
	private UserRepo userRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {
		final String apiKey = request.getHeader(Constants.AUTHORIZATION);

		if (apiKey != null && apiKey.startsWith(Constants.API_KEY)) {
			String key = apiKey.substring(7);

			Optional<User> user = apiKeyService.validateApiKey(key);

			if (user.isPresent()) {
				List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.get().getRole()));
				UsernamePasswordAuthenticationToken auth =
						new UsernamePasswordAuthenticationToken(user.get().getId(), null, authorities);
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(auth);
			} else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write(Constants.INVALID_API_KEY);
				return;
			}
		}

		filterChain.doFilter(request, response);
	}
}
