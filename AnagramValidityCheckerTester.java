package CodeUAss1Package;

import static CodeUAss1Package.AnagramValidityChecker.isSentenceAnagram;

public class AnagramValidityCheckerTester {

    public static void main(String[] args)
    {
        String s1 = "si Apepl Apepl ppppo";
        String s2 = "ppeAl ppopp Apepl is";
        System.out.println(isSentenceAnagram(s1, s2,true));
        System.out.println(s1 + " " +  s2);
    }

}
