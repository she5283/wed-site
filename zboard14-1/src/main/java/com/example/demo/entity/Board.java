package com.example.demo.entity;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Board {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private String password;
	private Integer readCnt;
	private LocalDate writeTime;
}
