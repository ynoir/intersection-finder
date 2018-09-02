package ch.ynoir.intersect.writer;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import ch.ynoir.intersect.model.Orthotope;
import ch.ynoir.intersect.model.Rectangle;

public interface Writer
{

	/**
	 * @param rectangles input as {@link Rectangle} list
	 * @param orthotopes input as {@link Orthotope} set
	 * @param intersections intersections {@link Orthotope} set
	 * @throws IOException
	 */
	public void write(List<Rectangle> rectangles, Set<Orthotope> orthotopes, Set<Orthotope> intersections) throws IOException;

}
