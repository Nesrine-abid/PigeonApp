package Users;

import Chirps.Chirp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<Long, User> pigeonUsers = new HashMap<>();
    public User getUserById(String userId) {
        for (User user : pigeonUsers.values()) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
    public Map<Long, User> addUser(String userId , String username, Date date) {
        if (isUserIdValid(userId) && isUsernameValid(username) && isRegistrationDateValid(date)) {
            if (!isUserIdUnique(userId)) {
                throw new IllegalArgumentException("Le userId n'est pas unique.");
            }
            User user = new User(userId, username, date);
            long nextId = getNextAvailableId();
            pigeonUsers.put(nextId, user);
            return pigeonUsers;
        } else {
            throw new IllegalArgumentException("Les critères de l'utilisateur ne sont pas valides.");
        }
    }
    private boolean isRegistrationDateValid(Date date) {
        return !date.after(new Date());
    }
    private boolean isUserIdValid(String userId) {
        return userId != null && userId.length() >= 4 && userId.length() <= 24;
    }
    private boolean isUsernameValid(String username) {
        return username != null && username.length() >= 4 && username.length() <= 24;
    }
    private boolean isUserIdUnique(String userId) {
        return pigeonUsers.values().stream().noneMatch(user -> user.getUserId().equals(userId));
    }
    private long getNextAvailableId() {
        long maxId = pigeonUsers.keySet().stream().max(Long::compareTo).orElse(0L);
        return maxId + 1;
    }
}
