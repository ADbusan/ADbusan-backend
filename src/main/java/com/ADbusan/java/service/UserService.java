package com.ADbusan.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ADbusan.java.dto.reponse.ResponseDto;
import com.ADbusan.java.dto.user.PostUserDto;
import com.ADbusan.java.dto.user.PostUserResponseDto;
import com.ADbusan.java.entity.MemberEntity;
import com.ADbusan.java.repository.MemberRepository;

@Service
public class UserService {
	@Autowired MemberRepository memberRepository;
	public ResponseDto<PostUserResponseDto>postUser(PostUserDto dto){
		
		String password = dto.getPassword();
		String password2 = dto.getPassword2();
		if(!password.equals(password2)) return ResponseDto.setFailed("비밀번호가 서로 다릅니다.");
		MemberEntity member = MemberEntity
				.builder()
				.email(dto.getEmail())
				.password(dto.getPassword()) 
				.nickname(dto.getNickname())
				.telNumber(dto.getTelNumber())
				.address(dto.getAddress()+""+dto.getAddressDetail())
				.build();
		memberRepository.save(member);
		return ResponseDto.setSuccess("회원가입에 성공", new PostUserResponseDto(true));
	}
		
}
