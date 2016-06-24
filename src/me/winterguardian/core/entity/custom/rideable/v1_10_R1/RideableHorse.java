package me.winterguardian.core.entity.custom.rideable.v1_10_R1;

import me.winterguardian.core.entity.EntityUtil;
import me.winterguardian.core.entity.custom.rideable.RideableEntity;
import net.minecraft.server.v1_10_R1.*;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_10_R1.event.CraftEventFactory;

public class RideableHorse extends EntityHorse implements RideableEntity {
    private float climbHeight, jumpHeight, jumpThrust, speed, backwardSpeed, sidewaySpeed;

    public RideableHorse(org.bukkit.World world) {
        this(((CraftWorld) world).getHandle());
    }

    public RideableHorse(World world) {
        super(world);
        this.climbHeight = 1f;
        this.jumpHeight = 1f;
        this.jumpThrust = 1f;
        this.speed = 1f;
        this.backwardSpeed = 0.25f;
        this.sidewaySpeed = 0.4f;

        this.goalSelector = new PathfinderGoalSelector((world != null) && (world.methodProfiler != null) ? world.methodProfiler : null);
        this.targetSelector = new PathfinderGoalSelector((world != null) && (world.methodProfiler != null) ? world.methodProfiler : null);

        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(20.0D);
    }

    @Override
    public void g(float sideMot, float forMot) {

        if (isVehicle() || !(passenger() instanceof EntityHuman)) {
            this.P = 1f;
            super.g(sideMot, forMot);
            return;
        }
        EntityHuman passenger = (EntityHuman) passenger();
        this.lastYaw = this.yaw = passenger.yaw;
        this.pitch = passenger.pitch * 0.75f;
        if (this.pitch > 0)
            this.pitch = 0;
        this.setYawPitch(this.yaw, this.pitch);
        this.aQ = this.aO = this.yaw;

        this.P = this.climbHeight;
        Boolean jump = EntityUtil.getProtectedField("be", EntityLiving.class, Boolean.class, passenger(), false);
        if(jump ==null)jump=false;
        sideMot = passenger.bf;
        forMot = passenger.bg;

        if (forMot < 0.0F)
            forMot *= this.backwardSpeed;

        sideMot *= this.sidewaySpeed;

        if (jump)
            if (this.inWater)
                this.cm();
            else if (this.onGround && this.jumpHeight != 0 && this.jumpThrust != 0) {
                this.motY = this.jumpHeight / 2;
                this.motZ = Math.cos(Math.toRadians(-this.yaw)) * this.jumpThrust * forMot; //normal X
                this.motX = Math.sin(Math.toRadians(-this.yaw)) * this.jumpThrust * forMot; //normal Y
            }
        l(this.speed / 5);
        superg(sideMot, forMot);
    }

    //1.8.8 version needs to go to 1.10 version.
    public void superg(float f, float f1) //override g(float float) in EntityLiving
    {
        if (ct())//server v client check
        {
            if ((isInWater())) //is in water check
            {
                double d0 = this.locY;
                float f3 = co();
                float f4 = 0.02F;
                float f2 = EnchantmentManager.d(this);
                if (f2 > 3.0F) {
                    f2 = 3.0F;
                }
                if (!this.onGround) {
                    f2 *= 0.5F;
                }
                if (f2 > 0.0F) {
                    f3 += (0.54600006F - f3) * f2 / 3.0F;
                    f4 += (cp() * 1.0F - f4) * f2 / 3.0F;
                }
                a(f, f1, f4);
                move(this.motX, this.motY, this.motZ);
                this.motX *= f3;
                this.motY *= 0.800000011920929D;
                this.motZ *= f3;
                this.motY -= 0.02D;
                if ((this.positionChanged) && (c(this.motX, this.motY + 0.6000000238418579D - this.locY + d0, this.motZ))) {
                    this.motY = 0.30000001192092896D;
                }
            } else if (ao()) { //is in lava
                double d1 = this.locY;
                a(f, f1, 0.02F);
                move(this.motX, this.motY, this.motZ);
                this.motX *= 0.5D;
                this.motY *= 0.5D;
                this.motZ *= 0.5D;
                if (!isNoGravity()) {
                    this.motY -= 0.02D;
                }
                if ((this.positionChanged) && (c(this.motX, this.motY + 0.6000000238418579D - this.locY + d1, this.motZ))) {
                    this.motY = 0.30000001192092896D;
                }
            } else if ((cG())) //has flag 7?
            {
                if (this.motY > -0.5D) {
                    this.fallDistance = 1.0F;
                }
                Vec3D vec3d = aB();
                float f5 = this.pitch * 0.017453292F;
                double d0 = Math.sqrt(vec3d.x * vec3d.x + vec3d.z * vec3d.z);
                double d2 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
                double d3 = vec3d.b();
                float f6 = MathHelper.cos(f5);
                f6 = (float) (f6 * f6 * Math.min(1.0D, d3 / 0.4D));
                this.motY += -0.08D + f6 * 0.06D;
                if ((this.motY < 0.0D) && (d0 > 0.0D)) {
                    double d4 = this.motY * -0.1D * f6;
                    this.motY += d4;
                    this.motX += vec3d.x * d4 / d0;
                    this.motZ += vec3d.z * d4 / d0;
                }
                if (f5 < 0.0F) {
                    double d4 = d2 * -MathHelper.sin(f5) * 0.04D;
                    this.motY += d4 * 3.2D;
                    this.motX -= vec3d.x * d4 / d0;
                    this.motZ -= vec3d.z * d4 / d0;
                }
                if (d0 > 0.0D) {
                    this.motX += (vec3d.x / d0 * d2 - this.motX) * 0.1D;
                    this.motZ += (vec3d.z / d0 * d2 - this.motZ) * 0.1D;
                }
                this.motX *= 0.9900000095367432D;
                this.motY *= 0.9800000190734863D;
                this.motZ *= 0.9900000095367432D;
                move(this.motX, this.motY, this.motZ);
                if ((this.positionChanged) && (!this.world.isClientSide)) {
                    double d4 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
                    double d5 = d2 - d4;
                    float f7 = (float) (d5 * 10.0D - 3.0D);
                    if (f7 > 0.0F) {
                        a(e((int) f7), 1.0F, 1.0F);
                        damageEntity(DamageSource.FLY_INTO_WALL, f7);
                    }
                }
                if ((this.onGround) && (!this.world.isClientSide) &&
                        (getFlag(7)) && (!CraftEventFactory.callToggleGlideEvent(this, false).isCancelled())) {
                    setFlag(7, false);
                }
            } else {
                float f5 = 0.91F;
                BlockPosition.PooledBlockPosition blockposition_pooledblockposition = BlockPosition.PooledBlockPosition.d(this.locX, getBoundingBox().b - 1.0D, this.locZ);
                if (this.onGround) {
                    f5 = this.world.getType(blockposition_pooledblockposition).getBlock().frictionFactor * 0.91F;
                }
                float f6 = 0.16277136F / (f5 * f5 * f5);
                float f3;
                if (this.onGround) {
                    f3 = cp() * f6;
                } else {
                    f3 = this.aS;
                }
                a(f, f1, f3);
                f5 = 0.91F;
                if (this.onGround) {
                    f5 = this.world.getType(blockposition_pooledblockposition.e(this.locX, getBoundingBox().b - 1.0D, this.locZ)).getBlock().frictionFactor * 0.91F;
                }
                if (m_()) {
                    this.motX = MathHelper.a(this.motX, -0.15000000596046448D, 0.15000000596046448D);
                    this.motZ = MathHelper.a(this.motZ, -0.15000000596046448D, 0.15000000596046448D);
                    this.fallDistance = 0.0F;
                    if (this.motY < -0.15D) {
                        this.motY = -0.15D;
                    }
                    boolean flag = isSneaking();
                    if ((flag) && (this.motY < 0.0D)) {
                        this.motY = 0.0D;
                    }
                }
                move(this.motX, this.motY, this.motZ);
                if ((this.positionChanged) && (m_())) {
                    this.motY = 0.2D;
                }
                if (hasEffect(MobEffects.LEVITATION)) {
                    this.motY += (0.05D * (getEffect(MobEffects.LEVITATION).getAmplifier() + 1) - this.motY) * 0.2D;
                } else {
                    blockposition_pooledblockposition.e(this.locX, 0.0D, this.locZ);
                }
                if ((this.world.isClientSide) && ((!this.world.isLoaded(blockposition_pooledblockposition) || (!this.world.getChunkAtWorldCoords(blockposition_pooledblockposition).p())))) {
                    if (this.locY > 0.0D) {
                        this.motY = -0.1D;
                    } else {
                        this.motY = 0.0D;
                    }
                } else if (!isNoGravity()) {
                    this.motY -= 0.08D;
                }
                this.motY *= 0.9800000190734863D;
                this.motX *= f5;
                this.motZ *= f5;
                blockposition_pooledblockposition.t();
            }
        }
        this.aG = this.aH;
        double d0 = this.locX - this.lastX;
        double d1 = this.locZ - this.lastZ;

        float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;
        if (f2 > 1.0F) {
            f2 = 1.0F;
        }
        this.aH += (f2 - this.aH) * 0.4F;
        this.aI += this.aH;
    }

    @Override
    public float getClimbHeight() {
        return this.climbHeight;
    }

    @Override
    public void setClimbHeight(float climbHeight) {
        this.climbHeight = climbHeight;
    }

    @Override
    public float getJumpHeight() {
        return this.jumpHeight;
    }

    @Override
    public void setJumpHeight(float jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    @Override
    public float getJumpThrust() {
        return this.jumpThrust;
    }

    @Override
    public void setJumpThrust(float jumpThrust) {
        this.jumpThrust = jumpThrust;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public float getBackwardSpeed() {
        return this.backwardSpeed;
    }

    @Override
    public void setBackwardSpeed(float backwardSpeed) {
        this.backwardSpeed = backwardSpeed;
    }

    @Override
    public float getSidewaySpeed() {
        return this.sidewaySpeed;
    }

    @Override
    public void setSidewaySpeed(float sidewaySpeed) {
        this.sidewaySpeed = sidewaySpeed;
    }


    public net.minecraft.server.v1_10_R1.Entity passenger() {
        return this.bw(); //returns passenger
    }
}