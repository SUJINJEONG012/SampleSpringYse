package sample.spring.yse;

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
	

}
