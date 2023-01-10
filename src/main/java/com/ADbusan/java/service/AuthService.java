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
//		입력받은 email 으로 데이터베이스에서 검색
		String email= dto.getEmail();
		MemberEntity member;
//		존재하지 않는다면 없는 아이디 "로그인 실패"반환
		try {
			member = memberRepository.findById(email).get();
		} catch (Exception e) {
			return ResponseDto.setFailed("login failed");
		}
//		존재한다면 입력받은 패스워드와 데이터베이스의 패스워드와 동일한지 검사
//		동일하지 않다면 "로그인 실패"반환
		String password = dto.getPassword();
		String dbpaswword =member.getPassword(); 
		if (!password.equals(dbpaswword)) {
			return ResponseDto.setFailed("login faild");
		}
//		토큰생성후 토큰 및 만료시간 반환
		
		LoginDto result = new LoginDto("jh",3600000);
		return ResponseDto.setSuccess("", result);
	}
}
