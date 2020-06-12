## ArraySet
* Класс `ArraySet` реализует неизменяемое упорядоченное множество.
    * Класс `ArraySet` реализует интерфейс `NavigableSet`.
    * Все операции над множествами производятся с максимально возможной асимптотической эффективностью.
    
* Для того, чтобы протестировать программу:
   * Скачайте
      * тесты
          * [info.kgeorgiy.java.advanced.base.jar](artifacts/info.kgeorgiy.java.advanced.base.jar)
          * [info.kgeorgiy.java.advanced.arrayset.jar](artifacts/info.kgeorgiy.java.advanced.arrayset.jar)
      * и библиотеки к ним:
          * [junit-4.11.jar](lib/junit-4.11.jar)
          * [hamcrest-core-1.3.jar](lib/hamcrest-core-1.3.jar)
          * [jsoup-1.8.1.jar](lib/jsoup-1.8.1.jar)
          * [quickcheck-0.6.jar](lib/quickcheck-0.6.jar)
   * Откомпилируйте программу
   * Протестируйте программу
      * Текущая директория должна:
         * содержать все скачанные `.jar` файлы;
         * содержать скомпилированные классы;
         * __не__ содержать скомпилированные самостоятельно тесты.
      * ```java -cp . -p . -m info.kgeorgiy.java.advanced.arrayset NavigableSet ru.ifmo.rain.shaposhnikov.arrayset.ArraySet```