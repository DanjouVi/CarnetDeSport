/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

/**
 *
 * @author vivi
 */
public class pseudoExistant extends Exception {

    /**
     * Creates a new instance of <code>speudoExistant</code> without detail
     * message.
     */
    public pseudoExistant() {
    }

    /**
     * Constructs an instance of <code>speudoExistant</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public pseudoExistant(String pseudo) {
        super("Le speudo : '"+ pseudo+"' est déja utilisé.");
    }
}
