package ch.ynoir.intersect.reader.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

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
		RootObject rootObject = mapper.readValue(new File(path), RootObject.class);
		return rootObject.getRects();
	}

}
