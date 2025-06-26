package src.main.java.org.selfCode.models;

import java.util.List;

public class CombinedResult {

    private boolean isSuccess;
    private boolean isBoardFilled;
    private List<Integer> successIndexList;

    public CombinedResult(boolean isSuccess, boolean isCompleted, List<Integer> successIndexList) {
        this.isSuccess = isSuccess;
        this.isBoardFilled = isCompleted;
        this.successIndexList = successIndexList;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {


        isSuccess = success;
    }

    public boolean isBoardFilled() {
        return isBoardFilled;
    }

    public void setBoardFilled(boolean boardFilled) {
        isBoardFilled = boardFilled;
    }

    public List<Integer> getSuccessIndexList() {
        return successIndexList;
    }

    public void setSuccessIndexList(List<Integer> successIndexList) {
        this.successIndexList = successIndexList;
    }
}
