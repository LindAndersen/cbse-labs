package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Server provider interface for entity processing mechanism, first order of business when updating the game state.
 * Currently followed by post-processing.
 */
public interface IEntityProcessingService {

    /**
     * Method for processing game objects during game update loop.
     *
     *
     *
     * @param gameData access to display configuration and gamekeys
     * @param world access to add/remove entities from map
     */
    void process(GameData gameData, World world);
}
