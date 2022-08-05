package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.BoardDto;
import com.example.demo.entity.Board;

@Mapper
public interface BoardDao {
	
	public List<BoardDto.ListDto> findAll();
	
	public Optional<BoardDto.ReadDto> findById(Integer bno);
	
	public Integer save(Board board);
	
	public Integer update(Board board);
	
	public Integer deleteById(Integer bno);
	
	public Integer increaseReadCnt(Integer bno);
	
	public Optional<String> findPassword(Integer bno);
}
