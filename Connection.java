/**
 * Created by mkutilek on 5/13/2016.
 */
public class Connection {
    Member m1 = null;
    Member m2 = null;
    Member m3 = null;
    public Connection(Member m1, Member m2, Member m3){
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    }
    public Connection(Member m1, Member m2){
        this.m1 = m1;
        this.m2 = m2;
    }
}
