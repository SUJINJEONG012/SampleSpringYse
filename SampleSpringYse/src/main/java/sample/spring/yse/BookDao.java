package sample.spring.yse;

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
 
 
 //selectOne데이터를 한 개만 가져올때 사용.
 public Map<String , Object> select_detail(Map<String, Object> map){
	 return this.sqlSessionTemplate.selectOne("book.select_detail", map);
 }
 
 
 
 
}