package model;

import java.time.LocalDate;

public class BoardDto {
    private int idx;
    private int topic;
    private boolean status;
    private String title;
    private String content;
    private String writer;
    private LocalDate date;
    private LocalDate update;

    public int getIdx() {return idx;}
    public int getTopic() {return topic;}
    public boolean isStatus() {return status;}
    public String getTitle() {return title;}
    public String getContent() {return content;}
    public String getWriter() {return writer;}
    public LocalDate getDate() {return date;}
    public LocalDate getUpdate() {return update;}

    public void setIdx(int idx) {this.idx = idx;}
    public void setTopic(int topic) {this.topic = topic;}
    public void setStatus(boolean status) {this.status = status;}
    public void setTitle(String title) {this.title = title;}
    public void setContent(String content) {this.content = content;}
    public void setWriter(String writer) {this.writer = writer;}
    public void setDate(LocalDate date) {this.date = date;}
    public void setUpdate(LocalDate update) {this.update = update;}

    @Override
    public String toString() {
        return "BoardDto{" +
                "idx=" + idx +
                ", topic=" + topic +
                ", status=" + status +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", date=" + date +
                ", update=" + update +
                '}';
    } // func end
} // class end
