package projectx.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Mihails Volkovs on 2015.02.02.
 */
public class PermutationGenerator {

    private int listValuesSize;

    private int resultListSize;

    private String[] currentList;

    private Map<String, String> nextValue = Maps.newHashMap();

    private int permutationsCount = 0;

    private List<String[]> permutations = Lists.newArrayList();

    public PermutationGenerator(String[] dataSet, int resultListSize) {
        this.listValuesSize = dataSet.length;
        this.resultListSize = resultListSize;
        init(dataSet);
    }

    public PermutationGenerator(long dataSet) {
        this(dataSet, ("" + dataSet).length());
    }

    public PermutationGenerator(long dataSet, int resultListSize) {
        this.listValuesSize = ("" + dataSet).length();
        this.resultListSize = resultListSize;
        init(dataSet);
    }

    private void init(String[] dataSet) {

        // rolling values
        String previous = dataSet[0];
        for (int valuesIndex = 1; valuesIndex < dataSet.length; valuesIndex++) {
            nextValue.put(previous, dataSet[valuesIndex]);
            previous = dataSet[valuesIndex];
        }
        nextValue.put(dataSet[dataSet.length - 1], dataSet[0]);

        // init
        currentList = new String[resultListSize];
        for (int i = 0; i < resultListSize; i++) {
            currentList[i] = dataSet[0];
        }
    }

    private void init(long dataSetLong) {
        String[] dataSet = ("" + dataSetLong).split("");

        // rolling values
        String previous = dataSet[0];
        for (int valuesIndex = 1; valuesIndex < dataSet.length; valuesIndex++) {
            nextValue.put(previous, dataSet[valuesIndex]);
            previous = dataSet[valuesIndex];
        }
        nextValue.put(dataSet[dataSet.length - 1], dataSet[0]);

        // init
        currentList = new String[resultListSize];
        for (int i = 0; i < resultListSize; i++) {
            currentList[i] = dataSet[0];
        }
    }

    public void generate() {
        generate(0, resultListSize - 1);
    }

    private void generate(int from, int to) {
        if (from > to) {
            return;
        }

        for (int i = 0; i < listValuesSize; i++) {
            if (from == to) {
                processList(currentList);
            } else {
                generate(from + 1, to);
            }
            roll(from);
        }
    }

    private void roll(int position) {
        currentList[position] = nextValue.get(currentList[position]);
    }

    private void processList(String[] list) {
        permutations.add(Arrays.copyOf(list, list.length));
        permutationsCount++;
    }

    public int getPermutationsCount() {
        return permutationsCount;
    }

    public void setPermutationsCount(int permutationsCount) {
        this.permutationsCount = permutationsCount;
    }

    public List<String[]> getPermutations() {
        return permutations;
    }

    public static void main(String... args) {
        PermutationGenerator generator = new PermutationGenerator(12345, 3);
        generator.generate();
        for (String[] permutation : generator.permutations) {
            System.out.println(ArrayUtils.toString(permutation));
        }
        System.out.println(generator.permutationsCount);
        System.out.println(generator.permutations.size());
    }

}