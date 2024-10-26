
import java.util.Stack;
import java.util.Scanner;

public class Main {
    private StringBuilder currentText;
    final Stack<String> undoStack;
    final Stack<String> redoStack;

    public Main() {
        currentText = new StringBuilder();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }


    public void show() {
        System.out.println("text sekarang : ");
        System.out.println(currentText.toString());
    }


    public void write(String textbaru) {
        undoStack.push(currentText.toString());
        redoStack.clear();
        currentText.append(textbaru);
        System.out.println("tambahkan teks: " + textbaru);
    }


    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentText.toString());
            currentText = new StringBuilder(undoStack.pop());
            System.out.println("batalkan teks.");
        } else {
            System.out.println("tidak ada perlu dibatalkan, karna teks tidak ada.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentText.toString());
            currentText = new StringBuilder(redoStack.pop());
            System.out.println("Pengulangan dilakukkan.");
        } else {
            System.out.println("tidak ada perlu pengulangan dilakukan.");
        }
    }

    public static void main(String[] args) {
        Main editor = new Main();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("SIMULASI EDITOR TEKS");
        System.out.println("Command yang tersedia: write <text>, show, undo, redo, exit");

        while (true) {
            System.out.print("\nEnter command: ");
            command = scanner.nextLine().trim();

            if (command.startsWith("write")) {
                String[] parts = command.split(" ", 2);
                if (parts.length == 2) {
                    editor.write(parts[1]);
                } else {
                    System.out.println("HARAP BERIKAN TEKS YANG INGIN DI TULIS.");
                }
            } else if (command.equals("show")) {
                editor.show();
            } else if (command.equals("undo")) {
                editor.undo();
            } else if (command.equals("redo")) {
                editor.redo();
            } else if (command.equals("exit")) {
                System.out.println("Keluar dari EDITOR TEKS...");
                break;
            } else {
                System.out.println("Command tidak tersedia, Coba lagi.");
            }
        }

        scanner.close();
    }
}

