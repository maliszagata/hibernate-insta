package pl.edu.agh.mwo.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Main {

    Session session;

    public static void main(String[] args) {
        Main main = new Main();

        main.createSomeUsers();

        main.addLike(1, 7);

//        main.deleteUser(1);
//        main.deleteUser(2);
//        main.deleteAlbum(3);
//        main.deletePhoto(8);

        main.close();
    }

    private void addLike(long userId, long photoId) {
        String userHql = "FROM User u WHERE u.id=" + userId;
        Query<User> userQuery = session.createQuery(userHql, User.class);
        User user = userQuery.uniqueResult();

        String photoHql = "FROM Photo p WHERE p.id=" + photoId;
        Query<Photo> photoQuery = session.createQuery(photoHql, Photo.class);
        Photo photo = photoQuery.uniqueResult();

        user.likePhoto(photo);

        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    private void saveUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    private void deleteUser(long userId) {
        String hql = "FROM User u WHERE u.id=" + userId;
        Query<User> query = session.createQuery(hql, User.class);
        User user = query.uniqueResult();

        Transaction deleteTransaction = session.beginTransaction();
        session.delete(user);
        deleteTransaction.commit();
    }

    private void deleteAlbum(long albumId) {
        String hql = "FROM Album u WHERE u.id=" + albumId;
        Query<Album> query = session.createQuery(hql, Album.class);
        Album album = query.uniqueResult();

        Transaction deleteTransaction = session.beginTransaction();
        session.delete(album);
        deleteTransaction.commit();
    }

    private void deletePhoto(long photoId) {
        String hql = "FROM Photo u WHERE u.id=" + photoId;
        Query<Photo> query = session.createQuery(hql, Photo.class);
        Photo photo = query.uniqueResult();

        Transaction deleteTransaction = session.beginTransaction();
        session.delete(photo);
        deleteTransaction.commit();
    }

    private void createSomeUsers() {
        //        User1
        User user1 = new User();
        user1.setName("instaUser");

        Album album1 = new Album();
        album1.setName("Fotki z wakacji");
        album1.setDescription("To są moje fotki z wakacji.");
        user1.addAlbum(album1);

        Photo photo1 = new Photo();
        photo1.setName("Wakacje w Łebie 2020");
        album1.addPhoto(photo1);

        Photo photo2 = new Photo();
        photo2.setName("Wakacje w Ustce 2021");
        album1.addPhoto(photo2);

        Album album2 = new Album();
        album2.setName("Fotki mojego psa");
        album2.setDescription("To są moje fotki mojego Fafika.");
        user1.addAlbum(album2);

        Photo photo3 = new Photo();
        photo3.setName("Fafik na spacerze");
        album2.addPhoto(photo3);

        Photo photo4 = new Photo();
        photo4.setName("Fafik słodko śpi");
        album2.addPhoto(photo4);
        user1.addAlbum(album2);

        this.saveUser(user1);

        //        User2
        User user2 = new User();
        user2.setName("fitUser");

        Album album3 = new Album();
        album3.setName("Fotki z siłowni");
        album3.setDescription("To są moje fotki z siłowni.");
        user2.addAlbum(album3);

        Photo photo5 = new Photo();
        photo5.setName("Podczas treningu");
        album3.addPhoto(photo5);

        Photo photo6 = new Photo();
        photo6.setName("Po treningu");
        album3.addPhoto(photo6);

        Album album4 = new Album();
        album4.setName("Fotki mojego jedzenia");
        album4.setDescription("To są moje fotki mojego jedzenia.");
        user2.addAlbum(album4);

        Photo photo7 = new Photo();
        photo7.setName("Śniadanie białkowe");
        album4.addPhoto(photo7);

        Photo photo8 = new Photo();
        photo8.setName("Obiad w cheat day");
        album4.addPhoto(photo8);
        user2.addAlbum(album4);

        this.saveUser(user2);
    }

    public Main() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public void close() {
        session.close();
        HibernateUtil.shutdown();
    }
}
