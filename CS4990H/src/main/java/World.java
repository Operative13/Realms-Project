import java.util.ArrayList;

/*
 * David Hau
 * CS4990H
 */
public class World
{
	static Governor governor = new Governor();
	static Environment environment;
	static ArrayList<Commoner> populace = new ArrayList<Commoner>();
	static ArrayList<Job> jobs = new ArrayList<Job>();
	
	private int turns = 0;
	private int maxTurns = Integer.MAX_VALUE;
	private boolean complete = false;
	
	private int initialEnvironmentResources = 0;
	
	World()
	{
		environment = new Environment();
		populateCommoner();
	}
	
	World(int seed)
	{
		environment = new Environment(seed);
		populateCommoner();
	}
	
	World(int seed, int turns)
	{
		maxTurns = turns;
		environment = new Environment(seed);
		populateCommoner();
	}
	
	private void populateCommoner()
	{
		for(int i = 0; i < 100; i++)
		{
			populace.add(i, new Commoner());;
		}
	}
	
	public void useDisaster()
	{
		
	}
	
	public int getGDP()
	{
		int gdp = 0;
		
		for(int i = 0; i < jobs.size(); i++)
		{
			gdp = gdp + (jobs.get(i).getGoodPrice() * jobs.get(i).getGoodSold());
		}
		
		return gdp;
	}
	
	public void simulationResults()
	{
		
		System.out.println("\nSimulation Finished in " + getTurns() +" days.");
	}
	
	public void simulate()
	{
		if(turns <= 0)
		{
			// initial setup
		}
		
		nextDay();
		
		if(turns >= maxTurns)
		{
			complete = true;
		}
	}
	
	public void nextDay()
	{
		turns = turns + 1;
		
		for(int i = 0; i < populace.size(); i++)
		{
			populace.get(i).resetReceivedPayment();
		}
	}
	
	public int getTurns() { return turns; }
	public boolean getComplete() { return complete; }
	public Governor getGovernor() { return governor; }
	public Environment getEnvironment() { return environment; }
	public ArrayList<Commoner> getPopulace() { return populace; }
	public int getInitialEnvironmentResources() { return initialEnvironmentResources; }
	
}