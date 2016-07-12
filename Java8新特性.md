[Link](http://www.jianshu.com/p/5fc2b3362702)
#lambda表达式
```
//1.用lambda表达式实现Runnable
        /*(params) -> expression
          (params) -> statement
          (params) -> { statements }
                */

        new Thread(new Runnable() {
            @Override
            public void run() {
                printLog("Before Java8, too much code for too little to do");
            }
        }).start();


        new Thread(() -> {
            printLog("In Java8, Lambda expression rocks !!");
        }).start();
```

```
 2.用lambda表达式进行事件处理

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        testButton.setOnClickListener((v) -> {

        });
```

```
 //3.使用lambda表达式对列表进行迭代

        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        //Java8之前
        for (String item : features) {
            printLog(item);
        }
        //Java8之后 API=24
        features.forEach(n -> {
            printLog(n);
        });
        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示
        features.forEach(System.out::print);
```

```
        //4.使用lambda表达式和函数式接口Predicate
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        System.out.println("Languages which starts with J :");

        Predicate<String> startWithJ = (s) -> s.startsWith("J");
        filter(languages, startWithJ);

        System.out.println("Languages which ends with a ");

        Predicate<String> endWidthA = (s) -> s.endsWith("a");
        filter(languages, endWidthA);

        System.out.println("Print all languages :");

        Predicate allLanguages = (s) -> true;

        filter(languages, allLanguages);

        System.out.println("Print no language : ");

        Predicate noLanguages = (s) -> false;

        filter(languages, noLanguages);

        System.out.println("Print language whose length greater than 4:");

        Predicate<String> length4 = (s) -> s.length() > 4;

        filter(languages, length4);

```

```
5.如何在lambda表达式中加入Predicate

        //甚至可以用and()、or()和xor()逻辑函数来合并Predicate，
        // 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入

        System.out.println("nName, which starts with 'J' and four letter long is");
        languages.stream().filter(startWithJ.and(length4))
                .forEach(n -> printLog(n.toString()));
```

```
 //6.使用lambda表达式Map和Reduce
        // 不使用lambda表达式为每个订单加上12%的税
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            System.out.println(price);
        }

        //使用lambda表达式
        printLog("使用lambda表达式Map和Reduce");
        costBeforeTax.stream().map(cost -> cost + .12 * cost).forEach(n -> printLog(n + ""));


        //6.2使用lambda表达式Map和Reduce示例
        /*
        * 在上个例子中，可以看到map将集合类（例如列表）元素进行转换的。
        * 还有一个 reduce() 函数可以将所有值合并成一个。Map和Reduce操作是函数式编程的核心操作，因为其功能，
        * reduce 又被称为折叠操作。另外，reduce 并不是一个新的操作，你有可能已经在使用它。
        * SQL中类似 sum()、avg() 或者 count() 的聚集函数，实际上就是 reduce 操作，
        * 因为它们接收多个值并返回一个值。流API定义的 reduceh() 函数可以接受lambda表达式，
        * 并对所有值进行合并。IntStream这样的类有类似 average()、count()、sum() 的内建方法来做 reduce 操作，
        * 也有mapToLong()、mapToDouble() 方法来做转换。这并不会限制你，你可以用内建方法，也可以自己定义。
        * 在这个Java 8的Map Reduce示例里，我们首先对所有价格应用 12% 的VAT，然后用 reduce() 方法计算总和。
        *
        * */

        // 为每个订单加上12%的税
        // 老方法：
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            total = total + price;
        }
        System.out.println("Total : " + total);
        // 新方法：

        double result = costBeforeTax.stream().map(cost -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
        printLog("New Mothod Total:" + result);
```

```
//7.通过过滤创建一个String列表
        Predicate<String> filterCondition1 = x -> x.length() > 4;
        List<String> strList = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        List<String> filtered = strList.stream().filter(filterCondition1).collect(Collectors.toList());

        System.out.printf("Original List : %s, filtered list : %s ", strList, filtered);

        System.out.println();
```

```
//8.对列表中的每个元素应用函数
        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(","));
        printLog(G7Countries);
```

```
//9.复制不同的值,创建一个子列表
        //如何利用流的 distinct() 方法来对集合进行去重。
        // 用所有不同的数字创建一个正方形列表
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map(x -> x * x).distinct().collect(Collectors.toList());
        System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
        System.out.println();
```

```
//10.计算集合元素的最大值、最小值、总和以及平均值

        /*
        * IntStream、LongStream 和 DoubleStream 等流的类中，有个非常有用的方法叫做 summaryStatistics() 。
        * 可以返回 IntSummaryStatistics、LongSummaryStatistics 或者 DoubleSummaryStatistic s，
        * 描述流中元素的各种摘要数据。在本例中，我们用这个方法来计算列表的最大值和最小值。
        * 它也有 getSum() 和 getAverage() 方法来获得列表的所有元素的总和及平均值。
        * */

        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
```
#函数式接口
#流API
#默认方法
#新的Date
#Time API
