package umaru.tomonova.tomonova.utils.gui;

import org.bukkit.enchantments.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

import org.bukkit.*;

public class ItemsCreator {
    private Material m;
    private String name;
    private List<String> lores;
    private Map<Enchantment, Integer> enchants;
    private int amount;


    public ItemsCreator(final Material m, final String name, final List<String> lores) {
        this.m = m;
        this.name = name;
        this.lores = lores;
        this.amount = 1;
    }
    public static ItemStack create(ItemsCreator ic){
        ItemStack itemStack = new ItemStack(ic.getMaterial(), ic.getAmount());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ic.getName());
        itemMeta.setLore(ic.getLores());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public Material getMaterial() {
        return this.m;
    }

    public void setMaterial(final Material m) {
        this.m = m;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<String> getLores() {
        return this.lores;
    }

    public void setLores(final List<String> lores) {
        this.lores = lores;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

}

