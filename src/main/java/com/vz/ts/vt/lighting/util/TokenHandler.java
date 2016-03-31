package com.vz.ts.vt.lighting.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vz.ts.vt.lighting.dto.InputForToken;

public final class TokenHandler {
	
	private static final String HMAC_ALGO = "HmacSHA256";
	private static final String SEPARATOR = ".";
	private static final String SEPARATOR_SPLITTER = "\\.";
	private static String REFRESH_BASE_64;
	
	private final Mac hmac;
	
	public TokenHandler(byte[] secretKey) {
		try {
			hmac = Mac.getInstance(HMAC_ALGO);
			hmac.init(new SecretKeySpec(secretKey, HMAC_ALGO));
			REFRESH_BASE_64 = toBase64("refresh".getBytes());
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			throw new IllegalStateException("failed to initialize HMAC: " + e.getMessage(), e);
		}
	}
	
	public String createTokenForUser(InputForToken user) {
		byte[] userBytes = toJSON(user);
		byte[] hash = createHmac(userBytes);
		final StringBuilder sb = new StringBuilder(170);
		sb.append(toBase64(userBytes));
		sb.append(SEPARATOR);
		sb.append(toBase64(hash));
		return sb.toString();
	}
	
	public String createRefreshToken(String token) {		
		final StringBuilder sb = new StringBuilder(180);
		sb.append(REFRESH_BASE_64);
		sb.append(SEPARATOR);
		sb.append(token);
		return sb.toString();
	}
	
	/*private AuthenticatedUser fromJSON(final byte[] userBytes) {
		try {
			return new ObjectMapper().readValue(new ByteArrayInputStream(userBytes), AuthenticatedUser.class);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}*/
	
	private byte[] toJSON(InputForToken user) {
		try {
			return new ObjectMapper().writeValueAsBytes(user);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException(e);
		}
	}
	
	private String toBase64(byte[] content) {
		return DatatypeConverter.printBase64Binary(content);
	}

	/*private byte[] fromBase64(String content) {
		return DatatypeConverter.parseBase64Binary(content);
	}*/
	
	// synchronized to guard internal hmac object
	private synchronized byte[] createHmac(byte[] content) {
		return hmac.doFinal(content);
	}

}
