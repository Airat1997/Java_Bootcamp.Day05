package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {

    private long id;
    private String login;

    private String password;
    private List<Chat> createdRooms;
    private List<Chat> visitRooms;

    public User(long id, String login, String password, List<Chat> createdRooms,
            List<Chat> visitRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.visitRooms = visitRooms;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + createdRooms +
                ", visitRooms=" + visitRooms +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(
                password, user.password) && Objects.equals(createdRooms, user.createdRooms)
                && Objects.equals(visitRooms, user.visitRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdRooms, visitRooms);
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Chat> getCreatedRooms() {
        return createdRooms;
    }

    public void setCreatedRooms(List<Chat> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public List<Chat> getVisitRooms() {
        return visitRooms;
    }

    public void setVisitRooms(List<Chat> visitRooms) {
        this.visitRooms = visitRooms;
    }
}
