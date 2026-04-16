// CSD Mar 2013 Juansa Sendra

public class LimitedTable extends RegularTable { //max 4 in dinning-room
    private int count = 0;
    public LimitedTable(StateManager state) {super(state);}
    
    public synchronized void enter(int id) throws InterruptedException {
        //Si hay 4 o más filósofos, se espera
        while(count >= 4){
            state.wenter(id);
            wait();
        }
        //Si no entra
        state.enter(id);
        count++;
    }
    
    public synchronized void exit(int id)  {
        //El filósofo sale
        state.exit(id);
        count--;
    }
}
