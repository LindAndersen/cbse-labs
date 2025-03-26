package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Interface for game plugin services that can start and stop plugins in the game.
 *
 * Implementations of this interface must handle the initialization and cleanup
 * of game-specific functionality.
 */
public interface IEntityProcessingService {

    /**
     * Method for processing game objects during game update loop.
     *
     * 
     *
     * @param gameData access to display configuration and gamekeys
     * @param world access to add/remove entities from map
     * @throws
     */
    void process(GameData gameData, World world);
}
