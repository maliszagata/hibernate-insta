package pl.edu.agh.mwo.hibernate;

public class Main {

//    Session session;

    public static void main(String[] args) {
        InstaQueries instaQueries = new InstaQueries();

        createSomeUsers(instaQueries);

        instaQueries.makeFriend(2, 3);
//        instaQueries.makeFriend(1,3);

        instaQueries.addLike(2, 9);
        instaQueries.addLike(2, 10);
        instaQueries.addLike(2, 1);
        instaQueries.addLike(1, 10);
//        instaQueries.addLike(1, 10);
//        instaQueries.addLike(2, 4);
//        instaQueries.addLike(2, 10);
//        instaQueries.addLike(3, 1);
//        instaQueries.addLike(3, 7);
//        instaQueries.addLike(3, 8);
//
        instaQueries.dislike(2, 9);
//
//        instaQueries.deleteUser(2);
//        instaQueries.deletePhoto(10);
//        instaQueries.deleteUser(3);
//        instaQueries.deleteAlbum(3);
//        instaQueries.deletePhoto(8);


        instaQueries.close();

    }


    private static void createSomeUsers(InstaQueries instaQueries) {
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
        user1.addPhoto(photo1);

        Photo photo2 = new Photo();
        photo2.setName("Wakacje w Ustce 2021");
        album1.addPhoto(photo2);
        user1.addPhoto(photo2);

        Album album2 = new Album();
        album2.setName("Fotki mojego psa");
        album2.setDescription("To są moje fotki mojego Fafika.");
        user1.addAlbum(album2);

        Photo photo3 = new Photo();
        photo3.setName("Fafik na spacerze");
        album2.addPhoto(photo3);
        user1.addPhoto(photo3);

        Photo photo4 = new Photo();
        photo4.setName("Fafik słodko śpi");
        album2.addPhoto(photo4);
        user1.addPhoto(photo4);
        user1.addAlbum(album2);

        instaQueries.saveUser(user1);

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
        user2.addPhoto(photo5);

        Photo photo6 = new Photo();
        photo6.setName("Po treningu");
        album3.addPhoto(photo6);
        user2.addPhoto(photo6);

        Album album4 = new Album();
        album4.setName("Fotki mojego jedzenia");
        album4.setDescription("To są moje fotki mojego jedzenia.");
        user2.addAlbum(album4);

        Photo photo7 = new Photo();
        photo7.setName("Śniadanie białkowe");
        album4.addPhoto(photo7);
        user2.addPhoto(photo7);

        Photo photo8 = new Photo();
        photo8.setName("Obiad w cheat day");
        album4.addPhoto(photo8);
        user2.addPhoto(photo8);
        user2.addAlbum(album4);

        instaQueries.saveUser(user2);

        //        User3
        User user3 = new User();
        user3.setName("influencer");

        Album album5 = new Album();
        album5.setName("Polecam");
        album5.setDescription("To są moje fotki rzeczy, które polecam.");
        user3.addAlbum(album5);

        Photo photo9 = new Photo();
        photo9.setName("Mój ulubiony kosmetyk");
        album5.addPhoto(photo9);
        user3.addPhoto(photo9);

        Photo photo10 = new Photo();
        photo10.setName("Ulubiona restauracja");
        album5.addPhoto(photo10);
        user3.addPhoto(photo10);

        instaQueries.saveUser(user3);
    }
}
