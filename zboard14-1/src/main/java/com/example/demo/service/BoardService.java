package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDao;
import com.example.demo.dto.BoardDto;
import com.example.demo.dto.BoardDto.DeleteDto;
import com.example.demo.dto.BoardDto.UpdateDto;
import com.example.demo.dto.BoardDto.WriteDto;
import com.example.demo.entity.Board;

// 스프링 왜써요?
// 의존성 주입을 통해 객체 생성과 사용을 분리 -> 코드 재사용성을 높인다
// @Component -> ComponentScan -> 기본값은 프로젝트 생성할 때 지정한 패키지

@Service
public class BoardService {
	@Autowired
	private BoardDao dao;
	
	public List<BoardDto.ListDto> findAll() {
		return dao.findAll();
	}
	
	public Optional<BoardDto.ReadDto> findById(Integer bno) {
		dao.increaseReadCnt(bno);
		Optional<BoardDto.ReadDto> result = dao.findById(bno);
		return result;
	}
	
	public Board save(WriteDto dto) {
		// 글을 작성하면 글 읽기로 이동시킨다 -> 글번호가 필요하다
		// 글 번호는 board에 들었다(SelectKey)
		Board board = dto.toEntity();
		dao.save(board);
		return board;
	}
	
	public Boolean update(UpdateDto dto) {
		Optional<String> result = dao.findPassword(dto.getBno());
		// 글 번호가 틀렸다면 false
		if(result.isEmpty())
			return false;
		// 비밀번호가 틀린 경우 false
		if(result.get().equals(dto.getPassword())==false)
			return false;
		dao.update(dto.toEntity());
		return true;
	}
	
	public Boolean deleteById(DeleteDto dto) {
		System.out.println(dto);
		Optional<String> result = dao.findPassword(dto.getBno());
		// 글 번호가 틀렸다면 false
		if(result.isEmpty())
			return false;
		// 비밀번호가 틀린 경우 false
		if(result.get().equals(dto.getPassword())==false)
			return false;
		dao.deleteById(dto.getBno());
		return true;
	}
}
