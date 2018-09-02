package ch.ynoir.intersect.finder.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ch.ynoir.intersect.model.Pair;

class PairTest
{

	@Test
	void shouldGetPairs()
	{
		// given
		List<String> items = Arrays.asList("A", "B", "C");
		// when
		List<Pair<String>> pairs = Pair.getPairs(items);
		// then
		assertThat(pairs, hasSize(3));
		assertThat(pairs, hasItem(new Pair<String>("A", "B")));
		assertThat(pairs, hasItem(new Pair<String>("A", "C")));
		assertThat(pairs, hasItem(new Pair<String>("B", "C")));
	}

}
