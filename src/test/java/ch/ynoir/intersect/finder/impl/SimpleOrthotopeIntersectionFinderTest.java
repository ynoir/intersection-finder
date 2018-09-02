package ch.ynoir.intersect.finder.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import ch.ynoir.intersect.model.Edge;
import ch.ynoir.intersect.model.Orthotope;
import ch.ynoir.intersect.model.Pair;

class SimpleOrthotopeIntersectionFinderTest
{

	private SimpleOrthotopeIntersectionFinder finder = new SimpleOrthotopeIntersectionFinder();

	@SuppressWarnings("unused")
	private static Stream<Arguments> getOrthotopes()
	{
		return Stream.of(
				Arguments.of(Arrays.asList(2,3, 0,4, 0,4, 2,3, 2,3, 2,3)),
				Arguments.of(Arrays.asList(0,4, 0,4, 2,3, 2,3, 2,3, 2,3)),
				Arguments.of(Arrays.asList(1,3, 0,3, 0,4, 1,4, 1,3, 1,3)),
				Arguments.of(Arrays.asList(0,4, 0,4, 2,5, 2,5, 2,4, 2,4)),
				Arguments.of(Arrays.asList(0,2, 0,2, 3,5, 3,5)),
				Arguments.of(Arrays.asList(0,2, 0,2, 3,5, 3,5)),
				Arguments.of(Arrays.asList(0,2, 0,2, 0,2, 2,4)),
				Arguments.of(Arrays.asList(0,2, 0,2, 2,4, 2,4))
				);
	}

	@ParameterizedTest
	@MethodSource("getOrthotopes")
	void shouldFind2dIntersections(List<Integer> input)
	{
		// given
		Orthotope o1 = new Orthotope(Arrays.asList(
				new Edge(input.get(0), input.get(1)), 
				new Edge(input.get(2), input.get(3))));
		Orthotope o2 = new Orthotope(Arrays.asList(
				new Edge(input.get(4), input.get(5)), 
				new Edge(input.get(6), input.get(7))));
		Optional<Orthotope> expectedIntersection = Optional.empty();
		if (input.size() == 12)
		{
			expectedIntersection = Optional.of(new Orthotope(Arrays.asList(
					new Edge(input.get(8), input.get(9)), 
					new Edge(input.get(10), input.get(11)))));
		}
		Pair<Orthotope> orthotopePair = new Pair<Orthotope>(o1, o2);
		// when
		Optional<Orthotope> intersection = finder.find(orthotopePair);
		// then
		assertThat(intersection, is(expectedIntersection));
	}

}
