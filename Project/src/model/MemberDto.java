package model;

public class MemberDto {
    private int member_idx; //회원번호
    private String member_name; //회원이름
    private String member_email; //회원 이메일
    private String pwd; //회원 비밀번호
    private String birthdate; // 회원 생년월일
    private String member_phone; // 회원 전화번호
    private String member_date; // 회원 등록일
    private boolean in_active; // 활성여부

    //생성자
    public MemberDto(int member_idx, String member_name, String member_email, String pwd, String birthdate,
                     String member_phone, String member_date, boolean in_active)
    {
        this.member_idx = member_idx;
        this.member_name = member_name;
        this.member_email = member_email;
        this.pwd = pwd;
        this.birthdate = birthdate;
        this.member_phone = member_phone;
        this.member_date = member_date;
        this.in_active = in_active;
    }

    //getter 및 setter
    public int getMember_idx() {return member_idx;}
    public void setMember_idx(int member_idx) {this.member_idx = member_idx;}

    public String getMember_name() { return member_name;}
    public void setMember_name(String member_name) {this.member_name = member_name;}

    public String getMember_email() { return member_email;}
    public void setMember_email(String member_email) {this.member_email = member_email;}

    public String getPwd() {return pwd;}
    public void setPwd(String pwd) {this.pwd = pwd;}

    public String getBirthdate() {return birthdate;}
    public void setBirthdate(String birthdate) {this.birthdate = birthdate;}

    public String getMember_phone() {return member_phone;}
    public void setMember_phone(String member_phone) {this.member_phone = member_phone;}

    public String getMember_date() {return member_date;}
    public void setMember_date(String member_date) {this.member_date = member_date;}

    public boolean isIn_active() {return in_active;}
    public void setIn_active(boolean in_active) {this.in_active = in_active;}

    // toString
    @Override
    public String toString() {
        return "MemberDto{" +
                "member_idx=" + member_idx +
                ", member_name='" + member_name + '\'' +
                ", member_email='" + member_email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", member_phone='" + member_phone + '\'' +
                ", member_date='" + member_date + '\'' +
                ", in_active=" + in_active +
                '}';
    }
}
