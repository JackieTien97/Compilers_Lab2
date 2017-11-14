package action;

import exception.NoItemException;

public interface Action {

    void doAction() throws NoItemException;
}
