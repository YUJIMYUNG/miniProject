import controller.MemberController;
import view.BoardView;
import model.MemberDto;
import view.CommentView;
import view.MemberView;
import view.VoteView;

import java.util.Scanner;

public class AppStart {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("디버그용 코드입니다.\n1.board 2.comment 3.member 4.vote ");
            int choose = scan.nextInt();
            if (choose == 1) {
                BoardView.getInstance().mainBoard();
            } else if (choose == 2) {
                CommentView.getInstance().mainPage(1);
            } else if (choose == 3) {
                MemberView.getInstance().mainPage();
            } else if (choose == 4) {
                VoteView.getInstance().VoteWrite();
            } else {
                return;
            }
        }
    }
}
