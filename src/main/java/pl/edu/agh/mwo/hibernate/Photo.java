package pl.edu.agh.mwo.hibernate;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private ZonedDateTime dateTime;

}
