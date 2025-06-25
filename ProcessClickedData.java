import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ProcessClickedData {
    private final HashMap<Integer,String> indexMap;

    public ProcessClickedData(){
        indexMap = new HashMap<>();
    }

    /**
     * Handles a click event at the specified index with the given value.
     * Updates the internal index map, checks for a win or completion,
     * and invokes the provided callback with a Result indicating the outcome.
     *
     * @param index    the index where the click occurred
     * @param value    the value to set at the specified index
     * @param callback the callback to consume the Result of the operation
     */
    public void handleClick(int index, String value, Consumer<Result> callback) {
        indexMap.put(index,value);
        SuccessResult result = isSuccess(index);
        if(result.isSuccess()) {
            callback.accept(new Result(true, false, result.getList()));
        } else if(isCompleted(index)) {
            callback.accept(new Result(false, true, List.of()));
        } else {
            callback.accept(new Result(false , false , List.of()));
        }
    }

    /**
     * Returns the current mapping of indices to their assigned values.
     *
     * @return a HashMap where the key is the index and the value is the assigned String
     */
    public HashMap<Integer, String> getIndexMap() {
        return indexMap;
    }
    // 0, 1 , 2
    // 3, 4 , 5
    // 6, 7 , 8
    private static final List<List<Integer>> WINNING_LINES = List.of(
            List.of(0, 1, 2),
            List.of(3, 4, 5),
            List.of(6, 7, 8),
            List.of(0, 3, 6),
            List.of(1, 4, 7),
            List.of(2, 5, 8),
            List.of(0, 4, 8),
            List.of(2, 4, 6)
    );


    private SuccessResult isSuccess(int index) {
        for (List<Integer> line : WINNING_LINES) {
            if (line.contains(index)) {
                Object a = indexMap.get(line.get(0));
                Object b = indexMap.get(line.get(1));
                Object c = indexMap.get(line.get(2));

                if (a != null && Objects.equals(a, b) && Objects.equals(b, c)) {
                    return new SuccessResult(line, true);
                }
            }
        }
        return new SuccessResult(List.of(), false);
    }

    private boolean isCompleted(int index){
        return indexMap.size() == 9;
    }
}
