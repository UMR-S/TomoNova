package umaru.tomonova.tomonova.gui.opGUI.communGUI.gamemodeGUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum ItemsInGamemode {


    ;


    private Material material;
    private String mainText;
    private ChatColor colorMainText;
    private List<String> subText;
    private Integer number; //1 ou -1 pour les on/off
    private Integer placeInInventory;
    private Boolean initiallyActivated;

    ItemsInGamemode(Material material, String mainText, ChatColor colorMainText, List<String> subText, Integer number, Integer placeInInventory, Boolean initiallyActivated) {
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
