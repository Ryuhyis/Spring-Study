package hello.core.singleton;

public class SingletonService {

    // static 영역에 instance를 미리 하나 생성해서 올려둔다
    private  static final  SingletonService instance = new SingletonService();

    // 이 객체 인스턴스가 필요하면 getInstance() 통해서만 조회, 항상 같은 인스턴스 반환
    public static SingletonService getInstance() {
        return instance;
    }
    // 딱 1개의 인스턴스만 존재해야하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다
    private  SingletonService() {
    }
    
    public void logic() {
        System.out.println("instance = " + instance);
    }
}
