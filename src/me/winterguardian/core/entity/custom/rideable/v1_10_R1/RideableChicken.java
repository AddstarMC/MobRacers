package me.winterguardian.core.entity.custom.rideable.v1_10_R1;

import me.winterguardian.core.entity.EntityUtil;
import me.winterguardian.core.entity.custom.rideable.RideableEntity;
import net.minecraft.server.v1_10_R1.*;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_10_R1.SpigotTimings;

public class RideableChicken extends EntityChicken implements RideableEntity {
    private float climbHeight, jumpHeight, jumpThrust, speed, backwardSpeed, sidewaySpeed;

    public RideableChicken(org.bukkit.World world) {
        this(((CraftWorld) world).getHandle());
    }

    public RideableChicken(World world) {
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
        if (passenger() == null || !(passenger() instanceof EntityHuman)) {
            this.P = 0.5f;
            super.g(sideMot, forMot);
            return;
        }

        this.lastYaw = this.yaw = ((EntityHuman) passenger()).yaw;
        this.pitch = ((EntityHuman) passenger()).pitch * 0.75f;
        if (this.pitch > 0)
            this.pitch = 0;
        this.setYawPitch(this.yaw, this.pitch);
        this.aQ = this.aO = this.yaw;

        this.P = this.climbHeight;


        Boolean jump = EntityUtil.getProtectedField("be", EntityLiving.class, Boolean.class, passenger(), false);
        if(jump==null)jump=false;
        sideMot = ((EntityLiving) passenger()).bg;
        forMot = ((EntityLiving) passenger()).bh;

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

        this.k(this.speed / 5);
        super.g(sideMot, forMot);
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
        if (cj())//check health is > 0
        {
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
        if (this.be) {
            if (isInWater()) {
                cm();
            } else if (ao()) {
                cn();
            } else if ((this.onGround) && (EntityUtil.getProtectedField("bC", EntityLiving.class, int.class, this, null) == 0)) {
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
}