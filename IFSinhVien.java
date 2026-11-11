package voanhKhoi25751321;

import java.util.List;

public interface IFSinhVien {
	boolean them(Student sv);
    boolean sua(String maSV, Student svMoi);
    boolean xoa(String maSV);
    boolean xoaTatCa();

    List<Student> layTatCa();
    Student timTheoMa(String maSV);
    List<Student> timTheoTen(String tuKhoa);
    List<Student> timTheoLop(String lop);
    List<Student> timTheoLoai(String loai);
    List<Student> timTheoMon(MonHoc monHoc);

    List<Student> sapXepTheoTen();
    List<Student> sapXepTheoThanhTienGiamDan();
}
