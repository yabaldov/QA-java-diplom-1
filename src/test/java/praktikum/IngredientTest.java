package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IngredientTest {

    private final static IngredientType INGREDIENT_TYPE = IngredientType.FILLING;
    private final static String INGREDIENT_NAME = "dinosaur";
    private final static float INGREDIENT_PRICE = 200;

    private Ingredient ingredient;

    @Before
    public void setup() {
        ingredient = new Ingredient(INGREDIENT_TYPE, INGREDIENT_NAME, INGREDIENT_PRICE);
    }

    @Test
    public void shouldGetIngredientType() {
        assertThat(
                String.format("Ingredient type should be '%s'.", INGREDIENT_TYPE.name()),
                ingredient.getType(),
                is(INGREDIENT_TYPE)
        );
    }

    @Test
    public void shouldGetIngredientName() {
        assertThat(
                String.format("Ingredient name should be '%s'.", INGREDIENT_NAME),
                ingredient.getName(),
                is(INGREDIENT_NAME)
        );
    }

    @Test
    public void shouldGetIngredientPrice() {
        assertThat(
                String.format("Ingredient type should be '%f'.", INGREDIENT_PRICE),
                ingredient.getPrice(),
                is(INGREDIENT_PRICE)
        );
    }
}
