package com.cafe.mbti;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe.mbti.domain.BoardVO;
import com.cafe.mbti.domain.MemberVO;
import com.cafe.mbti.service.BoardService;
import com.cafe.mbti.util.BoardPageCriteria;
import com.cafe.mbti.util.FileUtil;
import com.cafe.mbti.util.PageMaker;
import com.cafe.mbti.util.Target;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	@Resource(name = "resourcesPath")
	private String resourcesPath;

	@GetMapping("/list")
	public void listGET(HttpServletRequest request, Model model, Integer boardPage, Integer boardNumsPerPage,
			Integer boardSection, Integer boardList, String boardName, Integer boardNumber, Integer searchOption,
			String keyword) {
		logger.info("RequestURL: ({}){}", request.getMethod(), request.getRequestURI());
		BoardPageCriteria boardPageCriteria = new BoardPageCriteria();

		Target target = (Target) request.getSession().getAttribute("target");
		if (target == null) {
			target = new Target();
			request.getSession().setAttribute("target", target.setTarget(target, boardNumsPerPage, boardPage,
					boardSection, boardList, boardName, boardNumber, searchOption, keyword, 0, -1));
		} else {
			target = target.setTarget(target, boardNumsPerPage, boardPage, boardSection, boardList, boardName,
					boardNumber, searchOption, keyword, 0, -1);
		}
		boardPageCriteria = boardPageCriteria.setBoardPageCriteria(boardPageCriteria, boardPage, boardNumsPerPage,
				target.getBoardSection(), target.getBoardList(), target.getKeyword());
		List<BoardVO> boardVO = null;
		// 전체 게시글
		if (target.getBoardSection() == 0) {
			if (keyword != null && keyword != "") {
				switch (searchOption) {
				case 0: // 게시글 제목
					boardVO = boardService.readByBoardTitle(boardPageCriteria);
					model.addAttribute("pageMaker",
							pageMaker(boardPageCriteria, boardService.readCountByBoardTitle(boardPageCriteria)));
					break;
				case 1: // 게시글 내용
					boardVO = boardService.readByBoardContent(boardPageCriteria);
					model.addAttribute("pageMaker",
							pageMaker(boardPageCriteria, boardService.readCountByBoardContent(boardPageCriteria)));
					break;
				case 2: // 게시글 작성자
					boardVO = boardService.readByNicknameOnBoard(boardPageCriteria);
					model.addAttribute("pageMaker",
							pageMaker(boardPageCriteria, boardService.readCountByNicknameOnBoard(boardPageCriteria)));
					break;
				case 3: // 댓글 내용
					boardVO = boardService.readByCmRpContent(boardPageCriteria);
					model.addAttribute("pageMaker",
							pageMaker(boardPageCriteria, boardService.readCountByCmRpContent(boardPageCriteria)));
					break;
				case 4: // 댓글 작성자
					boardVO = boardService.readByNicknameOnCmRp(boardPageCriteria);
					model.addAttribute("pageMaker",
							pageMaker(boardPageCriteria, boardService.readCountByNicknameOnCmRp(boardPageCriteria)));
					break;
				case 5: // 게시글 작성일
					// boardVO = boardService.readAll(pageCriteria);
					break;
				}
			} else {
				boardVO = boardService.readAll(boardPageCriteria);
				model.addAttribute("pageMaker", pageMaker(boardPageCriteria, boardService.readCountOnBoard()));
			}
			// 해당 게시판
		} else {
			if (keyword != null && keyword != "") {
				switch (searchOption) {
				case 0: // 게시글 제목
					boardVO = boardService.readByBoardTitle2(boardPageCriteria);
					model.addAttribute("pageMaker",
							pageMaker(boardPageCriteria, boardService.readCountByBoardTitle2(boardPageCriteria)));
					break;
				case 1: // 게시글 내용
					boardVO = boardService.readByBoardContent2(boardPageCriteria);
					model.addAttribute("pageMaker",
							pageMaker(boardPageCriteria, boardService.readCountByBoardContent2(boardPageCriteria)));
					break;
				case 2: // 게시글 작성자
					boardVO = boardService.readByNicknameOnBoard2(boardPageCriteria);
					model.addAttribute("pageMaker",
							pageMaker(boardPageCriteria, boardService.readCountByNicknameOnBoard2(boardPageCriteria)));
					break;
				case 3: // 댓글 내용
					boardVO = boardService.readByCmRpContent2(boardPageCriteria);
					model.addAttribute("pageMaker",
							pageMaker(boardPageCriteria, boardService.readCountByCmRpContent2(boardPageCriteria)));
					break;
				case 4: // 댓글 작성자
					boardVO = boardService.readByNicknameOnCmRp2(boardPageCriteria);
					model.addAttribute("pageMaker",
							pageMaker(boardPageCriteria, boardService.readCountByNicknameOnCmRp2(boardPageCriteria)));
					break;
				case 5: // 게시글 작성일
					// boardVO = boardService.readAll(pageCriteria);
					break;
				}
			} else {
				boardVO = boardService.readBoard(boardPageCriteria);
				model.addAttribute("pageMaker",
						pageMaker(boardPageCriteria, boardService.readCountOnBoard2(boardPageCriteria)));
			}
		}
		model.addAttribute("boardVO", boardVO);
	} // end listGET()

	@GetMapping("/write")
	public void writeGET(HttpServletRequest request) {
		logger.info("RequestURL: ({}){}", request.getMethod(), request.getRequestURI());
	} // end writeGET()

	@PostMapping("/write")
	public String writePOST(HttpServletRequest request, RedirectAttributes redirectAttributes, BoardVO boardVO,
			MultipartFile[] files) throws IOException {
		Target target = (Target) request.getSession().getAttribute("target");
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("memberVO");
		FileUtil fileUtil = new FileUtil();
		String boardFiles = "*";
		logger.info("RequestURL: ({}){}", request.getMethod(), request.getRequestURI());

		if (files.length != 0) {
			for (MultipartFile file : files) {
				boardFiles = (boardFiles == "*") ? file.getOriginalFilename()
						: boardFiles + ", " + file.getOriginalFilename();
			}
		}
		boardVO.setBoardFiles(boardFiles);
		boardVO.setMemberNumber(memberVO.getMemberNumber());
		boardVO.setBoardSection(target.getBoardSection());
		boardVO.setBoardList(target.getBoardList());
		if (boardService.create(boardVO) == 1) {
			fileUtil.saveBoardFiles(resourcesPath, files, boardVO.getBoardSeqNextVal());
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
		logger.info("RequestURL: ({}){}", request.getMethod(), request.getRequestURI());

		target.setBoardNumber(boardNumber != null ? boardNumber : target.getBoardNumber());
		if (memberVO != null) {
			BoardVO boardVO = boardService.read(target.getBoardNumber(), memberVO.getMemberNumber());
			model.addAttribute("boardVO", boardVO);
		}
	} // end detailGET()

	@GetMapping("/update")
	public void updateGET(HttpServletRequest request, Model model) {
		Target target = (Target) request.getSession().getAttribute("target");
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("memberVO");
		logger.info("RequestURL: ({}){}", request.getMethod(), request.getRequestURI());

		BoardVO boardVO = boardService.read(target.getBoardNumber(), memberVO.getMemberNumber());
		model.addAttribute("boardVO", boardVO);
	} // end updateGET()

	@PostMapping("/update")
	public String updatePOST(HttpServletRequest request, RedirectAttributes redirectAttributes, Integer boardSection,
			Integer boardList, String boardName, String boardTitle, String boardContent, String boardFiles,
			MultipartFile[] files) throws IOException {
		Target target = (Target) request.getSession().getAttribute("target");
		logger.info("RequestURL: ({}){}", request.getMethod(), request.getRequestURI());

		FileUtil fileUtil = new FileUtil();

		boardFiles = (boardFiles == "*") ? boardFiles : boardFiles.replace("*, ", "");
		if (files.length != 0) {
			for (MultipartFile file : files) {
				boardFiles = (boardFiles == "*") ? file.getOriginalFilename()
						: boardFiles + ", " + file.getOriginalFilename();
			}
		}
		if (boardService.update(boardSection, boardList, boardName, boardTitle, boardContent, boardFiles,
				target.getBoardNumber()) == 1) {
			fileUtil.saveBoardFiles(resourcesPath, files, target.getBoardNumber());
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
		FileUtil fileUtil = new FileUtil();
		logger.info("RequestURL: ({}){}", request.getMethod(), request.getRequestURI());
		if (boardService.delete(target.getBoardNumber()) == 1) {
			fileUtil.deleteBoardFiles(resourcesPath, target.getBoardNumber());
			redirectAttributes.addFlashAttribute("message", "게시글이 삭제되었습니다.");
		} else {
			redirectAttributes.addFlashAttribute("message", "게시글 삭제를 실패했습니다.");
		}
		redirectAttributes.addFlashAttribute("targetURL", "list");
		return "redirect:..";
	} // end deletePOST()

	private PageMaker pageMaker(BoardPageCriteria boardPageCriteria, int boardTotalCount) {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setBoardPageCriteria(boardPageCriteria);
		pageMaker.setBoardTotalCount(boardTotalCount);
		pageMaker.setBoardPageData();
		return pageMaker;
	} // end pageMaker()
} // end BoardController
