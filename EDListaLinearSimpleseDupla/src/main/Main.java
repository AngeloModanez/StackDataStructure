/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import edpilha.EDStack;
import edpilha.Node;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("\nInforme a expressão: ");
        String sExp = in.next();
        in.close();

        new BalancedExpression();
        boolean result = BalancedExpression.verify(sExp);

        if (result) {
            System.out.println("\nExpressao correta");
        } else {
            System.out.println("\nExpressão incorreta");
        }
    }
}

class BalancedExpression {

    public static boolean verify(String expressao) {
        EDStack pilha = new EDStack();

        for (char c : expressao.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                pilha.push(new Node(c + "", (int) c));
            } else if (c == ')' && (pilha.empty() || (char) ((int) pilha.pop().getValue()) != '(')) {
                return false;
            } else if (c == '}' && (pilha.empty() || (char) ((int) pilha.pop().getValue()) != '{')) {
                return false;
            } else if (c == ']' && (pilha.empty() || (char) ((int) pilha.pop().getValue()) != '[')) {
                return false;
            }
        }

        return pilha.empty();
    }
}