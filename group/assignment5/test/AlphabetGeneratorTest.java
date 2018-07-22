
import org.junit.Test;

import java.util.ArrayList;

public class AlphabetGeneratorTest {

    @Test
    public void generateForExampleDictionary() {
        ArrayList dictionary = new ArrayList();

        dictionary.add("ART");
        dictionary.add("RAT");
        dictionary.add("CAT");
        dictionary.add("CAR");

        AlphabetGenerator alphabetGenerator = new AlphabetGenerator(dictionary);
        System.out.println(alphabetGenerator.getAlphabetOrder().toString());
    }


    @Test
    public void generateForRealDictionary() {
        ArrayList dictionary = new ArrayList();

        dictionary.add("ABC");
        dictionary.add("ABDE");
        dictionary.add("ABDF");
        dictionary.add("AC");
        dictionary.add("ACF");
        dictionary.add("BCF");

        AlphabetGenerator alphabetGenerator = new AlphabetGenerator(dictionary);
        System.out.println(alphabetGenerator.getAlphabetOrder().toString());
    }

}