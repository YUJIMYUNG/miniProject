package controller;

import model.BoardDao;
import model.BoardDto;
import model.MemberDao;
import model.MemberDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberController {
    private static MemberController memberController = new MemberController();
    private MemberController(){}
    public static MemberController getInstance(){return memberController;};

    // 멤버 등록 제어 함수
    public boolean memberWrite( String member_name, String member_email, LocalDate birthDate,
                               String member_phone, LocalDateTime member_date, boolean In_active)
    {
        MemberDto memberDto = new MemberDto( member_name,member_email,
                                        birthDate, member_phone, member_date, In_active);
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
    public boolean memberUpdate(MemberDto memberDto){
        boolean result = MemberDao.getInstance().memberUpdate(memberDto);
        return result;
    }
}
