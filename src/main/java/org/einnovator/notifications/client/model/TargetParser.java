package org.einnovator.notifications.client.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.util.StringUtils;

public class TargetParser {

	public static final String GROUP_PREFIX = "@";
	public static final String ROLE_PREFIX = ".";

	/**
	 * Parser to make Target from simple expression.
	 * 
	 * @param destination
	 *  username - user with specificed username or uuid
	 *  username@email - user with email
	 *  +1555444555 - user with phone
	 *  \@group - all users in group with specified uuid or root group with unique name
	 * .role - all users with global role
	 * .role@group - all user with role in group
	 * #obj - all users with any permission in object with specified uuid (Reserved for future use)
	 * @return the {@code Target}
	 */
	public static Target makeTarget(String destination) {
		if (!StringUtils.hasText(destination)) {
			return null;
		}
		destination = destination.trim();
		String type = null;
		String name = destination;
		if (destination.startsWith(GROUP_PREFIX)) {
			if (destination.length()<=1) {
				return null;
			}
			type = TargetType.GROUP.name();
			destination = destination.substring(1);
			name = destination;
		} else if (destination.startsWith(ROLE_PREFIX)) {
			if (destination.length()<=1) {
				return null;
			}
			type = TargetType.ROLE.name();
			destination = destination.substring(1);
			name = destination;
		}
		int i = destination.indexOf(GROUP_PREFIX);
		String group = null;
		if (i>0 && i<destination.length()-1) {
			group = destination.substring(i+1);
			name = destination.substring(0, i);
		}
		
		Target target = (Target)new Target()
				.withContextId(group)
				.withType(type)
				.withId(destination)
				.withName(name)
				;
		return target;
	}

	public static List<Target> makeTargets(Object... destinations) {
		List<Target> targets = new ArrayList<>();
		for (Object destination: destinations) {
			if (destination==null) {
				continue;
			}
			if (destination instanceof Target) {
				targets.add((Target) destination);
			} else if (destination.getClass().isArray()) {
				Object[] objs = (Object[])destination;
				for (Object obj: objs) {
					List<Target> targets2 = makeTargets(obj);
					if (targets2!=null) {
						targets.addAll(targets2);
					}					
				}
			} else if (destination instanceof String) {
				String[] a = ((String)destination).split(",");
				for (String destination2: a) {
					Target target = makeTarget(destination2);
					if (target!=null) {
						targets.add(target);
					}
				}				
			} else if (destination instanceof Collection) {
				for (Object obj: (Collection<?>)destination) {
					List<Target> targets2 = makeTargets(obj);
					if (targets2!=null) {
						targets.addAll(targets2);
					}					
				}
			}
		}
		return targets;
	}

	public static List<Target> makeTargets(List<String> destinations) {
		List<Target> targets = new ArrayList<>();
		if (destinations!=null) {
			for (String destination: destinations) {
				List<Target> targets2 = makeTargets(destination);
				if (targets2!=null) {
					targets.addAll(targets2);
				}
			}
		}
		return targets;
	}


	public static List<String> groups(String... groups) {
		List<String> destinations = new ArrayList<>(groups.length);
		for (String group: groups) {
			destinations.add(GROUP_PREFIX + group);
		}
		return destinations;
	}

	public static List<String> roles(String... roles) {
		List<String> destinations = new ArrayList<>(roles.length);
		for (String role: roles) {
			destinations.add(ROLE_PREFIX + role);
		}
		return destinations;
	}

	public static List<String> permissions(String... permissions) {
		List<String> destinations = new ArrayList<>(permissions.length);
		for (String permission: permissions) {
			destinations.add(ROLE_PREFIX + permission);
		}
		return destinations;
	}


	public static List<String> rolesInGroup(String group, String... roles) {
		List<String> destinations = new ArrayList<>(roles.length);
		for (String role: roles) {
			destinations.add(ROLE_PREFIX + role + GROUP_PREFIX + group);
		}
		return destinations;
	}

	public static List<String> rolesInGroups(String[] groups, String... roles) {
		List<String> destinations = new ArrayList<>(roles.length);
		for (String group: groups) {
			for (String role: roles) {
				destinations.add(ROLE_PREFIX + role + GROUP_PREFIX + group);
			}			
		}
		return destinations;
	}

	public static List<String> rolesInGroups(List<String> groups, String... roles) {
		return rolesInGroups(groups.toArray(new String[groups.size()]), roles);
	}

	public static List<String> roleInGroup(String role, String group) {
		return rolesInGroup(group, new String[] {role});
	}
	
}
