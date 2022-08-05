package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.BoardDto.ReadDto;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

// 사용자 요청은 DispatcherServlet이 접수
// 실제 요청을 담당할 Controller를 찾아(HandlerMapping) 부른다
// 컨트롤러 실행 -> 처리결과와 출력할 화면을 DispatcherServclet에 보고
//				Model	View
// DS가 MAV(ModelAndView)에서 뷰이름을 꺼내서 찾아서(ViewResolver) 사용자에게 출력

@Controller
public class BoardCntroller {
	@Autowired
	private BoardService service;
	
	@GetMapping({"/","/board/all"})
	public ModelAndView findAll() {
		return new ModelAndView("/list").addObject("list",service.findAll());
	}
	
	// @RequestParam가 생략되어있는거다
	@GetMapping("/board/read")
	public ModelAndView findById(@RequestParam Integer bno) {
		Optional<ReadDto> result = service.findById(bno);
		if(result.isEmpty())
			return new ModelAndView("/read_error");
		return new ModelAndView("/read").addObject("board",result.get());
	}
	
	@PostMapping("/board/update")
	public ModelAndView update(@ModelAttribute BoardDto.UpdateDto dto) {
		service.update(dto);
		return new ModelAndView("redirect:/board/read?bno="+dto.getBno());
	}
	
	@GetMapping("/board/write")
	public ModelAndView write() {
		return new ModelAndView("/write");
	}
	
	@PostMapping("/board/write")
	public ModelAndView write(@ModelAttribute BoardDto.WriteDto dto) {
		Board board = service.save(dto);
		return new ModelAndView("redirect:/board/read?bno="+board.getBno());
	}
	
	@PostMapping("/board/delete")
	public ModelAndView deleteById(@ModelAttribute BoardDto.DeleteDto dto) {
		service.deleteById(dto);
		System.out.println(dto);
		// 만만한게 루트....
		return new ModelAndView("redirect:/");
	}
}
