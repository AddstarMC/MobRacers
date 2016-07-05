/*
package me.winterguardian.core.entity.custom.rideable.v1_10_R1;

import me.winterguardian.core.entity.EntityUtil;
import me.winterguardian.core.entity.custom.rideable.RideableEntity;
import net.minecraft.server.v1_10_R1.*;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_10_R1.SpigotTimings;


public class RideableSquid extends EntitySquid implements RideableEntity {
    private float climbHeight, jumpHeight, jumpThrust, speed, backwardSpeed, sidewaySpeed;

    public RideableSquid(org.bukkit.World world) {
        this(((CraftWorld) world).getHandle());
    }

    public RideableSquid(World world) {
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
        this.setHealth(20f);
    }

    @Override
    public void g(float sideMot, float forMot) {
        if (passenger() == null || !(passenger() instanceof EntityHuman)) {
            this.P = 0.5f;
            superg(sideMot, forMot);
            return;
        }

        this.lastYaw = this.yaw = passenger().yaw;
        this.pitch = passenger().pitch * 0.75f;
        if (this.pitch > 0)
            this.pitch = 0;
        this.setYawPitch(this.yaw, this.pitch);
        this.aM = this.aI = this.yaw;

        this.P = this.climbHeight;

        Boolean jump = EntityUtil.getProtectedField("be", EntityLiving.class, Boolean.class, passenger(), false);
        if(jump ==null)jump=false;
        sideMot = ((EntityLiving) this.passenger()).bg;
        forMot = ((EntityLiving) this.passenger()).bh;
        if (forMot < 0.0F)
            forMot *= this.backwardSpeed;

        sideMot *= this.sidewaySpeed;

        if (jump)
            if (this.inWater)
                cm();
            else if (this.onGround && this.jumpHeight != 0 && this.jumpThrust != 0) {
                this.motY = this.jumpHeight / 2;
                this.motZ = Math.cos(Math.toRadians(-this.yaw)) * this.jumpThrust * forMot; //normal X
                this.motX = Math.sin(Math.toRadians(-this.yaw)) * this.jumpThrust * forMot; //normal Y
            }

        this.k(this.speed / 5);
        superg(sideMot, forMot);
    }

    public void superg(float f, float f1) {
        if (ct())//server v client check
        {
            if ((isInWater())) //is in water check
            {
                double d0 = this.locY;
                float f3 = 0.8F;
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
            } else if ((ao())) {
                double d0 = this.locY;
                a(f, f1, 0.02F);
                move(this.motX, this.motY, this.motZ);
                this.motX *= 0.5D;
                this.motY *= 0.5D;
                this.motZ *= 0.5D;
                this.motY -= 0.02D;
                if ((this.positionChanged) && (c(this.motX, this.motY + 0.6000000238418579D - this.locY + d0, this.motZ))) {
                    this.motY = 0.30000001192092896D;
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
                    f3 = this.aM;
                }
                a(f, f1, f3);
                f5 = 0.91F;
                if (this.onGround) {
                    f5 = this.world.getType(blockposition_pooledblockposition.e(this.locX, getBoundingBox().b - 1.0D, this.locZ)).getBlock().frictionFactor * 0.91F;
                }
                if (m_()) {
                    float f4 = 0.15F;
                    this.motX = MathHelper.a(this.motX, -f4, f4);
                    this.motZ = MathHelper.a(this.motZ, -f4, f4);
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
                if ((this.world.isClientSide) && ((!this.world.isLoaded(new BlockPosition((int) this.locX, 0, (int) this.locZ))) || (!this.world.getChunkAtWorldCoords(blockposition_pooledblockposition).p()))) {
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
    public void n() {
        Integer bC = EntityUtil.getProtectedField("bC", this, EntityLiving.class, int.class);
        if (bC != null && bC > 0)
            EntityUtil.setProtectedField("bC", this, int.class, (bC - 1));
        if (this.bi > 0) {
            double d0 = this.locX + (this.bf - this.locX) / this.bi;
            double d1 = this.locY + (this.bg - this.locY) / this.bi;
            double d2 = this.locZ + (this.bh - this.locZ) / this.bi;
            double d3 = MathHelper.g(this.bi - this.yaw);

            this.yaw = ((float) (this.yaw + d3 / this.bi));
            this.pitch = ((float) (this.pitch + (this.bj - this.pitch) / this.bi));
            this.bi -= 1;
            setPosition(d0, d1, d2);
            setYawPitch(this.yaw, this.pitch);
        } else if (!ct()) {
            this.motX *= 0.98D;
            this.motY *= 0.98D;
            this.motZ *= 0.98D;
        }
        if (Math.abs(this.motX) < 0.005D) {
            this.motX = 0.0D;
        }
        if (Math.abs(this.motY) < 0.005D) {
            this.motY = 0.0D;
        }
        if (Math.abs(this.motZ) < 0.005D) {
            this.motZ = 0.0D;
        }
        this.world.methodProfiler.a("ai");
        SpigotTimings.timerEntityAI.startTiming();
        if (cj()) {
            this.be = false;
            this.bf = 0.0F;
            this.bg = 0.0F;
            this.bh = 0.0F;
        } else if (ct()) {
            this.world.methodProfiler.a("newAi");
            doTick();
            this.world.methodProfiler.b();
        }
        SpigotTimings.timerEntityAI.stopTiming();
        this.world.methodProfiler.b();
        this.world.methodProfiler.a("jump");
        bC = EntityUtil.getProtectedField("bC",this, EntityLiving.class, int.class);
        if (bC == null)bC = 0;
        if (this.be) {
            if (isInWater()) {
                cm();
            } else if (ao()) {
                cn();
            } else if ((this.onGround) && (bC == 0)) {
                cl();
                EntityUtil.setProtectedField("bC", this, int.class, 10);
            }
        } else {
            EntityUtil.setProtectedField("bC", this, int.class, 0);
        }
        this.world.methodProfiler.b();
        this.world.methodProfiler.a("travel");
        this.aZ *= 0.98F;
        this.ba *= 0.98F;
        this.bf *= 0.9F;
        SpigotTimings.timerEntityAIMove.startTiming();
        g(this.aZ, this.ba);
        SpigotTimings.timerEntityAIMove.stopTiming();
        this.world.methodProfiler.b();
        this.world.methodProfiler.a("push");
        SpigotTimings.timerEntityAICollision.startTiming();
        cs();
        SpigotTimings.timerEntityAICollision.stopTiming();
        this.world.methodProfiler.b();
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

    @Override
    public net.minecraft.server.v1_10_R1.Entity bw() {

        return this.passengers.isEmpty() ? null : this.passengers.get(0);
    }

    private net.minecraft.server.v1_10_R1.Entity passenger() {
        return this.bw();
    }
}*/
