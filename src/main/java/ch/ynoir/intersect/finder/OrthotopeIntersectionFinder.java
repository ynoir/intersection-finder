package ch.ynoir.intersect.finder;

import java.util.Set;

import ch.ynoir.intersect.model.Orthotope;

/**
 * Finds intersections of n-dimensional orthotopes.
 */
public interface OrthotopeIntersectionFinder
{

	public Set<Orthotope> find(Set<Orthotope> orthotopes);

}
