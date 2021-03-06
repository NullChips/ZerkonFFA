package me.nullchips.ffa.handlers.kits;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import me.nullchips.ffa.FFA;

/**
 * Represents a kit.
 */
public abstract class Kit {

	private String name;
	private ArrayList<ItemStack> items;
	private ItemStack helmet;
	private ItemStack chestplate;
	private ItemStack leggings;
	private ItemStack boots;
	private int price;
	private int displayItem;
	private String displayName;

	public Kit(String name, int price) {
		this.name = name;
		this.items = new ArrayList<>();
		this.price = FFA.getPlugin().getConfig().getInt("Kits." + name + ".Price");
		this.displayItem = FFA.getPlugin().getConfig().getInt("Kits." + name + ".DisplayItem");
		this.displayName = FFA.getPlugin().getConfig().getString("Kits." + name + ".DisplayName");
	}



	public final String getName() {
		return name;
	}

	@SuppressWarnings("unchecked")
	public final ArrayList<ItemStack> getItems() {
		return (ArrayList<ItemStack>) items.clone();
	}

	@SafeVarargs
	public final void addItem(Material material, int amount, String name, String[] lore, SimpleEntry<Enchantment, Integer>... enchantments) {
		ItemStack item = new ItemStack(material, amount);
		for (SimpleEntry<Enchantment, Integer> entry : enchantments) {
			item.addEnchantment(entry.getKey(), entry.getValue());
		}

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);

		items.add(item);
	}

	public void giveKit(Player p, Kit k) {
		PlayerInventory pi = p.getInventory();
		pi.setArmorContents(null);
		pi.clear();
		
		for(ItemStack i : items) {
			pi.addItem(i);
		}
		pi.setBoots(getBoots());
		pi.setLeggings(getLeggings());
		pi.setChestplate(getChestplate());
		pi.setHelmet(getHelmet());
	}
	
	public final void setHelmet(ItemStack helmet) {
		this.helmet = helmet;
	}
	
	public final ItemStack getHelmet() {
		return this.helmet;
	}

	public final void setChestplate(ItemStack chestplate) {
		this.chestplate = chestplate;
	}
	
	public final ItemStack getChestplate() {
		return this.chestplate;
	}

	public final void setLeggings(ItemStack leggings) {
		this.leggings = leggings;
	}
	
	public final ItemStack getLeggings() {
		return this.leggings;
	}

	public final void setBoots(ItemStack boots) {
		this.boots = boots;
	}
	
	public final ItemStack getBoots() {
		return this.boots;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public final int getDisplayItem() {
		return displayItem;
	}

	public final String getDisplayName() {
		return displayName;
	}

}