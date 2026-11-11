package voanhKhoi25751321;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

public class ListSinhVien implements IFSinhVien {

    private ArrayList<Student> danhSach;

    public ListSinhVien() {
        this.danhSach = new ArrayList<>();
    }

    public ListSinhVien(ArrayList<Student> ds) {
        this.danhSach = ds;
    }

    public boolean them(Student sv) {
        if (sv == null) return false;                  
        if (timTheoMa(sv.getMaSV()) != null) return false;  
        return danhSach.add(sv);
    }

    public boolean sua(String maSV, Student svMoi) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaSV().equals(maSV)) {
                danhSach.set(i, svMoi);                 
                return true;
            }
        }
        return false;
    }
    public boolean xoa(String maSV) {
        return danhSach.removeIf(sv -> sv.getMaSV().equals(maSV));
    }

    public boolean xoaTatCa() {
        if (danhSach.isEmpty()) return false;  
        danhSach.clear();
        return true;
    }

    public List<Student> layTatCa() {
        return new ArrayList<>(danhSach);   
    }

    public Student timTheoMa(String maSV) {
        for (Student sv : danhSach)
            if (sv.getMaSV().equalsIgnoreCase(maSV)) return sv;
        return null;
    }

    public List<Student> tim(Predicate<Student> dieuKien) {
        List<Student> kq = new ArrayList<>();
        for (Student sv : danhSach)
            if (dieuKien.test(sv)) kq.add(sv);
        return kq;
    }

    public List<Student> timTheoTen(String tuKhoa) {
        List<Student> kq = new ArrayList<>();
        for (Student sv : danhSach)
            if (sv.getHoTen().toLowerCase().contains(tuKhoa.toLowerCase()))
                kq.add(sv);
        return kq;
    }
    public List<Student> timTheoLop(String lop) {
        List<Student> kq = new ArrayList<>();
        for (Student sv : danhSach)
            if (sv.getLop().equalsIgnoreCase(lop)) kq.add(sv);
        return kq;
    }

    public List<Student> timTheoLoai(String loai) {
        List<Student> kq = new ArrayList<>();
        for (Student sv : danhSach)
            if (sv.getLoai().equalsIgnoreCase(loai)) kq.add(sv);
        return kq;
    }

    public List<Student> timTheoMon(MonHoc monHoc) {
        List<Student> kq = new ArrayList<>();
        for (Student sv : danhSach)
            if (sv.getMonHoc() == monHoc) kq.add(sv);   
        return kq;
    }

    public int tongSoLuong() { return danhSach.size(); }

    public double tongThanhTien() {
        double tong = 0;
        for (Student sv : danhSach) tong += sv.thanhTien();
        return tong;
    }

    public void sapXep(Comparator<Student> cmp) {
        danhSach.sort(cmp);
    }

    public void sapXepGiamDan(Comparator<Student> cmp) {
        danhSach.sort(cmp.reversed());    
    }

    public List<Student> sapXepTheoTen() {
        List<Student> kq = new ArrayList<>(danhSach);
        kq.sort(Comparator.comparing(Student::getHoTen));
        return kq;
    }

    public List<Student> sapXepTheoThanhTienGiamDan() {
        List<Student> kq = new ArrayList<>(danhSach);
        kq.sort(Comparator.comparingDouble(Student::thanhTien).reversed());
        return kq;
    }


    public <K> Map<K, List<Student>> nhomTheo(Function<Student, K> layKey) {
        Map<K, List<Student>> kq = new LinkedHashMap<>();
        for (Student sv : danhSach) {
            K key = layKey.apply(sv);
            kq.computeIfAbsent(key, k -> new ArrayList<>()).add(sv);
        }
        return kq;
    }

    public <K> Map<K, Integer> demTheoNhom(Function<Student, K> layKey) {
        Map<K, Integer> kq = new LinkedHashMap<>();
        for (Student sv : danhSach)
            kq.merge(layKey.apply(sv), 1, Integer::sum);
        return kq;
    }

    public <K> Map<K, Double> tongTheoNhom(Function<Student, K> layKey,
                                           ToDoubleFunction<Student> layGiaTri) {
        Map<K, Double> kq = new LinkedHashMap<>();
        for (Student sv : danhSach) {
            K key = layKey.apply(sv);
            double val = layGiaTri.applyAsDouble(sv);
            kq.merge(key, val, Double::sum);       
        }
        return kq;
    }

    public <K> Map<K, Double> tongTheoNhom_Having(Function<Student, K> layKey,
                                                  ToDoubleFunction<Student> layGiaTri,
                                                  double minTong) {
        Map<K, Double> kq = new LinkedHashMap<>();
        for (Map.Entry<K, Double> e : tongTheoNhom(layKey, layGiaTri).entrySet())
            if (e.getValue() >= minTong) kq.put(e.getKey(), e.getValue());
        return kq;
    }

    public <K> Map<K, Integer> demTheoNhom_Having(Function<Student, K> layKey,
                                                  int minDem) {
        Map<K, Integer> kq = new LinkedHashMap<>();
        for (Map.Entry<K, Integer> e : demTheoNhom(layKey).entrySet())
            if (e.getValue() >= minDem) kq.put(e.getKey(), e.getValue());
        return kq;
    }
}