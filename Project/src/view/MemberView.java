package view;

import controller.MemberController;
import model.MemberDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class MemberView {
    //싱글톤
    private static MemberView memberView = new MemberView();
    private MemberView(){};
    public static MemberView getInstance(){return memberView;};
    Scanner scan = new Scanner(System.in);

    // 메인페이지
    public void mainPage() {
        System.out.println("   ____               _            __  __                                      ");
        System.out.println("  |  _ \\  _ __  _   _| |_ ___     |  \\/  | __ _ _ __   __ _  __ _  ___ _ __    ");
        System.out.println("  | |_) || '_ \\| | | | __/ _ \\    | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '__|   ");
        System.out.println("  |  __/ | | | | |_| | ||  __/    | |  | | (_| | | | | (_| | (_| |  __/ |      ");
        System.out.println("  |_|    |_| |_|\\__,_|\\__\\___|    |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_|      ");
        System.out.println("                                                           |___/               ");
        System.out.println("==============================================================================");

        System.out.print("1.로그인 2.회원가입  : ");
        int choose = scan.nextInt();
        if (choose == 1){
            memberLogin();
        } else if (choose == 2) {
            memberWrite();
        }
    }

    // 기능 페이지
    void functionPage(){
        System.out.println("=================function=================");
        System.out.print("1.회원 기능 2.게시판 기능 3.로그아웃: ");
        int choose = scan.nextInt();
        if (choose == 1){
            memberPage();
        } else if (choose == 2) {
            BoardView.getInstance().mainBoard();
        } else if (choose == 3) {
            mainPage();
        }
    }

    // 멤버 페이지
    void memberPage(){
        while (true) {
            System.out.println("=================Member page=================");
            System.out.print("1.회원 조회 2.회원 수정 3.회원 삭제 4.뒤로가기: ");
            int choose = scan.nextInt();
            if (choose == 1) {
                memberPrint();
            } else if (choose == 2) {
                memberUpdate();
            } else if (choose == 3) {
                memberDelete();
            } else if (choose == 4) {
                functionPage();
            }
        }
    }

    // 멤버 로그인 함수
    void memberLogin(){
        System.out.println("=================Login=================");
        System.out.print("이메일 : ");
        String member_email = scan.next();
        System.out.print("비밀번호 : ");
        String member_pwd = scan.next();
        boolean result = MemberController.getInstance().memberLogin(member_email, member_pwd);
        if (result){
            functionPage();
        }else {
            memberLogin();
        }
    }

    // 멤버 등록 함수
    void memberWrite(){
        System.out.println("=================Register=================");
        System.out.print("이름 : ");
        String member_name = scan.next();
        System.out.print("이메일(ex]abc123@domain.com: ");
        String member_email = scan.next();
        System.out.print("비밀번호 : ");
        String pwd = scan.next();

        DateTimeFormatter birthFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthdate = null;
        boolean validBirthdate = false;

        while (!validBirthdate){
            try {
                System.out.print("생년월일(ex] 2001-11-11) : ");
                String birthInput = scan.next();
                birthdate = LocalDate.parse(birthInput, birthFormatter);
                validBirthdate = true;
            } catch (DateTimeParseException e){
                System.out.println("[잘못된 형식입니다. 다시 입력해주세요]");
            }
        }

        String member_phone = null;
        boolean validPhone = false;
        while (!validPhone){
            System.out.print("전화번호(ex] 010-0000-0000) : ");
            member_phone = scan.next();
            if (member_phone.matches("\\d{3}-\\d{4}-\\d{4}")){
                validPhone = true;
            } else {
                System.out.println("[잘못된 형식입니다. 다시 입력해주세요]");
            }
        }
        System.out.print("활성:1/비활성:0 : ");
        int booleanInput = scan.nextInt();
        boolean In_active = (booleanInput == 1);

        boolean result = MemberController.getInstance().memberWrite(member_name, member_email, pwd, birthdate, member_phone, In_active);
        if (result){
            System.out.println("[회원 등록 성공]");
        }else {
            System.out.println("[회원 등록 실패]");
        }
    }

    // 멤버 출력 함수
    void memberPrint(){
        System.out.println("=================MemberList=================");
        ArrayList<MemberDto> result = MemberController.getInstance().memberPrint();
        for (int index = 0; index <= result.size() - 1; index++){
            System.out.print("회원 번호 : " + result.get(index).getMember_idx());
            System.out.print(" | 회원 이름 : " + result.get(index).getMember_name());
            System.out.print(" | 회원 이메일 : " + result.get(index).getMember_email());
            System.out.print(" | 회원 생년월일 : " + result.get(index).getBirthdate());
            System.out.print(" | 회원 전화번호 : " + result.get(index).getMember_phone());
            System.out.print(" | 회원 등록일 : " + result.get(index).getMember_date());
            System.out.println(" | 활성 여부(활성:1/비활성:0) : " + result.get(index).isIn_active());
        }
    }

    // 멤버 삭제 함수
    void memberDelete(){
        int loggedInUserId = MemberController.getInstance().getLoggedInUserId();
        System.out.println("=================MemberDelete=================");
        System.out.println("[본인의 회원 번호만 삭제 가능]");
        System.out.print("삭제할 회원 번호 : ");
        int deleteNum = scan.nextInt();
        if (deleteNum != loggedInUserId){
            System.out.println("[수정 실패 : 본인의 회원번호만 삭제 가능합니다.]");
            return;
        }
        boolean result = MemberController.getInstance().memberDelete(deleteNum);
        if (result){
            System.out.println("[회원 삭제 성공]");
        } else {
            System.out.println("[회원 삭제 실패 : 존재하지 않는 회원 / 관리자 문의]");
        }
    }

    // 멤버 수정 함수
    void memberUpdate(){
        int loggedInUserId = MemberController.getInstance().getLoggedInUserId();
        System.out.println("=================MemberUpdate=================");
        System.out.print("[본인의 정보만 수정 가능]");
        System.out.print("수정할 회원 번호 : ");
        int updateNum = scan.nextInt();

        if (updateNum != loggedInUserId){
            System.out.println("[수정 실패 : 본인의 회원 정보만 수정 가능합니다.]");
            return;
        }
        System.out.print("수정할 비밀번호 : ");
        String update_pwd = scan.next();
        System.out.print("수정할 전화번호 : ");
        String updatePhone = scan.next();
        System.out.print("활성 수정(활성:1/비활성:0) : ");
        int booleanInput = scan.nextInt();
        boolean update_active = (booleanInput == 1);

        MemberDto updateDto  = new MemberDto(updateNum, update_pwd, updatePhone, update_active);
        boolean result = MemberController.getInstance().memberUpdate(updateDto);
        if (result) {
            System.out.println("[회원 수정 성공]");
        } else {
            System.out.println("[회원 수정 실패]");
        }
    }
}
