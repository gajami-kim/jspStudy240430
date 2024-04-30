package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import netscape.javascript.JSObject;
import service.CommentService;
import service.CommentServiceImpl;


@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CommentController.class);
	//비동기 통신은 데이터를 요청한 곳으로 바로 결과를 보내주는 방식
	//rdp, destPage가 필요없음
	private CommentService csv;

    public CommentController() {
    	csv = new CommentServiceImpl();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//실행
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String url = request.getRequestURI();
		String path = url.substring(url.lastIndexOf("/")+1);
		log.info(">>>>cmt path {}",path);
		
		switch(path) {
		case "post":
			try {
				log.info("post case check 1");
				//js에서 보낸 데이터를 읽어들이는 작업
				//js('Object') -> controller(String)
				//'{...}'
				StringBuffer sb = new StringBuffer();
				String line ="";
				BufferedReader br = request.getReader(); //댓글 객체(배열형태)
				while((line=br.readLine())!=null) {
					sb.append(line); //한줄씩 추가
				}
				log.info(">>>> sb {}",sb.toString());
				//객체로 생성
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject)parser.parse(sb.toString()); //key:value
				log.info(">>>>jsonObj {}",jsonObj.toJSONString());
				
				//key를 이용하여 value를 추출
				int bno = Integer.parseInt(jsonObj.get("bno").toString());
				String writer = jsonObj.get("writer").toString();
				String content = jsonObj.get("content").toString();
				
				CommentVO cvo = new CommentVO(bno, writer, content);
				int isOk = csv.post(cvo);
				log.info(">>>>>cvo {}", cvo);
				log.info("isOk>>>{}",(isOk>0?"성공":"실패"));
				
				//결과 데이터 전송 => 화면으로 전송(response 객체의 body에 직접 기록)
				PrintWriter out = response.getWriter();
				out.print(isOk); //js의 result로 전송됨
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "list":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info(">>> parameter bno {}",bno);
				
				List<CommentVO> list = csv.getList(bno);
				log.info(">>> list {}",list);
				
				//list => json 형태로 변환 후 화면으로 보내기
				//[{댓1},{댓2},{...}] : [] JSONArray
				//{...} : JSONObject
				
				JSONArray jsonObjList = new JSONArray();
				JSONObject[] jsonObjArr = new JSONObject[list.size()];
				
				for(int i=0; i<list.size(); i++) {
					jsonObjArr[i] = new JSONObject();
					jsonObjArr[i].put("cno", list.get(i).getCno());
					jsonObjArr[i].put("bno",list.get(i).getBno());
					jsonObjArr[i].put("writer",list.get(i).getWriter());
					jsonObjArr[i].put("content",list.get(i).getContent());
					jsonObjArr[i].put("regdate",list.get(i).getRegdate());
					
					jsonObjList.add(jsonObjArr[i]);
				}
				//'[{댓1},{댓2},{...}]' => Obj를 String으로 변환하여 전송
				String jsonData = jsonObjList.toJSONString();
				
				//print
				PrintWriter out = response.getWriter();
				out.print(jsonData);  //jsonData = result
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "update":
			try {
				StringBuffer sb = new StringBuffer();
				String line ="";
				BufferedReader br = request.getReader();
				while((line = br.readLine())!=null) {
					sb.append(line);
				}
				
				JSONParser parser = new JSONParser(); //key:value
				JSONObject jsonObj = (JSONObject)parser.parse(sb.toString());
				
				int cno = Integer.parseInt(jsonObj.get("cno").toString());
				String content = jsonObj.get("content").toString();
				
				CommentVO cvo = new CommentVO(cno,content);
				int isOk = csv.modify(cvo);
				log.info("modify isOk>>>{}",(isOk>0?"성공":"실패"));
				PrintWriter out = response.getWriter();
				out.print(isOk);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "remove":
			try {
				int cno = Integer.parseInt(request.getParameter("cno")); //const resp = await fetch("/cmt/remove?!!cno!!="+cnoVal);
				int isOk = csv.delete(cno);
				log.info("remove isOk>>>{}",(isOk>0?"성공":"실패"));
				PrintWriter out = response.getWriter();
				out.print(isOk);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request,response);
	}

}
