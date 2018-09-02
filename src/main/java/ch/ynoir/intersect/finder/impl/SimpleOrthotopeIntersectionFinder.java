package ch.ynoir.intersect.finder.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ch.ynoir.intersect.finder.OrthotopeIntersectionFinder;
import ch.ynoir.intersect.finder.EdgeIntersectionFinder;
import ch.ynoir.intersect.model.Edge;
import ch.ynoir.intersect.model.Orthotope;
import ch.ynoir.intersect.model.Pair;

/**
 * Finds intersections of {@link Orthotope}s as such:
 * 1) build pairs of orthotopes
 * 2) find intersection by checking if all {@link Edge}s intersect
 * 3) restart at 1) with the intersections; collect all intersections
 * 4) return result when no more intersections are found
 */
public class SimpleOrthotopeIntersectionFinder implements OrthotopeIntersectionFinder
{

	private EdgeIntersectionFinder edgeIntersectionFinder = new SimpleEdgeIntersectionFinder();

	@Override
	public Set<Orthotope> find(Set<Orthotope> orthotopes)
	{
		Set<Orthotope> intersections = findLevelOne(orthotopes);
		if (intersections.isEmpty() == false)
		{
			intersections.addAll(find(intersections));
		}
		return intersections;
	}

	protected Set<Orthotope> findLevelOne(Set<Orthotope> orthotopes)
	{
		List<Pair<Orthotope>> pairs = Pair.getPairs(orthotopes);
		return pairs.stream()
			.map(this::find)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.collect(Collectors.toSet());
	}

	protected Optional<Orthotope> find(Pair<Orthotope> orthotopePair)
	{
		List<Edge> edges1 = orthotopePair.getItem1().getEdges();
		List<Edge> edges2 = orthotopePair.getItem2().getEdges();
		if (edges1.size() != edges2.size())
		{
			throw new IllegalArgumentException("Orthotope are not of the same dimension.");
		}
		List<Edge> intersectionEdges = IntStream.range(0, edges1.size())
			.mapToObj((idx) -> { return new Pair<Edge>(edges1.get(idx), edges2.get(idx));} )
			.map(edgeIntersectionFinder::find)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.collect(Collectors.toList());
		if (intersectionEdges.size() == edges1.size())
		{
			Orthotope intersection = new Orthotope(intersectionEdges);
			intersection.getLabels().addAll(orthotopePair.getItem1().getLabels());
			intersection.getLabels().addAll(orthotopePair.getItem2().getLabels());
			return Optional.of(intersection);
		}
		return Optional.empty();
	}

}
