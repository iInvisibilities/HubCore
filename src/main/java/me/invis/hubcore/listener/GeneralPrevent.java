package me.invis.hubcore.listener;

import me.invis.hubcore.util.enums.GeneralSetting;
import me.invis.hubcore.util.enums.Mobs;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GeneralPrevent implements Listener {

    @EventHandler
    private void onSpawn(EntitySpawnEvent event) {
        if (!GeneralSetting.ENTITIES.isEnabled()) {
            event.setCancelled(true);
            return;
        }
        EntityType eType = event.getEntityType();
        if(!GeneralSetting.MOBS.isEnabled()) {
            if(contains(eType.toString())) event.setCancelled(true);
        }
        else if(!GeneralSetting.OTHER_ENTITIES.isEnabled()) {
            if(!contains(eType.toString())) event.setCancelled(true);
        }
    }

    @EventHandler
    private void onDamage(EntityDamageEvent event) {
        if(event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) return;
        if(!GeneralSetting.DAMAGE.isEnabled()) event.setCancelled(true);
    }

    @EventHandler
    private void onEntDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player &&
                ((Player) event.getDamager()).hasPermission("hub.pvp")) return;
        if(!GeneralSetting.PVP.isEnabled()) event.setCancelled(true);
    }

    @EventHandler
    private void onExplosion(EntityExplodeEvent event) {
        if(!GeneralSetting.EXPLOSION.isEnabled()) event.setCancelled(true);
    }

    @EventHandler
    private void onBlockPlace(BlockPlaceEvent event) {
        if(event.getPlayer().hasPermission("hub.block.place")) return;
        if(!GeneralSetting.BLOCK_PLACE.isEnabled()) event.setCancelled(true);
    }

    @EventHandler
    private void onBlockBreak(BlockBreakEvent event) {
        if(event.getPlayer().hasPermission("hub.block.break")) return;
        if(!GeneralSetting.BLOCK_BREAK.isEnabled()) event.setCancelled(true);
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        if(!GeneralSetting.JOIN_MESSAGE.isEnabled()) event.setJoinMessage(null);
        if(!GeneralSetting.DAMAGE.isEnabled()) event.getPlayer().setHealth(event.getPlayer().getHealthScale());
        if(!GeneralSetting.HUNGER.isEnabled()) event.getPlayer().setFoodLevel(20);
    }

    @EventHandler
    private void onLeave(PlayerQuitEvent event) {
        if(!GeneralSetting.LEAVE_MESSAGE.isEnabled()) event.setQuitMessage(null);
    }

    @EventHandler
    private void onThrow(PlayerDropItemEvent event) {
        if(event.getPlayer().hasPermission("hub.item.throw")) return;
        if(!GeneralSetting.ITEM_THROW.isEnabled()) event.setCancelled(true);
    }

    @EventHandler
    private void onHunger(PlayerMoveEvent event) {
        if(!GeneralSetting.HUNGER.isEnabled()) event.getPlayer().setFoodLevel(20);
    }


    private boolean contains(String mob_type) {
        for(Mobs mob : Mobs.values()) {
            if(mob_type.equalsIgnoreCase(mob.toString())) return true;
        }
        return false;
    }
}
