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
        String exp = in.next();
        in.close();

        if (BalancedExpression.verify(exp)) {
            System.out.println("\nExpressão Pos-fixa: " + (new infixToPosfix()).convertToPosfix(exp));
        } else {
            System.out.println("\nExpressão incorreta");
        }
    }
}

class BalancedExpression {
    public static boolean verify(String expression) {
        EDStack stack = new EDStack();

        for (char c : expression.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(new Node(c + "", (int) c));
            } else if (c == ')' && (stack.empty() || (char) ((int) stack.pop().getValue()) != '(')) {
                return false;
            } else if (c == '}' && (stack.empty() || (char) ((int) stack.pop().getValue()) != '{')) {
                return false;
            } else if (c == ']' && (stack.empty() || (char) ((int) stack.pop().getValue()) != '[')) {
                return false;
            }
        }

        return stack.empty();
    }
}

class infixToPosfix {
    public int precedence(char character) {
        switch (character) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    public String convertToPosfix(String expression) {
        EDStack operation = new EDStack();
        EDStack result = new EDStack();

        for (char c : expression.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                result.push(new Node(c + "", (int) c));
            } else if (c == '(') {
                operation.push(new Node(c + "", (int) c));
            } else if (c == ')') {
                while (!operation.empty() && (char) ((int) operation.peek().getValue()) != '(') {
                    result.push(new Node(c + "", operation.pop().getValue()));
                }
                operation.pop();
            } else {
                while (!operation.empty() && precedence(c) <= precedence((char) ((int) operation.peek().getValue()))) {
                    result.push(new Node(c + "", operation.pop().getValue()));
                }
                operation.push(new Node(c + "", (int) c));
            }
        }

        while (!operation.empty()) {
            Node n = operation.pop();
            result.push(n);
        }

        return result.toString();
    }
}