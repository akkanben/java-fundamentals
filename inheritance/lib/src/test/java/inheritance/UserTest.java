package inheritance;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void test_user_constructor() {
        User sut = new User("Ben", "Mills");
        assert(sut.getFirstName().equals("Ben"));
        assert(sut.getLastName().equals("Mills"));
    }

    @Test
    void test_user_getfirstname() {
        User sut = new User("Ben", "Mills");
        assert(sut.getFirstName().equals("Ben"));
    }

    @Test
    void test_user_getlastname() {
        User sut = new User("Ben", "Mills");
        assert(sut.getLastName().equals("Mills"));
    }

    @Test
    void test_user_tostring() {
        User sut = new User("Ben", "Mills");
        assert(sut.toString().equals("Ben Mills"));
    }
}
