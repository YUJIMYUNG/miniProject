package controller;

import model.VoteDto;
import model.VoteDao;

public class VoteController {

    private static VoteController voteController = new VoteController();
    private VoteController() {}
    public static VoteController getInstance() {
        return voteController;
    } // 싱글턴 ed
    // test

    // 2024-12-04 21:49 김민형 브랜치 병합 테스트

    // 1.투표 작성 함수
    public boolean VoteWrite(String content,int vote_num,String[] vote_content) {
        VoteDto voteDto = new VoteDto(content,vote_num,vote_content); // voteDto 객체 작성

        boolean result = VoteDao.getInstance().VoteWrite(voteDto);
        return result;
    }

} // VoteController ed
