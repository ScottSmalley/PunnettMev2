import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BaseGeneTest {
    private BaseGene bg;

    @Before
    public void setUp(){
        bg = new BaseGene("Aa");
    }

    @Test
    public void buildTestGreen() {
        assertEquals("Aa", bg.build());
    }

    @Test
    public void buildTestRed() {
        assertEquals("aA", bg.build());
    }

    @Test
    public void buildCombinationTestGreen() {
        assertArrayEquals(new String []{"A", "a"}, bg.buildCombination().toArray());
    }

    @Test
    public void buildCombinationTestFixesOrderGreen() {
        bg = new BaseGene("aA");
        assertArrayEquals(new String []{"A", "a"}, bg.buildCombination().toArray());
    }

    @Test
    public void buildCombinationTestRed() {
        assertArrayEquals(new String []{"a", "A"}, bg.buildCombination().toArray());
    }

}