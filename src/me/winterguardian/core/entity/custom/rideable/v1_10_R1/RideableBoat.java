package me.winterguardian.core.entity.custom.rideable.v1_10_R1;

import me.winterguardian.core.entity.EntityUtil;
import me.winterguardian.core.entity.custom.rideable.RideableEntity;
import net.minecraft.server.v1_10_R1.*;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_10_R1.TrigMath;

public class RideableBoat extends EntityBoat implements RideableEntity {
    public boolean av;
    public int ax;
    public int ay;
    public int hurtTicks;
    public int aA;
    public float aB;
    public int deathTicks;
    public float aD;
    public float aE;
    public float aG;
    public float aH;
    public float aI;
    public int maxNoDamageTicks = 20;
    public float aK;
    public float aL;
    public float aM;
    public float aN;
    public float aO;
    public float aP;
    public float aQ;
    public float aR;
    public float aS = 0.02F;
    public EntityHuman killer;
    public float lastDamage;
    public float bf;
    public float bg;
    public float mm; //? no clue where this comes from
    public float bh;
    public boolean updateEffects = true;
    public EntityLiving lastDamager;
    public int hurtTimestamp;
    public int expToDrop;
    public int maxAirTicks = 300;
    protected int aF;
    protected int lastDamageByPlayerTime;
    protected boolean aV;
    protected int ticksFarFromPlayer;
    protected float aX;
    protected float aY;
    protected float aZ;
    protected float ba;
    protected float bb;
    protected int bc;
    protected boolean be;
    protected int bi;
    protected double bj;
    protected double bk;
    protected double bl;
    protected double bm;
    protected double bn;
    private float bB;
    private int bp;
    private int datawatcher9;

    private float climbHeight, jumpHeight, jumpThrust, speed, backwardSpeed, sidewaySpeed;

    public RideableBoat(org.bukkit.World world, double x, double y, double z) {
        this(((CraftWorld) world).getHandle(), x, y, z);
    }

    public RideableBoat(World world, double x, double y, double z) {
        super(world, x, y, z);

        this.aN = ((float) ((Math.random() + 1.0D) * 0.009999999776482582D));
        setPosition(this.locX, this.locY, this.locZ);
        this.aM = ((float) Math.random() * 12398.0F);
        this.yaw = ((float) (Math.random() * 3.1415927410125732D * 2.0D));
        this.aQ = this.yaw;
        this.K = 0.6F;

        this.datawatcher9 = 0;


        this.climbHeight = 1f;
        this.jumpHeight = 1f;
        this.jumpThrust = 1f;
        this.speed = 1f;
        this.backwardSpeed = 0.25f;
        this.sidewaySpeed = 0.4f;


    }

    @Override
    public void aS() {
        this.E = true;
        this.fallDistance = 0;
    }

    public void g(float sideMot, float forMot, EntityLiving entity) {
        if (passenger() == null || !(passenger() instanceof EntityHuman)) {
            this.P = 0.6f;
            superg(sideMot, forMot, entity);
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
 if(jump ==null)jump=false;
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

        this.bB = this.speed / 5;
        superg(sideMot, forMot, entity);
    }

    @Override
    public void m() {
        i();
        if (!this.world.isClientSide) {
            int i = this.datawatcher9;
            if (i > 0) {
                if (this.ay <= 0) {
                    this.ay = (20 * (30 - i));
                }
                this.ay -= 1;
                if (this.ay <= 0)
                    this.datawatcher9 = i - 1;

            }
        }
        entityLivingM();
        double d0 = this.locX - this.lastX;
        double d1 = this.locZ - this.lastZ;
        float f = (float) (d0 * d0 + d1 * d1);
        float f1 = this.aO;
        float f2 = 0.0F;

        this.aX = this.aY;
        float f3 = 0.0F;
        if (f > 0.0025000002F) {
            f3 = 1.0F;
            f2 = (float) Math.sqrt(f) * 3.0F;

            f1 = (float) TrigMath.atan2(d1, d0) * 180.0F / 3.1415927F - 90.0F;
        }
        if (this.aE > 0.0F) {
            f1 = this.yaw;
        }
        if (!this.onGround) {
            f3 = 0.0F;
        }
        this.aY += (f3 - this.aY) * 0.3F;
        this.world.methodProfiler.a("headTurn");
        f2 = h(f1, f2);
        this.world.methodProfiler.b();
        this.world.methodProfiler.a("rangeChecks");
        while (this.yaw - this.lastYaw < -180.0F) {
            this.lastYaw -= 360.0F;
        }
        while (this.yaw - this.lastYaw >= 180.0F) {
            this.lastYaw += 360.0F;
        }
        while (this.aO - this.aP < -180.0F) {
            this.aP -= 360.0F;
        }
        while (this.aO - this.aP >= 180.0F) {
            this.aP += 360.0F;
        }
        while (this.pitch - this.lastPitch < -180.0F) {
            this.lastPitch -= 360.0F;
        }
        while (this.pitch - this.lastPitch >= 180.0F) {
            this.lastPitch += 360.0F;
        }
        while (this.aQ - this.aL < -180.0F) {
            this.aL -= 360.0F;
        }
        while (this.aQ - this.aL >= 180.0F) {
            this.aL += 360.0F;
        }
        this.world.methodProfiler.b();
        this.bg += f2;
    }

    protected float h(float f, float f1) {
        float f2 = MathHelper.g(f - this.aO);

        this.aO += f2 * 0.3F;
        float f3 = MathHelper.g(this.yaw - this.aO);
        boolean flag = (f3 < -90.0F) || (f3 >= 90.0F);
        if (f3 < -75.0F) {
            f3 = -75.0F;
        }
        if (f3 >= 75.0F) {
            f3 = 75.0F;
        }
        this.aO = (this.yaw - f3);
        if (f3 * f3 > 2500.0F) {
            this.aO += f3 * 0.2F;
        }
        if (flag) {
            f1 *= -1.0F;
        }
        return f1;
    }

    public void entityLivingM() {
        if (this.bn > 0)
            this.bn -= 1;

        if (this.bi > 0) {
            double d0 = this.locX + (this.bj - this.locX) / this.bi;
            double d1 = this.locY + (this.bk - this.locY) / this.bi;
            double d2 = this.locZ + (this.bl - this.locZ) / this.bi;
            double d3 = MathHelper.g(this.bm - this.yaw);

            this.yaw = ((float) (this.yaw + d3 / this.bi));
            this.pitch = ((float) (this.pitch + (this.bn - this.pitch) / this.bi));
            this.bi -= 1;
            setPosition(d0, d1, d2);
            setYawPitch(this.yaw, this.pitch);
        } else {
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
        this.world.methodProfiler.a("newAi");
        this.world.methodProfiler.b();
        this.world.methodProfiler.b();
        this.world.methodProfiler.a("jump");
        if (this.be) {
            if (isInWater()) {
                cm();
            } else if (ao()) {
                cn();
            } else if ((this.onGround) && (this.bn == 0)) {
                cl();
                this.bn = 10;
            }
        } else {
            this.bn = 0;
        }
        this.world.methodProfiler.b();
        this.world.methodProfiler.a("travel");
        this.bf *= 0.98F;
        this.bg *= 0.98F;
        this.bh *= 0.9F;
        g(this.bf, this.bg, this.mm);
        this.world.methodProfiler.b();
        this.world.methodProfiler.a("push");
        this.world.methodProfiler.b();
    }

    public void superg(float f, float f1, EntityLiving entity) {
        if (isInWater()) {
            double d0 = this.locY;
            float f3 = 0.8F;
            float f4 = 0.02F;
            float f2 = EnchantmentManager.d(entity);
            if (f2 > 3.0F) {
                f2 = 3.0F;
            }
            if (!this.onGround) {
                f2 *= 0.5F;
            }
            if (f2 > 0.0F) {
                f3 += (0.54600006F - f3) * f2 / 3.0F;
                f4 += (this.bB * 1.0F - f4) * f2 / 3.0F;
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
        } else if (ao()) {
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
            if (this.onGround) {
                f5 = this.world.getType(new BlockPosition(MathHelper.floor(this.locX), MathHelper.floor(getBoundingBox().b) - 1, MathHelper.floor(this.locZ))).getBlock().frictionFactor * 0.91F;
            }
            float f6 = 0.16277136F / (f5 * f5 * f5);
            float f3;
            if (this.onGround) {
                f3 = this.bB * f6;
            } else {
                f3 = this.aS;
            }
            a(f, f1, f3);
            f5 = 0.91F;
            if (this.onGround) {
                f5 = this.world.getType(new BlockPosition(MathHelper.floor(this.locX), MathHelper.floor(getBoundingBox().b) - 1, MathHelper.floor(this.locZ))).getBlock().frictionFactor * 0.91F;
            }
            if (m_()) {
                float f4 = 0.15F;
                this.motX = MathHelper.a(this.motX, -f4, f4);
                this.motZ = MathHelper.a(this.motZ, -f4, f4);
                this.fallDistance = 0.0F;
                if (this.motY < -0.15D) {
                    this.motY = -0.15D;
                }
            }
            move(this.motX, this.motY, this.motZ);
            if ((this.positionChanged) && (m_())) {
                this.motY = 0.2D;
            }
            if ((this.world.isClientSide) && ((!this.world.isLoaded(new BlockPosition((int) this.locX, 0, (int) this.locZ))) || (!this.world.getChunkAtWorldCoords(new BlockPosition((int) this.locX, 0, (int) this.locZ)).p()))) {
                if (this.locY > 0.0D) {
                    this.motY = -0.1D;
                } else {
                    this.motY = 0.0D;
                }
            } else {
                this.motY -= 0.08D;
            }
            this.motY *= 0.9800000190734863D;
            this.motX *= f5;
            this.motZ *= f5;
        }
        this.aG = this.aH;
        double d0 = this.locX - this.lastX;
        double d1 = this.locZ - this.lastZ;

        float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;
        if (f2 > 1.0F) {
            f2 = 1.0F;
        }
        this.aH += (f2 - this.aH) * 0.4F;
        this.aK += this.aH;
    }

    protected void cl() {
        this.motY = 0.42f;
        if (isSprinting()) {
            float f = this.yaw * 0.017453292F;

            this.motX -= MathHelper.sin(f) * 0.2F;
            this.motZ += MathHelper.cos(f) * 0.2F;
        }
        this.impulse = true;
    }

    protected void cm() {
        this.motY += 0.03999999910593033D;
    }

    protected void cn() {
        this.motY += 0.03999999910593033D;
    }

    public boolean m_() {
        int i = MathHelper.floor(this.locX);
        int j = MathHelper.floor(getBoundingBox().b);
        int k = MathHelper.floor(this.locZ);
        Block block = this.world.getType(new BlockPosition(i, j, k)).getBlock();

        return ((block == Blocks.LADDER) || (block == Blocks.VINE));
    }

    protected void a(double d0, boolean flag, IBlockData block, BlockPosition blockposition) {
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
        if (this.passengers.size() == 0) {
            return null;
        } else {
            return this.passengers.get(0);
        }
    }
}
