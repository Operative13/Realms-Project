import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/*
 * David Hau
 * CS4990H
 */

public class Job
{
	ArrayList<Commoner> workers = new ArrayList<Commoner>();
	Environment harvestedEnvironment;
	
	
	private int budget = 0;
	private int jobOpenings = 0;
	private int workforce = 0;
	private String uniqueID = UUID.randomUUID().toString();
	private Good good;
	
	private int goodStockpile = 0;
	private int goodPrice = 0;
	private int jobSalary = 0;
	private int goodSold = 0;
	
	Job(int budget, int jobOpenings, Environment environment)
	{
		this.budget = budget;
		this.jobOpenings = jobOpenings;
		this.harvestedEnvironment = environment;
	}
	
	public void setGoodPrice(int price)
	{
		if(price >= 0)
		{
			goodPrice = price;
		}
	}
	
	public void hireWorker(Commoner commoner)
	{
		jobOpenings = jobOpenings - 1;
		workforce = workforce + 1;
		workers.add(commoner);
	}
	
	public void fireRandomWorker()
	{
		Random random = new Random();
		workers.remove(random.nextInt(workers.size()));
		jobOpenings = jobOpenings + 1;
		workforce = workforce - 1;
	}
	
	public void fireWorker(Commoner worker)
	{
		workers.remove(worker);
		jobOpenings = jobOpenings + 1;
		workforce = workforce - 1;
	}
	
	public void payWorkers()
	{
		for(int i = 0; i < jobOpenings; i++)
		{
			if(jobSalary <= budget)
			{
				workers.get(i).receivePayment(jobSalary);
				budget = budget - jobSalary;
			}
			else
			{
				fireWorker(workers.get(i));
				setJobOpenings(0);
			}
		}
	}
	
	public void vacancy(Commoner commoner)
	{
		jobOpenings = jobOpenings + 1;
		workforce = workforce - 1;
		workers.remove(commoner);
	}
	
	public void produceGood()
	{
		goodStockpile = goodStockpile + 1;
	}
	
	public void setJobOpenings(int jobOpenings)
	{
		if(jobOpenings > 0)
		{
			this.jobOpenings = jobOpenings;
		}
	}
	
	public void setJobSalary(int salary)
	{
		if(salary >= 0)
		{
			jobSalary = salary;
		}
	}
	
	public void setGood(Good good)
	{
		this.good = good;
	}
	
	public int getBudget() { return budget; }
	public int getJobOpenings() { return jobOpenings; }
	public int getWorkforce() { return workforce; }
	public String getUniqueID() { return uniqueID; }
	public Good getGood() { return good; }
	public int getGoodStockpile() { return goodStockpile; }
	public int getGoodPrice() { return goodPrice; }
	public int getJobSalary() { return jobSalary; }
	public int getGoodSold() { return goodSold; }
}
