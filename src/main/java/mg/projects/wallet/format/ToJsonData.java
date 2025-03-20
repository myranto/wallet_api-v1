package mg.projects.wallet.format;

public class ToJsonData<T,Y> {
    T data=null;
    Y error=null;

    public ToJsonData(T data, Y error) {
        this.data = data;
        this.error = error;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Y getError() {
        return error;
    }

    public void setError(Y error) {
        this.error = error;
    }
}
