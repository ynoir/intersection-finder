package ch.ynoir.intersect.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<T> {

	private final T item1;
	private final T item2;

	public static <T> List<Pair<T>> getPairs(Collection<T> items)
	{
		List<T> list = new ArrayList<>(items);
		List<Pair<T>> pairs = new ArrayList<>();
		for (int i=0; i<list.size()-1; i++)
		{
			for (int j=i+1; j<list.size(); j++)
			{
				pairs.add(new Pair<T>(list.get(i), list.get(j)));
			}
		}
		return pairs;
	}
}
