package ch.ynoir.intersect;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import ch.ynoir.intersect.finder.impl.SimpleOrthotopeIntersectionFinder;
import ch.ynoir.intersect.model.Orthotope;
import ch.ynoir.intersect.model.Rectangle;
import ch.ynoir.intersect.reader.Reader;
import ch.ynoir.intersect.reader.impl.JsonReader;
import ch.ynoir.intersect.writer.PrintStreamWriter;
import lombok.AllArgsConstructor;
import ch.ynoir.intersect.writer.HtmlWriter;

/**
 * Main class. Argument is a filename of a JSON file containing a list 'rects' 
 * with fields x, y, w, and h. Prints out the input and a list of intersections, 
 * including which rectangles intersect where.
 */
@AllArgsConstructor
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
		Optional<String> htmlOutputPath = args.length > 1 ? Optional.of(args[1]) : Optional.empty();

		new IntersectionFinderApp().run(inputFilePath, htmlOutputPath);
	}


	public void run(String inputFilePath, Optional<String> htmlOutputPath)
	{
		try
		{
			// input
			List<Rectangle> rectangles = readInputFile(inputFilePath);
			// processing
			Set<Orthotope> orthotopes = toOrthotopes(rectangles);
			Set<Orthotope> intersections = new SimpleOrthotopeIntersectionFinder().find(orthotopes);
			// output
			new PrintStreamWriter(System.out).write(rectangles, orthotopes, intersections);
			writeHtmlOutput(htmlOutputPath, rectangles, orthotopes, intersections);

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	private void writeHtmlOutput(Optional<String> htmlOutputPath, List<Rectangle> rectangles, Set<Orthotope> orthotopes,
			Set<Orthotope> intersections)
	{
		try
		{
			if (htmlOutputPath.isPresent())
			{
					new HtmlWriter(htmlOutputPath.get()).write(rectangles, orthotopes, intersections);
			}
		} catch (IOException e)
		{
			System.err.println("Could not write to file: " + e.getMessage());
			System.exit(-1);
		}
	}


	private List<Rectangle> readInputFile(String inputFilePath)
	{
		try
		{
			Reader reader = new JsonReader();
			return reader.read(inputFilePath);
		} catch (IOException e)
		{
			System.err.println("Could not read input file: " + e.getMessage());
			System.exit(-1);
			return null;
		}
	}


	private Set<Orthotope> toOrthotopes(List<Rectangle> rectangles)
	{
		Set<Orthotope> orthotopes = new HashSet<>();
		for (int i=0; i<rectangles.size(); i++)
		{
			Orthotope orthotope = rectangles.get(i).asOrthotope();
			orthotope.getLabels().add(Integer.toString(i+1));
			orthotopes.add(orthotope);
		}
		return orthotopes;
	}

	private static void showHelp()
	{
		System.out.println("Provide the path to the input file as a parameter."
				+ "Optionally, provide a second path to which the input rectangles " 
				+ "written as a HTML canvas visualization.");
	}

}
