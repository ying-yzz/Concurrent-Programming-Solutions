/**
 * Write a description of class Terrain1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.concurrent.locks.*;
public class Terrain1 implements Terrain { //unica variable condition para todo el terriotrio
    Viewer v;
    ReentrantLock l;
    Condition ocupado;
    public  Terrain1 (int t, int ants, int movs, String msg) {
        v=new Viewer(t,ants,movs,msg);
        l = new ReentrantLock();
        ocupado = l.newCondition();
    }
    public void     hi      (int a) {
        l.lock();
        try{
            v.hi(a);
        }finally{
           l.unlock(); 
        }
    }
    public void     bye     (int a) {
        l.lock();
        try{
            v.bye(a);
            ocupado.signalAll();
        }finally{
            l.unlock();
        }
   }
    public void     move    (int a) throws InterruptedException {
        l.lock();
        try{
        v.turn(a); Pos dest=v.dest(a); 
        while (v.occupied(dest)) {ocupado.await(); v.retry(a);}
        v.go(a); ocupado.signalAll();
        }finally{
            l.unlock();
        }
        
    }
}