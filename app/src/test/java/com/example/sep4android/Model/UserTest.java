package com.example.sep4android.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getProfiles() {
        PlantProfileList expeted = new PlantProfileList();
        User user = new User("", expeted, new PlantList());
        assertEquals(expeted, user.getProfiles());
    }
    @Test
    public void setProfiles() {
        PlantProfileList ppl = new PlantProfileList();
        User user = new User("", ppl, new PlantList());
        assertEquals(ppl, user.getProfiles());
        PlantProfileList expeted = new PlantProfileList();
        user.setProfiles(expeted);
        assertEquals(expeted, user.getProfiles());
    }

    @Test
    public void getPlants() {
        PlantList expeted = new PlantList();
        User user = new User("", new PlantProfileList(), expeted);
        assertEquals(expeted, user.getPlants());
    }
    @Test
    public void setPlants() {
        PlantList pl = new PlantList();
        User user = new User("", new PlantProfileList(), pl);
        assertEquals(pl, user.getPlants());
        PlantList expeted = new PlantList();
        user.setPlants(expeted);
        assertEquals(expeted, user.getPlants());
    }

    @Test
    public void getEmail() {
        String expeted = "test";
        User user = new User(expeted, new PlantProfileList(), new PlantList());
        assertEquals(expeted, user.getEmail());
    }
    @Test
    public void setEmail() {
        String email = "test";
        User user = new User(email, new PlantProfileList(), new PlantList());
        assertEquals(email, user.getEmail());
        String expeted = "bob";
        user.setEmail(expeted);
        assertEquals(expeted, user.getEmail());
    }

    @Test
    public void getPassword() {
        String expeted = "test";
        User user = new User("", expeted);
        assertEquals(expeted, user.getPassword());
    }
    @Test
    public void setPassword() {
        String pass = "test";
        User user = new User("", pass);
        assertEquals(pass, user.getPassword());
        String expeted = "jfufhenc";
        user.setPassword(expeted);
        assertEquals(expeted, user.getPassword());
    }
}