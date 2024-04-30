package handler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileRemoveHandler {
	//savePath, imageFileName
	
	private static final Logger log = LoggerFactory.getLogger(FileRemoveHandler.class);
	
	//savePath, imageFileName 매개변수로 받아 파일을 삭제하는 메서드
	public int deleteFile(String savePath, String imageFileName) {
		//return type int => 삭제 여부 값을 리턴
		
		boolean isDel = false; //삭제가 잘되었는지 체크하는 변수
		log.info(">>>> delete File Method 접근", imageFileName);
		
		//기전저장파일 + _th_ 같이 삭제 
		File fileDir = new File(savePath);
		File removeFile = new File(fileDir+File.separator+imageFileName);
		File removeThunmbFile = new File(fileDir+File.separator+"_th_"+imageFileName);
		
		//파일이 존재해야 삭제
		if(removeFile.exists() || removeThunmbFile.exists()) {
			isDel = removeFile.delete(); //삭제
			log.info(">>>> removeFile >>> {}",isDel);
			
			if(isDel) {
				isDel = removeThunmbFile.delete(); //삭제
				log.info(">>>> removeThunmbFile >>> {}",isDel);
			}
		}
		
		return isDel? 1:0;
	}

}