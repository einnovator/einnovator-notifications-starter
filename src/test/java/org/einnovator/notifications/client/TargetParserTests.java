package org.einnovator.notifications.client;

import java.util.List;

import org.einnovator.notifications.client.model.Target;
import org.einnovator.notifications.client.model.TargetParser;
import org.einnovator.sso.client.model.Role;
import org.junit.Test;

public class TargetParserTests {

	@Test
	public void test() {
		List<Target> targets = TargetParser.makeTargets(Role.ROLE_ADMIN, Role.ROLE_CLIENT);
		for (Target target: targets) {
			System.out.println(target);
		}
	}

}
