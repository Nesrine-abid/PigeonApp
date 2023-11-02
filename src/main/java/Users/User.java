package Users;
import Chirps.Chirp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class User {
    private String userId;
    private String username;
    private Date registrationDate;
    private List<User> subscriberList;
    private List<Chirp> chirpsList;

    public User(String userId, String username, Date date) {
        this.userId = userId;
        this.username = username;
        this.registrationDate = date;
        this.subscriberList = new ArrayList<User>();
        this.chirpsList = new ArrayList<Chirp>();
    }
    public String getUserId() {
        return userId;
    }
    public List<User> getSubscriberList() {
        return subscriberList;
    }
    public List<Chirp> getChirpsList() {
        return chirpsList;
    }

    public void setChirpsList(List<Chirp> chirps_list) {
        this.chirpsList = chirps_list;
    }

    public void setSubscriberList(List<User> subscriberList) {
        this.subscriberList = subscriberList;
    }

    public void subscribe(User user) {
        if (!subscriberList.contains(user) && !this.getUserId().equals(user.getUserId())) {
            subscriberList.add(user);
        } else {
            throw new IllegalArgumentException(" Un utilisateur ne peut ni s’abonner à lui-même, ni s’abonner plusieurs fois à la même personne.");
        }
    }
    public void unsubscribe(User user) {
        if (subscriberList.contains(user)) {
            subscriberList.remove(user);
        } else {
            throw new IllegalArgumentException("Un utilisateur ne peut se désabonné d'une personne si elle n'est pas dans sa liste d'abonnés");
        }
    }
    public Chirp publishChirp (int chirpID, String content) {
        if (isContentValid(content)){
            Chirp chirp = new Chirp(chirpID,this,content, LocalDateTime.now());
            this.getChirpsList().add(chirp);
            return chirp;
        } else {
            throw new IllegalArgumentException("Contenue du chirp est invalide.");
        }
    }
    public void repostChirp (Chirp chirp) {
        if (!this.getChirpsList().contains((chirp))) {
            chirp.repost(this);
        } else {
            throw new IllegalArgumentException(" Un utilisateur ne peut pas re-publier son propre chirp.");
        }
    }
    public void likeChirp (Chirp chirp) {
        chirp.like(this);
    }
    public void unlikeChirp (Chirp chirp) {
        chirp.unlike(this);
    }
    public void unrepostChirp (Chirp chirp) {
        chirp.unrepost(this);
    }
    private boolean isContentValid(String content) {
        return content != null && content.length() > 0 && content.length() <= 80;
    }
}
