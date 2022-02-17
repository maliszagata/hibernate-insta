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
    @ManyToMany(mappedBy = "likingUsers")
    private Set<Photo> likedPhotos = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user_owner")
    private Set<Album> albums = new HashSet<>();

    public String getName() {
        return name;
    }

    public Set<Photo> getLikedPhotos() {
        return likedPhotos;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void likePhoto(Photo photo) {
        likedPhotos.add(photo);
        photo.addLikingUser(this);
    }

    public void dislikePhoto(Photo photo) {
        likedPhotos.remove(photo);
    }

}
