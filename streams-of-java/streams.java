import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Scratch {
  public static void main(String[] args) {
    //* ------------------------------------------ A.1: creating an empty stream
    //creating an empty stream
    Stream<String> strings = Stream.empty();

    //* --------------------------------------- A.2: from an existing collection
    System.out.println("A.2");
    List<Integer> accounts = new ArrayList<>();
    accounts.add(748);
    accounts.add(495);
    accounts.add(362);

    //We could have also used Arrays.asList(748, 495, 362);
    Stream<Integer> streamOfAccounts = accounts.stream();

    /*
    748
    495
    362
     */

    streamOfAccounts.forEach(account -> System.out.println(account));

    //* ------------------------------------------------------------- B1: map()
    System.out.println("\nB1");

    //employee class
    class Employee {
      private int id;
      private char status; //A (active), R (removed)

      Employee(int id, char status) {
        this.id = id;
        this.status = status;
      }

      int getId() { return this.id; }
      char getStatus() { return this.status; }
    }

    Stream<Integer> employeeIds = Stream.of(748, 952, 397);

    //for each of these employee numbers we need to set the employment status
    Stream<Employee> employeeDetail = employeeIds.map(id -> {
      return new Employee(id, id == 748 ? 'R' : 'A');
    });

    employeeDetail.forEach(employee -> System.out.println("{" + employee.getId() + ':' + employee.getStatus() + "}"));

    /*
    {748:R}
    {952:A}
    {397:A}
     */
    //* ----------------------------------------------------------- B.2: filter
    System.out.println("\nB.2");

    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
    Stream<Integer> filtered = numbers.stream().filter(n -> n > 5);

    filtered.forEach(f -> System.out.println(f));

    /*
    6
    7
    8
    */
    //filtered result -> 6,7,8
    //* ------------------------------------------------------------ B.3 reduce
    System.out.println("\nB.3");

    // IntStream is Stream<Integer>
    IntStream numberRange = IntStream.range(0, 20);

    //.reduce(<accumulator>)
    //Returns OptionalInt. use getAsInt() to reutrn base type int
    int reduced = numberRange.reduce((a, b) -> a + b).getAsInt();

    // reduced: 190
    System.out.println("reduced: " + reduced);

    IntStream numberRangeAgain = IntStream.range(0, 20);
    //.reduce(<identity>, <accumulator>)
    int reducedAccumulator = numberRangeAgain.reduce(10, (a, b) -> a + b);

    //reducedAccumulator: 200
    System.out.println("reducedAccumulator: " + reducedAccumulator);

    //* ---------------------------------------------------------- B.4 distinct
    System.out.println("\nB.4");

    //Primitives and Wrapper Classes
    List<Integer> someNumbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
    Stream<Integer> distinctNumberStream = someNumbers.stream().distinct();

    System.out.println("distinct numbers");
    distinctNumberStream.forEach(number -> System.out.println(number));

    /*
      distinct numbers
      1
      2
      3
      4
      5
     */

    //Objects
    class Book {
      private String title;
      private String isbn;

      Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
      }

      @Override
      public int hashCode () {
        int result = 17;

        result = 31 * result + title.hashCode();
        result = 31 * result + isbn.hashCode();

        return result;
      }

      @Override
      public boolean equals(Object obj) {
        if (!(obj instanceof Book))
          return false;

        Book b = (Book) obj;
        return this.title.equals(b.title) && this.isbn.equals(b.isbn);
      }

      String getTitle(){
        return this.title;
      }
      String getIsbn(){
        return this.isbn;
      }
    }

    List<Book> books = Arrays.asList(
      new Book("The Heart of Man", "12-12-12"),
      new Book("The Giant Cup of Water", "12-12-12"),
      new Book("The Giant Cup of Water", "12-12-12")
    );

    Stream<Book> uniqueBooks = books.stream().distinct();

    System.out.println("Unique Books");
    uniqueBooks.forEach(book -> System.out.println(book.getTitle()));

    /*
      Unique Books
      The Heart of Man
      The Giant Cup of Water
     */

    //* ------------------------------------ C: Converting back to a collection
    System.out.println("\nC");

    Stream<Integer> numberStream = Stream.of(1, 2, 3);
    List<Integer> convertedList = numberStream.collect(Collectors.toList());

    Stream<Integer> numberStreamAgain = Stream.of(1, 2, 3);
    Set<Integer> convertedSet = numberStreamAgain.collect(Collectors.toSet());
  }
}