package ch.ynoir.intersect.reader;

import java.io.IOException;
import java.util.List;

import ch.ynoir.intersect.model.Rectangle;

public interface Reader
{
	public List<Rectangle> read(String path) throws IOException;
}
