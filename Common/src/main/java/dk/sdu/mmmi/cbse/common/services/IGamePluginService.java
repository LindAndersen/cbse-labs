package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Server provider interface for game plugin services that can start and stop plugins in the game.
 *
 * Implementations of this interface must handle the initialization and cleanup
 * of game-specific functionality.
 */
public interface IGamePluginService {

    /**
     * Initializes an entity type and adds it to the world.
     * @param gameData
     * @param world
     */
    void start(GameData gameData, World world);

    /**
     * Removes an entity from the world map and subsequently from the polygon map used to draw.
     * @param gameData
     * @param world
     */
    void stop(GameData gameData, World world);
}
