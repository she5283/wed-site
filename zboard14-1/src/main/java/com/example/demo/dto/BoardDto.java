package com.example.demo.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import com.example.demo.entity.Board;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardDto {
	
	@Data
	public static class ListDto{
		private Integer bno;
		private String title;
		private String writer;
		private Integer readCnt;
	}
	
	@Data
	public static class ReadDto{
		private Integer bno;
		private String title;
		private String content;
		private String writer;
		private Integer readCnt;
		private LocalDate writeTime;
	}
	
	@Data
	@Builder
	public static class WriteDto{
		@NotEmpty
		private String title;
		private String content;
		private String writer;
		private String password;
		public Board toEntity() {
			return Board.builder().title(title).content(content).writer(writer).password(password).build();
		}
	}
	
	@Data
	@Builder
	public static class UpdateDto{
		private Integer bno;
		private String title;
		private String content;
		private String password;
		public Board toEntity() {
			return Board.builder().bno(bno).title(title).content(content).build();
		}
	}
	
	@Data
	@Builder
	public static class DeleteDto{
		private Integer bno;
		private String password;
	}
}
