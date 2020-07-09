import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OffspringBuilderTest {
    private OffspringBuilder ob;
    private GeneBuilder parentOne;
    private GeneBuilder parentTwo;

    @Before
    public void setUp(){
        parentOne = new BaseGene("Aa");
        parentTwo = new BaseGene("Aa");
        ob = new OffspringBuilder(parentOne, parentTwo);
    }

    @Test
    public void computeTestOneGeneGreen() {
        assertArrayEquals(new String[]{"AA", "Aa", "Aa", "aa"}, ob.compute().toArray());
    }

    @Test
    public void computeTestOneGeneRed() {
        assertArrayEquals(new String[]{"AA", "AA", "AA", "AA"}, ob.compute().toArray());
    }

    @Test
    public void computeTestTwoGeneGreen() {
        parentOne = new Gene("Bb", parentOne);
        parentTwo = new Gene("Bb", parentTwo);
        ob = new OffspringBuilder(parentOne, parentTwo);
        assertArrayEquals(new String[]{
                "AABB", "AaBB", "AABb", "AaBb",
                "AaBB", "aaBB", "AaBb", "aaBb",
                "AABb", "AaBb", "AAbb", "Aabb",
                "AaBb", "aaBb", "Aabb", "aabb"
        }, ob.compute().toArray());
    }

    @Test
    public void computeTestThreeGeneGreen() {
        parentOne = new Gene("Bb", parentOne);
        parentTwo = new Gene("Bb", parentTwo);
        parentOne = new Gene("Cc", parentOne);
        parentTwo = new Gene("Cc", parentTwo);
        ob = new OffspringBuilder(parentOne, parentTwo);
        assertArrayEquals(new String[]{
                "AABBCC", "AaBBCC", "AABbCC", "AaBbCC", "AABBCc", "AaBBCc", "AABbCc", "AaBbCc",
                "AaBBCC", "aaBBCC", "AaBbCC", "aaBbCC", "AaBBCc", "aaBBCc", "AaBbCc", "aaBbCc",
                "AABbCC", "AaBbCC", "AAbbCC", "AabbCC", "AABbCc", "AaBbCc", "AAbbCc", "AabbCc",
                "AaBbCC", "aaBbCC", "AabbCC", "aabbCC", "AaBbCc", "aaBbCc", "AabbCc", "aabbCc",
                "AABBCc", "AaBBCc", "AABbCc", "AaBbCc", "AABBcc", "AaBBcc", "AABbcc", "AaBbcc",
                "AaBBCc", "aaBBCc", "AaBbCc", "aaBbCc", "AaBBcc", "aaBBcc", "AaBbcc", "aaBbcc",
                "AABbCc", "AaBbCc", "AAbbCc", "AabbCc", "AABbcc", "AaBbcc", "AAbbcc", "Aabbcc",
                "AaBbCc", "aaBbCc", "AabbCc", "aabbCc", "AaBbcc", "aaBbcc", "Aabbcc", "aabbcc"
        }, ob.compute().toArray());
    }
}