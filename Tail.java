/** ***********************************************************************
 *  Compilation:  javac -Xlint Tail.java
 *  Execution:    java Tail
 *  Dependencies: StdIn.java StdOut.java
 *
 *
 * % java Tail testes/*
 * ==> testes/ind.txt<==
 * changed for light and transient causes; and accordingly all experience
 * hath shewn, that mankind are more disposed to suffer, while evils are
 * sufferable, than to right themselves by abolishing the forms to which
 * they are accustomed. But when a long train of abuses and usurpations,
 * pursuing invariably the same Object evinces a design to reduce them
 * under absolute Despotism, it is their right, it is their duty, to
 * throw off such Government, and to provide new Guards for their future
 * security.--Such has been the patient sufferance of these Colonies; and
 * such is now the necessity which constrains them to alter their former
 * Systems of Government.
 *
 * ==> testes/les-miserables.txt<==
 *
 *
 *  Most people start at our Web site which has the main PG search facility:
 *
 *       http://www.gutenberg.org
 *
 *  This Web site includes information about Project Gutenberg-tm,
 *  including how to make donations to the Project Gutenberg Literary
 *  Archive Foundation, how to help produce our new eBooks, and how to
 *  subscribe to our email newsletter to hear about new eBooks.
 *
 *  ==> testes/teste1.txt<==
 *  aaa bbbb aa aaa ccc dd
 *
 *  ==> testes/teste2.txt<==
 *  Como é bom estudar MAC0323!
 *  Como é bom estudar MAC0323!
 *
 *
 *************************************************************************/

// This class provides methods for reading strings and numbers from standard
// input, file input, URLs, and sockets.
// https://www.ime.usp.br/~pf/sedgewick-wayne/stdlib/documentation/index.html
// This class provides methods for printing strings and numbers to standard output.
// https://www.ime.usp.br/~pf/sedgewick-wayne/stdlib/documentation/index.html
// http://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/StdOut.html
import edu.princeton.cs.algs4.*;


/** Está é classe é inspirada no Tarefa 04 do Paulo Felofiloff para
 * a edição de 2014 de MAC0323:
 *
 *     https://www.ime.usp.br/~pf/mac0323-2014/tarefas/T04.html
 *
 * Como diz a tarefa
 *
 *    "Resolva o problema sem desperdiçar memória.
 *     Use uma (ou mais) das ADTs que já estudamos."
 *
 */

public class Tail {
    // atributos de estado
    private String[] v;
    private int num;
    private String name;

    /** Construtores.
     *
     */
    // Construtor que prepara para que o método lines()
    // retorne um objeto iterável com as 10 últimas linhas
    // do arquivo fileName. O valor 10 é o default.
    public Tail(String fileName) {
        // escreva seu método a seguir
        num = 10;
        v = new String[10];
        name = fileName;
    }

    // Construtor que prepara para que o método lines()
    // retorne um objeto iterável com as k últimas linhas
    // do arquivo fileName.
    public Tail(String fileName, int k) {
        // escreva seu método a seguir
        num = k;
        v = new String[k];
        name = fileName;
    }

    /** Returns an iterable object containing the last k lines.
     *  The value of k depends on the constructor.
     */
     // escreva seu método a seguir
    public Iterable<String> lines() {
        Queue<String> Q = new Queue<String>();
        In in = new In(name);
        if (num != 0) {
            while (!in.isEmpty()) {
                String line = in.readLine();
                Q.enqueue(line);
                if (Q.size() > num) {
                    Q.dequeue();
                }
            }
        }
        return Q;
    }

   /***************************************************************************
    *  métodos privados
    **************************************************************************/

    // apenas para o uso do unit test abaixo
    private static void use() {
        String message = "Use: java Tail [-n NUM] [FILE]\n" +
            "   Print the last 10 lines of each FILE to standard output.\n" +
            "   With more than one FILE, precede each with a header giving the file name.\n" +
            "   -n NUM: output the last NUM lines, instead of the last 10";
        StdOut.println(message);
    }

   /** Unit test
    */
    public static void main(String[] args) {
        if (args.length == 0) {
            use();
            return;
        }

        if (args[0].equals("-n")) {
            int k = Integer.parseInt(args[1]);
            for (int i = 2;  i < args.length; i++) {
                StdOut.println("==> " + args[i] + "<==");
                Tail Tail = new Tail(args[i], k);
                for (String line: Tail.lines()) {
                    StdOut.println(line);
                }
                StdOut.println();
            }

        }
        else {
            for (int i = 0;  i < args.length; i++) {
                StdOut.println("==> " + args[i] + "<==");
                Tail Tail = new Tail(args[i]);
                for (String line: Tail.lines()) {
                    StdOut.println(line);
                }
                StdOut.println();
            }
        }
    }
}
