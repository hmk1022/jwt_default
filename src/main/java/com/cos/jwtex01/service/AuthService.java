package com.cos.jwtex01.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;



@Service
public class AuthService {

	@SuppressWarnings("unused")
	private void kakao () {
		// 카카오에 POST방식으로 key=value 데이터를 요청함. RestTemplate를 사용하면 요청을 편하게 할 수 있다.
		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("grant_type", "authorization_code");
			params.add("client_id", "{client_id}");
			params.add("redirect_uri", "http://localhost:8000/oauth2/kakao");
			params.add("code", "code");
			params.add("client_secret", "{secret_code}");

		// HttpHeader와 HttpBody를 HttpEntity에 담기 (why? rt.exchange에서 HttpEntity객체를 받게 되어있다.)
		HttpEntity<MultiValueMap<String, String>> kakaoRequest = new HttpEntity<>(params, headers);

		// HTTP 요청 - POST방식 - response 응답 받기
		ResponseEntity<String> response = rt.exchange(
		    "https://kauth.kakao.com/oauth/token",
		    HttpMethod.POST,
		    kakaoRequest,
		    String.class
		);
	}
	
}
