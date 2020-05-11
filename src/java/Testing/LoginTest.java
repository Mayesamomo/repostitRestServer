/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import Rest.UResource;

/**
 *
 * @author micha
 */
public class LoginTest {
     public static void main(String[] args) {
        String content = "{\"username\":\"Jane\",\"password\":\"password\"}";
        UResource ur = new UResource();
        System.out.println(ur.Login(content));
    }
}
