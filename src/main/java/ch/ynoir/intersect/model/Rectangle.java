package ch.ynoir.intersect.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Rectangle
{
	private Integer x;
	private Integer y;
	private Integer w;
	private Integer h;
}
