package com.ADbusan.java.service;

import org.springframework.stereotype.Service;

import com.ADbusan.java.dto.auth.AuthPostDto;
import com.ADbusan.java.dto.auth.LoginDto;
import com.ADbusan.java.dto.reponse.ResponseDto;
//Service : 해당클래스가 service 레이어 역할을함
@Service
public class AuthService {
	public ResponseDto<LoginDto> login(AuthPostDto dto){
		LoginDto result = new LoginDto("jh",3600000);
		return ResponseDto.setSuccess("login success", result);
	}
}
