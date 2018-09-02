package ch.ynoir.intersect.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * N-dimensional orthotope defined by a {@link Edge} list.
 * Each additional {@link Edge} is an additional dimension.
 */
@Data
@RequiredArgsConstructor
public class Orthotope
{
	private final List<Edge> edges;
	private Set<String> labels = new HashSet<>();
}
