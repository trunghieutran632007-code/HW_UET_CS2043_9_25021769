package TranTrungHieu_25021769_Week2.Bai2_03;

public class NumberWrapper {
    int value;
    public NumberWrapper(int value){
        this.value = value;
    }

    public void swap(NumberWrapper a, NumberWrapper b){
        NumberWrapper temp = new NumberWrapper(1);
        temp = a;
        a = b;
        b = temp;
    }
    
}
