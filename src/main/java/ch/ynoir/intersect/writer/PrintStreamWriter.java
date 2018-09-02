package ch.ynoir.intersect.writer;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ch.ynoir.intersect.model.Orthotope;
import ch.ynoir.intersect.model.Rectangle;
import lombok.AllArgsConstructor;

/**
 * Writes input and intersections to the provided printStream.
 */
@AllArgsConstructor
public class PrintStreamWriter implements Writer
{

	private PrintStream printStream;

	@Override
	public void write(List<Rectangle> rectangles, Set<Orthotope> orthotopes, Set<Orthotope> intersections)
			throws IOException
	{
		print(rectangles, intersections);
	}

	private void print(List<Rectangle> rectangles, Set<Orthotope> intersections)
	{
		printStream.println("Input:");
		for (int i=0; i<rectangles.size(); i++)
		{
			System.out.println(String.format("  %d: %s", i+1, rectangles.get(i)));
		}
		List<Orthotope> sorted = sortByLabels(intersections);
		System.out.println("Intersections:");
		for (int i=0; i<sorted.size(); i++)
		{
			Orthotope o = sorted.get(i);
			System.out.println(String.format("  %d: Rectangles %s intersect at %s", i+1, o.getLabels(), Rectangle.fromOrthotope(o)));
		}
	}

	private List<Orthotope> sortByLabels(Set<Orthotope> intersections)
	{
		return intersections.stream()
			.sorted( (o1, o2) -> {
				List<String> labels1 = new ArrayList<>(o1.getLabels());
				List<String> labels2 = new ArrayList<>(o2.getLabels());
				Collections.sort(labels1);
				Collections.sort(labels2);
				if (labels1.size() == labels2.size())
				{
					for (int i=0; i<labels1.size(); i++)
					{
						if (labels1.get(i).equals(labels2.get(i)) == false)
						{
							return labels1.get(i).compareTo(labels2.get(i));
						}
					}
				}
				return o1.getLabels().size() - o2.getLabels().size();
			})
			.collect(Collectors.toList());
	}

}
