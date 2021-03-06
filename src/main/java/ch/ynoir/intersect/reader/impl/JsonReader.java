package ch.ynoir.intersect.reader.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;

import ch.ynoir.intersect.model.Rectangle;
import ch.ynoir.intersect.model.RectangleList;
import ch.ynoir.intersect.reader.Reader;

public class JsonReader implements Reader
{

	@Override
	public List<Rectangle> read(String path) throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		RectangleList rootObject = mapper.readValue(new File(path), RectangleList.class);

		List<Rectangle> rects = rootObject.getRects();
		validate(rects);
		return rects;
	}

	private void validate(List<Rectangle> rects) throws IOException
	{
		if (rects == null)
		{
			throw new IOException("No rects array found.");			
		}
		for (Rectangle rect : rects) {
			if (rect.getX() == null || rect.getY() == null 
					|| rect.getW() == null || rect.getH() == null)
			{
				throw new IOException("Missing value.");
			}
		}
	}
}
