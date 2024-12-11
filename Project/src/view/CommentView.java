package view;

import controller.CommentController;
import model.CommentDto;

import javax.xml.stream.events.Comment;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class CommentView {
    private static CommentView commentView = new CommentView();
    CommentView(){}
    public static CommentView getInstance(){return commentView;}

    Scanner sc = new Scanner(System.in);

    public void mainPage(int board_idx){
        BoardView boardView = new BoardView();
        //MemberView memberView = new MemberView(); 접근제한자 수정

        while(true){
            // 댓글로 들어오면 전체 댓글 조회
            System.out.println("================= COMMENT LIST ====================");
            commentPrint(board_idx);

            // 댓글 등록,수정, 삭제 기능 선택
            System.out.print("1.댓글 등록 2.댓글 수정 3.댓글 삭제 4.게시글로 이동 5.로그아웃  1~5 중 선택하세요 : ");
            int choose = sc.nextInt();

            if(choose == 1){
                commentWrite(board_idx);
            } else if(choose == 2){
                commentUpdate();
            } else if(choose == 3){
                commentDelete();
            } else if(choose ==4){
                boardView.boardList();
            } else if(choose == 5){
                //로그아웃 - 메인페이지로
                //memberView.mainpage();
            }// if-else if end
        }//while end
    }// main end

    //1. 댓글 조회
    void commentPrint(int board_idx){
        // 객체 정보 요청
        ArrayList<CommentDto> result = CommentController.getInstance().commentPrint(board_idx);

        // 해당 게시글에 댓글이 있으면 출력
        if(result != null){
            for(int i = 0; i < result.size(); i++){
                System.out.printf("댓글 번호 : %s, 댓글 작성자 : %s, 내용 : %s, 작성 시간 : %s, 수정 여부 : %s\n",
                        result.get(i).getComment_idx(), result.get(i).getMember_name(), result.get(i).getComment_content(),
                        result.get(i).getComment_date(), result.get(i).getComment_update()? "원본" : "수정함");
            }// for end
        } else { // 댓글이 없으면 안내문 출력
            System.out.println("해당 게시글에는 댓글이 존재하지 않습니다.");
        }//if-else end

    }// commentPrint end

    //2. 댓글 등록
    void commentWrite(int board_idx){
        // 1. 입력받기
        sc.nextLine();

        System.out.print("댓글 내용을 입력하세요 : ");
        String content = sc.nextLine();

        // 2. 로그인 회원번호 가져오기
        // int logimMemberIdx = MemberController.getInstance().getLoginMemberIdx();
        int test = 1;//테스트용 로그인한 회원 번호

        // 3. boardIdx Content를 포함한 객체 생성
        CommentDto commentDto = new CommentDto(test, board_idx, content);

        //3. 입력받은 값 컨트롤러에 전달
        //댓글 작성자가 로그인 한 회원인지 검토하는 작업 추가해야함
        boolean result = CommentController.getInstance().commentWrite(commentDto);

        //3. 컨트롤러에게 전달 후 결과 출력
        if(result) {
            System.out.println("댓글 등록 성공");
        } else{
            System.out.println("댓글 등록 실패");
        }// if-else end
    }// commentWrite end


    //3. 댓글 수정
    void commentUpdate() {
        //1. 수정 댓글 번호 입력받기
        System.out.print("수정할 댓글의 번호를 입력하세요 : ");
        int updateCommentNum = sc.nextInt();

        //2. 수정하려는 댓글이 로그인 한 회원이 작성한 댓글인지 검토하는 로직 추가
        // int loginMemberIdx = MemberController.getInstance().getLoginMemberIdx();
        int test = 1;//테스트용 로그인한 회원 번호

        sc.nextLine();
        System.out.print("수정할 댓글의 내용을 입력하세요 : ");
        String updateCommentContent = sc.nextLine();

        //3. 수정 게시물 객체
        CommentDto updateCommentDto = new CommentDto(updateCommentNum, updateCommentContent);

        //4. controller에 전달
        boolean result = CommentController.getInstance().commentUpdate(updateCommentDto, test);

        //경로 출력
        if(result) {
            System.out.println("댓글 수정 완료");
        } else {
            System.out.println("댓글 수정 실패: 직접 작성한 댓글만 수정할 수 있습니다.");
        }// if-else end
    }// commnetUpdate end

    //4. 댓글 삭제
    void commentDelete(){
        //1. 삭제할 댓글 번호 입력받기
        sc.nextLine();
        System.out.print("삭제할 댓글 번호를 입력하세요. : ");
        int deleteCommentNum = sc.nextInt();

        //2. 수정하려는 댓글이 로그인 한 회원이 작성한 댓글인지 검토하는 로직 - 로그인 회원번호 가져와야함
        // int loginMemberIdx = MemberController.getInstance().getLoginMemberIdx();
        int test = 1;//테스트용

        //3. 결과값 보내기
        boolean result = CommentController.getInstance().commentDelete(deleteCommentNum, test);

        //4.응답 결과를 출력
        if(result){
            System.out.println("댓글 삭제 완료");
        } else{
            System.out.println("댓글 삭제 실패 : 직접 작성한 댓글만 삭제할 수 있습니다.");
        }// if-else end
    } //commentDelete end

    
}// class end
