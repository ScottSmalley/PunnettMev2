import java.util.ArrayList;

abstract class GeneWrapper implements GeneBuilder{
    protected String gene;
    protected GeneBuilder internalGene;
    public GeneWrapper(String gene, GeneBuilder internalGene){
        this.gene = gene;
        this.internalGene = internalGene;
    }
    abstract public String buildSingleParentGene();
    abstract public ArrayList<String> buildSingleParentGeneCombination();
}
