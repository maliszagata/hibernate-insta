package pl.edu.agh.mwo.hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "user_id")
    private long idUserOwner;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIdUserOwner(long idUserOwner) {
        this.idUserOwner = idUserOwner;
    }

    public long getIdUserOwner() {
        return idUserOwner;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "albumId")
    private Set<Photo> photos = new HashSet<>();

    public void addPhoto(Photo photo) {
        photos.add(photo);
    }
}
