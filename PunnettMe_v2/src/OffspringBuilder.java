import java.util.ArrayList;

public interface OffspringBuilder {
//    OffspringDataTree buildOffspringResults(GeneBuilder parentOneGenes, GeneBuilder parentTwoGenes);
    ArrayList<String> buildOffspringResults(GeneBuilder parentOneGenes, GeneBuilder parentTwoGenes);
}
