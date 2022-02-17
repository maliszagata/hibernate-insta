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


    public void setName(String name) {
        this.name = name;
    }

    @Column
    private String name;

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
