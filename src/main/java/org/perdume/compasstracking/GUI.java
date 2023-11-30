package org.perdume.compasstracking;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GUI implements Listener{
    private CompassTracking main = CompassTracking.getPlugin(CompassTracking.class);
    private final Inventory inv;

    private Player player;

    private List<Player> pll = new ArrayList<>();

    public GUI(Player p) {
        // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
        inv = Bukkit.createInventory(null, 45, "나침반 추적");

        player = p;

        // Put the items into the inventory
        initializeItems();
    }

    // You can call this whenever you want to put the items in
    public void initializeItems() {
        int i = 0;
        for (Player pl : player.getLocation().getWorld().getPlayers()) {
            if(pl != player) {
                ItemStack item = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta sm = (SkullMeta) item.getItemMeta();
                sm.setOwner(pl.getName());
                sm.setDisplayName(pl.getDisplayName());
                item.setItemMeta(sm);
                inv.setItem(i, item);
                pll.add(pl);
                i++;
            }
        }
        ItemStack itm = new ItemStack(Material.BARRIER);
        ItemMeta im = itm.getItemMeta();
        im.setDisplayName("추적중지");
        itm.setItemMeta(im);
        inv.setItem(44, itm);
    }

    // Nice little method to create a gui item with a custom name, and description
    // You can open the inventory with this
    public void openInventory() {
        player.openInventory(inv);
    }

    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (!e.getInventory().equals(inv)) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType().isAir()) return;

        // Using slots click is a best option for your inventory click's

        if(e.getSlot() == 44){
            main.trm.getTrack(player).ResetTrack();
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("추적종료(강제종료)"));
            player.closeInventory();
            return;
        }
        if(main.trm.getTrack(player).getTrack() != null) {
            main.trm.getTrack(player).ResetTrack();
        }
        main.trm.getTrack(player).Track(pll.get(e.getSlot()));
        player.closeInventory();
    }
}
