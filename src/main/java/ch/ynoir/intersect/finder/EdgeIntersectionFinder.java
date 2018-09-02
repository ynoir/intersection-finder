package ch.ynoir.intersect.finder;

import java.util.Optional;

import ch.ynoir.intersect.model.Edge;
import ch.ynoir.intersect.model.Pair;

/**
 * Finds intersections on 1d edge pairs.
 */
public interface EdgeIntersectionFinder
{

	public Optional<Edge> find(Pair<Edge> edgePair);

}
