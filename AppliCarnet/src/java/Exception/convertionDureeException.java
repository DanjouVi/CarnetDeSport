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
public class convertionDureeException extends Exception {

    /**
     * Creates a new instance of <code>mailExistant</code> without detail
     * message.
     */
    public convertionDureeException() {
    }

    /**
     * Constructs an instance of <code>mailExistant</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public convertionDureeException(String duree) {
        super("la duree n'est pas dans le bon format (HH:MM)<br/> Duree : "+duree);
    }
}
