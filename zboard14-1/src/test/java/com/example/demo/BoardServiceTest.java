package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.BoardDto.DeleteDto;
import com.example.demo.dto.BoardDto.UpdateDto;
import com.example.demo.dto.BoardDto.WriteDto;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

@SpringBootTest
public class BoardServiceTest {
	@Autowired
	private BoardService service;
	
	//@Transactional
	//@Test
	public void writeTest() {
		WriteDto dto = WriteDto.builder().title("aaa").content("eee").writer("spring").password("1234").build();
		Board result = service.save(dto);
		assertEquals(2, result.getBno());
	}
	
	@Test
	public void readTest() {
		assertEquals(true, service.findById(1).isPresent());
		assertEquals(true, service.findById(100).isEmpty());
	}
	
	//@Test
	public void updateTest() {
		UpdateDto d1 = UpdateDto.builder().bno(10).title("가가가").content("나나나").password("1234").build();
		UpdateDto d2 = UpdateDto.builder().bno(1).title("가가가").content("나나나").password("1111").build();
		UpdateDto d3 = UpdateDto.builder().bno(1).title("가가가").content("나나나").password("1234").build();
		assertEquals(false, service.update(d1));
		assertEquals(false, service.update(d2));
		assertEquals(true, service.update(d3));
	}
	
	@Transactional
	//@Test
	public void deleteTest() {
		DeleteDto d1 = DeleteDto.builder().bno(10).password("1234").build();
		DeleteDto d2 = DeleteDto.builder().bno(1).password("1111").build();
		DeleteDto d3 = DeleteDto.builder().bno(1).password("1234").build();
		assertEquals(false, service.deleteById(d1));
		assertEquals(false, service.deleteById(d2));
		assertEquals(true, service.deleteById(d3));
	}
	
	//@Test
	public void findAllTest() {
		assertNotEquals(0, service.findAll());
	}
}
