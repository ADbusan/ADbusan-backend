package com.ADbusan.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ADbusan.java.entity.MemberEntity;
//	해당 인터페이스가 repository 임을 명시
@Repository
//	repository는 interpace로 작성
//	jparepository interpace를 상속받아야함
//	jparepository <table(entityclass),primary key type>
public interface MemberRepository extends JpaRepository<MemberEntity, String> {
//	@query 커스텀 orm 메서드를 작성
//	테이블 명을 alias로 지정해서 사용
//	?1,?2....: 매개변수로 받아온 변수를 해당 위치로 넣기위한 구문
	@Query("SELECT m FROM MEMBER m WHERE m.email = ?1")
	List<MemberEntity> myFindAll(String email);
}