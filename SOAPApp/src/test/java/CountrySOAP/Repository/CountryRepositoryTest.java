package CountrySOAP.Repository;

import io.spring.guides.gs_producing_web_service.Country;
import io.spring.guides.gs_producing_web_service.Currency;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    static Stream<Arguments> initCountries() {
        return Stream.of(
                Arguments.of("Spain", new Country("Spain", 46704314, "Madrid", Currency.EUR)),
                Arguments.of("United Kingdom", new Country("United Kingdom", 63705000, "London", Currency.GBP)));
    }

    @ParameterizedTest
    @MethodSource("initCountries")
    public void findCountryTest(String countryName, Country expectedCountry) {
        Country countryInDB = countryRepository.findCountry(countryName);
        Assertions.assertEquals(countryInDB, expectedCountry);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionWhenEmptyInputStringTest() {
        countryRepository.findCountry("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionWhenNullInputStringTest() {
        countryRepository.findCountry(null);
    }
}