package hello.core.singleton;

public class StatefulService {
    //    private int price;  // 상태를 유지하는 필드
/*
    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 문제 지점
    }
*/
/*

    public int getPrice() {
        return price;
    }
*/
    //필드 대신에 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용할 것.
    public int order(String name, int price) {

        System.out.println("name = " + name + " price = " + price);
        return price; // 문제 지점
    }
}
