package club.someoneice.rest;

import committee.nova.sittable.common.entity.impl.SittableEntity;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import project.studio.manametalmod.MMM;
import project.studio.manametalmod.entity.nbt.ManaMetalModRoot;

public class Event {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onPlayerSitTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        NBTTagCompound nbt = player.getEntityData();
        if (event.side.isClient()) return;

        if (event.phase == TickEvent.Phase.START) {
            if (!player.isRiding()) {
                nbt.setInteger("rest", 0);
                return;
            }

            int rest = nbt.getInteger("rest") + 1;
            if (rest >= 20 * 5) {
                nbt.setInteger("rest", 0);
                ManaMetalModRoot mmmroot = MMM.getEntityNBT(player);
                mmmroot.mana.addOxygen(10);

                if (Main.isSittableInstall) {
                    if (player.ridingEntity instanceof SittableEntity) {
                        mmmroot.mana.addOxygen(5);
                    }
                }
            } else nbt.setInteger("rest", rest);
        }
    }
}
