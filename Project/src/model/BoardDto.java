package model;

import java.time.LocalDateTime;

public class BoardDto {
    private int idx;
    private int topic;
    private int status;
    private int version;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime date;
    private LocalDateTime update;
    private boolean active;

    /*
    topic
    1.공지 2.회의록 3.투표 4.토의

    status
    1.완료 2.미완
    */

    // 빈 생성자
    public BoardDto(){}

    // boardWrite 생성자
    public BoardDto(int topic, String title, String content){
        this.topic = topic;
        this.title=title;
        this.content=content;
        version=0;

        // 공지 - 진행현황을 완료로 초기화, 나머지는 미완으로 초기화
        if(topic==1){
            status=1;
        } else{
            status=0;
        } // if end

        // 작성자 member에게서 받아오는거 작성해야함
        writer="sample";

        date=LocalDateTime.now();
        update=LocalDateTime.now();
        active=true;
    } // init end

    // boardList 생성자
    public BoardDto(int idx, int topic, String title, String content, String writer, LocalDateTime date, int status, int version, LocalDateTime update, boolean active) {
        this.idx=idx;
        this.topic = topic;
        this.title=title;
        this.content=content;
        this.writer=writer;
        this.date=date;
        this.status=status;
        this.version=version;
        this.update=update;
        this.active=active;
    } // init end

    // boardPrint 생성자
    public BoardDto(int idx, int topic, String title, String content, String writer, LocalDateTime date, int status, int version, LocalDateTime update) {
        this.idx=idx;
        this.topic = topic;
        this.title=title;
        this.content=content;
        this.writer=writer;
        this.date=date;
        this.status=status;
        this.version=version;
        this.update=update;
    }

    public int getIdx() {return idx;}
    public int getTopic() {return topic;}
    public int getStatus() {return status;}
    public int getVersion() {return version;}
    public String getTitle() {return title;}
    public String getContent() {return content;}
    public String getWriter() {return writer;}
    public LocalDateTime getDate() {return date;}
    public LocalDateTime getUpdate() {return update;}
    public boolean getActive() {return active;}

    public void setIdx(int idx) {this.idx = idx;}
    public void setTopic(int topic) {this.topic = topic;}
    public void setStatus(int status) {this.status = status;}
    public void setVersion(int version) {this.version = version;}
    public void setTitle(String title) {this.title = title;}
    public void setContent(String content) {this.content = content;}
    public void setWriter(String writer) {this.writer = writer;}
    public void setDate(LocalDateTime date) {this.date = date;}
    public void setUpdate(LocalDateTime update) {this.update = update;}
    public void setActive(boolean active) {this.active = active;}

    @Override
    public String toString() {
        return "BoardDto{" +
                "idx=" + idx +
                ", topic=" + topic +
                ", status=" + status +
                ", version=" + version +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", date=" + date +
                ", update=" + update +
                '}';
    }
} // class end
