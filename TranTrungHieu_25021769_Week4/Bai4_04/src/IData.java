package Bai4_04.src;
interface IData {
void show(); // Mặc định là public abstract
}

class DataManager implements IData {
// Cố tình KHÔNG ghi public
//2.Sẽ có lỗi Cannot reduce the visibility of the inherited method from IData
//3.Sửa thành: public void show() {}
//Do method void có mức truy cập ở interface mặc định là public,
//nếu ta Override lại ở class thì sẽ không được thu hẹp mức truy cập
//như trong ví dụ là thu hẹp từ public về package
    public void show() { //Đã sửa
        System.out.println("Show Data");
    }
}
