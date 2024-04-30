package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {
	
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	private BoardDAO bdao; //인터페이스로 생성
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl(); //class로 생성
	}

	@Override
	public int register(BoardVO bvo) {
		log.info("register service in!!");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info("list service in!!");
		return bdao.selectList(pgvo);
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info("detail service in!!");
		return bdao.selectOne(bno);
	}

	@Override
	public int update(BoardVO bvo) {
		log.info("update service in!!");
		return bdao.update(bvo);
	}

	@Override
	public int delete(int bno) {
		//게시글을 지우기 전 댓글을 삭제하고 글 삭제
		log.info("update service in!!");
		CommentServiceImpl csv = new CommentServiceImpl();
		int isOk = csv.removeAll(bno);
		log.info("comment removeAll >> {}", isOk);
		return bdao.delete(bno);
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		log.info("getTotal service in");
		return bdao.total(pgvo);
	}

	@Override
	public String getFileName(int bno) {
		log.info("getFileName service in");
		return bdao.getFileName(bno);
	}

	@Override
	public int readCount(int bno) {
		log.info("readCount service in");
		return bdao.readCount(bno);
	}

}
