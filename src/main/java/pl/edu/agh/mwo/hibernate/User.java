package pl.edu.agh.mwo.hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    public long getId() {
        return id;
    }

    @OneToMany(mappedBy = "likingUsers")
    private Set<Photo> likedPhotos = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<Album> albums = new HashSet<>();

    public Set<Album> getAlbums() {
        return albums;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<Photo> photos = new HashSet<>();

    public Set<Photo> getPhotos() {
        return photos;
    }

    @ManyToMany
    @JoinTable(name = "users_users",
            joinColumns = @JoinColumn(name = "user1_id"),
            inverseJoinColumns = @JoinColumn(name = "user2_id")
    )
    private Set<User> friends = new HashSet<>();

    public Set<User> getFriends() {
        return friends;
    }

    @ManyToMany
    @JoinTable(name = "users_users",
            joinColumns = @JoinColumn(name = "user2_id"),
            inverseJoinColumns = @JoinColumn(name = "user1_id")
    )
    private Set<User> friendOf = new HashSet<>();


    public Set<User> getFriendOf() {
        return friendOf;
    }

    public String getName() {
        return name;
    }

    public Set<Photo> getLikedPhotos() {
        return likedPhotos;
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void likePhoto(Photo photo, Album album) {
        long photoFriends = friends.stream()
                .filter(f -> f.getId() == album.getIdUserOwner())
                .count();

        if (photoFriends > 0) {
            likedPhotos.add(photo);
            photo.addLikingUser(this);
        }
    }

    public void dislikePhoto(Photo photo) {
        likedPhotos.remove(photo);
    }

    public void addFriend(User user) {
        this.friends.add(user);
    }

    public void addFriendOf(User user) {
        this.friendOf.add(user);
    }

    public void addPhoto(Photo photo) {
        photos.add(photo);
    }
}
