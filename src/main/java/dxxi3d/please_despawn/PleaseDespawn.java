package dxxi3d.please_despawn;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PleaseDespawn implements ModInitializer {

    private static final String MOD_NAME = "Please, Despawn!";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        LOGGER.info("[{}] mod loaded!", MOD_NAME);
    }
}
