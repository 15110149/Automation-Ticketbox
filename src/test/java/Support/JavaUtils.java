package Support;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Locale;

public class JavaUtils {

    public static String generateEmail(){
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("vi"), new RandomService());

        String email = fakeValuesService.bothify("????##@gmail.com");
        return email;
    }

    public static String generateCustomerName(){
        Faker faker = new Faker();
        String customerName = faker.address().firstName()+faker.address().lastName();

        return customerName;
    }

    public static String generatePassword(){
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("vi"), new RandomService());

        String password = fakeValuesService.regexify("[a-z1-9]{10}");
        return password;
    }
}
