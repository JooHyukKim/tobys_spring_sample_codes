package main.user.springbook.dao;

public class DaoFactory {
    // 이후 MessageDao AccountDao 등의 구현에서 중복코드를 피하고자.
    public UserDao UserDao() {
        UserDao userDao = new UserDao(this.connectionMaker());
        return userDao;
    }

    private ConnectionMaker connectionMaker() {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        return connectionMaker;
    }
}
