package cegepst.factories;

import cegepst.ennemies.Ganon;
import cegepst.ennemies.Wizard;
import cegepst.ennemies.Zelda;
import cegepst.game.Player;

import java.util.Random;

public class WaveFactory {

    public Zelda createZelda(Player player) {
        Zelda zelda = new Zelda(player);
        randomSpawn(zelda);
        return zelda;
    }

    public Ganon createGanon() {
        Ganon ganon = new Ganon();
        ganonSpawn(ganon);
        return ganon;
    }

    public Wizard createWizard(Ganon ganon) {
        Wizard wizard = new Wizard(ganon);
        return wizard;
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

    public void ganonSpawn(Ganon ganon) {
        Random random = new Random();
        int spawn = random.nextInt(7);
        switch(spawn) {
            case 0: ganon.teleport(975, 450); break;
            case 1: ganon.teleport(585, 720); break;
            case 2: ganon.teleport(1356, 720); break;
            case 3: ganon.teleport(971, 986); break;
            case 4: ganon.teleport(588, 1253); break;
            case 5: ganon.teleport(1356, 1253); break;
            case 6: ganon.teleport(968, 1513); break;
        }
    }
}
