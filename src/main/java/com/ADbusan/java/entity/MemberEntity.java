package com.ADbusan.java.entity;

import javax.validation.constraints.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
//	해당 클래스가 entity 클래스임을 명시
//	인자로 entity의 이름을 지정 할 수 있음
@Entity(name="MEMBER")
//	해당 entity 클래스와 데이터베이스 table을
//	인자로 지정한 이름으로 매핑
@Table(name="MEMBER")
public class MemberEntity {
//	해당 필드가 primary key 임을 명시
	@Id
//	해당 primary key의 value 자동생성을 지시
//	@GeneratedValue
	private String email;
	private String password;
	private String nickname;
	private String profile;
	private String telNumber;
	private String address;
}