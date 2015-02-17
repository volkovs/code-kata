package projectx.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang.ArrayUtils;

import java.util.Map;

/**
 * Created by Mihails Volkovs on 2015.02.02.
 */
public class PermutationGenerator {

    private int listValuesSize;

    private int resultListSize;

    private String[] currentList;

    private Map<String, String> nextValue = Maps.newHashMap();

    private int permutations = 0;

    public PermutationGenerator(String[] dataSet, int resultListSize) {
        this.listValuesSize = dataSet.length;
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
        permutations++;
        System.out.println(ArrayUtils.toString(list));
    }

    public int getPermutations() {
        return permutations;
    }

    public void setPermutations(int permutations) {
        this.permutations = permutations;
    }

    public static void main(String... args) {
        PermutationGenerator generator = new PermutationGenerator(new String[]{"1", "2", "3", "4", "5"}, 3);
        generator.generate();
        System.out.println(generator.permutations);
    }

}