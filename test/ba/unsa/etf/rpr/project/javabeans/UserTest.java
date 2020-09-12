package ba.unsa.etf.rpr.project.javabeans;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    public void testConstructor(){
        User user = new User("Samija","Kujrakovic","skujrakovi1@etf.unsa.ba","skujrakovi1","somepassword10");
        assertEquals("Samija",user.getName());
        assertEquals("Kujrakovic",user.getSurname());
        assertEquals("skujrakovi1@etf.unsa.ba",user.getEmail());
        assertEquals("skujrakovi1",user.getUsername());
        assertEquals("somepassword10",user.getPassword());
    }
    @Test
    public void testSetters(){
        User user = new User("Samija","Kujrakovic","skujrakovi1@etf.unsa.ba","skujrakovi1","somepassword10");
        user.setName("Name");
        user.setSurname("Surname");
        user.setEmail("Email");
        user.setPassword("Password");
        user.setUsername("Username");
        assertEquals("Name",user.getName());
        assertEquals("Surname",user.getSurname());
        assertEquals("Email",user.getEmail());
        assertEquals("Username",user.getUsername());
        assertEquals("Password",user.getPassword());
    }

    @Test
    public void testToString(){
        User user = new User("Samija","Kujrakovic","skujrakovi1@etf.unsa.ba","skujrakovi1","somepassword10");
        assertEquals("Samija Kujrakovic",user+"");
    }
}