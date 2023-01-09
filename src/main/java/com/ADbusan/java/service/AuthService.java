package com.ADbusan.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ADbusan.java.dto.auth.AuthPostDto;
import com.ADbusan.java.dto.auth.LoginDto;
import com.ADbusan.java.dto.reponse.ResponseDto;
import com.ADbusan.java.entity.MemberEntity;
import com.ADbusan.java.repository.MemberRepository;
//Service : 해당클래스가 service 레이어 역할을함
@Service
public class AuthService {
	@Autowired MemberRepository memberRepository;
	public String hello(){
//		entity class로 entity 빌드 
		MemberEntity memberEntity = MemberEntity
				.builder()
				.email("qwe@qwe.com")
				.password("qwe123")
				.nickname("junhyon")
				.telNumber("010-123-1234")
				.address("busan")
				.build();
//		빌드한 entity르 ㄹ데이터베이스에 저장
		memberRepository.save(memberEntity);
//		memberrepository가 상속받은 jparepository 메서드를 사용하여 데이터 검색
		MemberEntity savedMemberEntity = 
				memberRepository.findById("qwe@qwe.com").get();
//		memberrepository에 작성한 커스텀 메서드를 사용
		List<MemberEntity>list = memberRepository.myFindAll("qwe@qwe.com");
		System.out.println(list.toString());
		return savedMemberEntity.getNickname();
		
	}
	
	public ResponseDto<LoginDto> login(AuthPostDto dto){
		LoginDto result = new LoginDto("jh",3600000);
		return ResponseDto.setSuccess("", result);
	}
}
