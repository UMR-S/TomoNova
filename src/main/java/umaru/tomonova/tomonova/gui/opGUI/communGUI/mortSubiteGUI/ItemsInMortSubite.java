package umaru.tomonova.tomonova.gui.opGUI.communGUI.mortSubiteGUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum ItemsInMortSubite {

    MORT_SUBITE_ACTIVATED(Material.BONE, "Mort subite activé", ChatColor.GREEN, Arrays.asList(new String[]{""}) ,1,0, true),
    MORT_SUBITE_DESACTIVATED(Material.BONE, "Mort subite désactivé", ChatColor.RED, Arrays.asList(new String[]{""}) ,-1,0,false),
    TIME(Material.CLOCK, "Temps : ", ChatColor.LIGHT_PURPLE, Arrays.asList(new String[]{""}) ,1,4, true),
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

    ItemsInMortSubite(Material material, String mainText, ChatColor colorMainText, List<String> subText, Integer number, Integer placeInInventory, Boolean initiallyActivated) {
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
