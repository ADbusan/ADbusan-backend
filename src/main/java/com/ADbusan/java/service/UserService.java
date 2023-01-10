package com.ADbusan.java.service;

import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.ADbusan.java.dto.reponse.ResponseDto;
import com.ADbusan.java.dto.user.GetUserResponseDto;
import com.ADbusan.java.dto.user.PostUserDto;
import com.ADbusan.java.dto.user.PostUserResponseDto;
import com.ADbusan.java.entity.MemberEntity;
import com.ADbusan.java.repository.MemberRepository;

@Service
public class UserService {
	@Autowired MemberRepository memberRepository;
	public ResponseDto<GetUserResponseDto>getUser(String email){
//		해당 이메일을 데이터베이스에서 검색
		MemberEntity member;
		try {
			member = memberRepository.findById(email).get();
		} catch (Exception e) {
			return ResponseDto.setFailed("not exist user");
			// TODO: handle exception
		}
//		GetUserResponseDto responseData= new GetUserResponseDto();
//		responseData.setEmail(member.getEmail());
//		responseData.setEmail(member.getNickname());
//		responseData.setEmail(member.getProfile());
//		responseData.setEmail(member.getTelNumber());
//		responseData.setEmail(member.getAddress());
//		return ResponseDto.setSuccess("get user ", responseData)
//		존재하지않으면 "NOT Exist User" 메세지를 포함한 Failed Responce 반환
		
//		존재하면 User정보 반환
		return ResponseDto.setSuccess("get user success", new GetUserResponseDto(member));
	}
	
	
	
	
	public ResponseDto<PostUserResponseDto>postUser(PostUserDto dto){
//		데이터베이스에 해당 이메일이 존재하는지 체크
//		존재한다면 failed response를 반환
//		select * from m where email =?
		String email = dto.getEmail();
		
		
		try {
			if (memberRepository.existsById(email))
				return ResponseDto.setFailed("이미 존재하는 이메일 입니다.");
		} catch (Exception e) {
			return ResponseDto.setFailed("데이터베이스 오류입니다.");
		}

		
		
//		try {
//			MemberEntity member = memberRepository.findById(email).get();
//		}catch (Exception e) {
//			return ResponseDto.setFailed("이미 존재하는 이메일 입니다.");
//		}
		
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
//		japrepository.save(entity) 메서드
//		해당 entity id가 데이터베이스 테이블에 존재하지 않으면!
//		entity insert 작업을 수행
//		!!하지뫈!!
//		해당 entity id가 데이터베이스 테이블에 존재하면!
//		존재하는 entity update 작업을 수행
		
		memberRepository.save(member);
		return ResponseDto.setSuccess("회원가입에 성공", new PostUserResponseDto(true));
	}
		
}
