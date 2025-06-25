import java.util.List;

public class SuccessResult {

    private final List<Integer> list;
    private final boolean success;

    public SuccessResult(List<Integer> list, boolean success) {
        this.list = list;
        this.success = success;
    }

    public List<Integer> getList() {
        return list;
    }

    public boolean isSuccess() {
        return success;
    }
}
