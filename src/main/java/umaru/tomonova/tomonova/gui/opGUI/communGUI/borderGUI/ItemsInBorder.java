package umaru.tomonova.tomonova.gui.opGUI.communGUI.borderGUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum ItemsInBorder {

    INITIAL_LENGtH(Material.LIME_STAINED_GLASS_PANE, "Taille initiale", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{""}) ,1,1, true),
    FINAL_LENGTH(Material.RED_STAINED_GLASS_PANE, "Taille finale", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{""}) ,1,2,true),
    TIME(Material.CLOCK, "Temps bordure", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{""}) ,1,4,true),
    VITESSE(Material.NETHER_STAR, "Vitesse bordure", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{""}) ,1,5,true),
    NBSUBBORDER(Material.RED_STAINED_GLASS, "Nombre de sous bordure", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{""}) ,1,7,true),
    SB1_LENGTH(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "Taille finale sous bordure 1", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{""}) ,1,11, true),
    SB1_TIME(Material.CLOCK, "Temps sous bordure 1", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{""}) ,1,13,true),
    SB2_LENGTH(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "Taille finale sous bordure 2", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{""}) ,1,20, true),
    SB2_TIME(Material.CLOCK, "Temps sous bordure 2", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{""}) ,1,22,true),
    SB3_LENGTH(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "Taille finale sous bordure 3", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{""}) ,1,29, false),
    SB3_TIME(Material.CLOCK, "Temps sous bordure 3", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{""}) ,1,31,false),
    SB4_LENGTH(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "Taille finale sous bordure 4", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{""}) ,1,38, false),
    SB4_TIME(Material.CLOCK, "Temps sous bordure 4", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{""}) ,1,40,false),
    SB5_LENGTH(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "Taille finale sous bordure 5", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{""}) ,1,47, false),
    SB5_TIME(Material.CLOCK, "Temps sous bordure 5", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{""}) ,1,49,false),
    //In time
    ADD1(Material.GREEN_BANNER, "+1", ChatColor.GREEN, Arrays.asList(new String[]{""}) ,1,5, false),
    ADD10(Material.GREEN_BANNER, "+10", ChatColor.GREEN, Arrays.asList(new String[]{""}) ,1,6, false),
    ADD100(Material.GREEN_BANNER, "+100", ChatColor.GREEN, Arrays.asList(new String[]{""}) ,1,7,false),
    MINUS1(Material.RED_BANNER, "-1", ChatColor.RED, Arrays.asList(new String[]{""}) ,1,3,false),
    MINUS10(Material.RED_BANNER, "-10", ChatColor.RED, Arrays.asList(new String[]{""}) ,1,2, false),
    MINUS100(Material.RED_BANNER, "-100", ChatColor.RED, Arrays.asList(new String[]{""}) ,1,1, false);


    private Material material;
    private String mainText;
    private ChatColor colorMainText;
    private List<String> subText;
    private Integer number; //1 ou -1 pour les on/off
    private Integer placeInInventory;
    private Boolean initiallyActivated;

    ItemsInBorder(Material material, String mainText, ChatColor colorMainText, List<String> subText, Integer number, Integer placeInInventory, Boolean initiallyActivated) {
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
