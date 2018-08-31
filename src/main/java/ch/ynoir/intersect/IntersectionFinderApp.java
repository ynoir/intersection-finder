package ch.ynoir.intersect;

import java.io.IOException;
import java.util.List;

import ch.ynoir.intersect.model.Rectangle;
import ch.ynoir.intersect.reader.Reader;
import ch.ynoir.intersect.reader.impl.JsonReader;

public class IntersectionFinderApp
{

	public static void main(String[] args)
	{
		if (args.length < 1)
		{
			showHelp();
			System.exit(0);
		}

		String inputFilePath = args[0];
		Reader reader = new JsonReader();
		List<Rectangle> rectangles;
		try
		{
			rectangles = reader.read(inputFilePath);
			for (Rectangle r : rectangles)
			{
				System.out.println(r);
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void showHelp()
	{
		System.out.println("Provide the path to the input file as a parameter.");
	}

}
