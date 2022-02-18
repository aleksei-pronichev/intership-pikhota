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
        Country spain = new Country();
        spain.setName("Spain");
        spain.setPopulation(46704314);
        spain.setCapital("Madrid");
        spain.setCurrency(Currency.EUR);

        Country uk = new Country();
        uk.setName("United Kingdom");
        uk.setCapital("London");
        uk.setCurrency(Currency.GBP);
        uk.setPopulation(63705000);

        return Stream.of(
                Arguments.of("Spain", spain),
                Arguments.of("United Kingdom", uk));
    }

    @ParameterizedTest
    @MethodSource("initCountries")
    public void findCountryTest(String countryName, Country expectedCountry) {
        Country countryInDB = countryRepository.findCountry(countryName);
        Assertions.assertEquals(countryInDB.getName(), expectedCountry.getName());
        Assertions.assertEquals(countryInDB.getCapital(), expectedCountry.getCapital());
        Assertions.assertEquals(countryInDB.getCurrency(), expectedCountry.getCurrency());
        Assertions.assertEquals(countryInDB.getPopulation(), expectedCountry.getPopulation());
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