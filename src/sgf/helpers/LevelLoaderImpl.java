package sgf.helpers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import sgf.model.level.Level;
import sgf.model.level.LevelImpl;
import sgf.model.level.Wave;
import sgf.model.map.Map;

/**
 * Implementation of interface LevelLoader.
 */
public class LevelLoaderImpl implements LevelLoader {
    private final int levelsNumber;

    /**
     * Constructor that calculates how many levels are available.
     */
    public LevelLoaderImpl() {
        this.levelsNumber = this.countMapFiles();
    }

    @Override
    public Level loadLevel(final int levelID) {
        // Creates the chosen level by loading the corresponding map and waves.
        final Map map = new MapLoaderImpl(levelID).getMap();
        final List<Wave> waves = new WavesLoaderImpl(map, levelID).getWaves();
        return new LevelImpl(waves, map, levelID);
    }

    @Override
    public int getLevelsNumber() {
        return this.levelsNumber;
    }

    // This method counts how many files are there into the folder res/levels.
    private int countMapFiles() {
//        return (int) Stream.of(new File("res" + File.separator + "levels").listFiles())
//                .filter(file -> !file.isDirectory())
//                .count();
        final URL folderUrl = ClassLoader.getSystemResource("levels"); // Restituisce un URL invalido (il file/directory corrispondente non esiste a quanto pare.
        final File f = new File(folderUrl.getPath());
        System.out.println(f.isFile());         // All tests
        System.out.println(f.isDirectory());
        System.out.println(f.isAbsolute());
        System.out.println(f.exists());
        return (int) Stream.of(new File(folderUrl.getPath()).listFiles())
                           .filter(file -> !file.isDirectory())
                           .count();
    }
}
