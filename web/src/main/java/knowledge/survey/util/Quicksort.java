package knowledge.survey.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
 
public class Quicksort {
    public static final Random RND = new Random();
 
    private static <E> void swap(List<E> ls, int i, int j) {
    	Collections.swap(ls, i, j);
    }
 
    private static <E> int partition(List<E> ls, int begin, int end, Comparator<? super E> cmp) {
        int index = begin + RND.nextInt(end - begin + 1);
        E pivot = ls.get(index);
        swap(ls, index, end);        
        for (int i = index = begin; i < end; ++ i) {
            if (cmp.compare(ls.get(i), pivot) <= 0) {
                swap(ls, index++, i);
            }
        }
        swap(ls, index, end);        
        return (index);
    }
 
    private static <E> void qsort(List<E> ls, int begin, int end, Comparator<? super E> cmp) {
        if (end > begin) {
            int index = partition(ls, begin, end, cmp);
            qsort(ls, begin, index - 1, cmp);
            qsort(ls, index + 1,  end,  cmp);
        }
    }
 
    public static <E> void sort(List<E> ls, Comparator<? super E> cmp) {
        qsort(ls, 0, ls.size() - 1, cmp);
    }
}