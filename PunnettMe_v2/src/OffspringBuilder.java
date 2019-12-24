import java.util.ArrayList;

public interface OffspringBuilder {
    ArrayList<String> buildOffspringResults(GeneBuilder parentOneGenes, GeneBuilder parentTwoGenes);
}
