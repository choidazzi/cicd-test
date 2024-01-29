/**
 * 
 */
package kr.letech.study.boot.front.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <pre>
 * 
 * </pre>
 *  
 * << 개정이력 >>
 *   
 *  수정일      수정자		수정내용
 *  ------------------------------------------------
 *  2024-01-03  CSY			최초 생성
 */

@Controller
public class FrontController {
	
	@GetMapping("/front/index")
	public String index(Model model) {
		model.addAttribute("name", "신짱구");
		return "index";
	}
	
	@GetMapping("/front/boards")
	public String list() {
		return "board/list";
	}

	@GetMapping("/front/boards/register")
	public	 String register() {
		return "board/register";
	}

	@GetMapping("/front/boards/{postId}")
	public String detail(@PathVariable String postId, Model model) {
		model.addAttribute("postId", postId);
		return "board/detail";
	}

	@GetMapping("/front/boards/{postId}/edit")
	public String edit(@PathVariable String postId, Model model) {
		model.addAttribute("postId", postId);
		return "board/edit";
	}

	@GetMapping("/front/boards/login")
	public String login() {
		return "board/login";
	}
}
