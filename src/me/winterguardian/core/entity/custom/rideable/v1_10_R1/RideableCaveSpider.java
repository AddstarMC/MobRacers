package me.winterguardian.core.entity.custom.rideable.v1_10_R1;

import me.winterguardian.core.entity.EntityUtil;
import me.winterguardian.core.entity.custom.rideable.RideableEntity;
import net.minecraft.server.v1_10_R1.*;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;

import java.lang.reflect.Field;

public class RideableCaveSpider extends EntityCaveSpider implements RideableEntity
{
	private float climbHeight, jumpHeight, jumpThrust, speed, backwardSpeed, sidewaySpeed;

	public RideableCaveSpider(org.bukkit.World world)
	{
		this(((CraftWorld)world).getHandle());
	}

	public RideableCaveSpider(World world)
	{
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
	public void aS()
	{
		this.E = true;
		this.fallDistance = 0;
	}
	
	@Override
	public void g(float sideMot, float forMot)
	{
		if(passenger() == null || !(passenger() instanceof EntityHuman))
		{
			this.P = 0.5f;
	        super.g(sideMot, forMot);
	        return;
	    }
		
		this.lastYaw = this.yaw = ((EntityHuman) passenger()).yaw;
		this.pitch = ((EntityHuman) passenger()).pitch * 0.75f;
		if(this.pitch > 0)
			this.pitch = 0;
		this.setYawPitch(this.yaw, this.pitch);
		this.aQ = this.aO = this.yaw;
	
		this.P = this.climbHeight;
	

		boolean jump = EntityUtil.getProtectedField("bE",passenger(),EntityLiving.class, Boolean.class,false);
		sideMot = ((EntityLiving) passenger()).bg;
		forMot = ((EntityLiving) passenger()).bh;

		if (forMot < 0.0F)
			forMot *= this.backwardSpeed;
	
		sideMot *= this.sidewaySpeed;
	 
		if(jump)
			if(this.inWater)
				this.cm();
			else if(this.onGround && this.jumpHeight != 0 && this.jumpThrust != 0)
			{
				this.motY = this.jumpHeight / 2;
				this.motZ = Math.cos(Math.toRadians(-this.yaw)) * this.jumpThrust * forMot; //normal X
				this.motX = Math.sin(Math.toRadians(-this.yaw)) * this.jumpThrust * forMot; //normal Y
			}

		this.k(this.speed / 5);
		super.g(sideMot, forMot);
	}
	@Override
    protected void cm()
    {
        this.motY += 0.03999999910593033D;
    }


	@Override
	public boolean m_()
	{
		return false;
	}

	@Override
	public float getClimbHeight()
	{
		return this.climbHeight;
	}

	@Override
	public void setClimbHeight(float climbHeight)
	{
		this.climbHeight = climbHeight;
	}

	@Override
	public float getJumpHeight()
	{
		return this.jumpHeight;
	}

	@Override
	public void setJumpHeight(float jumpHeight)
	{
		this.jumpHeight = jumpHeight;
	}
	
	@Override
	public float getJumpThrust()
	{
		return this.jumpThrust;
	}

	@Override
	public void setJumpThrust(float jumpThrust)
	{
		this.jumpThrust = jumpThrust;
	}

	@Override
	public float getSpeed()
	{
		return this.speed;
	}

	@Override
	public void setSpeed(float speed)
	{
		this.speed = speed;
	}

	@Override
	public float getBackwardSpeed()
	{
		return this.backwardSpeed;
	}

	@Override
	public void setBackwardSpeed(float backwardSpeed)
	{
		this.backwardSpeed = backwardSpeed;
	}

	@Override
	public float getSidewaySpeed()
	{
		return this.sidewaySpeed;
	}

	@Override
	public void setSidewaySpeed(float sidewaySpeed)
	{
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