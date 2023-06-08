package com.cafe.mbti;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe.mbti.domain.BoardVO;
import com.cafe.mbti.domain.MemberVO;
import com.cafe.mbti.service.BoardService;
import com.cafe.mbti.util.PageCriteria;
import com.cafe.mbti.util.PageMaker;
import com.cafe.mbti.util.Target;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/list")
	public void listGET(HttpServletRequest request, Model model, Integer page, Integer numsPerPage, Integer boardSection, Integer boardList, String boardName, Integer boardNumber, Integer searchOption, String keyword) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		Target target = target(request, numsPerPage, boardSection, boardList, boardName, boardNumber);
		PageCriteria pageCriteria = pageCriteria(page, numsPerPage, target.getBoardSection(), target.getBoardList(), keyword);
		if (keyword != null) {
			switch (searchOption) {
			case 0:
				model.addAttribute("boardVO", boardService.readByBoardTitle(pageCriteria));
				break;
			case 1:
				model.addAttribute("boardVO", boardService.readByBoardContent(pageCriteria));
				break;
			case 2:
				model.addAttribute("boardVO", boardService.readByNicknameOnBoard(pageCriteria));
				break;
			case 3:
				model.addAttribute("boardVO", boardService.readByCmRpContent(pageCriteria));
				break;
			case 4:
				model.addAttribute("boardVO", boardService.readByNicknameOnCmRp(pageCriteria));
				break;
			case 5:
				//model.addAttribute("boardVO", boardService.readBoard(pageCriteria));
				break;
			}
		} else {
			if (target.getBoardSection() == 0) {
				// 전체 게시글
				model.addAttribute("boardVO", boardService.readAll(pageCriteria));
			} else {
				// 해당 게시판
				model.addAttribute("boardVO", boardService.readBoard(pageCriteria));
			}
		}
		model.addAttribute("pageMaker", pageMaker(pageCriteria));
	} // end listGET()
	
	@GetMapping("/write")
	public void writeGET(HttpServletRequest request, Model model) {
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
	} // end writeGET()
	
	@PostMapping("/write")
	public String writePOST(HttpServletRequest request, RedirectAttributes redirectAttributes, BoardVO boardVO) {
		Target target = (Target) request.getSession().getAttribute("target");
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("memberVO");
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		boardVO.setMemberNumber(memberVO.getMemberNumber());
		boardVO.setBoardSection(target.getBoardSection());
		boardVO.setBoardList(target.getBoardList());
		if (boardService.create(boardVO) == 1) {
			redirectAttributes.addFlashAttribute("message", "게시글이 등록되었습니다.");
		} else {
			redirectAttributes.addFlashAttribute("message", "게시글 등록을 실패했습니다.");
		}
		redirectAttributes.addFlashAttribute("targetURL", "list");
		return "redirect:..";
	} // end writePOST()
	
	@GetMapping("/detail")
	public void detailGET(HttpServletRequest request, Model model, Integer boardNumber) {
		Target target = (Target) request.getSession().getAttribute("target");
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("memberVO");
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		target.setBoardNumber(boardNumber != null ? boardNumber : target.getBoardNumber());
		if (memberVO != null) {
			BoardVO boardVO = boardService.read(target.getBoardNumber(), memberVO.getMemberNumber());
			logger.info(boardVO.toString());
			model.addAttribute("boardVO", boardVO);
		}
	} // end detailGET()
	
	@GetMapping("/update")
	public void updateGET(HttpServletRequest request, Model model) {
		Target target = (Target) request.getSession().getAttribute("target");
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("memberVO");
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());

		BoardVO boardVO = boardService.read(target.getBoardNumber(), memberVO.getMemberNumber());
		model.addAttribute("boardVO", boardVO);
	} // end updateGET()
	
	@PostMapping("/update")
	public String updatePOST(HttpServletRequest request, RedirectAttributes redirectAttributes, Integer boardSection, Integer boardList, String boardName, String boardTitle, String boardContent) {
		Target target = (Target) request.getSession().getAttribute("target");
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		
		if (boardService.update(boardSection, boardList, boardName, boardTitle, boardContent, target.getBoardNumber()) == 1) {
			redirectAttributes.addFlashAttribute("message", "게시글이 수정되었습니다.");
		} else {
			redirectAttributes.addFlashAttribute("message", "게시글 수정을 실패했습니다.");
		}
		redirectAttributes.addFlashAttribute("targetURL", "detail");
		redirectAttributes.addFlashAttribute("targetNumber", target.getBoardNumber());
		return "redirect:..";
	} // end updatePOST()
	
	@PostMapping("/delete")
	public String deletePOST(HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		Target target = (Target) request.getSession().getAttribute("target");
		logger.info("RequestURL: ({}){}",request.getMethod(), request.getRequestURI());
		if (boardService.delete(target.getBoardNumber()) == 1) {
			redirectAttributes.addFlashAttribute("message", "게시글이 삭제되었습니다.");
		} else {
			redirectAttributes.addFlashAttribute("message", "게시글 삭제를 실패했습니다.");
		}
		redirectAttributes.addFlashAttribute("targetURL", "list");
		return "redirect:..";
	} // end deletePOST()
	
	private PageCriteria pageCriteria(Integer page, Integer numsPerPage, Integer boardSection, Integer boardList, String keyword) {
		PageCriteria pageCriteria = new PageCriteria();
		pageCriteria.setPage(page != null ? page : pageCriteria.getPage());
		pageCriteria.setNumsPerPage(numsPerPage != null ? numsPerPage : pageCriteria.getNumsPerPage());
		pageCriteria.setBoardSection(boardSection);
		pageCriteria.setBoardList(boardList);
		pageCriteria.setKeyword(keyword != null ? keyword : pageCriteria.getKeyword());
		return pageCriteria;
	} // end pageCriteria()
	
	private PageMaker pageMaker(PageCriteria pageCriteria) {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(pageCriteria);
		pageMaker.setTotalCount(boardService.readBoardCount());
		pageMaker.setPageData();
		return pageMaker;
	} // end pageMaker()
	
	private Target target(HttpServletRequest request, Integer page, Integer boardSection, Integer boardList, String boardName, Integer boardNumber) {
		Target target = (Target) request.getSession().getAttribute("target");
		if (target == null) {
			request.getSession().setAttribute("target", new Target());
			target = (Target) request.getSession().getAttribute("target");
			target.setPage(page != null ? page : target.getPage());
			target.setBoardSection(boardSection != null ? boardSection : target.getBoardSection());
			target.setBoardList(boardList != null ? boardList : target.getBoardList());
			target.setBoardName(boardName != null ? boardName : target.getBoardName());
			target.setEncodedBoardName(boardName != null ? encodedString(boardName) : target.getBoardName());
			target.setBoardNumber(boardNumber != null ? boardNumber : target.getBoardNumber());
		} else {
			target.setPage(page != null ? page : target.getPage());
			target.setBoardSection(boardSection != null ? boardSection : target.getBoardSection());
			target.setBoardList(boardList != null ? boardList : target.getBoardList());
			target.setBoardName(boardName != null ? boardName : target.getBoardName());
			target.setEncodedBoardName(boardName != null ? encodedString(boardName) : target.getBoardName());
			target.setBoardNumber(boardNumber != null ? boardNumber : target.getBoardNumber());
		}
		logger.info(target.toString());
		return target;
	}
	
	private String encodedString(String encodedString) {
		try {
			encodedString = URLEncoder.encode(encodedString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodedString;
	} // end encodedString()
} // end BoardController
