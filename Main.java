import java.util.Scanner;

public class Main {
    public static String[] todos = new String[10];
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Before delete");
        addToDoList("Menulis");
        addToDoList("Menggambar");
        addToDoList("Membaca");
        showToDoList();
        removeToDoList(2);
        System.out.println("After delete");
        showMainMenu();
    }

    public static void showToDoList() {
        System.out.println("To Do List");
        for (int i = 0; i < todos.length; i++) {
            String todo = todos[i];
            if (todos[i] != null) {
                System.out.println((i + 1) + "." + todo);
            }
        }
    }

    public static void addToDoList(String todo) {
        resizeArrayIfFull();

        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                todos[i] = todo;
                break;
            }
        }
    }

    private static void resizeArrayIfFull() {
        boolean isFull = isArrayFull();

        if (isFull) {
            resizeArrayToTwoTimesBigger();
        }
    }

    private static void resizeArrayToTwoTimesBigger() {
        String[] temp = todos;
        todos = new String[todos.length * 2];
        for (int i = 0; i < temp.length; i++) {
            todos[i] = temp[i];
        }
    }

    private static boolean isArrayFull() {
        boolean isFull = true;
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public static boolean removeToDoList(Integer number) {
        if (isSelectedTodoNotValid(number)) return false;

        for (int i = number - 1; i < todos.length; i++) {
            if (i == (todos.length - 1)) {
                todos[i] = null;
            } else {
                todos[i] = todos[i + 1];
            }
        }
        return true;
    }

    private static boolean isSelectedTodoNotValid(Integer number) {
        if (number <= 0) {
            return true;
        }

        if (number - 1 > todos.length - 1) {
            return true;
        }

        if (todos[number - 1] == null) {
            return true;
        }
        return false;
    }

    public static boolean editToDoList(Integer number, String newTodo){
        if (isSelectedTodoNotValid(number)){
            return false;
        }
        todos[number - 1] = newTodo;
        return true;
    }

    public static void showMainMenu(){
        boolean isRunning = true;
        while (isRunning){
            showToDoList();
            System.out.println("Menu");
            System.out.println("1. Tambah");
            System.out.println("2. Hapus");
            System.out.println("3. Edit");
            System.out.println("4. Keluar");
            String selectedMenu= input("Pilih");
            switch (selectedMenu){
                case "1":
                    showMenuAddTodoList();
                    System.out.println("Menu add to do list");
                    //showmenutodolist();
                    break;
                case "2":
                    showMenuRemoveTodoList();
                    System.out.println("Menu remove to do list");
                    //showmenuremovetodolist();
                    break;
                case "3":
                    showMenuEditTodoList();
                    System.out.println("Menu edit to do list");
                    break;
                case "4":
                    isRunning = false;
                    //exit
                    break;
                default:
                    System.out.println("Pilih yang betol lek!");
            }
        }
    }

    public static String input(String info){
        System.out.println(info + ": ");
        String data = scanner.nextLine();
        return data;
    }

    public static void showMenuAddTodoList(){
        System.out.println("Menambah TODO list");
        String todo = input("Todo (x jika batal)");
        if (todo.equals("x")){

        }else{
            addToDoList(todo);
        }
    }
    public static void showMenuRemoveTodoList(){
        System.out.println("Menghapus TODO LIST");
        String todoYangDipilih = input("Nomor todo yang di hapus (x jika batal)");
        if (todoYangDipilih.equals("x")){

        }else{
            boolean success = removeToDoList(Integer.valueOf(todoYangDipilih));
            if (!success){
                System.out.println("Gagal menghapus todo list: "+ todoYangDipilih);
            }
        }
    }

    public static void showMenuEditTodoList(){
        System.out.println("MENGEDIT TODO LIST");
        String selectedTodo = input("Masukkan nomor todo ( x jika batal)");
        if (selectedTodo.equals("x")){
            return;
        }
        String newTodo = input("Masukkan todo yang baru(x jika batal");
        if (newTodo.equals("x")){
            return;
        }
        boolean isEditTodoSuccess = editToDoList(Integer.valueOf(selectedTodo),newTodo);
        if (isEditTodoSuccess){
            System.out.println("Berhasil Menegedit todo");
        }else{
            System.out.println("Gagal mengedit todo");
        }
    }
}
