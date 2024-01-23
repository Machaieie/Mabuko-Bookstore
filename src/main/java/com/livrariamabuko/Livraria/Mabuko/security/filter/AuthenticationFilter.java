package com.livrariamabuko.Livraria.Mabuko.security.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.AuthenticationException;
import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.livrariamabuko.Livraria.Mabuko.DTOs.AuthResponseDTO;
import com.livrariamabuko.Livraria.Mabuko.model.User;
import com.livrariamabuko.Livraria.Mabuko.security.security.JwtUtil;
import org.springframework.security.core.Authentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;


    public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;

	}

    @Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				request.getParameter("username"), request.getParameter("password"));
		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User principal = (User) authResult.getPrincipal();

		String access_token = JWT.create().withSubject(principal.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 72 * 1000))
				.withIssuer(request.getRequestURI().toString()).withClaim("roles", principal.getAuthorities().stream()
						.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(JwtUtil.getAlgorithm());

		AuthResponseDTO authResponseDTO = new AuthResponseDTO(principal.getId(),principal.getName(),
				principal.getUsername(), access_token);
		authResponseDTO.setRoles(principal.getRoles());

		response.setContentType(APPLICATION_JSON_VALUE);

		new ObjectMapper().writeValue(response.getOutputStream(), authResponseDTO);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Map<String, Object> data = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		data.put("timestamp", sdf.format(Calendar.getInstance().getTime()));

		if (failed.getCause() instanceof SecurityException) {
			data.put("message", failed.getMessage());
			data.put("details", "");
		} else {
			data.put("message", "Username ou senha invalidos");
			data.put("details", failed.getMessage());
		}

		response.getOutputStream().println(objectMapper.writeValueAsString(data));
	}

}
