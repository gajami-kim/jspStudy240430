package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	
	//로그객체
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	//DAO 객체 연결
	private MemberDAO mdao; //repository->interface
	
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl(); //repository->class
	}

	@Override
	public int register(MemberVO mvo) {
		log.info("register service in!");
		return mdao.insert(mvo);
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info("login service in!");
		return mdao.login(mvo);
	}

	@Override
	public int lastLogin(String id) {
		log.info("login service in!");
		return mdao.lastLogin(id);
	}

	@Override
	public List<MemberVO> getList() {
		log.info("list service in!");
		return mdao.list();
	}

	@Override
	public int modify(MemberVO mvo) {
		log.info("list service in!");
		return mdao.update(mvo);
	}

	@Override
	public int remove(String id) {
		log.info("remove service in!");
		return mdao.remove(id);
	}

}
