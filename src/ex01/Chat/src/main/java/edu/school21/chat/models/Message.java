package edu.school21.chat.models;

import java.util.Date;
import java.util.Objects;

public class Message {

    private Long id;
    private User author;
    private Chat room;
    private String text;
    private Date date;

    public Message(Long id, User author, Chat room, String text, Date date) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Message message = (Message) obj;
        return id == message.id && message.author.equals(this.author) && message.room.equals(
                this.room) && message.text.equals(this.text) && message.date.equals(this.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, room, text, date);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", room=" + room +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }

    public static void main(String[] args) {
        String s = "";
        String s1 = "";
        System.out.println(s.hashCode());
        System.out.println(s1.hashCode());
    }
}