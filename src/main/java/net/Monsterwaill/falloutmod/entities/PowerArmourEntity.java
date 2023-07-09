package net.Monsterwaill.falloutmod.entities;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.item.custom.armour.PowerArmourItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class PowerArmourEntity extends HumanoidEntity {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FalloutMod.MOD_ID,"textures/armour/power.png");

    public PowerArmourEntity(EntityType<? extends HumanoidEntity> entityType, Level level) {
        super(entityType, level);
        this.setInvulnerable(true);
        this.setCustomName(Component.translatable("Power Armour "/* + fileNameToUsable(this.getMark())*/));
        this.refreshSkin();
    }

    public PowerArmourEntity(EntityType<? extends HumanoidEntity> entityType, Level level, String customName, ResourceLocation skin) {
        super(entityType, level, customName, skin);
        this.setInvulnerable(true);
        this.setCustomName(Component.translatable("Power Armour "/* + fileNameToUsable(this.getMark())*/));
        this.refreshSkin();
    }

    public PowerArmourEntity(EntityType<? extends HumanoidEntity> entityType, Level level, String customName) {
        super(entityType, level, customName);
        this.setInvulnerable(true);
        this.setCustomName(Component.translatable("Power Armour "/* + fileNameToUsable(this.getMark())*/));
        this.refreshSkin();
    }

    public PowerArmourEntity(EntityType<? extends HumanoidEntity> entityType, Level level, ResourceLocation skin) {
        super(entityType, level, skin);
        this.setInvulnerable(true);
        this.setCustomName(Component.translatable("Power Armour "/* + fileNameToUsable(this.getMark())*/));
        this.refreshSkin();
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!player.level.isClientSide && hand == InteractionHand.MAIN_HAND) {
            if (armourSlotsEmpty(player)) {
                this.putSelfOntoPlayer(player);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean isPersistenceRequired() {
        return true;
    }

    @Override
    protected void registerGoals() {
        // no goals because no AI
    }


    public ResourceLocation getSkin() {
        return TEXTURE;
    }
    private void refreshSkin() {
        this.skin = TEXTURE;
    }


    // @TODO fix entity despawns
    @Override
    public void checkDespawn() {
        super.checkDespawn();
    }

    /**
     * Removes Underscores from the string
     * Capitalises the first letter of every word
     * Returns the new, more understandable, string.
     */
    public static String fileNameToUsable(String name) {
        String spaced = name.replace("_", " ");
        String[] words = spaced.split(" ");
        StringBuilder output = new StringBuilder();
        for (String word : words) {
            output.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1))
                    .append(" ");
        }
        return output.toString();
    }

    public static String nameFromSlot(EquipmentSlot slot) {
        return switch(slot) {
            case MAINHAND -> "hand";
            case OFFHAND -> "offhand";
            case FEET -> "boots";
            case LEGS -> "leggings";
            case CHEST -> "chestplate";
            case HEAD -> "helmet";
        };
    }

    public static boolean isWearingAllPowerArmour(Player player) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() != EquipmentSlot.Type.ARMOR) continue;

            ItemStack currentSlot = player.getItemBySlot(slot);
            if (!(currentSlot.getItem() instanceof PowerArmourItem item)) {
                return false;
            }
        }
        return true;
    }

    public void takeArmourOffPlayer(Player player) {
        if (!isWearingAllPowerArmour(player)) return;

        this.setItemSlot(EquipmentSlot.HEAD, player.getItemBySlot(EquipmentSlot.HEAD));
        this.setItemSlot(EquipmentSlot.CHEST, player.getItemBySlot(EquipmentSlot.CHEST));
        this.setItemSlot(EquipmentSlot.LEGS, player.getItemBySlot(EquipmentSlot.LEGS));
        this.setItemSlot(EquipmentSlot.FEET, player.getItemBySlot(EquipmentSlot.FEET));

        player.setItemSlot(EquipmentSlot.HEAD,ItemStack.EMPTY);
        player.setItemSlot(EquipmentSlot.CHEST,ItemStack.EMPTY);
        player.setItemSlot(EquipmentSlot.LEGS,ItemStack.EMPTY);
        player.setItemSlot(EquipmentSlot.FEET,ItemStack.EMPTY);
    }
    private void putSelfOntoPlayer(Player player) {
        player.setItemSlot(EquipmentSlot.HEAD,this.getItemBySlot(EquipmentSlot.HEAD));
        player.setItemSlot(EquipmentSlot.CHEST,this.getItemBySlot(EquipmentSlot.CHEST));
        player.setItemSlot(EquipmentSlot.LEGS,this.getItemBySlot(EquipmentSlot.LEGS));
        player.setItemSlot(EquipmentSlot.FEET,this.getItemBySlot(EquipmentSlot.FEET));

        this.remove(Entity.RemovalReason.DISCARDED);
    }
    private static boolean armourSlotsEmpty(Player player) {
        for (ItemStack item : player.getInventory().armor) {
            if (!item.isEmpty()) return false;
        }
        return true;
    }

    public static void spawnNew(Level level, BlockPos pos, Player player) {
        PowerArmourEntity power = new PowerArmourEntity(FalloutEntities.POWER_ARMOR_ENTITY.get(), level);
        power.takeArmourOffPlayer(player);
        power.setPos(Vec3.atCenterOf(pos.above()));
        power.setYRot(player.getYRot());
        level.addFreshEntity(power);
        power.refreshSkin();
    }

    @Override
    public void tick() {
        super.tick();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 25.0D).add(Attributes.MOVEMENT_SPEED, 0.2D).add(Attributes.ATTACK_DAMAGE, 1D).add(Attributes.FLYING_SPEED, 3D);
    }
}