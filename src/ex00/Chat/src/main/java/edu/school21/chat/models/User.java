package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {

    private int id;
    private String login;
    private List<Chat> listOfCreatedRooms;
    private List<Chat> listOfUserSocializes;

    public User(int id, String login, List<Chat> listOfCreatedRooms,
            List<Chat> listOfUserSocializes) {
        this.id = id;
        this.login = login;
        this.listOfCreatedRooms = listOfCreatedRooms;
        this.listOfUserSocializes = listOfUserSocializes;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", listOfCreatedRooms=" + listOfCreatedRooms +
                ", listOfUserSocializes=" + listOfUserSocializes +
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
                listOfCreatedRooms, user.listOfCreatedRooms) && Objects.equals(
                listOfUserSocializes, user.listOfUserSocializes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, listOfCreatedRooms, listOfUserSocializes);
    }

    public int getId() {
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

    public List<Chat> getListOfCreatedRooms() {
        return listOfCreatedRooms;
    }

    public void setListOfCreatedRooms(List<Chat> listOfCreatedRooms) {
        this.listOfCreatedRooms = listOfCreatedRooms;
    }

    public List<Chat> getListOfUserSocializes() {
        return listOfUserSocializes;
    }

    public void setListOfUserSocializes(List<Chat> listOfUserSocializes) {
        this.listOfUserSocializes = listOfUserSocializes;
    }
}
