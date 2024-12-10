import view.BoardView;
import model.MemberDto;
import view.CommentView;
import view.MemberView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AppStart {
    public static void main(String[] args) {
        LocalDateTime dt=LocalDateTime.now();
        System.out.println(dt);
        System.out.println(dt.getMonthValue());
        String dtFormat1=dt.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm"));
        System.out.println(dtFormat1);

        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("디버그용 코드입니다.\n1.board 2.comment 3.member 4.vote ");
            int choose = scan.nextInt();
            if (choose == 1) {
                BoardView.getInstance().mainBoard();
            } else if (choose == 2) {
                CommentView.getInstance().mainPage();
            } else if (choose == 3) {

            } else if (choose == 4) {

            } else {
                return;
            }
        }
    }
}
