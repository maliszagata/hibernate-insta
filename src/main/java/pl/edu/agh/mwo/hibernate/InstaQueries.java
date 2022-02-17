package pl.edu.agh.mwo.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class InstaQueries {

    Session session;

    public InstaQueries() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public void close() {
        session.close();
        HibernateUtil.shutdown();
    }

    public void addLike(long userId, long photoId) {
        String userHql = "FROM User u WHERE u.id=" + userId;
        Query<User> userQuery = session.createQuery(userHql, User.class);
        User user = userQuery.uniqueResult();

        String photoHql = "FROM Photo p WHERE p.id=" + photoId;
        Query<Photo> photoQuery = session.createQuery(photoHql, Photo.class);
        Photo photo = photoQuery.uniqueResult();

        user.likePhoto(photo);
        saveUser(user);
    }

    public void dislike(long userId, long photoId) {
        String userHql = "FROM User u WHERE u.id=" + userId;
        Query<User> userQuery = session.createQuery(userHql, User.class);
        User user = userQuery.uniqueResult();

        String photoHql = "FROM Photo p WHERE p.id=" + photoId;
        Query<Photo> photoQuery = session.createQuery(photoHql, Photo.class);
        Photo photo = photoQuery.uniqueResult();

       photo.removeLike(user);
        saveUser(user);
    }

    public void saveUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    public void deleteUser(long userId) {
        String hql = "FROM User u WHERE u.id=" + userId;
        Query<User> query = session.createQuery(hql, User.class);
        User user = query.uniqueResult();

        Transaction deleteTransaction = session.beginTransaction();
        for (Photo likedPhoto : user.getLikedPhotos()) {
            likedPhoto.removeLike(user);
            session.update(likedPhoto);
        }
        user.getLikedPhotos().clear();
        session.delete(user);
        deleteTransaction.commit();
    }

    public void deleteAlbum(long albumId) {
        String hql = "FROM Album u WHERE u.id=" + albumId;
        Query<Album> query = session.createQuery(hql, Album.class);
        Album album = query.uniqueResult();

        Transaction deleteTransaction = session.beginTransaction();
        session.delete(album);
        deleteTransaction.commit();
    }

    public void deletePhoto(long photoId) {
        String hql = "FROM Photo u WHERE u.id=" + photoId;
        Query<Photo> query = session.createQuery(hql, Photo.class);
        Photo photo = query.uniqueResult();

        Transaction deleteTransaction = session.beginTransaction();
        session.delete(photo);
        deleteTransaction.commit();
    }


}
