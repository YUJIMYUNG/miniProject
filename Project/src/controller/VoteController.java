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
    public boolean VoteWrite(String content, LocalDateTime deadline,ArrayList<String> votedtos_choices) {
        VoteDto voteDto = new VoteDto(content,deadline,votedtos_choices); // voteDto 객체 작성

        boolean result = VoteDao.getInstance().VoteWrite(voteDto);
        return result;
    }

    /// 2.투표 조회 및 투표 함수
    public ArrayList<VoteDto> VotePage(int board_idx) {
        ArrayList<VoteDto> result = VoteDao.getInstance().VotePage(board_idx);

    }
} // VoteController ed
