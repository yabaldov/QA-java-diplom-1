package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    private final String nameOfValue;
    private final int orderNumber;

    public IngredientTypeTest(String nameOfValue, int orderNumber) {
        this.nameOfValue = nameOfValue;
        this.orderNumber = orderNumber;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"SAUCE", 0},
                {"FILLING", 1},
        };
    }
    @Test
    public void shouldHaveValueAndInCorrectOrder() throws Exception {
        assertThat(
                "Enum constants with expected names should be in correct order.",
                IngredientType.valueOf(nameOfValue).ordinal(),
                is(equalTo(orderNumber))
        );
    }
}
