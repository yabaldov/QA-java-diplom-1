package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BunTest {

    private Bun bun;
    private static final String BUN_NAME = "white bun";
    private static final float BUN_PRICE = 400.5F;

    @Before
    public void setup() {
        bun = new Bun(BUN_NAME, BUN_PRICE);
    }

    @Test
    public void shouldGetBunName() {
        assertThat(String.format("Name of the bun should be '%s'.", BUN_NAME), bun.getName(), is(BUN_NAME));
    }

    @Test
    public void shouldGetBunPrice() {
        assertThat(String.format("Price of the bun should be %f", BUN_PRICE), bun.getPrice(), is(BUN_PRICE));
    }
}
