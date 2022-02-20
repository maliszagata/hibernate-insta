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
        User user = queryForUser(userId);
        Photo photo = queryForPhoto(photoId);
        Album album = queryForAlbum(photo.getAlbumId());
        user.likePhoto(photo, album);
        saveUser(user);
    }

    public void dislike(long userId, long photoId) {
        User user = queryForUser(userId);
        Photo photo = queryForPhoto(photoId);
        photo.removeLike(user);
        saveUser(user);
    }

    public void saveUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    public void deleteUser(long userId) {
        User user = queryForUser(userId);

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
        Album album = queryForAlbum(albumId);
        Transaction deleteTransaction = session.beginTransaction();
        session.delete(album);
        deleteTransaction.commit();
    }

    public void deletePhoto(long photoId) {
        Photo photo = queryForPhoto(photoId);
        Transaction deleteTransaction = session.beginTransaction();
        session.delete(photo);
        deleteTransaction.commit();
    }

    public void makeFriend(long user1Id, long user2Id) {
        User user1 = queryForUser(user1Id);
        User user2 = queryForUser(user2Id);

        user1.addFriend(user2);
        user2.addFriend(user1);

        Transaction deleteTransaction = session.beginTransaction();
        session.save(user1);
        deleteTransaction.commit();

        user1.addFriendOf(user2);
        user2.addFriendOf(user1);
    }

    public Album queryForAlbum(long albumId) {
        String hql = "FROM Album a WHERE a.id=" + albumId;
        Query<Album> query = session.createQuery(hql, Album.class);
        return query.uniqueResult();
    }

    public User queryForUser(long userId) {
        String userHql = "FROM User u WHERE u.id=" + userId;
        Query<User> userQuery = session.createQuery(userHql, User.class);
        return userQuery.uniqueResult();
    }

    public Photo queryForPhoto(long photoId) {
        String photoHql = "FROM Photo p WHERE p.id=" + photoId;
        Query<Photo> photoQuery = session.createQuery(photoHql, Photo.class);
        return photoQuery.uniqueResult();
    }

}
