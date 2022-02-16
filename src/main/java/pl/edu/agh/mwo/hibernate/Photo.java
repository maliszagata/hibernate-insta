package pl.edu.agh.mwo.hibernate;

import javax.persistence.*;
import java.time.ZonedDateTime;
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

    @ManyToMany(mappedBy = "likedPhotos", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> likingUsers = new HashSet<>();

    public void addLikingUser(User user) {
        likingUsers.add(user);
    }
}
