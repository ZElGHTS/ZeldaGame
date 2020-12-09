package cegepst;

import java.util.Random;

public class WaveFactory {

    public Zelda createZelda(Player player) {
        Zelda zelda = new Zelda(player);
        randomSpawn(zelda);
        return zelda;
    }

    public void randomSpawn(Zelda zelda) {
        Random random = new Random();
        int spawn = random.nextInt(10);
        switch(spawn) {
            case 0: zelda.teleport(608, 1374); break;
            case 1: zelda.teleport(920, 1654); break;
            case 2: zelda.teleport(1140, 1652); break;
            case 3: zelda.teleport(1437, 1354); break;
            case 4: zelda.teleport(1024, 686); break;
            case 5: zelda.teleport(454, 1907); break;
            case 6: zelda.teleport(1634, 1907); break;
            case 7: zelda.teleport(664, 1568); break;
            case 8: zelda.teleport(1445, 1568); break;
            case 9: zelda.teleport(1439, 496); break;
        }
    }
}
