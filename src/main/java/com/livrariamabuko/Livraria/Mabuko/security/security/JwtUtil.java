package com.livrariamabuko.Livraria.Mabuko.security.security;

import org.springframework.stereotype.Service;

import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JwtUtil {
    public static final String JWT_HEADER = "Authorization";

	public static final String JWT_PREFIX = "Bearer ";

	private static final String SECRET_KEY = "#secret@CPN2022!";

	public static Algorithm getAlgorithm() {
		return Algorithm.HMAC256(JwtUtil.SECRET_KEY.getBytes());
	}

}
