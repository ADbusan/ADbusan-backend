package com.ADbusan.java.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set")
public class ResponseDto<D> {
	private boolean status;
	private String message;
	private D data;
//	어디서나 클래스로 접근가능(public static)
	public static<D> ResponseDto<D> setSuccess(String message,D data){
//		생성자 함수
		return ResponseDto.set(true, message, data);
	}
	public static<D> ResponseDto<D> setFailed(String messge){
		return ResponseDto.set(false, messge, null);	
	}
}
