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
    private User user;
    private Chirp chirp;

    @BeforeEach
    public void setUp() {
        usersList = new UserRepository();
        usersList.addUser("UID1", "Auteur", new Date());
        user = usersList.getUserById("UID1");
        chirp = new Chirp(1,user,"Contenu du chirp",LocalDateTime.now());
    }

    @Test
    public void testRepostOwnChirp() {
        // L'auteur essaye de republier son propre chirp
        assertThrows(IllegalArgumentException.class, () -> {
            user.repostChirp(chirp);
        });
    }

//    @Test
//    public void testLikeSameChirpMultipleTimes() {
//        User user1 = new User("UID2", "Utilisateur1");
//        User user2 = new User("UID3", "Utilisateur2");
//
//        // L'auteur aime le chirp initialement
//        assertEquals(1, chirp.getLikeCount());
//
//        // L'utilisateur 1 aime le chirp
//        chirp.like(user1);
//        assertEquals(2, chirp.getLikeCount());
//
//        // L'utilisateur 1 tente d'aimer le même chirp à nouveau
//        assertThrows(IllegalArgumentException.class, () -> {
//            chirp.like(user1);
//        });
//
//        // L'utilisateur 2 aime le chirp
//        chirp.like(user2);
//        assertEquals(3, chirp.getLikeCount());
//    }
//
//    @Test
//    public void testRepostSameChirpMultipleTimes() {
//        User user1 = new User("UID2", "Utilisateur1");
//        User user2 = new User("UID3", "Utilisateur2");
//
//        // L'utilisateur 1 re-publie le chirp
//        chirp.repost(user1);
//        assertEquals(1, chirp.getRepostCount());
//
//        // L'utilisateur 1 tente de re-publier le même chirp à nouveau
//        assertThrows(IllegalArgumentException.class, () -> {
//            chirp.repost(user1);
//        });
//
//        // L'utilisateur 2 re-publie le chirp
//        chirp.repost(user2);
//        assertEquals(2, chirp.getRepostCount());
//    }
//
//    @Test
//    public void testNegativeRepostCount() {
//        User user1 = new User("UID2", "Utilisateur1");
//
//        // L'utilisateur 1 re-publie le chirp
//        chirp.repost(user1);
//        assertEquals(1, chirp.getRepostCount());
//
//        // L'utilisateur 1 tente de dé-republier (un-repost) le chirp (ce n'est pas possible)
//        assertThrows(IllegalArgumentException.class, () -> {
//            chirp.unrepost(user1);
//        });
//
//        assertEquals(1, chirp.getRepostCount());
//    }
}
