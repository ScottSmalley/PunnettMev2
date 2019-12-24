import java.util.ArrayList;

public class BaseGene implements GeneBuilder {
    private String gene;
    public BaseGene(String gene){
        this.gene = gene;
    }

    @Override
    public String buildSingleParentGene() {
        return gene;
    }

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
