package pl.edu.agh.mwo.hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private long albumId;

    @Column(name = "user_id")
    private long idUserOwner;

    public Set<User> getLikingUsers() {
        return likingUsers;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @ManyToMany
    @JoinTable(name = "photos_users",
            joinColumns = @JoinColumn(name = "photo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Set<User> likingUsers = new HashSet<>();

    public void addLikingUser(User user) {
        likingUsers.add(user);
    }

    public void removeLike(User user) {
        likingUsers.remove(user);
    }
}
