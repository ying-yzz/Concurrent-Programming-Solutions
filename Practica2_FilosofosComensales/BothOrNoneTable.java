// CSD Mar 2013 Juansa Sendra

public class BothOrNoneTable extends RegularTable { //both or none
    public BothOrNoneTable(StateManager state) {super(state);}
    
    public synchronized void takeLR(int id) throws InterruptedException{
        while(!state.rightFree(id) || !state.leftFree(id)){
            //wtakeLR espera a coge ambos tenedores
            state.wtakeLR(id);
            wait();
        }
        //se coge ambos tenedores, derecho e izquierdo
        state.takeLR(id);
    }
}
