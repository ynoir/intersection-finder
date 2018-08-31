package ch.ynoir.intersect.reader.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;

import ch.ynoir.intersect.model.Rectangle;
import ch.ynoir.intersect.reader.Reader;

public class JsonReader implements Reader
{

	@Data
	@NoArgsConstructor
	private static class RootObject
	{
		private List<Rectangle> rects;
	}

	@Override
	public List<Rectangle> read(String path) throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		RootObject rootObject = mapper.readValue(new File(path), RootObject.class);

		List<Rectangle> rects = rootObject.getRects();
		validate(rects);
		return rects;
	}

	private void validate(List<Rectangle> rects) throws IOException
	{
		for (Rectangle rect : rects) {
			if (rect.getX() == null || rect.getY() == null 
					|| rect.getW() == null || rect.getH() == null)
			{
				throw new IOException("Missing value.");
			}
		}
	}
}
