/**
 * Represents the first gene in any single parent
 * Punnett Square field. For example, 'Aa' would be
 * a field. 'A' and 'a' are two traits for one parent.
 * The other reproducing partner could be 'AA,' which
 * generates different combinations than being 'Aa'
 * as well.
 * @author Scott Smalley
 */
import java.util.ArrayList;

public class BaseGene implements GeneBuilder {
    private String gene;
    public BaseGene(String gene){
        this.gene = gene;
    }

    /**
     * Being the base gene, it all starts at the base.
     * @return String
     */
    @Override
    public String buildSingleParentGene() {
        return gene;
    }

    /**
     * Generates the base gene's combinations.
     * These are simple, just each character in the
     * gene is a combination at this point.
     * @return String[]
     */
    @Override
    public ArrayList<String> buildSingleParentGeneCombination(){
        ArrayList<String> geneCombinations = new ArrayList<>();
        String[] geneSplit = gene.split("");
        try{
            geneCombinations.add(geneSplit[0]);
            geneCombinations.add(geneSplit[1]);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error parsing combinations... exiting.");
            System.exit(0);
        }
        return geneCombinations;
    }
}
