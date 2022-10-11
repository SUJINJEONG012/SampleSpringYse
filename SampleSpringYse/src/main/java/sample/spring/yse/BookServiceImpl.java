package sample.spring.yse;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookDao bookDao;
	
	//BookDao.insert메서드를 실행시키는 서비스 메서드를 작성
	
	@Override
	public String create(Map<String, Object> map) {
	    int affectRowCount = this.bookDao.insert(map);
	    System.out.print("@@@@@@");
	    if (affectRowCount ==  1) {
	        return map.get("book_id").toString();
	    }
	    return null;
	}
	
	@Override
	public Map<String, Object> detail(Map<String, Object> map){
		return this.bookDao.select_detail(map);
	}
	
	
	/*
	 * 수정의 경우 입력과는 다르게 pk를 가져오거나 하는 절차가 필요없으므로 
	 * 그저 1개의 행이 제대로 영향 받았는지만 검사하면된다.
	 * 
	 * */
	@Override
	public boolean edit(Map<String, Object> map) {
		int affectRowCount= this.bookDao.update(map);
		return affectRowCount == 1; //한 개의 행이 제대로 되었는지 체크 
	}
	
	
	@Override
	public boolean remove(Map<String, Object> map) {
		int affectRowCount = this.bookDao.delete(map);
		return affectRowCount == 1;
	}
	
	
	@Override
	public List<Map<String, Object>> list(Map<String, Object> map){
		// 책 목록 DAO를 곧바로 리턴하는 서비스 메서드를 만든다. 
		return this.bookDao.selectList(map);
	}
	

}
