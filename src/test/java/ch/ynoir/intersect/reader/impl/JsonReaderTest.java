package ch.ynoir.intersect.reader.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import ch.ynoir.intersect.model.Rectangle;
import ch.ynoir.intersect.reader.Reader;

class JsonReaderTest
{

	private static String RESOURCE_FOLDER = "src/test/resources/";

	@Test
	void shouldReadEmptyJsonInput() throws IOException
	{
		// given
		Reader reader = new JsonReader();
		// when
		List<Rectangle> rectangles = reader.read(RESOURCE_FOLDER + "input_empty.json");
		// then
		assertThat(rectangles, hasSize(0));
	}

	@Test
	void shouldReadJsonInput() throws IOException
	{
		// given
		Reader reader = new JsonReader();
		// when
		List<Rectangle> rectangles = reader.read(RESOURCE_FOLDER + "input_normal.json");
		// then
		assertThat(rectangles, hasSize(4));
		assertThat(rectangles.get(0), hasToString("Rectangle(x=100, y=100, w=250, h=80)"));
		assertThat(rectangles.get(1), hasToString("Rectangle(x=120, y=200, w=250, h=150)"));
		assertThat(rectangles.get(2), hasToString("Rectangle(x=140, y=160, w=250, h=100)"));
		assertThat(rectangles.get(3), hasToString("Rectangle(x=160, y=140, w=350, h=190)"));
	}

	@Test
	void shouldThrowErrorOnInvalidInput_emptyFile() throws IOException
	{
		assertThrows(IOException.class, () ->
		{
			new JsonReader().read(RESOURCE_FOLDER + "input_invalid.json");
		});
	}


	@Test
	void shouldThrowErrorOnInvalidInput_missingFields()
	{
		assertThrows(IOException.class, () ->
		{
			List<Rectangle> read = new JsonReader().read(RESOURCE_FOLDER + "input_invalid2.json");
			for (Rectangle rect : read) {
				System.out.println(rect);
			}
		});
	}

	@Test
	void shouldReadJsonInput_additionalValues() throws IOException
	{
		// given
		Reader reader = new JsonReader();
		// when
		List<Rectangle> rectangles = reader.read(RESOURCE_FOLDER + "input_additional_values.json");
		// then
		assertThat(rectangles, hasSize(1));
		assertThat(rectangles.get(0), hasToString("Rectangle(x=100, y=100, w=250, h=80)"));
	}

}
