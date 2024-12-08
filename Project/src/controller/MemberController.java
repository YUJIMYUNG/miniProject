package controller;

import model.MemberDao;
import model.MemberDto;
import view.MemberView;

import java.time.LocalDate;
import java.util.ArrayList;

public class MemberController {
    private static MemberController memberController = new MemberController();
    private MemberController(){}
    public static MemberController getInstance(){return memberController;};

    // 멤버 로그인 제어 함수


    // 멤버 등록 제어 함수
    public boolean memberWrite(String member_name, String member_email, String pwd , LocalDate birthDate,
                               String member_phone, boolean In_active)
    {
        MemberDto memberDto = new MemberDto( member_name,member_email, pwd,
                                        birthDate, member_phone,  In_active);
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
        boolean result = MemberDao.getInstance().memberUpdate(updateDto);
        return result;
    }
}
