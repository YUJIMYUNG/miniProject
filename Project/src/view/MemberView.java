package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MemberView {
    //싱글톤
    private static MemberView memberView = new MemberView();
    private MemberView(){};
    public static MemberView getInstance(){return memberView;};

    Scanner scan = new Scanner(System.in);

    // 멤버 등록 함수
    public void memberWrite(){
        System.out.print("이름 : "); String member_name = scan.next();
        System.out.print("이메일 : "); String member_email = scan.next();
        System.out.print("비밀번호 : "); String pwd = scan.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        System.out.print("생년월일(ex] 2001-11-11) : "); String input = scan.next();
        LocalDate birthdate = LocalDate.parse(input, formatter);
        System.out.print("전화번호 : "); String member_phone = scan.next();
    }

    // 멤버 출력 함수


    // 멤버 삭제 함수


    // 멤버 수정 함수

}
