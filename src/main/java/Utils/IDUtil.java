package Utils;

import java.util.UUID;

public class IDUtil {
	public static Long generateID() {
		return UUID.randomUUID().getLeastSignificantBits() * -1;
	}
}
