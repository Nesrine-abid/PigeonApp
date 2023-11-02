package Chirps;

import Users.User;
import Users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ChirpTest {
    private UserRepository usersList;
    private User user0,user1,user2;
    private Chirp chirp;

    @BeforeEach
    public void setUp() {
        usersList = new UserRepository();
        usersList.addUser("UID0", "Author", new Date());
        usersList.addUser("UID1", "User1", new Date());
        usersList.addUser("UID2", "User2", new Date());

        user0 = usersList.getUserById("UID0");
        user1 = usersList.getUserById("UID1");
        user2 = usersList.getUserById("UID2");
        chirp = user0.publishChirp(1,"Contenu du chirp");
    }

    @Test
    public void testRepostOwnChirp() {
        // L'auteur essaye de republier son propre chirp
        assertThrows(IllegalArgumentException.class, () -> {
            user0.repostChirp(chirp);
        });
    }
    @Test
    public void testLikeSameChirpMultipleTimes() {
        // Author aime le chirp
        assertEquals(1, chirp.getLikeCount());
        // User 1 aime le chirp
        chirp.like(user1);
        assertEquals(2, chirp.getLikeCount());
        // User 1 tente d'aimer le même chirp à nouveau
        assertThrows(IllegalArgumentException.class, () -> {
            chirp.like(user1);
        });
        // User 2 aime le chirp
        chirp.like(user2);
        assertEquals(3, chirp.getLikeCount());
    }
    @Test
    public void testRepostSameChirpMultipleTimes() {
        // User 1 republie le chirp
        chirp.repost(user1);
        assertEquals(1, chirp.getRepostCount());
        // User 1 tente de re-publier le même chirp à nouveau
        assertThrows(IllegalArgumentException.class, () -> {
            chirp.repost(user1);
        });
        // L'utilisateur 2 re-publie le chirp
        chirp.repost(user2);
        assertEquals(2, chirp.getRepostCount());
    }
    @Test
    public void testNegativeRepostCount() {

        // User1 tente de un-reposter le chirp
        assertThrows(IllegalArgumentException.class, () -> {
            chirp.unrepost(user1);
        });
        assertEquals(0, chirp.getRepostCount());
    }
}
