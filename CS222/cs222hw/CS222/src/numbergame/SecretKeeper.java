package numbergame;


import java.util.*;

/**
 * @author Baris Aktemur
 */

public class SecretKeeper {
    private int secretNumber;
    private int numDigits;
    private int rightNumberRightPlace = 0;
    private int rightNumberWrongPlace = 0;

    public SecretKeeper(int numDigits) {
        if (numDigits <= 0)
            throw new IllegalArgumentException("Num digits should be positive.");
        this.numDigits = numDigits;
    }

    public int getNumDigits() {
        return numDigits;
    }

    // Accessor method for debugging and testing purposes. Do NOT change this.
    public int getSecretNumber() {

        return secretNumber;
    }

    public boolean hasDuplicateDigits(int secretNumber) {
        String numberAsString = String.valueOf(secretNumber);
        for (int i = 0; i < numDigits; i++) {
            for (int j = i + 1; j < numDigits; j++) {
                if (numberAsString.charAt(i) == numberAsString.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }


    // Sets a random secret number that has `numDigits` digits.
    public void setSecretNumber() {
        do {
            int decimals = 1;
            for (int i = 0; i < this.numDigits; i++) {
                decimals *= 10;
            }
            randomNumGenerator(decimals);
        }
        while (!isValidNumber(secretNumber));
    }

    private void randomNumGenerator(int decimals) {
        Random random = new Random();
        Double randomNumber = random.nextDouble();
        secretNumber = (int) Math.floor(randomNumber * decimals);
    }

    // Sets the secret number manually
    public void setSecretNumber(int secretNumber) {
        if (!isValidNumber(secretNumber))
            throw new IllegalArgumentException("Secret number is invalid.");
        this.secretNumber = secretNumber;
    }

    // A number is valid if it has `numDigits` digits,
    // and all its digits are unique.
    public boolean isValidNumber(int number) {
        int lengthOfSecretNum = String.valueOf(number).length();
        return (lengthOfSecretNum == numDigits && !hasDuplicateDigits(number));

    }

    // Returns the similarity of `number` to `secretNumber`.
    public Similarity getSimilarity(int number) {
        String numberAsString = String.valueOf(number);
        String secretNumberAsString = String.valueOf(secretNumber);
        rightNumberWrongPlace = 0;
        rightNumberRightPlace = 0;
        if (isValidNumber(number)) {

            for (int i = 0; i < numDigits; i++) {
                for (int j = 0; j < numDigits; j++) {
                    if (numberAsString.charAt(j) == secretNumberAsString.charAt(i)) {
                        rightNumberWrongPlace++;
                        break;
                    }
                }
            }
            for (int k = 0; k < numDigits; k++) {
                if (numberAsString.charAt(k) == secretNumberAsString.charAt(k)) {
                    rightNumberRightPlace++;
                }
            }

            rightNumberWrongPlace = rightNumberWrongPlace - rightNumberRightPlace;
        }
        return new Similarity(rightNumberRightPlace,rightNumberWrongPlace);
    }
}

