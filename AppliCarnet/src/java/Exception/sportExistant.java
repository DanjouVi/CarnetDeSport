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
public class sportExistant extends Exception {

    /**
     * Creates a new instance of <code>sportExistant</code> without detail
     * message.
     */
    public sportExistant() {
    }

    /**
     * Constructs an instance of <code>sportExistant</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public sportExistant(String nomSport) {
        super("le Sport : "+ nomSport +" existe deja");
    }
}
