import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GeneTest {
    private Gene g;
    private BaseGene bg;

    @Before
    public void setUp() throws Exception {
        bg = new BaseGene("Aa");
        g = new Gene("Bb", bg);
    }

    @Test
    public void buildTestGreen() {
        assertEquals("AaBb", g.build());
    }

    @Test
    public void buildTestRed() {
        g = new Gene("bB", bg);
        assertEquals("AabB", g.build());
    }

    @Test
    public void buildCombinationTestTwoGenesGreen() {
        assertArrayEquals(new String[]{"AB", "aB", "Ab", "ab"}, g.buildCombination().toArray());
    }

    @Test
    public void buildCombinationTestTwoGenesRed() {
        g = new Gene("bB", bg);
        assertArrayEquals(new String[]{"Ab", "ab", "AB", "aB"}, g.buildCombination().toArray());
    }

    @Test
    public void buildCombinationTestThreeGenesGreen() {
        g = new Gene("Cc", g);
        assertArrayEquals(new String[]{
                "ABC", "aBC", "AbC", "abC",
                "ABc", "aBc", "Abc", "abc"
        }, g.buildCombination().toArray());
    }

    @Test
    public void buildCombinationTestThreeGenesRed() {
        g = new Gene("cC", g);
        assertArrayEquals(new String[]{
                "ABC", "ABc","aBC", "AbC",
                "abC","aBc", "Abc", "abc"
        }, g.buildCombination().toArray());
    }
}