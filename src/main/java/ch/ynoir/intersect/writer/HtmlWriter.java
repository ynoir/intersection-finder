package ch.ynoir.intersect.writer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ch.ynoir.intersect.model.Orthotope;
import ch.ynoir.intersect.model.Rectangle;
import lombok.AllArgsConstructor;

/**
 * Writes orthotopes to an html file for visualization.
 */
@AllArgsConstructor
public class HtmlWriter implements Writer
{

	private String path;

	@Override
	public void write(List<Rectangle> rectangles, Set<Orthotope> orthotopes, Set<Orthotope> intersections) throws IOException
	{
		this.write(orthotopes, path);
	}

	protected void write(Collection<Orthotope> orthotopes, String path) throws IOException
	{
		List<String> lines = new ArrayList<>();
		lines.add("<!DOCTYPE html><html><body>");
		lines.add("<canvas id=\"myCanvas\" width=\"600\" height=\"500\" style=\"border:1px solid #d3d3d3;\" />");
		lines.add("<script>");
		lines.add("var c = document.getElementById(\"myCanvas\");");
		lines.add("var ctx = c.getContext(\"2d\");");
		for (Orthotope o : orthotopes)
		{
			lines.add("ctx.globalAlpha = 0.2;");
			Rectangle r = Rectangle.fromOrthotope(o);
			lines.add(String.format("ctx.fillRect(%d, %d, %d, %d);", 
					r.getX(), r.getY(), r.getW(), r.getH()));

			lines.add("ctx.globalAlpha = 1.0;");
			String label = o.getLabels().stream().collect(Collectors.joining(","));
			lines.add(String.format("ctx.fillText(\"%s\", %d, %d);", 
					label, r.getX(), r.getY()));
		}
		lines.add("ctx.stroke();");
		lines.add("</script>");
		lines.add("</body>");
		lines.add("</html>");
		writeToFile(lines, path);
	}

	private void writeToFile(List<String> lines, String path) throws IOException
	{
		Path file = Paths.get(path);
		Files.write(file, lines, Charset.forName("UTF-8"));
	}

}
