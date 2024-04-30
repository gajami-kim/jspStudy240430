package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	//DB설정 mybatis 라이브러리 사용하여 DB 구성
	private SqlSession sql;
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession(); //연결해놓은 session을 열어줌
	}

	//메서드 구현 
	@Override
	public int insert(BoardVO bvo) {
		log.info("insert dao in!!");
		//실제 DB로 저장
		//sql.insert(mapperNamespace.id, bvo);
		int isOk = sql.insert("BoardMapper.add", bvo);
		//insert, update, delete => DB가 변경되는 구문은 반드시 commit 필요 !!!
		if(isOk>0) { //isOk가 성공했다면
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<BoardVO> selectList(PagingVO pgvo) {
		log.info("selectList dao in!!");
		
		return sql.selectList("BoardMapper.list",pgvo);
	}

	@Override
	public BoardVO selectOne(int bno) {
		log.info("selectOne dao in!!");
		return sql.selectOne("BoardMapper.detail", bno);
	}

	@Override
	public int update(BoardVO bvo) {
		log.info("update dao in!!");
		int isOk =  sql.update("BoardMapper.update", bvo);
		if(isOk>0) { //isOk가 성공했다면
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int delete(int bno) {
		log.info("delete dao in!!");
		int isOk = sql.delete("BoardMapper.delete", bno);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	//public 뒤에 int가 mapper에서 result로 나가는 값
	public int total(PagingVO pgvo) {
		log.info("getTotal dao in!!");
		return sql.selectOne("BoardMapper.total",pgvo);
	}

	@Override
	public String getFileName(int bno) {
		log.info("getFileName dao in!!");
		return sql.selectOne("BoardMapper.fileName",bno);
	}

	@Override
	public int readCount(int bno) {
		log.info("readCount dao in!!");
		int isOk = sql.update("BoardMapper.count",bno);
		if(isOk>0) {sql.commit();}
		return isOk;
	}

	
}
