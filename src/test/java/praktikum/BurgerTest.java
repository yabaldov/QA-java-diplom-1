package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    private final static String BLACK_BUN_NAME = "black bun";
    private final static float BLACK_BUN_PRICE = 100F;
    private final static String HOT_SAUCE_INGREDIENT_NAME = "hot sauce";
    private final static float HOT_SAUCE_INGREDIENT_PRICE = 100F;
    private final static IngredientType HOT_SAUCE_INGREDIENT_TYPE = IngredientType.SAUCE;
    private final static String DINOSAUR_INGREDIENT_NAME = "dinosaur";
    private final static float DINOSAUR_INGREDIENT_PRICE = 200F;
    private final static IngredientType DINOSAUR_INGREDIENT_TYPE = IngredientType.FILLING;
    private final static float EXPECTED_TOTAL_PRICE = BLACK_BUN_PRICE * 2 + HOT_SAUCE_INGREDIENT_PRICE + DINOSAUR_INGREDIENT_PRICE;

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    @Mock
    Ingredient ingredientOneMore;

    @Before
    public void setup() {
        burger = new Burger();
    }

    @Test
    public void shouldSetBuns() {
        Mockito.when(bun.getName()).thenReturn(BLACK_BUN_NAME);
        Mockito.when(bun.getPrice()).thenReturn(BLACK_BUN_PRICE);
        burger.setBuns(bun);

        assertThat("Burger should contain a bun with the expected name.", burger.bun.getName(), is(BLACK_BUN_NAME));
        assertThat("Burger should contain a bun with the expected price.", burger.bun.getPrice(), is(BLACK_BUN_PRICE));
    }

    @Test
    public void shouldAddIngredient() {
        burger.addIngredient(ingredient);

        assertThat(
                "After adding the list of ingredients should contain 1 item.",
                burger.ingredients.size(),
                equalTo(1)
        );
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhileRemovingNonExistentIngredient() throws Exception {
        burger.removeIngredient(burger.ingredients.size());
    }

    @Test
    public void shouldRemoveIngredient() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(burger.ingredients.size() - 1);

        assertThat(
                "After removing the list of ingredients should contain no item.",
                burger.ingredients.size(),
                equalTo(0)
        );
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhileMovingNonExistentIngredient() throws Exception {
        burger.moveIngredient(0, 1);
    }

    @Test
    public void shouldMoveIngredientToTheSamePosition() {
        Mockito.when(ingredient.getName()).thenReturn(HOT_SAUCE_INGREDIENT_NAME);
        burger.addIngredient(ingredient);

        burger.moveIngredient(0, 0);

        assertThat(
                "After moving the list of ingredients should contain 1 item.",
                burger.ingredients.size(),
                equalTo(1)
        );
        assertThat(
                "After moving the ingredient should have the expected name.",
                burger.ingredients.get(0).getName(),
                is(HOT_SAUCE_INGREDIENT_NAME)
        );
    }

    @Test
    public void shouldSwapIngredients() throws Exception {
        Mockito.when(ingredient.getName()).thenReturn(HOT_SAUCE_INGREDIENT_NAME);
        Mockito.when(ingredientOneMore.getName()).thenReturn(DINOSAUR_INGREDIENT_NAME);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientOneMore);

        burger.moveIngredient(0, 1);

        assertThat(
                String.format("After moving the first item should have %s name.", DINOSAUR_INGREDIENT_NAME),
                burger.ingredients.get(0).getName(),
                is(DINOSAUR_INGREDIENT_NAME)
        );
        assertThat(
                String.format("After moving the first item should have %s name.", DINOSAUR_INGREDIENT_NAME),
                burger.ingredients.get(1).getName(),
                is(HOT_SAUCE_INGREDIENT_NAME)
        );
    }

    @Test
    public void shouldReturnBurgerTotalPrice() {
        Mockito.when(bun.getPrice()).thenReturn(BLACK_BUN_PRICE);
        Mockito.when(ingredient.getPrice()).thenReturn(HOT_SAUCE_INGREDIENT_PRICE);
        Mockito.when(ingredientOneMore.getPrice()).thenReturn(DINOSAUR_INGREDIENT_PRICE);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientOneMore);

        assertThat(String.format(
                "Total price of the burger should be %.2f", EXPECTED_TOTAL_PRICE),
                burger.getPrice(),
                is(EXPECTED_TOTAL_PRICE)
        );
    }

    @Test
    public void shouldReturnBurgerReceipt() {
        Mockito.when(bun.getName()).thenReturn(BLACK_BUN_NAME);
        Mockito.when(bun.getPrice()).thenReturn(BLACK_BUN_PRICE);
        Mockito.when(ingredient.getName()).thenReturn(HOT_SAUCE_INGREDIENT_NAME);
        Mockito.when(ingredient.getPrice()).thenReturn(HOT_SAUCE_INGREDIENT_PRICE);
        Mockito.when(ingredient.getType()).thenReturn(HOT_SAUCE_INGREDIENT_TYPE);
        Mockito.when(ingredientOneMore.getName()).thenReturn(DINOSAUR_INGREDIENT_NAME);
        Mockito.when(ingredientOneMore.getPrice()).thenReturn(DINOSAUR_INGREDIENT_PRICE);
        Mockito.when(ingredientOneMore.getType()).thenReturn(DINOSAUR_INGREDIENT_TYPE);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientOneMore);

        String expectedReceipt = String.format("(==== %s ====)%n", BLACK_BUN_NAME) + String.format("= %s %s =%n", HOT_SAUCE_INGREDIENT_TYPE.toString().toLowerCase(), HOT_SAUCE_INGREDIENT_NAME) +
                String.format("= %s %s =%n", DINOSAUR_INGREDIENT_TYPE.toString().toLowerCase(), DINOSAUR_INGREDIENT_NAME) +
                String.format("(==== %s ====)%n", BLACK_BUN_NAME) +
                String.format("%nPrice: %f%n", EXPECTED_TOTAL_PRICE);

        assertThat(
                "The burger receipt should be in accordance with input data." ,
                burger.getReceipt(),
                is(expectedReceipt)
        );
    }
}
