package com.ADbusan.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ADbusan.java.dto.reponse.ResponseDto;
import com.ADbusan.java.dto.user.GetUserResponseDto;
import com.ADbusan.java.dto.user.PatchUserDto;
import com.ADbusan.java.dto.user.PostUserDto;
import com.ADbusan.java.dto.user.ResultResponseDto;
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
	
	
	
	
	public ResponseDto<ResultResponseDto>postUser(PostUserDto dto){
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
		return ResponseDto.setSuccess("회원가입에 성공", new ResultResponseDto(true));
	}
	public ResponseDto<GetUserResponseDto>patchUser(PatchUserDto dto){
//		dto에서 이메일을 가져옴
		String email = dto.getEmail();
	
//		repository를 이용해서 데이터베이스에 있는  member 테이블의 중 
//		해당 email에 해당하는 데이터를 불러옴
		MemberEntity member=null;
		try {
			member = memberRepository.findById(email).get();
		} catch (Exception e) {
//			만약 존재 하지 않으면 filed response로 "not exist user"반환
			return ResponseDto.setFailed("not exist user");
		}
		
//		request body로 받은 nickname 과 profile로 각가 변경
		member.setNickname(dto.getNickname());
		member.setProfile(dto.getProfile());
//		변경한 entity를 repository를 이용해서 데이터베이스에 적용(저장)
		memberRepository.save(member);
//		결과를 responseDto에 담아서 반환
		return ResponseDto.setSuccess("user patch success",new GetUserResponseDto(member));
		
		
		
		
		
	}
	
	public ResponseDto<ResultResponseDto>deleteUser(String email){
//		repository를 이용해서 데이터베이스에 있는 Member 테이블 중
//		email에 해당하는 데이터를 삭제
		memberRepository.deleteById(email);
		
		return ResponseDto.setSuccess("delete user success", new ResultResponseDto(true));
	}
	
	
	
	
	
	
		
}
