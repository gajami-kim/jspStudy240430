console.log("board_detail.js in");
console.log(bnoVal);
console.log(id);

document.getElementById('cmtAddBtn').addEventListener('click',()=>{
    let cmtText = document.getElementById('cmtText').value;
    const cmtWriter = document.getElementById('cmtWriter').value;
    if(cmtText==null || cmtText==''){
        alert('댓글을 입력해주세요');
        return false;
    } else {
        //댓글 등록
        let cmtData = {
            bno : bnoVal,
            writer : cmtWriter,
            content : cmtText
        };
        //댓글을 비동기로 전송(통신, 호출)
        postCommentToServer(cmtData).then(result=>{
            console.log(result); //isOk값을 찍어봄(기록)
            if(result==='1'){
                alert("댓글등록성공!!");
                document.getElementById('cmtText').value="";
            }
            //댓글출력
            printCommentList(bnoVal);
        })
    }
});

//비동기 함수
async function postCommentToServer(cmtData){
    try {
        //보낼 때 필요한 요소 method(get(생략가능)/post), headers(어떤 정보를 보내는지에 대한 content-type), body
        const url = "/cmt/post";
        const config = {
            method: 'post',
            headers:{
                'Content-Type':'application/json; charset=utf-8'
            },
            body:JSON.stringify(cmtData)
        };

        const resp = await fetch(url,config);
        const result = await resp.text(); //isOk 값(0/1)을 리턴받음
        return result;

    } catch (error) {
        console.log(error);
    }
}

function spreadCommentList(result){ //result = 댓글 리스트(배열)
    console.log(result);
    let div = document.getElementById('commentLine');
    div.innerHTML=''; //원래 div 구조를 지우기
    for(let i=0; i<result.length; i++) {
        let html = `<div>`;
        html+=`<div>comment line</div>`;
        html+=`<div>${result[i].cno} / ${result[i].writer} / ${result[i].regdate}</div>`;
        html+=`<div>`;
        if(result[i].writer!=id){
            html+=`<input type="text" class="cmtText" value="${result[i].content}" readonly="readonly">`;
        }
        if(result[i].writer==id) {       
            html+=`<input type="text" class="cmtText" value="${result[i].content}">`;
            html+=`<button type="button" data-cno=${result[i].cno} class="cmtModBtn">수정</button>`;
            html+=`<button type="button" data-cno=${result[i].cno} class="cmtDelBtn">삭제</button> <br>`;
        }
        html+=`</div></div><hr>`;
        div.innerHTML+=html; //각 댓글을 누적하여 담기
    }
}

//댓글 리스트 요청
async function getCommentListFromServer(bno) {
    try {
        const resp = await fetch("/cmt/list?bno="+bno);
        const result = await resp.json(); //'[{댓1},{댓2},{댓3}]'
        return result;
    } catch (error) {
        console.log(error);
    }
}

function printCommentList(bno){
    getCommentListFromServer(bno).then(result=>{
        console.log(result);
        if(result.length>0) {
            spreadCommentList(result);
        } else {
            let div = document.getElementById('commentLine');
            div.innerHTML = `<div>comment가 없습니다.</div>`;
        }
    })
}

//수정 : cno, content => result로 isOK
async function updateCommentFromServer(cmtData) {
    try {
        const url = "/cmt/update";
        const config={
            method : 'post',
            headers:{
                'Content-Type':'application/json; charset=utf-8'
            },
            body:JSON.stringify(cmtData)
        };
        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

//삭제 : cno => result list(x) isOk / lsit준 것 처럼
async function removeCommentFromServer(cnoVal) {
    try {
        const resp = await fetch("/cmt/remove?cno="+cnoVal);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

//버튼 클릭 함수
document.addEventListener('click',(e)=>{
    console.log(e.target);
    //삭제버튼일 경우
    if(e.target.classList.contains('cmtDelBtn')) {
        let cnoVal = e.target.dataset.cno; //data-con 값 추출
        console.log("cnoVal="+cnoVal);
        removeCommentFromServer(cnoVal).then(result=>{
            if(result==='1') {
                alert("댓글 삭제 완료");
                printCommentList(bnoVal);
            }
        })
    }
    
    //수정버튼일 경우
    if(e.target.classList.contains('cmtModBtn')) {
        let cnoVal = e.target.dataset.cno; //data-con 값 추출
        console.log("cnoVal="+cnoVal);
        //내 타겟을 기준으로 가장 가까운 div 찾기 (수정버튼이 포함되어 있는 div)
        let div = e.target.closest('div'); // closest : e.target을 기준으로 가장 가까운 괄호값을 찾아라
        console.log(div);
        let cmtText = div.querySelector('.cmtText').value;
        console.log(cmtText);
        let cmtData = {
            cno:cnoVal,
            content:cmtText
        }
        updateCommentFromServer(cmtData).then(result=>{
            if(result==='1'){
                alert("댓글 수정 완료");
                printCommentList(bnoVal);
            }
        })
    }
})