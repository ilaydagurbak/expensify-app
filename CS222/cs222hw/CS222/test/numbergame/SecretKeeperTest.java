package numbergame;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SecretKeeperTest {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor1() {
        SecretKeeper secretKeeper = new SecretKeeper(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2() {
        SecretKeeper secretKeeper = new SecretKeeper(0);
    }

    @Test
    public void testRandomSecretNumber1() {
        SecretKeeper secretKeeper = new SecretKeeper(4);
        secretKeeper.setSecretNumber();
        int number = secretKeeper.getSecretNumber();

        assertTrue(number >= 1000);
        assertTrue(number <= 9999);
    }

    @Test
    public void testRandomSecretNumber2() {
        SecretKeeper secretKeeper = new SecretKeeper(4);
        secretKeeper.setSecretNumber();
        int number = secretKeeper.getSecretNumber();

        assertTrue(secretKeeper.isValidNumber(number));
    }

    @Test
    public void testIsValidNumber1() {
        SecretKeeper secretKeeper = new SecretKeeper(4);

        assertTrue(secretKeeper.isValidNumber(1234));
    }

    @Test
    public void testIsValidNumber2() {
        SecretKeeper secretKeeper = new SecretKeeper(4);

        assertTrue(secretKeeper.isValidNumber(8403));
    }

    @Test
    public void testIsValidNumber3() {
        SecretKeeper secretKeeper = new SecretKeeper(4);

        assertFalse(secretKeeper.isValidNumber(0234));
    }

    @Test
    public void testIsValidNumber4() {
        SecretKeeper secretKeeper = new SecretKeeper(4);

        assertFalse(secretKeeper.isValidNumber(12345));
    }

    @Test
    public void testIsValidNumber5() {
        SecretKeeper secretKeeper = new SecretKeeper(4);

        assertFalse(secretKeeper.isValidNumber(4894));
    }

    @Test
    public void testIsValidNumber6() {
        SecretKeeper secretKeeper = new SecretKeeper(4);

        assertFalse(secretKeeper.isValidNumber(4090));
    }

    @Test
    public void testIsValidNumber7() {
        SecretKeeper secretKeeper = new SecretKeeper(4);

        assertFalse(secretKeeper.isValidNumber(0));
    }

    @Test
    public void testIsValidNumber8() {
        SecretKeeper secretKeeper = new SecretKeeper(4);

        assertFalse(secretKeeper.isValidNumber(-1234));
    }

    @Test
    public void testSimilarity1() {
        SecretKeeper secretKeeper = new SecretKeeper(4);
        secretKeeper.setSecretNumber(7803);
        Similarity similarity = secretKeeper.getSimilarity(7803);

        assertEquals(new Similarity(4, 0), similarity);
    }

    @Test
    public void testSimilarity2() {
        SecretKeeper secretKeeper = new SecretKeeper(4);
        secretKeeper.setSecretNumber(7803);
        Similarity similarity = secretKeeper.getSimilarity(7083);

        assertEquals(new Similarity(2, 2), similarity);
    }

    @Test
    public void testSimilarity3() {
        SecretKeeper secretKeeper = new SecretKeeper(4);
        secretKeeper.setSecretNumber(7803);
        Similarity similarity = secretKeeper.getSimilarity(3780);

        assertEquals(new Similarity(0, 4), similarity);
    }

    @Test
    public void testSimilarity4() {
        SecretKeeper secretKeeper = new SecretKeeper(4);
        secretKeeper.setSecretNumber(7803);
        Similarity similarity = secretKeeper.getSimilarity(1294);

        assertEquals(new Similarity(0, 0), similarity);
    }

    @Test
    public void testSimilarity5() {
        SecretKeeper secretKeeper = new SecretKeeper(4);
        secretKeeper.setSecretNumber(7803);
        Similarity similarity = secretKeeper.getSimilarity(5823);

        assertEquals(new Similarity(2, 0), similarity);
    }

    @Test
    public void testSimilarity6() {
        SecretKeeper secretKeeper = new SecretKeeper(4);
        secretKeeper.setSecretNumber(7803);
        Similarity similarity = secretKeeper.getSimilarity(5238);

        assertEquals(new Similarity(0, 2), similarity);
    }
}
