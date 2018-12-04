import java.util.ArrayList;
import java.util.Random;

/*
 * David Hau
 * CS4990H
 */

public class Commoner
{
	ArrayList<Good> goods = new ArrayList<Good>();
	
	private final double MINIMUM = 0.0;
	private final double MAXIMUM = 100.0;
	private final double MINIMUM_NECESSITY = 3.0;
	
	private double necessityCurrent = MAXIMUM;
	private double luxuryCurrent = MAXIMUM;
	
	private int gold = 0;
	private boolean life = true;
	private double necessityRequired = 30.0;
	private double luxuryRequired = 0.0;
	
	private String jobID = "UNEMPLOYED";
	private Job job;
	private boolean receivedPayment = false;
	
	Commoner()
	{
		Random random = new Random();
		necessityRequired = Math.abs(random.nextInt() % 100 + 1);;
		if(necessityRequired < MINIMUM_NECESSITY)
		{
			necessityRequired = MINIMUM_NECESSITY;
		}
		else if(necessityRequired > MAXIMUM)
		{
			necessityRequired = MAXIMUM;
		}
		
		random = new Random();
		luxuryRequired = Math.abs(random.nextInt() % 100 + 1);;
		if(luxuryRequired < MINIMUM)
		{
			luxuryRequired = MINIMUM;
		}
		else if (luxuryRequired > MAXIMUM)
		{
			luxuryRequired = MAXIMUM;
		}
		
		
	}
	
	public void necessityDecay()
	{
		necessityCurrent = necessityCurrent - 10;
		
		if(necessityCurrent < MINIMUM)
		{
			necessityCurrent = MINIMUM;
		}
	}
	
	public void necessityDecay(int decayRate)
	{
		necessityCurrent = necessityCurrent - decayRate;
		
		if(necessityCurrent < MINIMUM)
		{
			necessityCurrent = MINIMUM;
		}
	}
	
	public void luxuryDecay()
	{
		luxuryCurrent = luxuryCurrent - 20;
	}
	
	public void luxuryDecay(int decayRate)
	{
		luxuryCurrent = luxuryCurrent - decayRate;
		
		if(luxuryCurrent < MINIMUM)
		{
			luxuryCurrent = MINIMUM;
		}
	}
	
	public void obtainJob(Job job)
	{
		if(job.getJobOpenings() > 0)
		{
			job.hireWorker(this);
			this.jobID = job.getUniqueID();
			this.job = job;
		}
	}
	
	public void loseJob(Job job)
	{
		jobID = "UNEMPLOYED";
		job.vacancy(this);
	}
	
	public void produceGood(Job job)
	{
		if(jobID != "UNEMPLOYED")
		{
			this.job = job; // update job
			job.produceGood();
			this.job = job;
		}
	}
	
	public void receivePayment(int payment)
	{
		if(!receivedPayment)
		{
			gold = gold + payment;
		}
		receivedPayment = true;
	}

	public void evaluate()
	{
		
	}
	
	public void resetReceivedPayment() { receivedPayment = false; }
	
	public int getGold() { return gold; }
	public boolean getLife() { return life; }
	public String getjobID() { return this.jobID; }
	public Job getJob() { return job; }
	public void setGold(int gold) { this.gold = gold; }
	public boolean getReceivedPayment() { return receivedPayment; }
}
