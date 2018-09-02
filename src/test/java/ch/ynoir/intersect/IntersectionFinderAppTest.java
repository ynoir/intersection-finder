package ch.ynoir.intersect;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class IntersectionFinderAppTest
{

	private final String INPUT_FILE_PATH = "src/test/resources/input_normal.json";
	private final String EXPECTED_OUTPUT_FILE_PATH = "src/test/resources/output.txt";

	@Test
	public void shouldProduceExpectedOutput() throws IOException
	{
		// given
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (PrintStream ps = new PrintStream(baos, true, "UTF-8")) {
			System.setOut(ps);
			// when
			IntersectionFinderApp app = new IntersectionFinderApp();
			app.run(INPUT_FILE_PATH, Optional.empty());
		}
		String output = baos.toString("UTF-8");
		// then
		String expectedOutput = new String(Files.readAllBytes(Paths.get(EXPECTED_OUTPUT_FILE_PATH)));
		assertThat(output, is(expectedOutput));
	}

}
