import java.util.Random;

/*
 * David Hau
 * CS4990H
 */

public class Good
{
	private int productionTime = 1;
	private int cost = 1;
	private int decay = 1;
	private double necessityStatic = 0.0;
	private double necessityDynamic = 0.0;
	private double luxuryStatic = 0.0;
	private double luxuryDynamic = 0.0;
	
	private int goodLifetime = 0;
	
	Good()
	{
		Random random = new Random();
		int goodNumber = Math.abs(random.nextInt() % 100 + 1);
		int priorityNumberNecessity = Math.abs(random.nextInt() % 2 + 1);
		int priorityNumberLuxury = Math.abs(random.nextInt() % 2 + 1);
		int goodType = 0;
		boolean defaultGood = false;
		
		// Chance of good with both necessity and luxury
		if(goodNumber <= 10)
		{
			goodType = 1;
		}
		// Chance of necessity good
		else if(goodNumber <= 55)
		{
			goodType = 2;
		}
		// Chance of luxury good
		else
		{
			goodType = 3;
		}
		
		// Necessity &Luxury
		if(goodType == 1)
		{
			necessityStatic = Math.abs(random.nextInt() % 100 + 1);
			necessityDynamic = Math.abs(random.nextInt() % 100 + 1);
			
			// Necessity Settings
			setNecessitySettings(priorityNumberNecessity);
			
			// Luxury Settings
			setLuxurySettings(priorityNumberLuxury);
			
		}
		// Necessity Only
		else if(goodType == 2)
		{
			setNecessitySettings(priorityNumberNecessity);
		}
		// Luxury Only
		else if(goodType == 3)
		{
			setLuxurySettings(priorityNumberLuxury);
		}
		// Default
		else
		{
			necessityDynamic = 1.0;
			necessityStatic = 1.0;
			luxuryDynamic = 1.0;
			luxuryStatic = 1.0;
			defaultGood = true;
		}
		
		// Check if default good
		if(!defaultGood)
		{
			setProductionTimeSetting();
			setCostSetting();
			setDecaySetting();
		}
	}
	
	public void setNecessitySettings(int priorityNumberNecessity)
	{
		if(getNecessityTotal() > 100.0)
		{
			if(priorityNumberNecessity == 1)
			{
				double tempNum = getNecessityTotal();
				necessityDynamic = necessityDynamic - (tempNum - 100.0);
				
				if(necessityDynamic < 0)
				{
					tempNum = necessityDynamic;
					necessityDynamic = 0;
					necessityStatic = necessityStatic - tempNum;
				}
			}
			else
			{
				double tempNum = getNecessityTotal();
				necessityStatic = necessityStatic - (tempNum - 100.0);
				
				if(necessityStatic < 0)
				{
					tempNum = necessityStatic;
					necessityStatic = 0;
					necessityDynamic = necessityDynamic - tempNum;
				}
			}
		}
	}
	
	public void setLuxurySettings(int priorityNumberLuxury)
	{
		if(getLuxuryTotal() > 100.0)
		{
			if(priorityNumberLuxury == 1)
			{
				double tempNum = getLuxuryTotal();
				luxuryDynamic = luxuryDynamic - (tempNum - 100.0);
				
				if(luxuryDynamic < 0)
				{
					tempNum = luxuryDynamic;
					luxuryDynamic = 0;
					luxuryStatic = luxuryStatic - tempNum;
				}
			}
			else
			{
				double tempNum = getLuxuryTotal();
				luxuryStatic = luxuryStatic - (tempNum - 100.0);
				
				if(luxuryStatic < 0)
				{
					tempNum = luxuryStatic;
					luxuryStatic = 0;
					luxuryDynamic = luxuryDynamic - tempNum;
				}
			}
		}
	}
	
	public void setProductionTimeSetting()
	{
		int timeNecessityStatic = 0;
		int timeNecessityDynamic = 0;
		int timeLuxuryStatic = 0;
		int timeLuxuryDynamic = 0;
		
		if(necessityStatic > 0)
		{
			timeNecessityStatic = (int) ((int)(necessityStatic % 3) + 1);
		}
		
		if(necessityDynamic > 0)
		{
			timeNecessityDynamic = (int) ((int)(necessityDynamic % 10) + 1);
		}
		
		if(luxuryStatic > 0)
		{
			timeLuxuryStatic = (int) ((int)(luxuryStatic % 4) + 1);
		}
		
		if(luxuryDynamic > 0)
		{
			timeLuxuryDynamic = (int) ((int)(luxuryStatic % 8) + 1);
		}
		
		productionTime = timeNecessityStatic + timeNecessityDynamic + timeLuxuryStatic + timeLuxuryDynamic;
	}
	
	public void setCostSetting()
	{
		int costNecessityStatic = 0;
		int costNecessityDynamic = 0;
		int costLuxuryStatic = 0;
		int costLuxuryDynamic = 0;
		
		if(necessityStatic > 0)
		{
			costNecessityStatic = (int) ((int) necessityStatic * 5);
		}
		
		if(necessityDynamic > 0)
		{
			costNecessityDynamic = (int) ((int) necessityDynamic * 1);
		}
		
		if(luxuryStatic > 0)
		{
			costLuxuryStatic = (int) ((int) luxuryStatic * 20);
		}
		
		if(luxuryDynamic > 0)
		{
			costLuxuryDynamic = (int) ((int) luxuryDynamic * 10);
		}
		
		cost = costNecessityStatic + costNecessityDynamic + costLuxuryStatic + costLuxuryDynamic;
	}
	
	public void setDecaySetting()
	{
		int necessityDecay = 0;
		int luxuryDecay = 0;
		
		if(necessityStatic > 0)
		{
			necessityDecay = (int) ((int)(necessityStatic % 3) + 1);
		}
		
		if(luxuryStatic > 0)
		{
			luxuryDecay = (int) ((int)(luxuryStatic % 4) + 1);
		}
		
		if(necessityDecay > luxuryDecay)
		{
			decay = necessityDecay;
		}
		else
		{
			decay = luxuryDecay;
		}
		
		goodLifetime = decay;

	}
	
	public void decayGood()
	{
		if(goodLifetime > 0)
		{
			goodLifetime = goodLifetime - 1;
		}
	}
	
	public int getProductionTime() { return productionTime; }
	public int getCost() { return cost; }
	public int getDecay() { return decay; }
	public double getNecessityStatic() { return necessityStatic; }
	public double getNecesssityDynamic() { return necessityDynamic; }
	public double getNecessityTotal() { return necessityStatic + necessityDynamic; }
	public double getLuxuryStatic() { return luxuryStatic; }
	public double getLuxuryDynamic( ) { return luxuryDynamic; }
	public double getLuxuryTotal() { return luxuryStatic + luxuryDynamic; }
	public int getGoodLifetime() { return goodLifetime; }
	
}
