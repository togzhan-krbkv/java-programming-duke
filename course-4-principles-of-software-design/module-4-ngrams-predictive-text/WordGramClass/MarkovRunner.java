/**
 * MarkovRunner, runs and times word level Markov model implementations
 * (the direct search based MarkovWord and the HashMap based
 * EfficientMarkovWord) against a training text, treating them
 * interchangeably through the IMarkovModel interface.
 *
 * @author Togzhan K.
 */
import edu.duke.*;

public class MarkovRunner
{
    public void runModel(IMarkovModel markov, String text, int size)
    {
        markov.setTraining(text);
        System.out.println("running with " + markov);

        long startTime = System.nanoTime();
        for (int k = 0; k < 3; k++)
        {
            String st = markov.getRandomText(size);
            printOut(st);
        }
        long endTime = System.nanoTime();
        System.out.println("Finished in " + (endTime - startTime) + " nanoseconds");
    }

    public void runModel(IMarkovModel markov, String text, int size, int seed)
    {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);

        long startTime = System.nanoTime();
        for (int k = 0; k < 3; k++)
        {
            String st = markov.getRandomText(size);
            printOut(st);
        }
        long endTime = System.nanoTime();
        System.out.println("Finished in " + (endTime - startTime) + " nanoseconds");
    }

    public void runMarkov()
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');

        MarkovWord mw = new MarkovWord(3);
        runModel(mw, st, 200, 643);
    }

    public void testHashMap()
    {
        String st = "this is a test yes this is really a test";
        EfficientMarkovWord emw = new EfficientMarkovWord(2);
        emw.setRandom(42);
        emw.setTraining(st);
        printOut(emw.getRandomText(50));

        st = "this is a test yes this is really a test yes a test this is wow";
        emw.setRandom(42);
        emw.setTraining(st);
        printOut(emw.getRandomText(50));
    }

    public void compareMethods()
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 100;
        int seed = 42;

        MarkovWord mw = new MarkovWord(2);
        runModel(mw, st, size, seed);

        EfficientMarkovWord emw = new EfficientMarkovWord(2);
        runModel(emw, st, size, seed);
    }

    private void printOut(String s)
    {
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for (int k = 0; k < words.length; k++)
        {
            System.out.print(words[k] + " ");
            psize += words[k].length() + 1;
            if (psize > 60)
            {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

    public static void main(String[] args)
    {
        MarkovRunner runner = new MarkovRunner();
        runner.testHashMap();
    }
}
