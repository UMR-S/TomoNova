package umaru.tomonova.tomonova.utils.world.deleteworld;

import java.io.File;

public class DeleteWorldUtils {
    public static void deleteWorlds(String ... worlds) {
        //Supprime les mondes
        for (final String world : worlds) {
            deleteWorld(new File(world));
        }
    }
    public static boolean deleteWorld(final File path) {
        if (path.exists()) {
            final File[] files = path.listFiles();
            for (int i = 0; i < files.length; ++i) {
                if (files[i].isDirectory()) {
                    deleteWorld(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
        return path.delete();
    }
}
