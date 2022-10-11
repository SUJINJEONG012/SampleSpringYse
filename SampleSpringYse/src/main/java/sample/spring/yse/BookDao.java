package sample.spring.yse;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public int insert(Map<String, Object> map) {
		return this.sqlSessionTemplate.insert("book.insert", map);
	}

	// selectOne데이터를 한 개만 가져올때 사용.
	public Map<String, Object> select_detail(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("book.select_detail", map);
	}

// 책 수정 기능 추가

	public int update(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("book.update", map);
	}

// 책 삭제 기능추가 

	public int delete(Map<String, Object> map) {
		return this.sqlSessionTemplate.delete("book.delete", map);
	}

	// 책 목록 조회
	public List<Map<String, Object>> selectList(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("book.select_list", map);
		//sqlSessionTemplate.selectList => 결과집합목록을 반환,
		// Map<String, Object>의 목록 List 타입으로 읽어들일 수 있다.
		
	}

}