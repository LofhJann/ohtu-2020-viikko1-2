package ohtuesimerkki;

import java.util.List;

public interface Reader {
    List<Player> getPlayers();

    default int extractInt(String str) {
        return Integer.parseInt(str.trim());
    }
}
