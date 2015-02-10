package lv.volkovs.kata18;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class JDepend {

	private Map<String, String[]> dependencies = Maps.newHashMap();

	public void add(String from, String dependenciesString) {
		String[] directDependencies = dependenciesString.split(" ");
		dependencies.put(from, directDependencies);
	}

	public String dependenciesFor(String from) {
		Set<String> uniqueDependencies = Sets.newHashSet();
		addDependenciesFor(from, uniqueDependencies, from);
		List<String> result = Lists.newArrayList(uniqueDependencies);
		Collections.sort(result);

		StringBuilder sb = new StringBuilder();
		Joiner.on(" ").appendTo(sb, result);
		return sb.toString();
	}

	public void addDependenciesFor(String from, Set<String> uniqueDependencies, String originalFor) {
		String[] directDependencies = dependencies.get(from);
		if (directDependencies == null) {
			return;
		}

		for (String directDependency : directDependencies) {
			if (!originalFor.equals(directDependency)) {
				uniqueDependencies.add(directDependency);
				addDependenciesFor(directDependency, uniqueDependencies, originalFor);
			}
		}
	}

}
