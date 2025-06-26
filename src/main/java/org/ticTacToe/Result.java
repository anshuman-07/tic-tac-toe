package src.main.java.org.ticTacToe;

import java.util.List;

public class Result {

    private boolean isSuccess;
    private boolean isCompleted;
    private List<Integer> successIndexList;

    public Result(boolean isSuccess, boolean isCompleted, List<Integer> successIndexList) {
        this.isSuccess = isSuccess;
        this.isCompleted = isCompleted;
        this.successIndexList = successIndexList;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {


        isSuccess = success;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public List<Integer> getSuccessIndexList() {
        return successIndexList;
    }

    public void setSuccessIndexList(List<Integer> successIndexList) {
        this.successIndexList = successIndexList;
    }
}
