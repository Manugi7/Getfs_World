package com.manugi.getf_world.entities;

import com.manugi.getf_world.init.InitItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.UUID;

import static com.manugi.getf_world.init.InitItems.CRISPY;
import static com.manugi.getf_world.init.InitMobs.MARB;

public class MarbEntity extends TamableAnimal {

    private static final float START_HEALTH = 8.0F;
    private static final float TAME_HEALTH = 20.0F;

    private static final EntityDataAccessor<Boolean> DATA_INTERESTED_ID = SynchedEntityData.defineId(MarbEntity.class, EntityDataSerializers.BOOLEAN);

    public MarbEntity(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
    }

    @Nullable
    @Override
    public MarbEntity getBreedOffspring(ServerLevel svlvl, AgeableMob mob) {
        MarbEntity marb = MARB.get().create(svlvl);
        if (marb != null) {
            UUID uuid = this.getOwnerUUID();
            if (uuid != null) {
                marb.setOwnerUUID(uuid);
                marb.setTame(true);
            }
        }

        return marb;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new TemptGoal(this,0.0D,Ingredient.of(CRISPY.get().asItem()),false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
    }

    public void setIsInterested(boolean p_30445_) {
        this.entityData.set(DATA_INTERESTED_ID, p_30445_);
    }

    public boolean canMate(Animal p_30392_) {
        if (p_30392_ == this) {
            return false;
        } else if (!this.isTame()) {
            return false;
        } else if (!(p_30392_ instanceof MarbEntity)) {
            return false;
        } else {
            MarbEntity marb = (MarbEntity) p_30392_;
            if (!marb.isTame()) {
                return false;
            } else if (marb.isInSittingPose()) {
                return false;
            } else {
                return this.isInLove() && marb.isInLove();
            }
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 1.0F).add(Attributes.MAX_HEALTH, 8.0D);
    }

    @Override
    public boolean hurt(DamageSource p_30386_, float p_30387_) {

        Entity entity = p_30386_.getEntity();

        if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
            p_30387_ = (p_30387_ + 1.0F) / 2.0F;
        }

        return super.hurt(p_30386_, p_30387_);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {

        ItemStack itemstack = player.getItemInHand(hand);
        Random rand = new Random();
        InteractionResult interactionresult = super.mobInteract(player, hand);

        if (this.level.isClientSide) {
            boolean flag = this.isOwnedBy(player) || this.isTame() || itemstack.is(Items.BONE) && !this.isTame();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            if (this.isTame()) {
                if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                    this.heal((float) itemstack.getFoodProperties(this).getNutrition());
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }

                    this.gameEvent(GameEvent.EAT, this);
                    return InteractionResult.SUCCESS;
                }

            if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(player)) {

                this.setOrderedToSit(!this.isOrderedToSit());
                this.jumping = false;
                this.navigation.stop();
                this.setTarget((LivingEntity) null);
                return InteractionResult.SUCCESS;
            }

            } else if (itemstack.is(new ItemStack(CRISPY.get()).getItem())) {

                if (rand.nextInt(4) == 3) {
                    this.customTammingParticles();
                    this.tame(player);
                    this.navigation.stop();
                    this.setTarget((LivingEntity) null);
                    this.setOrderedToSit(true);
                } else {
                    this.customFailingParticles();
                }
                itemstack.shrink(1);

                return InteractionResult.SUCCESS;

            }
        }

        return super.mobInteract(player, hand);
    }

    private void customTammingParticles () {

        ParticleOptions particleoptions = ParticleTypes.NOTE;

        for (int i = 0; i < 7; ++i) {

            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.level.addParticle(particleoptions, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
        }
    }

    private void customFailingParticles () {

        ParticleOptions particleoptions = ParticleTypes.SMOKE;

        for (int i = 0; i < 7; ++i) {

            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.level.addParticle(particleoptions, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
        }
    }

    public boolean isFood(ItemStack p_27600_) {
        return p_27600_.is(CRISPY.get().asItem());
    }

}
