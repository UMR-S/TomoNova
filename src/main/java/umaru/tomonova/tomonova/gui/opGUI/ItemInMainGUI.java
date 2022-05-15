package umaru.tomonova.tomonova.gui.opGUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum ItemInMainGUI {
    //Ligne 1 (Configs basiques)
    BORDURE(Material.WHITE_STAINED_GLASS_PANE, "Bordure", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{"La BORDURE Umaru"}) ,1,0, true),
    PVP(Material.DIAMOND_SWORD, "PvP", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{"Configuration du PVP"}) ,1,1,true),
    NBPLAYERSINTEAM(Material.BLAZE_ROD, "Joueurs par team", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{"A augmenter pour faire une pyramide de traps"}) ,1,2, true),
    NETHER(Material.OBSIDIAN, "Nether", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{"Le NETHER Umaru"}) ,1,3, true),
    MORTSUBITE(Material.BONE, "Mort subite", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{"su **bite** =w="}) ,1,4,true),
    REGLES(Material.WRITABLE_BOOK, "Petites règles", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{"Les préférées de Natsume"}) ,1,5,true),
    GAMEMODE(Material.REDSTONE, "Gamemode", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{"Pour activer le switch, taupe gun, tomo adventure et tout ce qu'on ajoutera"}) ,1,6, true),

    //Ligne 2 (Configs du monde)
//    ITEM_MAIN_GUI(Material., "", ChatColor., Arrays.asList(new String[]{""}) ,1,9,true),
//    ITEM_MAIN_GUI(Material., "", ChatColor., Arrays.asList(new String[]{""}) ,1,10,true),
//    ITEM_MAIN_GUI(Material., "", ChatColor., Arrays.asList(new String[]{""}) ,1,11,true),
//    ITEM_MAIN_GUI(Material., "", ChatColor., Arrays.asList(new String[]{""}) ,1,12,true),
//    ITEM_MAIN_GUI(Material., "", ChatColor., Arrays.asList(new String[]{""}) ,1,13,true),
//    ITEM_MAIN_GUI(Material., "", ChatColor., Arrays.asList(new String[]{""}) ,1,14,true),

    //Ligne 3 (Configs spécifique au jeu)

    //Ligne 4 (Sauvegarder/charger des configs)
//    ITEM_MAIN_GUI(Material., "", ChatColor., Arrays.asList(new String[]{""}) ,1),
//    ITEM_MAIN_GUI(Material., "", ChatColor., Arrays.asList(new String[]{""}) ,1),

    //Ligne 5 (Lancer le jeu/Fermer la config)
    START(Material.BEACON, "Start", ChatColor.GREEN, Arrays.asList(new String[]{"Kud en sueur devant l'anglais"}) ,1,36, true),
    CLOSE(Material.BARRIER, "Fermer", ChatColor.RED, Arrays.asList(new String[]{"Toi ferme ta gueule, toi ferme ta gueule, PNL Oh Lala"}) ,1,44, true);

    private Material material;
    private String mainText;
    private ChatColor colorMainText;
    private List<String> subText;
    private Integer number; //1 ou -1 pour les on/off
    private Integer placeInInventory;
    private Boolean initiallyActivated;

    ItemInMainGUI(Material material, String mainText, ChatColor colorMainText, List<String> subText, Integer number, Integer placeInInventory, Boolean initiallyActivated) {
        this.material = material;
        this.mainText = mainText;
        this.colorMainText = colorMainText;
        this.subText = subText;
        this.number = number;
        this.placeInInventory = placeInInventory;
        this.initiallyActivated = initiallyActivated;
    }

    public Material getMaterial() {
        return material;
    }

    public String getMainText() {
        return mainText;
    }

    public ChatColor getColorMainText() {
        return colorMainText;
    }

    public List<String> getSubText() {
        return subText;
    }

    public Integer getNumber() {
        return number;
    }
    public Integer getPlaceInInventory(){
        return placeInInventory;
    }

    public Boolean getInitiallyActivated() {
        return initiallyActivated;
    }
}
