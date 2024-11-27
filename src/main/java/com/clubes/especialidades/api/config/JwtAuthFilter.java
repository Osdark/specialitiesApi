package com.clubes.especialidades.api.config;

import com.clubes.especialidades.api.service.CustomUserDetailsService;
import com.clubes.especialidades.api.service.JwtTokenProvider;
import com.clubes.especialidades.api.utils.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {
		final String authHeader = request.getHeader(Constants.AUTHORIZATION);
		String servletPath = request.getServletPath();

		if (servletPath.startsWith("/swagger-ui") ||
			servletPath.startsWith("/v3/api-docs") ||
			servletPath.equals("/auth/login")) {
			filterChain.doFilter(request, response);
			return;
		}

		if (authHeader == null || !authHeader.startsWith(Constants.BEARER)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Constants.INVALID_TOKEN);
			return;
		}

		String jwtToken = authHeader.substring(7);
		String username = jwtTokenProvider.extractUsername(jwtToken);

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (jwtTokenProvider.validateToken(jwtToken, userDetails)) {
				List<GrantedAuthority> authorities = jwtTokenProvider.extractRolesFromToken(jwtToken);
				UsernamePasswordAuthenticationToken authToken =
						new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}

		filterChain.doFilter(request, response);
	}
}
