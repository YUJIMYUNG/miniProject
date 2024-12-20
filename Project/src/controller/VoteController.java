package controller;

import model.VoteDto;
import model.VoteDao;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class VoteController {

    private static VoteController voteController = new VoteController();
    private VoteController() {}
    public static VoteController getInstance() {
        return voteController;
    } // 싱글턴 ed


    /// 1.투표 작성 함수
    public boolean VoteWrite(String content,int board_idx,int member_idx, LocalDateTime deadline,ArrayList<String> votedtos_choices) {
        // 객체 생성
        VoteDto voteDto = new VoteDto(content,board_idx,member_idx,deadline,votedtos_choices); // voteDto 객체 작성

        boolean result = VoteDao.getInstance().VoteWrite(voteDto);
        return result;
    }

    /// 2.투표 조회 및 투표 함수
    public ArrayList<VoteDto> VotePage(int board_idx) {
       ArrayList<VoteDto> result = VoteDao.getInstance().VotePage(board_idx);
        return result;
    }

    /// 3.투표 업데이트 함수
    public boolean VoteUpdate(String str) {
        boolean result = VoteDao.getInstance().VoteUpdate(str);
        return result;
    }

    /// 4.선택지 검증용 함수
    public boolean ChoiceCheck(String str) {
        boolean result = VoteDao.getInstance().ChoiceCheck(str);
        return result;
    }
} // VoteController ed
