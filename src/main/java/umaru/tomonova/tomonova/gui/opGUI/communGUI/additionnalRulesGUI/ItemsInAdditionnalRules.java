package umaru.tomonova.tomonova.gui.opGUI.communGUI.additionnalRulesGUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum ItemsInAdditionnalRules {
    PERMANENT_DAY_ACTIVATED(Material.CLOCK, "Jour permanent", ChatColor.GREEN, Arrays.asList(new String[]{"Activé"}) ,1,0, true),
    PERMANENT_DAY_DESACTIVATED(Material.CLOCK, "Jour permanent", ChatColor.RED, Arrays.asList(new String[]{"Désactivé"}) ,-1,0,false),
    RODLESS_ACTIVATED(Material.FISHING_ROD, "Rodless", ChatColor.GREEN, Arrays.asList(new String[]{"Activé"}) ,1,1, true),
    RODLESS_DESACTIVATED(Material.FISHING_ROD, "Rodless", ChatColor.RED, Arrays.asList(new String[]{"Désactivé"}) ,-1,1,false),
    HORSELESS_ACTIVATED(Material.SADDLE, "Horseless", ChatColor.GREEN, Arrays.asList(new String[]{"Activé"}) ,1,2, true),
    HORSELESS_DESACTIVATED(Material.SADDLE, "Horseless", ChatColor.RED, Arrays.asList(new String[]{"Désactivé"}) ,-1,2,false),
    COLLISIONS_ACTIVATED(Material.COBBLED_DEEPSLATE_WALL, "Collisions team", ChatColor.GREEN, Arrays.asList(new String[]{"Activé"}) ,1,3, false),
    COLLISIONS_DESACTIVATED(Material.COBBLED_DEEPSLATE_WALL, "Collisions team", ChatColor.RED, Arrays.asList(new String[]{"Désactivé"}) ,-1,3,true),
    FRIENDLY_FIRE_ACTIVATED(Material.IRON_SWORD, "Friendly fire", ChatColor.GREEN, Arrays.asList(new String[]{"Activé"}) ,1,4, false),
    FRIENDLY_FIRE_DESACTIVATED(Material.IRON_SWORD, "Friendly fire", ChatColor.RED, Arrays.asList(new String[]{"Désactivé"}) ,-1,4,true),
    AUTOSMELL_ACTIVATED(Material.FURNACE, "Autosmell", ChatColor.GREEN, Arrays.asList(new String[]{"Activé"}) ,1,5, false),
    AUTOSMELL_DESACTIVATED(Material.FURNACE, "Autosmell", ChatColor.RED, Arrays.asList(new String[]{"Désactivé"}) ,-1,5,true),
    WOOD_CUTTER_ACTIVATED(Material.OAK_LOG, "Bûcheron", ChatColor.GREEN, Arrays.asList(new String[]{"Activé"}) ,1,6, false),
    WOOD_CUTTER_DESACTIVATED(Material.OAK_LOG, "Bûcheron", ChatColor.RED, Arrays.asList(new String[]{"Désactivé"}) ,-1,6,true),
    NOFALL_ACTIVATED(Material.CHAINMAIL_BOOTS, "No fall", ChatColor.GREEN, Arrays.asList(new String[]{"Activé"}) ,1,7, false),
    NOFALL_DESACTIVATED(Material.CHAINMAIL_BOOTS, "No fall", ChatColor.RED, Arrays.asList(new String[]{"Désactivé"}) ,-1,7,true),
    COMPASS_ACTIVATED(Material.COMPASS, "Compass", ChatColor.GREEN, Arrays.asList(new String[]{"Activé"}) ,1,8, false),
    COMPASS_DESACTIVATED(Material.COMPASS, "Compass", ChatColor.RED, Arrays.asList(new String[]{"Désactivé"}) ,-1,8,true),
    REGEN_ACTIVATED(Material.COOKED_CHICKEN, "Régénration de la vie", ChatColor.GREEN, Arrays.asList(new String[]{"Activé"}) ,1,9, false),
    REGEN_DESACTIVATED(Material.COOKED_CHICKEN, "Régénération de la vie", ChatColor.RED, Arrays.asList(new String[]{""}) ,-1,9,true);
    private Material material;
    private String mainText;
    private ChatColor colorMainText;
    private List<String> subText;
    private Integer number; //1 ou -1 pour les on/off
    private Integer placeInInventory;
    private Boolean initiallyActivated;

    ItemsInAdditionnalRules(Material material, String mainText, ChatColor colorMainText, List<String> subText, Integer number, Integer placeInInventory, Boolean initiallyActivated) {
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
