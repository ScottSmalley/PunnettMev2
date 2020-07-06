/**
 * Represents the behaviors required by
 * a class that deals with building
 * Offspring results.
 * @author Scott Smalley
 */
import java.util.ArrayList;

public interface OffspringBuildBehavior {
    /**
     * An offspring builder should
     * build the results and return
     * them in an ArrayList<String>.
     *
     * @return ArrayList<String>
     */
    ArrayList<String> buildResults();
}
