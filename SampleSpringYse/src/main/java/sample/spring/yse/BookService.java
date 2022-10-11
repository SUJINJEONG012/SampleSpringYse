package sample.spring.yse;

import java.util.List;
import java.util.Map;

public interface BookService {

	//생성
	String create(Map<String, Object> map);

    //조회 
	Map<String, Object> detail(Map<String, Object> map);

    //수정
	boolean edit(Map<String, Object> map);
	
	boolean remove(Map<String, Object> map);

	//목록조회 , 결과집합을 리스트로 보여주기 위해 맵을 리스트타입으로 읽어들일수 있음. 
	List<Map<String, Object>> list(Map<String, Object> map);

}
