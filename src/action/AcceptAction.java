package action;

import exception.NoItemException;

public class AcceptAction implements Action {

    @Override
    public void doAction() throws NoItemException {
        System.out.println("S'->S");
        System.out.println("Success!!");
        System.exit(0);
    }
}
