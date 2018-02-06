/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package justget10;

/**
 *
 * @author Bojana
 */
public class Result {
    private int result;
    private String name;
    
    public Result(int result, String name){
        this.result = result;
        this.name = name;
    }

    public int getResult() {
        return result;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return name + " " + result;
    }
    
    
    
}
