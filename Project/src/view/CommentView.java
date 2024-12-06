package view;

import controller.CommentController;
import model.CommentDto;

import java.util.ArrayList;
import java.util.Scanner;

public class CommentView {
    private static CommentView commentView = new CommentView();
    private CommentView(){}
    public static CommentView getInstance(){return commentView;}

    Scanner sc = new Scanner(System.in);

    public void mainPage(){
        while(true){
            System.out.print("1.댓글 조회 2.댓글 등록 3.댓글 수정 4.댓글 삭제  1~4 중 선택하세요 : ");
            int choose = sc.nextInt();

            if(choose == 1){
                commentPrint();
            } else if(choose == 2){
                commentWrite();
            }
        }//while end
    }// main end

    //1. 댓글 조회
    void commentPrint(){
        // 객체 정보 요청
        ArrayList<CommentDto> result = CommentController.getInstance().commentPrint();

        for(int i = 0; i < result.size(); i++){
            System.out.printf("댓글 번호 : %s, 댓글 작성자 : %s, 내용 : %s, 작성 시간 : %s, 수정 여부 : %s\n",
                    result.get(i).getComment_idx(), result.get(i).getMember_idx(), result.get(i).getComment_content(),
                    result.get(i).getComment_date(), result.get(i).getComment_update());
        }// for end
    }// commentPrint end

    //2. 댓글 등록
    void commentWrite(){
        //컨트롤러에서 댓글 작성자가 로그인 한 회원인지 검토하는 작업 추가해야함

        // 1. 입력받기
        sc.nextLine();

        System.out.print("댓글 내용을 입력하세요 : ");
        String content = sc.nextLine();

        //2. 입력받은 값 컨트롤러에 전달
        boolean result = CommentController.getInstance().commentWrite(content);

        //3. 컨트롤러에게 전달 후 결과 출력
        if(result) {
            System.out.println("댓글 등록 성공");
        } else{
            System.out.println("댓글 등록 실패");
        }// if-else end
    }// commentWrite end


    
}// class end
