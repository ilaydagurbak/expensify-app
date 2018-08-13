package numbergame;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FabulousSecretFinderTest {
    @Test
    public void testFindSecretNumber1() {
        SecretKeeper keeper = new SecretKeeper(4);
        keeper.setSecretNumber(9083);
        SecretFinder finder = new FabulousSecretFinder(keeper);

        assertTrue(9083 == finder.findSecretNumber());
    }

    @Test
    public void testFindSecretNumber2() {
        SecretKeeper keeper = new SecretKeeper(5);
        keeper.setSecretNumber(42061);
        SecretFinder finder = new FabulousSecretFinder(keeper);

        assertTrue(42061 == finder.findSecretNumber());
    }

    @Test(expected = Exception.class)
    public void testFindSecretNumber3() {
        SecretKeeper keeper = new BadSecretKeeper(5);
        keeper.setSecretNumber(42061);
        SecretFinder finder = new FabulousSecretFinder(keeper);

        finder.findSecretNumber();
    }
}

class BadSecretKeeper extends SecretKeeper {
    public BadSecretKeeper(int numDigits) {
        super(numDigits);
    }

    @Override
    public Similarity getSimilarity(int number) {
        return new Similarity(0, getNumDigits() - 1);
    }
}
