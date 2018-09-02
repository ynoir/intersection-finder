package ch.ynoir.intersect.model;

import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Rectangle
{
	private Integer x;
	private Integer y;
	private Integer w;
	private Integer h;

	public Orthotope asOrthotope()
	{
		return new Orthotope(Arrays.asList(
				new Edge(getX(), getX() + getW()), 
				new Edge(getY(), getY() + getH())));
	}

	public static Rectangle fromOrthotope(Orthotope orthotope)
	{
		List<Edge> edges = orthotope.getEdges();
		if (edges.size() != 2)
		{
			throw new IllegalArgumentException("Must be of dimention 2.");
		}
		Rectangle rectangle = new Rectangle();
		rectangle.setX(edges.get(0).getPoint1());
		rectangle.setY(edges.get(1).getPoint1());
		rectangle.setW(edges.get(0).getPoint2() - rectangle.getX());
		rectangle.setH(edges.get(1).getPoint2() - rectangle.getY());
		return rectangle;
	}

}
