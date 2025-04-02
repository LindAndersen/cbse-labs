package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *Service provider interface for post-processing of entities. Currently used to make world updates after
 * initial processing is done.
 */
public interface IPostEntityProcessingService {

    /**
     * Used to update the world in post
     * @param gameData
     * @param world
     */
    void process(GameData gameData, World world);
}
