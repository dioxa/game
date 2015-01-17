package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.base.World;

public class WorldTest {

    private World world;

    @Before
    public void setUp() {
        world = new World();
        world.setCellOwner(1, 1, 1);
    }

    @Test
    public void testSetCellOwnerByTurnIfPlayerFarAway() {
        Assert.assertFalse(world.setCellOwnerByTurn(5, 5, 1));
        Assert.assertFalse(world.setCellOwnerByTurn(3, 1, 1));

    }

    @Test
    public void testSetCellOwnerByTurnIfPlayerOnNextCell() {

        Assert.assertTrue(world.setCellOwnerByTurn(2, 1, 1));
        Assert.assertTrue(world.setCellOwnerByTurn(1, 2, 1));

    }

    @Test
    public void testSetCellOwnerByTurnIfTurnOutOfBounds() {

        Assert.assertFalse(world.setCellOwnerByTurn(-10, -10, 1));
        Assert.assertFalse(world.setCellOwnerByTurn(-1, -1, 1));

    }

    @Test
    public void testSetCellOwnerByTurnIfTurnOnAlreadyOwnedCell() {

        Assert.assertFalse(world.setCellOwnerByTurn(1, 1, 1));
        world.setCellOwner(2, 1, 1);
        Assert.assertFalse(world.setCellOwnerByTurn(2, 1, 1));

    }

    @Test
    public void testCheckMovePopulationAvailability() {
        world.setCellOwner(2, 1, 1);
        world.update();
    }

}
