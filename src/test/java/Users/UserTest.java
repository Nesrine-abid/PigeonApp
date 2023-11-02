package Users;

import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private UserRepository usersList = new UserRepository();

    @Test
    public void testAddUserWithFutureRegistrationDate() {
        Date futureDate = new Date(System.currentTimeMillis() + 100000);
        assertThrows(IllegalArgumentException.class, () -> {
            usersList.addUser("UID1", "FuturUser", futureDate);
        });
    }

    @Test
    public void testAddUser() {
        Map<Long, User> pigeonUsers = usersList.addUser("UID1", "User", new Date());
        assertEquals(pigeonUsers.size(),1);
    }

    @Test
    public void testAddUserWithDuplicatedId() {
        usersList.addUser("UID1", "DeplicatedUser", new Date());
        assertThrows(IllegalArgumentException.class, () -> {
            usersList.addUser("UID1", "DeplicatedUser", new Date());
        });
    }
    @Test
    public void testSelfSubscription() {
        usersList.addUser("UID1", "Reda", new Date());
        assertThrows(IllegalArgumentException.class, () -> {
            usersList.getUserById("UID1").subscribe(usersList.getUserById("UID1"));
        });
    }

    @Test
    public void testDuplicateSubscription() {
        usersList.addUser("UID6", "Reda", new Date());
        usersList.addUser("UID7", "Hiba", new Date());
        usersList.getUserById("UID6").subscribe(usersList.getUserById("UID7"));

        assertThrows(IllegalArgumentException.class, () -> {
            usersList.getUserById("UID6").subscribe(usersList.getUserById("UID7"));
        });
    }

    @Test
    public void testSubscription() {
        usersList.addUser("UID6", "Reda", new Date());
        usersList.addUser("UID7", "Hiba", new Date());
        User user1 = usersList.getUserById("UID6");
        User user2 = usersList.getUserById("UID7");

        // Assurez-vous que user2 n'est pas dans la liste d'abonnements de user1
        assertFalse(user1.getSubscriberList().contains(user2));

        //Abonnez user1 à user2
        user1.subscribe(user2);

        // Assurez-vous que user2 est dans la liste d'abonnements de user1
        assertTrue(user1.getSubscriberList().contains(user2));
    }
}
