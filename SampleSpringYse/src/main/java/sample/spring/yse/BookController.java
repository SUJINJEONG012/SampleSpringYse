package sample.spring.yse;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {

	@Autowired
	BookService bookService; // 의존성주입, 인터페이스 사용됨 주의

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		return new ModelAndView("book/create");
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();

		String bookId = this.bookService.create(map);
		if (bookId == null) {
			mav.setViewName("redirect:/create");
		} else {
			mav.setViewName("redirect:/detail?bookId=" + bookId);
		}

		return mav;
	}

	// 상세화면

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = this.bookService.detail(map);

		ModelAndView mav = new ModelAndView();
		mav.addObject("data", detailMap);
		String bookId = map.get("bookId").toString();
		mav.addObject("bookId", bookId);
		mav.setViewName("/book/detail");
		return mav;
	}

	// 수정화면
	/*
	 * 책 수정화면은 입력화면+상세화면다. 책입력화면의 형식을 그대로 따라가지만 데이터베이스에 저장된 데이터만 그려주면 된다. 따라서 책 데이터는
	 * 상세화면 서비스에서 가지고 오는, 뷰는 책 입력화면을 복사한다.
	 */

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = this.bookService.detail(map);

		ModelAndView mav = new ModelAndView();
		mav.addObject("data", detailMap);
		mav.setViewName("/book/update");
		return mav;
	}

	/*
	 * 수정화면 2 책 수정화면에서 책 수정 기능으로보내주는 파라미터는 4개 하나는 GET파라미터로 전달되는 bookId 나머지 3개는
	 * title, category, price
	 * 
	 * 
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView updatePost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();

		boolean isUpdateSuccess = this.bookService.edit(map);
		if (isUpdateSuccess) {
			String bookId = map.get("bookId").toString();

			// 정상적으로 데이터가 변경됐을경우 확인을 위해 상세페이지로 이동
			mav.setViewName("redirect:/detail?bookId=" + bookId);
		} else {
			// 만약 변경이안됐을경우, GET 메서드로리다이렉트 하는 방법도 있겠지만, 갱신화면을 바로 다시 보여줌
			mav = this.update(map);
		}

		return mav;
	}

	/*
	 * 책 삭제기능
	 *
	 */

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deletePost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();

		// 삭제가 성공했는지 확인
		boolean isDeleteSuccess = this.bookService.remove(map);
		if (isDeleteSuccess) {
			// 삭제가 성공하면 상세가 없어지므로 목록으로 리다이렉트
			mav.setViewName("redirect:/list");
		} else {
			String bookId = map.get("bookId").toString();
			// 삭제가 실패했으면 다시 상세 페이지로 이동
			mav.setViewName("redirect:/detail?bookId=" + bookId);

		}
		return mav;
	}
	
	//책 목록
	
	@RequestMapping(value="list")
	public ModelAndView list(@RequestParam Map<String, Object> map) {
		//책 목록을 데이터 베이스에서 가지고 온다.
		List<Map<String,Object>> list = this.bookService.list(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", list);
		
		//키워드 조건,검색어를 전달하는 기능만 추가되므로 서비스레이어는 수정하지 않는다.
		if(map.containsKey("keyword")) {
			//목록페이지에는 http파라미터가 있을 수도 있고, 없을 수도있다. 
			//따라서 파라미터가 있는지 확인
			mav.addObject("keyword", map.get("keyword"));
		}
		
		//뷰를 리턴한다.
		mav.setViewName("/book/list");
		return mav;
	}

	
	
	
	
	
	
	

}
