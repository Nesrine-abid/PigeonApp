package Chirps;

import Users.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Chirp {
    private int chirpId;
    private User author;
    private String content;
    private LocalDateTime publicationDateTime;
    private int likeCount;
    private List<User> likedBy;
    private int repostCount;
    private List<User> repostedBy;
    public Chirp(int chirpId, User author, String content, LocalDateTime publicationDateTime) {
        this.chirpId = chirpId;
        this.author = author;
        this.content = content;
        this.publicationDateTime = publicationDateTime;
        this.likeCount = 1;
        this.likedBy = new ArrayList<>();
        this.likedBy.add(author);
        this.repostCount = 0;
        this.repostedBy = new ArrayList<>();
    }
    public int getLikeCount() {
        return likeCount;
    }
    public int getRepostCount() {
        return repostCount;
    }
    public void like(User user) {
        if (!likedBy.contains(user)) {
            likedBy.add(user);
            likeCount++;
        }else {
            throw new IllegalArgumentException(" Le user ne peut pas liker plusieurs fois par le même chirp.");
        }
    }
    public void unlike(User user) {
        if (likedBy.contains(user)) {
            likedBy.remove(user);
            likeCount--;
        }
    }
    public void repost(User user) {
        if (!repostedBy.contains(user) && !author.equals(user)) {
            repostedBy.add(user);
            repostCount++;
        } else {
            throw new IllegalArgumentException(" Le post ne peut pas être publier plusieurs fois par la même personne.");
        }
    }
    public void unrepost(User user) {
        if (repostedBy.contains(user)) {
            repostedBy.remove(user);
            repostCount--;
        }else {
            throw new IllegalArgumentException(" Le post ne peut pas être unreposted si il n'est pas déjà reposted.");
        }
    }
}