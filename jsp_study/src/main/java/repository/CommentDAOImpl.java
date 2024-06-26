package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import orm.DatabaseBuilder;

public class CommentDAOImpl implements CommentDAO {
	
	private static final Logger log = LoggerFactory.getLogger(CommentDAOImpl.class);
	private SqlSession sql;
	
	public CommentDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int post(CommentVO cvo) {
		log.info("post dao in");
		int isOk = sql.insert("CommentMapper.post",cvo);
		if(isOk>0) {sql.commit();}
		return isOk;
	}

	@Override
	public List<CommentVO> list(int bno) {
		log.info("list dao in");
		return sql.selectList("CommentMapper.list",bno);
	}

	@Override
	public int delete(int cno) {
		log.info("delete dao in");
		int isOk = sql.delete("CommentMapper.del",cno);
		if(isOk>0) {sql.commit();}
		return isOk;
	}

	@Override
	public int update(CommentVO cvo) {
		log.info("update dao in");
		int isOk = sql.update("CommentMapper.update",cvo);
		if(isOk>0) {sql.commit();}
		return isOk;
	}

	@Override
	public int remove(int bno) {
		log.info("BoardDAO >> removeAll dao in");
		int isOk = sql.delete("CommentMapper.removeAll",bno);
		if(isOk>=0) {sql.commit();} //댓글이 없어도 commit 될 수 있게 조건 설정
		return isOk;
	}
	
}
