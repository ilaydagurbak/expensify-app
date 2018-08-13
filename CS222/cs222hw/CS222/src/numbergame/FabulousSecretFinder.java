package numbergame;

import java.util.*;

public class FabulousSecretFinder extends SecretFinder {
    private int guess;
    private int maxPossible;
    private List<Integer> possibleNumbersArray = new ArrayList<Integer>();
    private int currentIndex;

    public FabulousSecretFinder(SecretKeeper secretKeeper) {
        super(secretKeeper);
        guess = 1;
        for (int i = 1; i < secretKeeper.getNumDigits(); i++) {
            guess *= 10;
        }
        maxPossible = guess * 10;
        populateArray();
    }

    protected void populateArray(){
        int j = 0;
        for(int k = (maxPossible/10); k < maxPossible; k++){
            if(secretKeeper.isValidNumber(k)){
                possibleNumbersArray.add(k);
            }
        }
    }

    @Override
    protected int makeAGuess() {
        guess = possibleNumbersArray.get(0);
        while (!secretKeeper.isValidNumber(guess)) {
            for(int i = 0; i < possibleNumbersArray.size(); i++){
                currentIndex = i;
                Similarity similar = secretKeeper.getSimilarity(possibleNumbersArray.get(i));
                process(similar);
            }

            guess = possibleNumbersArray.get(0);
            if (guess > maxPossible)
                throw new RuntimeException("That's not possible, SecretKeeper is a LIAR!!!");
        }
        return guess;
    }

    @Override
    protected void process(Similarity similarity) {
        // I'm a simple finder. What can I do sometimes?
        if(!(similarity.getPositive() == 4 && similarity.getNegative() == 0)){
            possibleNumbersArray.remove(currentIndex);
        }
    }
}
