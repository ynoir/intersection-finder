package ch.ynoir.intersect.finder.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.ynoir.intersect.model.Edge;
import ch.ynoir.intersect.model.Pair;

class SimpleEdgeIntersectionFinderTest
{

	private SimpleEdgeIntersectionFinder finder = new SimpleEdgeIntersectionFinder();

	@SuppressWarnings("unused")
	private static Stream<Arguments> getEdges()
	{
		return Stream.of(
				Arguments.of(new Edge(0, 4), new Edge (2, 5), Optional.of(new Edge (2, 4))),
				Arguments.of(new Edge(0, 4), new Edge (2, 3), Optional.of(new Edge (2, 3))),
				Arguments.of(new Edge(2, 5), new Edge (0, 4), Optional.of(new Edge (2, 4))),
				Arguments.of(new Edge(2, 3), new Edge (0, 4), Optional.of(new Edge (2, 3))),
				Arguments.of(new Edge(0, 4), new Edge (0, 5), Optional.of(new Edge (0, 4))),
				Arguments.of(new Edge(0, 4), new Edge (0, 3), Optional.of(new Edge (0, 3))),
				Arguments.of(new Edge(0, 4), new Edge (0, 4), Optional.of(new Edge (0, 4))),
				Arguments.of(new Edge(1, 4), new Edge (0, 4), Optional.of(new Edge (1, 4))),
				Arguments.of(new Edge(1, 4), new Edge (2, 4), Optional.of(new Edge (2, 4))),
				Arguments.of(new Edge(0, 2), new Edge (2, 4), Optional.empty()),
				Arguments.of(new Edge(0, 2), new Edge (3, 4), Optional.empty()),
				Arguments.of(new Edge(3, 4), new Edge (0, 2), Optional.empty()),
				Arguments.of(new Edge(2, 4), new Edge (0, 2), Optional.empty()),
				Arguments.of(new Edge(0, 0), new Edge (0, 0), Optional.empty()));
	}

	@ParameterizedTest
	@MethodSource("getEdges")
	void shouldFindEdgeIntersections(Edge edge1, Edge edge2, Optional<Edge> expectedIntersection)
	{
		// when
		Optional<Edge> intersection = finder.find(new Pair<Edge>(edge1, edge2));
		// then
		assertThat(intersection, is(expectedIntersection));
	}

}
