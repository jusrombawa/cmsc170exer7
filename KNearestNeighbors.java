import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.LinkedList;

public class KNearestNeighbors
{
	BufferedReader br;
	FileReader fr;
	
	LinkedList<double[]> inputData;
	LinkedList<double[]> trainingData;
	LinkedList<Integer> classList;
	
	String[] tempStrArray;
	double[] tempDoubleArray;

	
	int dimension;
	int trainDim;
	int k; //neighbor count

	public KNearestNeighbors(String input, String training)
	{
	
		//read input
		
		inputData = new LinkedList<double[]>();
		
		try
		{

			fr = new FileReader(input);
			br = new BufferedReader(fr);

			String currline;

			br = new BufferedReader(new FileReader(input));
			

			while ((currline = br.readLine()) != null)
			{
				//split into string array
				tempStrArray = currline.split(" ");
				tempDoubleArray = new double[tempStrArray.length];
				
				//parse one at a time to double
				for(int i=0;i<tempStrArray.length;i++)
				{
					tempDoubleArray[i] = Double.parseDouble(tempStrArray[i]);
				}
				//take note of input dimension, this will be the dimension for euclidean distance
				dimension = tempDoubleArray.length;
				
				inputData.add(tempDoubleArray);
				
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			}
			
			catch (IOException ex)
			{

				ex.printStackTrace();

			}

		}
		
		//Same thing but this time with training data
		
		trainingData = new LinkedList<double[]>();
		classList = new LinkedList<Integer>();
		
		try
		{

			fr = new FileReader(training);
			br = new BufferedReader(fr);

			String currline;

			br = new BufferedReader(new FileReader(training));
			boolean first = true;

			while ((currline = br.readLine()) != null)
			{
				if(first) //take k
				{
					k = Integer.parseInt(currline);
					first = false;
				}
				
				else
				{
					//split into string array
					tempStrArray = currline.split(" ");
					tempDoubleArray = new double[tempStrArray.length - 1];
					classList.add(Integer.parseInt(tempStrArray[tempStrArray.length-1]));
				
					//parse one at a time to double
					for(int i=0;i<tempStrArray.length - 1;i++)
					{
						tempDoubleArray[i] = Double.parseDouble(tempStrArray[i]);
					}
					
					
					
					
					
					trainDim = tempDoubleArray.length;
					trainingData.add(tempDoubleArray);
				}
				
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			}
			
			catch (IOException ex)
			{

				ex.printStackTrace();

			}

		}
		
		/*And finally, we classify the input*/
		for(int i=0;i<inputData.size();i++)
		{
			classify(i);
		}
	}
	
	public void classify(int i)
	{
		LinkedList<Double> distList = new LinkedList<Double>();

		for(int j = 0; j<trainingData.size(); j++)
		{
			distList.add(distance(inputData.get(i),trainingData.get(j)));
		}
		
		for(int j=0;j<distList.size();j++)
			System.out.println(distList.get(j));
		System.out.println("-----");
	}
	
	private double distance(double[] pointA, double[] pointB)
	{
		double tempDist = 0;

		
		for(int i=0;i<pointA.length;i++)
		{
			tempDist += Math.pow((pointA[i]-pointB[i]),2) ;
		}
		
		return Math.sqrt(tempDist);
	}
}
