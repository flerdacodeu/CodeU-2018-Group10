import org.junit.Test;

import java.util.List;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.Set;



import static org.junit.Assert.*;



public class AlphabetDiscovererTest {

    private AlphabetDiscoverer alphabetDiscoverer = new AlphabetDiscoverer();



    @Test

    public void testConsistentDictionary() {

        List<String> dictionary = Arrays.asList(

                "ART", "RAT", "CAT", "CAR"

        );

        List<List<Character>> possibleExpectedOutput = new ArrayList<>();

        possibleExpectedOutput.add(Arrays.asList('A', 'T', 'R', 'C'));

        possibleExpectedOutput.add(Arrays.asList('T', 'A', 'R', 'C'));



        assertTrue(possibleExpectedOutput.contains(alphabetDiscoverer.getAlphabet(dictionary)));

    }



    @Test

    public void testAllAlphabetsInConsistentDictionary() {

        List<String> dictionary = Arrays.asList(

                "ART", "RAT", "CAT", "CAR"

        );

        List<List<Character>> expectedResult = new ArrayList<>();

        expectedResult.add(Arrays.asList('A', 'T', 'R', 'C'));

        expectedResult.add(Arrays.asList('T', 'A', 'R', 'C'));

        List<List<Character>> actualResult = alphabetDiscoverer.getAllPossibleAlphabets(dictionary);

        assertEquals(expectedResult, actualResult);

    }



    @Test(expected = IllegalArgumentException.class)

    public void testInconsistentDictionary() {

        List<String> dictionary = Arrays.asList(

                "AB", "BC", "BA", "CC"

        );

        alphabetDiscoverer.getAlphabet(dictionary);

    }



    @Test(expected = IllegalArgumentException.class)

    public void testEmptyDictionary() {

        alphabetDiscoverer.getAlphabet(new ArrayList<>());

    }



    @Test

    public void testIllegalConstraints() {

        List<String> dictionary = Arrays.asList(

                "AB", "BC", "BA", "CC"

        );

        /*

        A -> B -> C - |

        <- - - - - - -

         */

        List<Constraint<Character>> expectedConstraints = Arrays.asList(

                new Constraint<>('B', 'C'),

                new Constraint<>('A', 'B'),

                new Constraint<>('C', 'A')

        );

        Set<Constraint<Character>> actualConstraints = alphabetDiscoverer.getIllegalConstraints(dictionary);

        assertTrue(actualConstraints.size() == 1);

        assertTrue(expectedConstraints.contains(actualConstraints.iterator().next()));

    }



    @Test

    public void testIllegalConstraintsWithTwoCircuits() {

        List<String> dictionary = Arrays.asList(

                "AB", "BC", "BA", "CC", "D", "E", "FF", "FD"

        );

        /*

        A -> B -> C -> | -> D -> E -> F - |

        <- - - - - - -       < - - - - - -

         */

        List<List<Constraint<Character>>> expectedConstraintsGroups = Arrays.asList(

                Arrays.asList(

                        new Constraint<>('B', 'C'),

                        new Constraint<>('A', 'B'),

                        new Constraint<>('C', 'A')

                ),

                Arrays.asList(

                        new Constraint<>('D', 'E'),

                        new Constraint<>('E', 'F'),

                        new Constraint<>('F', 'D')

                )

        );

        checkConstraintsFromTwoCircuits(expectedConstraintsGroups, dictionary, false);

    }

    @Test

    public void testIllegalConstraintsWithTwoCircuitsWithCommonEdge() {

        List<String> dictionary = Arrays.asList(

                "A", "B", "CC", "CA", "DD", "DB"

        );

        // circuits are A-B-C-A and B-C-D-B with common edge B-C

        List<List<Constraint<Character>>> expectedConstraintsGroups = Arrays.asList(

                Arrays.asList(

                        new Constraint<>('A', 'B'),

                        new Constraint<>('B', 'C'),

                        new Constraint<>('C', 'A')

                ),

                Arrays.asList(

                        new Constraint<>('B', 'C'),

                        new Constraint<>('C', 'D'),

                        new Constraint<>('D', 'B')

                )

        );

        checkConstraintsFromTwoCircuits(expectedConstraintsGroups, dictionary, true);

    }

    private void checkConstraintsFromTwoCircuits(List<List<Constraint<Character>>> expectedConstraintsGroups, List<String> dictionary, boolean commonEdgesPossible) {
        Set<Constraint<Character>> actualConstraints = alphabetDiscoverer.getIllegalConstraints(dictionary);

        assertEquals(2, actualConstraints.size());

        actualConstraints.forEach(e -> {

            if (expectedConstraintsGroups.get(0).contains(e)) {

                if (!commonEdgesPossible) {
                    
                    assertFalse(expectedConstraintsGroups.get(1).contains(e));
                    
                }
            } else {

                assertTrue(expectedConstraintsGroups.get(1).contains(e));

            }

        });
    }

}
