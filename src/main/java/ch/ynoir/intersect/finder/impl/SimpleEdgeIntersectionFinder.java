package ch.ynoir.intersect.finder.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import ch.ynoir.intersect.finder.EdgeIntersectionFinder;
import ch.ynoir.intersect.model.Edge;
import ch.ynoir.intersect.model.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Finds intersections of 1-dimensional {@link Edge}s by ordering the 
 * edge points and checking if the second edge starts before the first 
 * one ends.
 */
public class SimpleEdgeIntersectionFinder implements EdgeIntersectionFinder
{

	@Data
	@AllArgsConstructor
	private static class EdgePoint
	{
		private Edge edge;
		private Integer point;
	}

	@Override
	public Optional<Edge> find(Pair<Edge> edgePair)
	{
		List<EdgePoint> edgePoints = Arrays.asList(
				new EdgePoint(edgePair.getItem1(), edgePair.getItem1().getPoint1()),
				new EdgePoint(edgePair.getItem1(), edgePair.getItem1().getPoint2()),
				new EdgePoint(edgePair.getItem2(), edgePair.getItem2().getPoint1()),
				new EdgePoint(edgePair.getItem2(), edgePair.getItem2().getPoint2()));
		Collections.sort(edgePoints, (edgePoint1, edgePoint2) -> {
			return edgePoint1.getPoint() - edgePoint2.getPoint();
		});
		if (edgePoints.get(0).getEdge() != edgePoints.get(1).getEdge() 
				&& ! edgePoints.get(1).getPoint().equals(edgePoints.get(2).getPoint()))
		{
			return Optional.of(new Edge(
					edgePoints.get(1).getPoint(), 
					edgePoints.get(2).getPoint()));
		}
		return Optional.empty();
	}

}
