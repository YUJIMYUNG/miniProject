package controller;

import model.MemberDao;
import model.MemberDto;
import view.MemberView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberController {
    private static MemberController memberController = new MemberController();
    private MemberController(){}
    private int loggedInUserId = -1;
    public static MemberController getInstance(){return memberController;};

    // 멤버 로그인 제어 함수

    public boolean memberLogin(String member_email, String member_pwd){
        if (member_email == null || member_email.isEmpty()){
            System.out.println("[이메일을 입력해주세요]");
            return false;
        }
        if (member_pwd == null || member_pwd.isEmpty()){
            System.out.println("[비밀번호를 입력해주세요]");
            return false;
        }
        MemberDto loginDto = new MemberDto(member_email, member_pwd);
        boolean loginSuccessful = MemberDao.getInstance().memberLogin(loginDto);
        if (loginSuccessful) {
            System.out.println("DEBUG: 로그인 후 loggedInUserId = " + loggedInUserId);
            loggedInUserId = loginDto.getMember_idx();
            System.out.println("[로그인 성공]");
        }else {
            System.out.println("[로그인 실패 : 아이디/비밀번호가 올바르지 않습니다.");
        }
        return loginSuccessful;
    }

    //로그인된 회원 번호 반환
    public int getLoggedInUserId(){
        return loggedInUserId;
    }

    // 멤버 등록 제어 함수
    public boolean memberWrite(String member_name, String member_email, String pwd , LocalDate birthDate,
                               String member_phone, boolean In_active)
    {
        MemberDto memberDto = new MemberDto( member_name,member_email, pwd,
                                        birthDate, member_phone, In_active);
        memberDto.setMember_date(LocalDateTime.now());
        return MemberDao.getInstance().memberWrite(memberDto);
    }

    // 멤버 출력 제어 함수
    public ArrayList<MemberDto> memberPrint(){
        ArrayList<MemberDto> result = MemberDao.getInstance().memberPrint();
        return result;
    }

    // 멤버 삭제 제어함수
    public boolean memberDelete(int deleteNum){
        boolean result = MemberDao.getInstance().memberDelete(deleteNum);
        return result;
    }

    // 멤버 수정 제어 함수
    public boolean memberUpdate(MemberDto updateDto){
        if (updateDto.getMember_idx() != loggedInUserId){
            System.out.println("[수정 실패 : 본인의 정보만 수정할 수 있습니다.]");
            return false;
        }
        return MemberDao.getInstance().memberUpdate(updateDto);
    }
}
