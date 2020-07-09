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
    public BaseGene(String gene) throws Error{
        //2 character length is the ONLY valid gene input.
        if (gene.length() != 2){
            throw new Error("Gene alleles only contain 2 characters.");
        }
        /*
        Inputs that look like "AA", "Aa", and "aa"
        are valid, but we need to make sure if we
        get "aA" that we correct it.
        */
        String[] geneSplit = gene.split("");
        if (geneSplit[0].equals(geneSplit[0].toLowerCase()) && geneSplit[1].equals(geneSplit[1].toUpperCase())){
            this.gene = geneSplit[1] + geneSplit[0];
        }
        else{
            this.gene = gene;
        }
    }

    /**
     * Returns the Base Gene String.
     * @return String
     */
    @Override
    public String build() {
        return gene;
    }

    /**
     * Builds the base gene's offspring combinations.
     * Starting point for the gene combinations. These
     * are simple, just each character in the gene is
     * a combination at this point.
     * @return String[]
     */
    @Override
    public ArrayList<String> buildCombination(){
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
