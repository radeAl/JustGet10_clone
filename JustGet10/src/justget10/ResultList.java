/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package justget10;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import javax.naming.ldap.SortKey;

/**
 *
 * @author rade
 */
public class ResultList  {
    private List<Result> list;
    
    public ResultList(){
        list = new ArrayList<>();
        //Sada citam iz datoteke podatke i upisujem u listu
        
        Path path = Paths.get("results.txt");

        try (Scanner sc = new Scanner(path, StandardCharsets.UTF_8.name())) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String tmp[] = line.split(",");
                String name = tmp[0].trim();
                int r = Integer.parseInt(tmp[1].trim());
                list.add(new Result(r, name));
               
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public void addResult(int r, String name){
        list.add(new Result(r, name));
        
    
    }

    public void sortList(){
        list.sort(new Comparator<Result>(){
            public int compare(Result o1, Result o2) {
                return -Integer.compare(o1.getResult(), o2.getResult());
            }
            
        
        });
    }
    
    public String getResultList(){
        StringBuffer sb = new StringBuffer();
        int i = 1;
        for (Result result : list) {
            sb.append("" + i + ". " + result + "\n");
            i++;
        }
        
        return sb.toString();
    
    }
   
    public void save(){
        Path path = Paths.get("results.txt");
        sortList();
        try (BufferedWriter out = Files.newBufferedWriter(path)) {
            
            for (Result result : list) {
                out.append(result.getName() + ", " + result.getResult() + "\n");
            }
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
    }
    
    
    
    
}
