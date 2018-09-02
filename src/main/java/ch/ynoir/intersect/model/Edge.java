package ch.ynoir.intersect.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * One dimensional edge defined by two absolute points.
 */
@Data
@AllArgsConstructor
public class Edge
{

	private final Integer point1;
	private final Integer point2;

}
