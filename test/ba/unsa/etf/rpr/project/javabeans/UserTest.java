package ba.unsa.etf.rpr.project.javabeans;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    public void testConstructor(){
        User user = new User("Samija","Kujrakovic","skujrakovi1@etf.unsa.ba","skujrakovi1","somepassword10");
        assertEquals(user.getName(),"Samija");
        assertEquals(user.getSurname(),"Kujrakovic");
        assertEquals(user.getEmail(),"skujrakovi1@etf.unsa.ba");
        assertEquals(user.getUsername(),"skujrakovi1");
        assertEquals(user.getPassword(),"somepassword10");
    }
    @Test
    public void testSetters(){
        User user = new User("Samija","Kujrakovic","skujrakovi1@etf.unsa.ba","skujrakovi1","somepassword10");
        user.setName("Name");
        user.setSurname("Surname");
        user.setEmail("Email");
        user.setPassword("Password");
        user.setUsername("Username");
        assertEquals(user.getName(),"Name");
        assertEquals(user.getSurname(),"Surname");
        assertEquals(user.getEmail(),"Email");
        assertEquals(user.getUsername(),"Username");
        assertEquals(user.getPassword(),"Password");
    }

    @Test
    public void testToString(){
        User user = new User("Samija","Kujrakovic","skujrakovi1@etf.unsa.ba","skujrakovi1","somepassword10");
        assertEquals("Samija Kujrakovic",user+"");
    }
}